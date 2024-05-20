import java.util.Arrays;

public class BACKUP {

    HeapPrinter printer = new HeapPrinter();
    /**
     * The array of 'Ride' objects stored in this heap
     */
    Ride[] rideArray;
    /**
     * The current position in the heap
     */
    protected int next;
    /**
     * The number of vehicles (rides) that the company has available (i.e., the size of the heap)
     */
    private final int MAX_CAPACITY = 20;
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
     * Adds the specified ride to the heap (maintaining heap order)
     * @param r The ride object being added to the heap
     */
    public void insert(Ride r) {
        // validates the passed ride before inserting it into the heap
        if (this.hasRide(r)) {
            debug("Unable to insert the passed ride! Ride is invalid or already contained in this heap...");
            return;

        } // end if

        if (next >= rideArray.length) {
            debug("Unable to insert the passed ride, maximum ride limit has been reached! Max capacity: " + MAX_CAPACITY);
            return;

        } // end if

        // sets the next spare slot in the heap to the passed 'Ride' object
        rideArray[next] = r;
        // performs up-heap to maintain heap order
        upHeap();
        next++;

    } // end void

    /**
     * Adds each ride in the passed ride array to the heap (maintaining heap order)
     * @param rideArray The ride array containing the rides that are being added to the heap
     */
    public void insert(Ride[] rideArray) {
        for(Ride ride : rideArray) {
            insert(ride);

        } // end for

    } // end void

    /**
     * Removes the passed ride from this heap (maintaining heap order)
     * @param r The ride object being removed from the heap
     * @return The passed ride object if it was found in the heap, else returns null
     */
    public Ride remove(Ride r) {
        // removes and stores the passed ride if it exists in the heap
        Ride removedRide = remove(r, rideArray);
        // reduce the size of the heap by one
        next--;

        return removedRide;

    } // end ride

    /**
     * Removes the specified ride from the passed heap (maintaining heap order)
     * @param r The ride object being removed from the heap
     * @param rideArray The ride array to remove the passed ride from
     */
    public Ride remove(Ride r, Ride[] rideArray) {
        // return early if the passed ride is not contained in the heap
        if (!hasRide(r, rideArray)) {
            debug("Unable to remove the passed ride from the heap, ride was not found!");
            return null;

        } // end if

        // fetches the heap-index of the passed ride
        int indexRide = getIndex(r, rideArray);
        // fetches the index of the last ride in the heap
        int indexLast = getRides(rideArray);

        debug(String.format("Attempting to remove ride... ID = %d, Timestamp = %s, IndexLast: %d, IndexRide: %d", r.id, r.time.toString(), indexLast, indexRide));

        // if the last element is not the root element
        if (indexRide != indexLast)
            // swap the last element with the first
            swap(indexRide, indexLast);

        Ride removedRide = rideArray[indexLast];
        // delete the last element
        rideArray[indexLast] = null;
        // down heap to maintain heap order
        downHeap(indexLast, rideArray);

        debug(("Successfully removed the passed ride from the heap!"));

        return removedRide;

    } // end void

    /**
     * Checks if this heap contains any rides
     * @return A boolean value that is true if this heap contains any rides, else returns false.
     */
    public boolean isEmpty() {
        // iterates through each element in the heap
        for (Object element : rideArray)
            // if the current element is a Ride object, then the heap is not empty
            if (element instanceof Ride)
                return false;

        // else, the heap is empty since no ride objects were found
        return true;
    } // end boolean

    /**
     * Looks at the first item in the list to show the next trip (highest priority)
     * @return The 'First' ride object in the heap or null if none exist
     */
    public Ride peek() {
        return rideArray[1];

    } // end void

    /**
     * Take a ride array with a specified number of rides and puts them into heap order
     * @param rides The ride array to being put into heap order
     */
    public Ride[] heapify(Ride[] rides) {
        // counts the number of rides in the passed array then calls the heapify method with the ride count
        return heapify(getRides(rides), rides);

    } // end ride

