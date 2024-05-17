/**
 * Creates a new dynamic replaceable minimum heap data structure which stores custom 'Ride' objects ordered by their scheduled timestamps
 */
public class MinHeap {
    /**
     * The array of 'Ride' objects stored in this heap
     */
    Ride[] rideArray;
    /**
     * The current position in the heap
     */
    private int next;
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
     * Constructs a new minimum heap object
     */
    public MinHeap() {
        rideArray = new Ride[MAX_CAPACITY];
        next = 1;

    } // end constructor

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
     * Removes the specific ride from the heap (maintaining heap order)
     * @param r The ride object being removed from the heap
     */
    public Ride remove(Ride r) {
        // return early if the passed ride is not contained in the heap
        if (!hasRide(r)) {
            debug("Unable to remove the passed ride from the heap, ride was not found!");
            return null;

        } // end if

        // stores the index of the passed ride in the heap
        int indexRide = 0;
        // fetches the index of the last ride in the heap
        int indexLast = next - 1;

        debug(String.format("Attempting to remove ride... ID = %d, Timestamp = %s", r.id, r.time.toString()));

        // iterate through the heap to fetch the index of the passed 'Ride'
        for (int i = 0; i < rideArray.length; i++) {
            if (rideArray[i] == r) {
                indexRide = i;
                break;

            } // end if

        } // end for

        debug("Successfully found ride at index: " + indexRide);

        // if the last element is not the root element
        if (indexRide != indexLast)
            // swap the last element with the first
            swap(indexRide, indexLast);

        // delete the last element
        rideArray[indexLast] = null;
        // down heap to maintain heap order
        downHeap();
        // decrement the next element since one node has been removed now
        next--;

        debug(("Successfully removed the passed ride from the heap!"));

        return rideArray[next - 1];

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
     * @param rideNum The current ride number
     */
    public Ride[] heapify(Ride[] rides, int rideNum) {
        // ensure there is at least 2 rides in the heap before heapifying
        if (next <= 2) {
            debug("Unable to heapify the passed ride array, must have at least 2 rides before heapifying...");
            return null;

        } // end if


        downHeap(rides, rideNum);
        return rides;

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

    /**
     * Implements the heap sort algorithm to return a 'Ride' array of all the items in the heap in order (i.e., next ride to last ride)
     */
    public void sort() {


    } // end void

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
                debug(String.format("UPHEAP: Child time: %s was greater than Parent time: %s, no swap was made",
                        rideArray[indexChild].time.toString(), rideArray[indexParent].time.toString()));

            debug(String.format("UPHEAP: Child index is now set to: %d", indexParent));
            // sets child ride index to match parent ride index to continue up the heap for ordering
            indexChild = indexParent;

        } // end while
        debug("UPHEAP: Upheap complete!");

    } // end void

    /**
     * Performs the down-heap operation on this heap
     */
    private void downHeap() {
        downHeap(rideArray, next);

    } // end void

    /**
     * Performs the down-heap operation on the passed array, generally used after the removal of a ride from the heap or for the heapify function
     * @param rideArray The array to perform the down-heap function on
     * @param rideNum The number of rides in the passed heap
     */
    private void downHeap(Ride[] rideArray, int rideNum) {
        // int indexSmallest;
        int indexParent = 1;

        HeapPrinter printer = new HeapPrinter();

        // loop until the parent is bigger than the array size
        while (indexParent < rideNum) {
            // indices of left and right child
            int indexLeft = indexParent * 2;
            int indexRight = indexParent * 2 + 1;
            // index of the smallest value in this iteration
            int indexSmallest = indexLeft;

            debug(String.format("DOWNHEAP: Parent: %d, Left: %d, Right: %d, Smallest: %d, Next: %d", indexParent, indexLeft, indexRight, indexSmallest, next));
            debug(String.format("DOWNHEAP: %s is smaller than %d", isParentBigger(rideArray, rideNum, indexLeft), indexLeft));

            // if the left child is not null and less than the parent
            if (isParentBigger(rideArray, rideNum, indexLeft)) {
                debug(String.format("DOWNHEAP: Swapping left child with parent: Left = %d, Right = %d", indexLeft, indexParent));
                // updates the parent index to match the new parent
                indexSmallest = indexLeft;

            } // end if

            // id the right child is not an empty element and less than the parent
            if (isParentBigger(rideArray, rideNum, indexRight)) {
                debug(String.format("DOWNHEAP: Swapping left child with parent: Left = %d, Right = %d", indexRight, indexParent));
                // sets the index of the smallest value to the right childs index
                indexSmallest = indexRight;

            } // end if

            //printer.printTime(rideArray, indexSmallest, indexParent);

            // if the parent array isn't the smallest, swap parent with the smallest child
            if (isParentBigger(rideArray, rideNum, indexSmallest)) {
                debug(String.format("DOWNHEAP: Index parent was not equal to the smallest index! Parent: %d, Smallest: %d", indexParent, indexSmallest));
                swap(indexSmallest, indexParent);
                indexParent = indexSmallest;

            } else {
                debug(String.format("DOWNHEAP: Index parent is now the smallest index! Breaking loop... Parent: %d, Smallest: %d", indexParent, indexSmallest));
                break;

            } // end if

        } // end while

    } // end void

    /**
     * Iterates through the heap to see if it already contains the passed ride
     * @param r The ride object being searched for in the heap
     * @return A boolean value that is true if the passed ride is already contained within this list, else returns false
     */
    private boolean hasRide(Ride r) {
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

        debug(String.format("ISPARENTBIGGER: indexChild: %d, indexParent: %d", indexChild, indexChild / 2));

        // checks if the indices of either ride is referencing a null element
        if (ride1 == null || ride2 == null) {
            System.out.println("Unable to compare rides! At least one ride was null...");
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
     * Swaps the two elements at the passed indices in the heap with each other
     * @param indexChild The index of the child element being swapped
     * @param indexParent The index of the parent element being swapped
     */
    private void swap(int indexChild, int indexParent) {
        // creates reference objects to the child/parent nodes
        Ride child = rideArray[indexChild];
        Ride parent = rideArray[indexParent];

        // prints current time values of child/parent nodes
        debug(String.format("Child time: %s was less than parent time: %s, attempting to swap values...", child.time.toString(), parent.time.toString()));

        // swaps child with parent
        Ride tempChild = child;
        rideArray[indexChild] = parent;
        rideArray[indexParent] = tempChild;

        // prints new time values of child/parent nodes
        debug(String.format("New child time: %s, New parent time: %s", rideArray[indexChild].time.toString(), rideArray[indexParent].time.toString()));

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