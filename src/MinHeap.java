/**
 * Creates a new dynamic replaceable minimum heap data structure which stores custom 'Ride' objects ordered by their scheduled timestamps
 */
public class MinHeap {
    // TEMP - used to print heap while testing
    HeapPrinter printer = new HeapPrinter();
    /**
     * The array used to store each ride in this min heap
     */
    Ride[] rides;
    /**
     * The next available position in the heap (i.e., if there are 20 rides in the heap, 0 is null, 1-20 are rides, next will be pointing to index 21)
     */
    public int next;
    /**
     * The number of vehicles (rides) that the company has available (i.e., the size of the heap)
     */
    public final int MAX_CAPACITY = 21;
    /**
     * The maximum number of passengers per vehicle
     */
    private final int MAX_PASSENGERS = 6;
    /**
     * ~ FOR DEVELOPER USE ONLY! ~ <br><br>
     *
     * True if debugging mode should be enabled, else false
     */
    private final boolean DEBUGGING = true;

    /**
     * Constructs a new minimum heap object
     */
    public MinHeap() {
        // creates a new ride array
        rides = new Ride[MAX_CAPACITY];
        // points to the first available slot in the heap
        next = 1;

    } // end constructor

    /**
     * Adds each ride in the passed ride array to the default heap (maintaining heap order)
     * @param rideArray The ride array containing the rides that are being inserted into the default heap
     */
    public void insert(Ride[] rideArray) {
        // loops through the passed array and inserts any rides that are contained
        for (Ride ride : rideArray)
            insert(ride);

    } // end void

    /**
     * Adds the specified ride to the default heap (maintaining heap order)
     * @param r The ride object being added to the default heap
     */
    public void insert(Ride r) {
        if (r == null) {
            return;

        } // end if

        if (hasRide(r, rides)) {
            debug(String.format("Unable to insert ride! Ride was already contained in the array... Ride ID: %d, Ride Time: %s", r.id, r.getTime()), "insert");
            return;

        } // end if

        if (next >= rides.length) {
            debug(String.format("Unable to insert the passed ride! Maximum ride limit has been reached... Ride ID: %d, Ride Time: %s, Max Capacity: %d", r.id, r.getTime(), MAX_CAPACITY), "insert");
            return;

        } // end if

        debug("Attempting to insert ride... \n " + r, "insert");

        // sets the next spare slot in the heap to the passed 'Ride' object
        rides[next] = r;
        // performs up-heap to maintain heap order
        upHeap(rides);
        // increment next available node counter
        next++;

    } // end void

    /**
     * Removes the passed ride from the default heap (maintaining heap order)
     * @param r The ride object being removed from the default heap
     */
    public Ride remove(Ride r) {
        if (r == null)
            return null;

        // test
        debug(String.format("Attempting to remove ride... RideID = %d, RideTimestamp = %s", r.id, r.getTime()), "remove");

        // return early if the passed ride is not contained in the heap
        if (!hasRide(r, rides)) {
            // test
            debug("Unable to remove the passed ride from the heap! Ride was not found... ", "remove");
            return null;

        } // end if

        // fetches the heap-index of the passed ride
        int indexRide = getIndex(r, rides);
        // fetches the index of the last ride in the heap
        int indexLast = next - 1;

        // if the last element is not the root element
        if (indexRide != indexLast)
            // swap the last element with the first
            swap(indexRide, indexLast, rides);

        Ride removedRide = rides[indexLast];
        // delete the last element
        rides[indexLast] = null;
        // down heap to maintain heap order
        downHeap(indexRide, rides);
        // decrement next available node counter
        next--;

        //rides = rideArray;
        return removedRide;

    } // end void

    /**
     * Checks if the passed ride array contains any rides or not
     * @return A boolean value that is true if the passed heap contains any rides, else returns false.
     */
    public boolean isEmpty(Ride[] rideArray) {
        // iterates through each element in the heap
        for (Object element : rideArray)
            // if the current element is a Ride object, then the heap is not empty - return false
            if (element instanceof Ride)
                return false;

        return true;

    } // end boolean

    /**
     * Returns the first ride in the default heap (lowest value) without removing it
     * @return The ride object at the top of the heap if any exist, else returns null
     */
    public Ride peek() {
        // calls the peek method passing the default heap
        return peek(rides);

    } // end ride

