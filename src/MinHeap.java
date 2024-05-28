import java.time.Duration;
import java.time.LocalTime;

/**
 * Creates a new dynamic replaceable minimum heap data structure that can store custom 'Ride' objects ordered by their scheduled timestamps
 */
public class MinHeap {
    /**
     * The array used to store each ride contained within this min heap
     */
    Ride[] rides;
    /**
     * Points to the next index position in the heap (i.e., if there are 10 rides in the heap, index 0 is null, index 1-10 will be rides, and next will point to index 11)
     */
    public int next;
    /**
     * The number of vehicles (rides) that the company has available (i.e., the size of the heap, this is set to 21 to allow for 20 rides, since index 0 is not used
     */
    public final int MAX_CAPACITY = 21;
    /**
     * ~ FOR DEVELOPER USE ONLY! ~ <br><br>
     *
     * True if debugging mode should be enabled, else false
     */
    private final boolean DEBUGGING = true;

    /**
     * Constructs a new minimum heap object and points to the first ride slot by default
     */
    public MinHeap() {
        // creates a new ride array
        rides = new Ride[MAX_CAPACITY];
        // points to the first available slot in the heap
        next = 1;
    }

    /**
     * Adds each ride in the passed ride array to the heap (maintaining heap order)
     * @param rideArray The ride array containing the rides that are being inserted into the heap
     * @return A boolean value that is true if all rides are successfully inserted into the heap, else returns false
     */
    public boolean insert(Ride[] rideArray) {
        // return early if the passed array contains no rides
        if (rideArray == null || isEmpty(rideArray)) {
            System.out.println("Unable to add ride! The passed ride array was either null or empty...");
            return false;
        }

        // loops through the passed array and inserts any rides that it contains
        for (Ride ride : rideArray) {
            if (ride == null)
                continue;

            // if this ride is successfully inserted
            if (!insert(ride))
                return false;
        }

        return true;
    }

    /**
     * Adds the specified ride to the heap (maintaining heap order)
     * @param r The ride object being added to the heap
     * @return A boolean value that is true if the passed ride is successfully inserted into the heap, else return false
     */
    public boolean insert(Ride r) {
        if (r == null)
            return false;

        // if maximum capacity has been reached, the passed ride cannot be added
        if (next >= MAX_CAPACITY) {
            debug("Unable to insert the passed ride! Maximum ride limit has been reached...", "Insert(Ride r)");
            return false;
        }

        // prevents duplicate rides being added to the heap
        if (hasRide(r) || !isUniqueId(r)) {
            debug("Unable to insert ride! Ride was already contained in the array...", "Insert(Ride r)");
            return false;
        }

        debug("Attempting to insert ride... \n " + r, "Insert(Ride r)");

        // if this ride in not optimizable, add it to the array
        if (isOptimizable(r)) {
            upHeap();
            return true;
        }

        // sets the next spare slot in the heap to the passed 'Ride' object
        rides[next] = r;
        // up-heaps to restore heap order
        upHeap();
        // increments the next index pointer
        next++;
        return true;
    }

    /**
     * Removes the passed ride from the heap (maintaining heap order)
     * @param r The ride object being removed from the heap
     * @return A boolean value that is true if the ride was successfully removed from the heap, else returns false
     */
    public boolean remove(Ride r) {
        debug("Attempting to remove ride...", "remove");
        if (r == null)
            return false;

        // return early if the passed ride is not contained in the heap
        if (!hasRide(r)) {
            debug("Unable to remove the passed ride! Ride was not contained in the heap... ", "remove(Ride r)");
            return false;
        }

        // fetches the heap-index of the passed ride
        int indexRide = getIndex(r);
        // fetches the index of the last ride in the heap
        int indexLast = next - 1;

        // if the last element is not the root element
        if (indexRide != indexLast)
            // swap the first and last elements in the heap
            swap(indexRide, indexLast);

        // delete the last element
        rides[indexLast] = null;
        // down heap to restore heap order
        downHeap(indexRide);
        // decrement the next index pointer
        next--;

        return true;
    }

    /**
     * Checks if the heap contains any rides or not
     * @return A boolean value that is true if the heap contains any rides, else returns false.
     */
    public boolean isEmpty() {
        // checks if the default heap is empty
        return isEmpty(rides);
    }