    /**
     * Take a ride array with a specified number of rides and puts them into heap order
     * @param rideNum The number of rides in the array
     * @param rides The ride array to being put into heap order
     * @return The passed ride array object after heapify has been performed on it
     */
    public Ride[] heapify(int rideNum, Ride[] rides) {
        int indexParent = rideNum / 2;

        // ensure there is at least 2 rides in the heap before performing heapify
        if (indexParent < 1) {
            debug("[HEAPIFY] Unable to heapify the passed ride array, must have at least 2 rides before attempting to heapify...");
            return null;

        } // end if

        // downheap each parent node until
        while (indexParent > 1) {
            downHeap(indexParent, rides);
            indexParent--;
            System.out.println("Downheaping: " + rideArray[indexParent]);

        } // end while

        rideArray = Arrays.copyOf(rides, 20);
        System.out.println("[Heapify] New array:\n");
        printer.printID(rideArray);
        return rideArray;

    } // end void

    /**
     * Prints the current schedule to the console
     */
    public void dump() {
        System.out.println("\nPrinting ride schedule to the console....\n");
        for (Object ride : rideArray)
            if (ride instanceof Ride)
                System.out.println(ride);

    } // end void

    public Ride[] sort() {
        return sort(rideArray);

    } // end ride array

    /**
     * Returns a 'Ride' array of all the items in the heap in order (i.e., next ride to last ride)
     * @return A sorted array of this heaps Ride objects, else if the heap is null, or contains less than 2 or any un-sortable elements, returns null
     */
    public Ride[] sort(Ride[] rideArray) {
        // if there is only one element in the array
        if (getRides(rideArray) < 2)
            return rideArray;

        // build a max heap
        Ride[] heap = new Ride[20];
        int next = 1;

        // heapify the passed array before sorting
        heapify(rideArray);

        for (int i = getRides(rideArray); i > 0; i--) {
            System.out.println("[Heapify] i =========== " + i + " Ride = \n" + rideArray[i]);
            // swap the first and last elements in ride array with each-other
            swap(i, 1, rideArray);
            System.out.println("Length: " + heap.length + " next: " + next + " i: " + i);
            // remove last element and store it in the new min heap
            heap[next] = remove(rideArray[i], rideArray);
            next++;
            // temp
            System.out.println("Sorted heap:");
            printer.printTime(heap, i);

        } // end for

        return heap;

    } // end ride array

    /**
     * Performs the up heap operation after an insertion
     */
    private void upHeap() {
        // sets child ride index to match the index of the most recently added ride in the heap
        int indexChild = next;

        // while child ride is not the root of the heap
        while (indexChild > 1) {
            // grabs parent ride index
            int indexParent = indexChild / 2;

            //System.out.println(String.format(" Upheap: %s is less than %s? " + isParentBigger(rideArray, next, indexChild), rideArray[indexChild].time.toString(), rideArray[indexParent].time.toString()));

            // if the child rides timestamp is less than the parent rides timestamp
            if (isParentBigger(rideArray, next, indexChild))
                // swaps the child ride with the parent ride
                swap(indexChild, indexParent);
            else
                // else writes informative debug message to console if debugging mode is enabled
                //debug(String.format("UPHEAP: Child time: %s was greater than Parent time: %s, no swap was made",
                //rideArray[indexChild].time.toString(), rideArray[indexParent].time.toString()));

                //debug(String.format("UPHEAP: Child index is now set to: %d", indexParent));
                // sets child ride index to match parent ride index to continue up the heap for ordering
                indexChild = indexParent;

        } // end while
        //debug("UPHEAP: Upheap complete!");

    } // end void

    /**
     * Performs the down-heap operation on this heap
     */
    private void downHeap() {
        downHeap(next, rideArray);

    } // end void