    /**
     * Returns the first ride in the passed heap (lowest value) without removing it
     * @return The ride object at the top of the heap if any exist, else returns null
     */
    public Ride peek(Ride[] rideArray) {
        return rideArray[1];

    } // end ride

    /**
     * Iterates through the passed ride array to return the number of rides in it
     * @param rideArray The ride array to iterate through
     * @return An integer value denoting the number of rides in the passed array
     */
    private int getRideCount(Ride[] rideArray) {
        if (rideArray == null)
            return -1;

        int count = 0;

        // if the list isn't empty
        if (rideArray.length > 0)
            // iterates through the passed array counting each non-null element
            for(Ride ride : rideArray)
                if (ride != null)
                    count++;

        return count;

    } // end void

    /**
     * Fetches the index of the first available slot in the passed ride array
     * @param rideArray The ride array being checked for availability
     * @return An integer value denoting the index of the next available slot if any exists, else returns -1
     */
    private int getNext(Ride[] rideArray) {
        // iterates through the passed ride array and returns its first available slot (i.e., ride count + 1)
        return getRideCount(rideArray) + 1;

    } // end int



    /**
     * Iterates through the passed array returning the index of the passed ride object, if it is contained, else returns -1
     * @param r The ride to search for in the ride array
     * @param rideArray The heap to ride array being search
     * @return An integer value denoting the index of the passed ride if it is found, else returns -1
     */
    private int getIndex(Ride r, Ride[] rideArray) {
        // gets the index of the last ride in the passed array
        int indexLast = getRideCount(rideArray);

        // iterate through the heap to fetch the index of the passed 'Ride'
        for (int i = 1; i <= indexLast; i++) {
            System.out.println("Loop = " + i);
            if (rideArray[i] == null)
                continue;
            //System.out.println("Passed ride: \n" + rideArray[i]);
            if (rideArray[i].compareTo(r) == 0) {
                return i;

            } // end if

        } // end for

        System.out.println("No index found! returning -1");
        return -1;

    } // end int

    /**
     * Iterates through the passed ride array to see if it already contains the passed specified ride
     * @param r The ride object being searched for in the heap
     * @param rideArray The ride array being searched
     * @return A boolean value that is true if the passed ride is already contained within this list, else returns false
     */
    private boolean hasRide(Ride r, Ride[] rideArray) {
        if (isEmpty(rideArray))
            return false;

        // if the passed ride matches one of the ride array elements - return true
        for (Ride ride : rideArray)
            if (ride == r)
                return true;

        return false;

    } // end boolean

    /**
     * Performs the up heap operation on the default heap starting from the last leaf node and continuing up the min heap until it finds a smaller parent value
     */
    private void upHeap() {
        upHeap(rides);

    } // end void

    /**
     * Performs the up heap operation on the passed heap starting from the last leaf node and continuing up the min heap until it finds a smaller parent value
     * @param rideArray The array to up-heap
     */
    private void upHeap(Ride[] rideArray) {
        // gets the index of the last ride in the passed array
        int indexChild = getRideCount(rideArray);

        while (indexChild > 1) {
            int indexParent = indexChild / 2;

            if (isSmaller(indexChild, indexParent, rideArray))
                swap(indexChild, indexParent, rideArray);

            indexChild = indexParent;

        } // end while

    } // end void

    /**
     * Down heaps the array starting at the root node
     * @param rideArray
     */
    private void downHeap(Ride[] rideArray) {
        downHeap(1, rideArray);

    } // end void

    /**
     * Down heaps the array starting at the root node
     * @param indexStart The index that the down heap should start from
     * @param rideArray The ride array that is being down heaped
     */
    private void downHeap(int indexStart, Ride[] rideArray) {
        // test
        System.out.println("\n\n\n\nDownheaping...\n\n");
        // gets the size of the heap for an iteration counter
        int next = getNext(rideArray);
        // stores the index of the smaller child
        int indexSmallest;
        // stores the index of the parent
        int indexParent = indexStart;

        while (indexParent < next) {
            // gets child indices
            int indexLeftChild = indexParent * 2;
            int indexRightChild = indexLeftChild + 1;
            debug(String.format("Next: %d, IndexParent: %d, IndexLeftChild: %d, IndexRightChild: %d", next, indexParent, indexLeftChild, indexRightChild), "downheap");

            if (!isValidIndexes(indexLeftChild, indexRightChild, rideArray)) {
                debug(String.format("Invalid indices passed! One or more indices were out of the bounds of the array... IndexLeft = %d, IndexRight = %d, ArrayLength = %d", indexLeftChild, indexRightChild, rideArray.length), "downheap");
                break;

            } // end if

//            // checks if value at left child is less than right child and swaps them if not
//            if (isSmaller(indexRightChild, indexLeftChild, rideArray))
//                swap(indexLeftChild, indexRightChild, rideArray);

            // fetches the index of the child with the smallest timestamp
            indexSmallest = getSmaller(indexLeftChild, indexRightChild, rideArray);
            // check that the smallest value is within the bounds of the array
            if (indexSmallest >= next)
                // if the index of the smallest value is not included in the current array count, do not proceed
                return;

            // if the parent value is not the smallest, swap and continue, else break the loop
            if (isSmaller(indexSmallest, indexParent, rideArray)) {
                swap(indexSmallest, indexParent, rideArray);
                indexParent = indexSmallest;

            } else {
                //debug("Downheap Complete! Parent time: " + rideArray[indexParent].time, "Downheap" );
                break;

            } // end if

        } // end while

    } // end void