    /**
     * Checks if the passed heap contains any rides or not
     * @param rides The ride array to check for rides
     * @return A boolean value that is true if the heap contains any rides, else returns false.
     */
    public boolean isEmpty(Ride[] rides) {
        // if the index pointer hasn't been moved, this heap must be empty
        if (next == 1)
            return false;

        // else iterates through each element in the heap
        for (Object element : rides)
            // if the current element is a Ride object, then the heap is not empty - return false
            if (element instanceof Ride)
                return false;

        return true;
    }

    /**
     * Iterates through the heap to see if it contains the passed ride
     * @param r The ride object being searched for in the heap
     * @return A boolean value that is true if the passed ride is already contained within the heap, else returns false
     */
    public boolean hasRide(Ride r) {
        // if the heap is empty, it must not contain the passed ride
        if (isEmpty())
            return false;

        // sets pointers at each end of the heap
        int left = 1;
        int right = getRideCount();

        // moves pointers inward until they intersect, returning true if the passed ride is found by either pointer
        for (; left < right; left++, right--)
            if (rides[left] == r || r == rides[right])
                return true;

        return false;
    }



    /**
     * Puts the heap in heap order. Starts at the lowest node that could
     * possibly have any children, and works back towards the root, down-heaping at each node
     * @return A heapified version of the default ride array
     */
    public Ride[] heapify() {
        return heapify(next - 1, rides);
    }

    /**
     * Takes a heap and puts it in heap order. Starts at the lowest node that could
     * possibly have any children, and works back towards the root, down-heaping at each node
     * @param rideNum The number of rides in the passed array
     * @return A heapified version of the passed ride array
     */
    public Ride[] heapify(int rideNum, Ride[] rideArray) {
        // if the passed ride array length is greater than the max. capacity, reject it and return early
        if (rideArray.length > MAX_CAPACITY) {
            debug("Invalid ride array length! Ride array was greater than the maximum allowed capacity... RideCount: " + rideNum + ", Max. Capacity: " + MAX_CAPACITY, "heapify(int rideNum, Ride[] rideArray)");
            return null;
        }

        // if the passed ride count was below zero, count the rides in the passed array
        if (rideNum < 1 || rideNum > rides.length) {
            debug("Invalid ride count passed! Must be at least 1 ride in the heap to heapify... RideCount: " + rideNum, "heapify(int rideNum, Ride[] rideArray)");
            return null;
        }

        // overwrite the default heap with the passed heap
        rides = rideArray;

        // if passed array has 0-based indices, convert to 1-based
        if (rides[0] != null)
            convertTo1Based(rides);

        int indexParent = rideNum / 2;
        // starting at least non-leaf node, down-heap each root
        for (int i = indexParent; i > 0; i--)
            downHeap(i);

        // overrides the heap with the new heapified array
        return rides;
    }

    /**
     * Uses the heap sort algorithm to sort and return a ride array of all items in the heap in order (i.e., next ride to last ride)
     * @return A ride array sorted into ascending order
     */
    public Ride[] sort() {
        // if the array has 0 or 1 ride, return the passed array as it is already sorted
        if (getRideCount() < 2)
            return rides;

        // stores the value of the next index pointer for later restoration
        int next = this.next;
        // stores the index of the last ride
        int indexLast = next - 1;

        // extracts elements from the heap one by one
        for(int i = indexLast; i > 0; i--) {
            // swap the root element with the last element
            swap(i, 1);
            //ridesSorted[indexLast - i + 1] = ride;
            this.next--;
            // heapify the unsorted array and repeat until all items are sorted, reducing size of heap each time using 'i'
            heapify(i, rides);
        }

        // reverse the array since the sort method leaves it backwards
        reverseHeap();
        // restores the next index pointers position
        this.next = next;
        return rides;
    }

    /**
     * Returns the first ride in the heap (lowest value) without removing it
     * @return The first ride object in the top of the heap if any exists
     */
    public Ride peek() {
        return rides[1];
    }

    public void dump() {
        for (Ride ride : rides)
            if (ride != null)
                System.out.println(ride);
    }

    /**
     * Performs the up heap operation on the passed heap starting from the last leaf node and continuing up the min heap until it finds a smaller parent value
     */
    private void upHeap() {
        // gets the index of the last ride in the passed array
        int indexChild = getRideCount();

        // while we have not hit the root node
        while (indexChild > 1) {
            // get index of current nodes parent
            int indexParent = indexChild / 2;

            // if the child's node value is smaller than the parent's node value, swap them
            if (isSmaller(indexChild, indexParent))
                swap(indexChild, indexParent);

            // move to the next parent
            indexChild = indexParent;
        }
    }