    /**
     * Performs the down-heap operation on the passed array, generally used after the removal of a ride from the heap or for the heapify function
     * @param rideNum The number of rides in the passed heap
     * @param rideArray The array to perform the down-heap function on
     */
    private void downHeap(int rideNum, Ride[] rideArray) {
        // int indexSmallest;
        int indexParent = 1;

        // loop until the parent is bigger than the array size
        while (indexParent < rideNum) {
            // indices of left and right child
            int indexLeft = indexParent * 2;
            int indexRight = indexParent * 2 + 1;
            // index of the smallest value in this iteration
            int indexSmallest = indexLeft;

            //debug(String.format("DOWNHEAP: Parent: %d, Left: %d, Right: %d, Smallest: %d, Next: %d", indexParent, indexLeft, indexRight, indexSmallest, next));
            //debug(String.format("DOWNHEAP: %s is smaller than %d? %s ", indexLeft / 2, indexLeft, isParentBigger(rideArray, rideNum, indexLeft)));

            // if the left child is not null and less than the parent
            if (isParentBigger(rideArray, indexParent, indexLeft)) {
                //debug(String.format("DOWNHEAP: Swapping left child with parent: Left = %d, Right = %d", indexLeft, indexParent));
                // updates the parent index to match the new parent
                indexSmallest = indexLeft;

            } // end if

            // id the right child is not an empty element and less than the parent
            if (isParentBigger(rideArray, indexParent, indexRight)) {
                //debug(String.format("DOWNHEAP: Swapping left child with parent: Left = %d, Right = %d", indexRight, indexParent));
                // sets the index of the smallest value to the right childs index
                indexSmallest = indexRight;

            } // end if

            // if the parent value isn't the smallest, swap parent with the smallest child
            if (isParentBigger(rideArray, indexParent, indexSmallest)) {
                //debug(String.format("DOWNHEAP: Index parent was not equal to the smallest index! Parent: %d, Smallest: %d", indexParent, indexSmallest));
                swap(indexSmallest, indexParent, rideArray);
                indexParent = indexSmallest;

            } else {
                //debug(String.format("DOWNHEAP: Index parent is now the smallest index! Breaking loop... Parent: %d, Smallest: %d", indexParent, indexSmallest));
                break;

            } // end if

        } // end while

    } // end void

    /**
     * Iterates through this heap to see if it already contains the passed ride
     * @param r The ride object being searched for in this heap
     * @return A boolean value that is true if the passed ride is already contained within this list, else returns false
     */
    private boolean hasRide(Ride r) {
        return hasRide(r, rideArray);

    } // end bool

    /**
     * Iterates through the passed ride array to see if it already contains the specified ride
     * @param r The ride object being searched for in the heap
     * @param rideArray The ride array being searched
     * @return A boolean value that is true if the passed ride is already contained within this list, else returns false
     */
    private boolean hasRide(Ride r, Ride[] rideArray) {
        if (isEmpty())
            return false;

        // iterates through the heap
        for (Ride ride : rideArray)
            // if the passed ride is found within this heap, return true
            if (ride == r)
                return true;

        //debug("No duplicate ride with ID: " + r.id + " exists in the heap...");
        return false;

    } // end boolean

    /**
     * Compares the ride objects at 'indexRide1' and 'indexRide2' against each other by their timestamps
     * @param rideArray The array in which the referenced elements are contained
     * @param indexChild The index of the child that is being compared against its parent
     * @return A boolean value that is true if the parent of the passed node is bigger, else returns false<br><br>
     *
     * Note: If either ride object is null, or if either index exceeds the heap size, this function will return false by default.
     */
    private boolean isParentBigger(Ride[] rideArray, int rideNum, int indexChild) {
        // fetch both ride objects if their indexes were valid
        Ride ride1 = indexChild <= rideNum ? rideArray[indexChild] : null;
        Ride ride2 = indexChild / 2 <= rideNum ? rideArray[indexChild / 2] : null;

        //debug(String.format("ISPARENTBIGGER: indexChild: %d, indexParent: %d, numRides: %d", indexChild, indexChild / 2, rideNum));

        // checks if the indices of either ride is referencing a null element
        if (ride1 == null || ride2 == null) {
            //System.out.println("Unable to compare rides! At least one ride was null...");
            return false;

        } // end if

        // returns true if ride 1 is smaller than ride 2, else returns false
        return ride1.compareTo(ride2) < 0;

    } // end void