    /**
     * Checks if the passed indices are both within the bounds of the passed array
     * @param index1 The first index being checked in the passed ride array
     * @param index2 The second index being checked in the passed ride array
     * @param rideArray The ride array being used to validate bounds
     * @return A boolean value that is true if the passed indices are both within the bounds of the passed ride array
     */
    private boolean isValidIndexes(int index1, int index2, Ride[] rideArray) {
        return isValidIndex(index1, rideArray) && isValidIndex(index2, rideArray);

    } // end void

    /**
     * Checks if the passed index is within the bounds of the passed array
     * @param index The index being checked in the passed ride array
     * @param rideArray The ride array being used to validate bounds
     * @return A boolean value that is true if the passed index within the bounds of the passed ride array
     */
    private boolean isValidIndex(int index, Ride[] rideArray) {
        return index < next && index > 0;

    } // end void

    /**
     * Puts the default heap in heap order. Starts at the lowest node that could
     * possibly have any children, and works back towards the root, down-heaping at each node
     * @return A heapified version of the default ride array
     */
    public Ride[] heapify() {
        return heapify(next - 1, rides);

    } // end ride array

    /**
     * Takes a heap and puts it in heap order. Starts at the lowest ndoe that could
     * possibly have any children, and works back towards the root, down-heaping at each node
     * @param rideArray The ride array that should be heapified
     * @return A heapified version of the passed ride array
     */
    public Ride[] heapify(Ride[] rideArray) {
        // calls heapify method after counting rides in the array
        return heapify(getRideCount(rideArray), rideArray);

    } // end ride array

    /**
     * Takes a heap and puts it in heap order. Starts at the lowest node that could
     * possibly have any children, and works back towards the root, down-heaping at each node
     * @param rideNum The number of rides in the passed array
     * @param rideArray The ride array that should be heapified
     * @return A heapified version of the passed ride array
     */
    public Ride[] heapify(int rideNum, Ride[] rideArray) {
        // if the passed ride count was below zero, count the rides in the passed array
        if (rideNum < 1 || rideNum > rideArray.length) {
            debug("Invalid ride count passed! Must be at least 1 ride in the heap to heapify... RideCount: " + rideNum, "heapify");
            return null;

        } // end if

        // if passed array has 0-based indices, convert to 1-based
        if (rideArray[0] != null) {
            for (int i = rideArray.length - 1; i >= 0; i--)
                rideArray[i + 1] = rideArray[i];

            rideArray[0] = null;

        } // end if

        int indexParent = rideNum / 2;

        // starting at least non-leaf node, downheap each root
        for (int i = indexParent; i > 0; i--)
            downHeap(i, rideArray);

        // updates the default heap to match the newly heapified array
        rides = rideArray;
        // test
        System.out.println("Heapify allegedly complete! RidesCount: " + getRideCount(rides) + " RideArrayCount: " + getRideCount(rideArray));

        // overrides the default heap with the new heapified array
        return rideArray;

    } // end ride array