    /**
     * Down heaps the array starting at the root node of the heap and working its way down to the last ride
     * @param indexStart The index that the down heap should start from
     */
    private void downHeap(int indexStart) {
        // stores the index of the smaller child
        int indexSmallest;
        // stores the index of the parent
        int indexParent = indexStart;

        while (indexParent < next) {
            // sets pointers to the left child and its parent
            int indexLeftChild = indexParent * 2;
            int indexRightChild = indexLeftChild + 1;

            // if passed indices are out of bounds, return early
            if (!isValidIndexes(indexLeftChild, indexRightChild)) {
                debug(String.format("Invalid indices passed! One or more indices were out of the bounds of the array... IndexLeft = %d, IndexRight = %d, ArrayLength = %d", indexLeftChild, indexRightChild, rides.length), "downHeap(int indexStart)");
                return;
            }

            // fetches the index of the child with the smallest timestamp
            indexSmallest = getSmaller(indexLeftChild, indexRightChild);
            // check that the smallest value is within the bounds of the array
            if (indexSmallest >= next)
                // if the index of the smallest value is not included in the current array count, do not proceed
                return;

            // if the parent value is not the smallest, swap and continue, else break the loop
            if (isSmaller(indexSmallest, indexParent)) {
                swap(indexSmallest, indexParent);
                indexParent = indexSmallest;

            } else break;
        }
    }

    /**
     * Optimizes the passed ride by merging it with any rides with the same location ID that are within 10 minutes of this rides timestamp (if possible)
     * @param r The ride being checked for optimization capability
     * @return A boolean value that is true if the ride was successfully optimized,
     */
    private boolean isOptimizable(Ride r) {
        debug("Attempting to optimize...", "isOptimizable(Ride r)");
        // if this heap is empty, it is not possible to optimize the passed ride - return early
        if (isEmpty())
            return false;

        // for each ride in the array, compare location id's
        for(Ride ride : rides) {
            // if the ride is null, there must be no rides left to check
            if (ride == null)
                continue;

            // if this ride is not booked within 10 minutes of the passed right, check next ride
            if (getTimeDiff(r, ride) > 10)
                continue;

            // if this rides location doesn't match the new rides location, check next ride
            if (ride.startId == r.startId && ride.endId == r.endId) {
                // updates this rides time to the later time
                ride.time = r.compareTo(ride) < 0 ? ride.time : r.time;
                // try merge passengers, if this doesn't work, this will return false
                return ride.addPassenger(r.passengers);
            }
        }
        return false;
    }

    /**
     * Swaps the elements at index1 and index2 with each other
     * @param index1 The index of the element being swapped with that at index2
     * @param index2 The index of the element being swapped with that at index1
     */
    private void swap(int index1, int index2) {
        // validates the passed indices
        if (!isValidIndexes(index1, index2)) {
            debug(String.format("Unable to swap values! Index was out of bounds... Index1 = %d, Index2 = %d, RideArray Length = %d", index1, index2, rides.length), "swap(int index1, int index2)");
            return;
        }

        //  if the passed array only has one value in it, there is no need to swap
        if (getRideCount() < 2)
            return;

        // creates references to the indexed nodes
        Ride child = rides[index1];
        Ride parent = rides[index2];

        // swaps child with parent
        Ride tempChild = child;
        rides[index1] = parent;
        rides[index2] = tempChild;
    }

    /**
     * Calculates the difference in time between the timestamps of the passed rides
     * @param ride1 The ride object being compared against ride2
     * @param ride2 The ride object being compared against ride1
     * @return An integer value denoting the time difference in minutes
     */
    private int getTimeDiff(Ride ride1, Ride ride2) {
        // stores the two timestamps for simpler comparison
        LocalTime time1 = ride1.time.toLocalTime();
        LocalTime time2 = ride2.time.toLocalTime();

        // calculates the minutes in between
        Duration timeDiff = Duration.between(time1, time2);
        // converts the difference to a positive (absolute) value and into minutes
        int minDiff = Math.abs((int) timeDiff.toMinutes());

        return minDiff;
    }

    /**
     * Iterates through the passed ride array to return the number of rides in it
     * @return An integer value denoting the number of rides in the passed array or -1 if the heap is null
     */
    private int getRideCount() {
        if (rides == null || rides.length < 1)
            return -1;

        // keeps track of the ride count
        int count = 0;

        // iterates through the passed array counting each non-null element
        for(Ride ride : rides)
            if (ride != null)
                count++;

        return count;
    }