    /**
     * Compares the ride objects at 'indexChild' and 'indexRide2' against each other by their timestamps
     * @param rideArray The array in which the referenced elements are contained
     * @param indexChild The index of the child that is being compared against its parent
     * @return A boolean value that is true if the parent of the passed node is smaller, else returns false<br><br>
     *
     * Note: If either ride object is null, or if either index exceeds the heap size, this function will return false by default.
     */
    private boolean isParentSmaller(Ride[] rideArray, int rideNum, int indexChild) {
        // fetch both ride objects if their indexes were valid
        Ride ride1 = indexChild <= rideNum ? rideArray[indexChild] : null;
        Ride ride2 = indexChild / 2 <= rideNum ? rideArray[indexChild / 2] : null;

        // checks if the indices of either ride is referencing a null element
        if (ride1 == null || ride2 == null)
            return false;

        // returns true if ride 1 is smaller than ride 2, else returns false
        return ride1.compareTo(ride2) < 0;

    } // end void

    /**
     * Swaps the two elements at the passed indices in this heap with each other
     * @param indexChild The index of the child element being swapped
     * @param indexParent The index of the parent element being swapped
     */
    private void swap(int indexChild, int indexParent) {
        swap(indexChild, indexParent, rideArray);

    } // end void

    /**
     * Swaps the two elements at the passed indices in the passed heap with each other
     * @param rideArray The heap in which the swap will occur at the passed indices
     * @param indexChild The index of the child element being swapped
     * @param indexParent The index of the parent element being swapped
     */
    private void swap(int indexChild, int indexParent, Ride[] rideArray) {
        // creates references to the child/parent nodes
        Ride child = rideArray[indexChild];
        Ride parent = rideArray[indexParent];

        // prints current time values of child/parent nodes
        //debug(String.format("Child time: %s was less than parent time: %s, attempting to swap values...", child.time.toString(), parent.time.toString()));

        printer.printTime(rideArray, indexChild, indexParent);

        // swaps child with parent
        Ride tempChild = child;
        rideArray[indexChild] = parent;
        rideArray[indexParent] = tempChild;

        // prints new time values of child/parent nodes
        //debug(String.format("New child time: %s, New parent time: %s", rideArray[indexChild].time.toString(), rideArray[indexParent].time.toString()));

        printer.printTime(rideArray, indexChild, indexParent);

    } // end void

    /**
     * Fetches the heap index of the passed ride object
     * @param r The ride to search the heap for
     * @return An integer value denoting the index of the passed ride if it is found, else returns -1
     */
    private int getIndex(Ride r) {
        return getIndex(r, rideArray);

    } // end int

    /**
     * Searches the passed ride array and fetches the heap-index of the passed ride object
     * @param r The ride to search the heap for
     * @param rideArray The ride array being searched
     * @return An integer value denoting the index of the passed ride if it is found, else returns -1
     */
    private int getIndex(Ride r, Ride[] rideArray) {
        // gets the index of the last ride in the passed array
        int indexLast = getRides(rideArray);

        // iterate through the heap to fetch the index of the passed 'Ride'
        for (int i = 1; i <= indexLast; i++) {
            System.out.println("Loop = " + i);
            //System.out.println("Passed ride: \n" + rideArray[i]);
            if (rideArray[i] == r) {
                //debug("[GETINDEX] Successfully found ride at index: " + i);
                return i;

            } // end if

        } // end for

        debug("[GETINDEX] Unable to fetch index for the passed ride, ride is not in the heap!");
        return -1;

    } // end int

    /**
     * Iterates through the passed ride array to return the number of rides in it
     * @param rideArray The ride array to iterate through
     * @return An integer value denoting the number of rides in the passed array
     */
    private int getRides(Ride[] rideArray) {
        int count = 0;

        // iterates through the passed array counting each non-null element
        for(Ride ride : rideArray)
            if (ride != null)
                count++;

        return count;

    } // end void

    /**
     * ~ FOR DEVELOPER USE ONLY ~<br><br>
     *
     * Prints debug messages to console if debugging mode is enabled
     * @param msg The debug message to be printed to the console
     */
    private void debug(String msg) {
        if (DEBUGGING)
            System.out.println("[DEBUG] " + msg);

    } // end void


} // end class