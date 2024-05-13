/**
 * Creates a new dynamic replaceable minimum heap data strucutre which stores custom 'Ride' objects ordered by their scheduled timestamps
 */
public class MinHeap {
    /**
     * ~ FOR DEVELOPER USE ONLY! ~ <br><br>
     *
     * True if debugging mode should be enabled, else false
     */
    private boolean debugging = true;
    /**
     * The array of 'Ride' objects stored in this heap
     */
    private Ride[] rideArray;
    /**
     * The current position in the heap
     */
    private int next;

    /**
     * Constructs a new minimum heap object
     */
    public MinHeap(int size) {
        rideArray = new Ride[size];
        next = 1;

    } // end constructor

    /**
     * Adds the specified ride to the heap (maintaining heap order)
     * @param r The ride object being added to the heap
     */
    public void insert(Ride r) {
        rideArray[next] = r;
        upHeap();
        next++;

    } // end void

    /**
     * Removes the specific ride from the heap (maintaining heap order)
     * @param r The ride object being removed from the heap
     */
    public void remove(Ride r) {
        if (isEmpty())
            return;

        if (rideArray[1] != rideArray[next])
            rideArray[1] = rideArray[next];

        rideArray[next] = null;

        downHeap();

    } // end void

    /**
     * Returns whether there are passengers waiting or not
     * @return A boolean value that is true if there are passengers waiting, and false if not
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
     */
    public void peek() {


    } // end void

    /**
     * Take a ride array with a specified number of rides and puts them into heap order
     * @param rides The ride array to being put into heap order
     * @param rideNum The current ride number
     */
    public void heapify(Ride[] rides, int rideNum) {


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
    public void upHeap() {
        // sets child ride index to match the index of the most recently added ride in the heap
        int indexChild = next;
        System.out.println("Index child = " + indexChild);
        // while child ride is not the root of the heap
        while (indexChild > 1) {
            // grabs parent ride index
            int indexParent = indexChild / 2;
            debug(String.format("Up-heaping with child index: %d and parent index: %d", indexChild, indexParent));
            // if the child rides timestamp is less than the parent rides timestamp
            if (rideArray[indexChild].compareTo(rideArray[indexParent]) == -1) {
                // swaps the child ride with the parent ride
                swap(indexChild, indexParent);

            } // end if

            debug(String.format("Setting child index %d to parent index %d", indexChild, indexParent));
            // sets child ride index to match parent ride index to continue up the heap for ordering
            indexChild = indexParent;

        } // end while

    } // end void

    public void swap(int indexChild, int indexParent) {
        // prints informative debug message to console if debugging mode is enabled
        debug(String.format("Child time: %s was less than parent time: %s, swapping ride %d with ride %d",
                rideArray[indexChild].time.toString(), rideArray[indexParent].time.toString(),
                rideArray[indexChild].id, rideArray[indexParent].id));

        // swaps child ride with parent ride
        Ride tempRide = rideArray[indexChild];
        rideArray[indexChild] = rideArray[indexParent];
        rideArray[indexParent] = tempRide;

    } // end void

    /**
     * Performs the down heap operation after the removal of a ride from the heap or for the heapify function
     */
    public void downHeap() {


    } // end void

    /**
     * ~ FOR DEVELOPER USE ONLY ~<br><br>
     *
     * Prints debug messages to console if debugging mode is enabled
     * @param msg
     */
    private void debug(String msg) {
        if (debugging)
            System.out.println(msg);

    } // end void


} // end class