    /**
     * Iterates through the heap returning the index of the passed ride object if it is found
     * @param r The ride to search for in the heap
     * @return An integer value denoting the index of the passed ride if it is found, else returns -1 if it is not.
     */
    private int getIndex(Ride r) {
        // gets the index of the last ride in the passed array
        int indexLast = getRideCount();

        // iterate through the heap to fetch the index of the passed 'Ride'
        for(int i = 1; i <= indexLast; i++)
            // if the ride is found, returns its index
            if (rides[i] != null && rides[i].compareTo(r) == 0)
                return i;

        return -1;
    }

    /**
     * Iterates through the heap to ensure that the passed ride has a unique ID
     * @param r The ride being compared
     * @return A boolean value that is true if the passed ride has a unique ID, else returns false
     */
    private boolean isUniqueId(Ride r) {
        // if the heap is empty, the passed id must be unique
        if (isEmpty())
            return true;

        // sets pointers at each end of the heap
        int left = 1;
        int right = getRideCount();

        // moves pointers inward until they intersect, returning true if the passed ride is found by either pointer
        for (; left <= right; left++, right--)
            if (rides[left].id == r.id || r.id == rides[right].id)
                return false;

        return true;
    }

    /**
     * Checks if the passed indices are both within the bounds of the heap
     * @param index1 The first index being checked in the passed ride array
     * @param index2 The second index being checked in the passed ride array
     * @return A boolean value that is true if the passed indices are both within the bounds of the heap
     */
    private boolean isValidIndexes(int index1, int index2) {
        return isValidIndex(index1) && isValidIndex(index2);
    }

    /**
     * Checks if the passed index is within the bounds of the passed array
     * @param index The index being checked in the heap
     * @return A boolean value that is true if the passed index is within the bounds of the heap
     */
    private boolean isValidIndex(int index) {
        return index <= getRideCount() && index > 0;
    }

    /**
     * Compares the two ride objects at the passed indices to see if the timestamp for index1 is smaller than index2
     * @param index1 The index of the first ride object
     * @param index2 The index of the second ride object
     * @return An integer value denoting the index of smaller ride objects of out the two that were passed,
     * if no element exists or if either index is out of bounds, this function will return false by default
     */
    private int getSmaller(int index1, int index2) {
        // compares the passed values against each other and returns the smallest of the two
        return isSmaller(index1, index2) ? index1 : index2;
    }

    /**
     * Compares the two ride objects in the heap to see if the timestamp for index1 is smaller than index2
     * @param index1 The index of the first ride object
     * @param index2 The index of the second ride object
     * @return A boolean value that is true if the timestamp at index1 is smaller than that at index2, else returns false.
     */
    private boolean isSmaller(int index1, int index2) {
        // takes the largest and smallest value of the indices and checks that they are within the bounds of the array
        if (Math.min(index1, index2) < 0 && Math.max(index1, index2) > rides.length)
            return false;

        // get the total ride count
        int indexLastRide = getRideCount();

        // validate the passed indices to prevent out of bounds exception
        if (Math.max(index1, index2) > indexLastRide || Math.min(index1, index2) < 0)
            return false;

        // fetches the ride objects being compared
        Ride ride1 = rides[index1];
        Ride ride2 = rides[index2];

        return ride1.compareTo(ride2) == -1;
    }

    /**
     * Converts the passed 0-based ride array into a 1-based ride array
     * @param rideArray The ride array to convert to a 1-based ride array
     */
    private void convertTo1Based(Ride[] rideArray) {
        // shifts each element in the array one position to the right
        for (int i = rideArray.length - 1; i >= 0; i--)
            rideArray[i + 1] = rideArray[i];

        // sets 0 to null since it is not used
        rideArray[0] = null;
    }

    /**
     * Reverses the current order of the heap, intended for use after heap sort as the array will be in ascending order
     */
    private void reverseHeap() {
        // sets pointers to the first and last ride in the heap
        int left = 1;
        int right = getRideCount();

        // while the pointers have not met or passed each other
        while (left < right) {
            // temporarily store the ride at the index of the left pointer
            Ride temp = rides[left];
            // swap the rides at both pointers with each other and moves pointers inward
            rides[left++] = rides[right];
            rides[right--] = temp;
        }
    }

    /**
     * ~ FOR DEVELOPER USE ONLY ~<br><br>
     *
     * Prints debug messages to console if debugging mode is enabled
     * @param msg The debug message to be printed to the console
     * @param function The name of the function in which the debugging message is executed
     */
    private void debug(String msg, String function) {
        if (DEBUGGING)
            System.out.println(String.format("[MinHeap : %s] %s", function, msg));
    }

}