    /**
     * Uses the heap sort algorithm to sort and return a ride array of all items in the default heap in order (i.e., next ride to last ride)
     * @return A ride array ordered by timestamps
     */
    public Ride[] sort() {
        // if the array has 0 or 1 ride, return the passed array as it is already sorted
        if (getRideCount(rides) < 2)
            return rides;

        // gets the index of the last ride in the heap
        int indexLast = next - 1;

        // extracts elements from the heap one by one
        for(int i = indexLast; i > 0; i--) {
            //test
            System.out.println("Before swap...");
            printer.printTime(rides, i, 1);

            // swap the root element with the last element
            swap(i, 1, rides);
            //ridesSorted[indexLast - i + 1] = ride;
            next--;
            // heapify the unsorted array and repeat until all items are sorted, reducing size of heap each time using 'i'
            heapify(i, rides);

        } // end for

        // reverse the array since the sort method leaves it backwards
        reverseHeap();
        printer.printTime(rides);

        next = getNext(rides);

        return rides;

    } // end ride array

    /**
     * Swaps the elements at index1 and index2 with each other in the passed ride array
     * @param index1 The index of the element being swapped with that at index2
     * @param index2 The index of the element being swapped with that at index1
     * @param rideArray The array in which the swapped elements are contained
     */
    private void swap(int index1, int index2, Ride[] rideArray) {
        if (Math.max(index1, index2) >= rideArray.length) {
            debug(String.format("Unable to swap values! Index was out of bounds... Index1 = %d, Index2 = %d, RideArray Length = %d", index1, index2, rideArray.length), "swap");
            return;

        } // end if

        //  if the passed array only has one value in it, there is no need to swap
        if (getRideCount(rideArray) < 2)
            return;

        // creates references to the indexed nodes
        Ride child = rideArray[index1];
        Ride parent = rideArray[index2];

        // swaps child with parent
        Ride tempChild = child;
        rideArray[index1] = parent;
        rideArray[index2] = tempChild;

    } // end void

    /**
     * Compares the two ride objects in the passed ride array to see if the timestamp for index1 is smaller than index2
     * @param index1 The index of the first ride object
     * @param index2 The index of the second ride object
     * @param rideArray The ride array containing the compared values
     * @return An integer value equal to the index that contains the smaller element,
     * if no element exists or if either index is out of bounds, this function will return -1
     */
    private int getSmaller(int index1, int index2, Ride[] rideArray) {
        // test
        debug(String.format("Fetching smaller value: Index1: %d, Index2: %d", index1, index2), "getSmaller");
        // takes the largest and smallest value of the indices and checks that they are within the bounds of the array
        if (Math.min(index1, index2) < 0 && Math.max(index1, index2) > rideArray.length)
            return -1;
        return isSmaller(index1, index2, rideArray) ? index1 : index2;

    } // end bool

    /**
     * Compares the two ride objects in the passed ride array to see if the timestamp for index1 is smaller than index2
     * @param index1 The index of the first ride object
     * @param index2 The index of the second ride object
     * @param rideArray The ride array containing the compared values
     * @return A boolean value that is true if the timestamp at index1 is smaller than that at index2, else returns false.
     */
    private boolean isSmaller(int index1, int index2, Ride[] rideArray) {
        // get the total ride count
        int indexLastRide = getRideCount(rideArray);

        // validate the passed indices to prevent out of bounds exception
        if (Math.max(index1, index2) > indexLastRide || Math.min(index1, index2) < 0) {
            debug(String.format("Unable to compare timestamps! Index out of bounds error: " +
                    "Index1 = %d, Index2 = %d, IndexLastRide = %d", index1, index2, indexLastRide), "isSmaller");
            return false;

        } // end if

        // fetches the ride objects being compared
        Ride ride1 = rideArray[index1];
        Ride ride2 = rideArray[index2];

        return ride1.compareTo(ride2) == -1;

    } // end bool

    /**
     * Reverses the current order of the default heap, intended for use after heap sort as the array will be in ascending order
     */
    private void reverseHeap() {
        // sets pointers to the first and last ride in the heap
        int left = 1;
        int right = getRideCount(rides);

        // while the pointers have not met or passed each other
        while (left < right) {
            // temporarily store the ride at the index of the left pointer
            Ride temp = rides[left];
            // swap the rides at both pointers with each other
            rides[left] = rides[right];
            rides[right] = temp;

            // move pointers inward
            left++;
            right--;

        } // end while

    } // end void

    /**
     * ~ FOR DEVELOPER USE ONLY ~<br><br>
     *
     * Prints debug messages to console if debugging mode is enabled
     * @param msg The debug message to be printed to the console
     * @param function The name of the function in which the debugging message is executed
     */
    private void debug(String msg, String function) {
        if (DEBUGGING)
            System.out.println(String.format("[%s] %s", function.toUpperCase(), msg));

    } // end void

} // end class