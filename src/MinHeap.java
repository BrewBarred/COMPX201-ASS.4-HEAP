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
            next++;
            // performs up-heap to maintain heap order
            upHeap();

    } // end void

    /**
     * Removes the specific ride from the heap (maintaining heap order)
     * @param r The ride object being removed from the heap
     */
    public Ride remove(Ride r) {
            if (!hasRide(r)) {
                debug("Unable to remove the passed ride from the heap, ride was not found!");
                return null;

            } // end if

            debug("Attempting to remove ride... ID = " + r.id);

            // stores the index of the passed ride in the heap
            int indexRide = 0;
            // iterate through the heap to fetch the index of the passed 'Ride'
            for (int i = 0; i < rideArray.length; i++)
                if (rideArray[i] == r)
                    indexRide = i;

            debug("Successfully found ride at index: " + indexRide);

            // fetches the index of the last ride in the heap
            int indexLast = next - 1;
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

            debug(("Successfully removed ride at index \"" + (next) + "\""));

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
    private void upHeap() {
        // sets child ride index to match the index of the most recently added ride in the heap
        int indexChild = next - 1;

        // while child ride is not the root of the heap
        while (indexChild > 1) {
            // grabs parent ride index
            int indexParent = indexChild / 2;

            // if the child rides timestamp is less than the parent rides timestamp
            if (rideArray[indexChild].compareTo(rideArray[indexParent]) < 0)
                // swaps the child ride with the parent ride
                swap(indexChild, indexParent);
            else
                // else writes informative debug message to console if debugging mode is enabled
                debug(String.format("Child time: %s was greater than Parent time: %s, no swap was made",
                        rideArray[indexChild].time.toString(), rideArray[indexParent].time.toString()));

            debug(String.format("Setting child index %d to parent index %d", indexChild, indexParent));
            // sets child ride index to match parent ride index to continue up the heap for ordering
            indexChild = indexParent;

        } // end while

    } // end void

    /**
     * Performs the down heap operation after the removal of a ride from the heap or for the heapify function
     */
    private void downHeap() {
        // sets child ride index to match the index of the most recently added ride in the heap
       // int indexSmallest;
        int indexParent = 1;

        // while child ride is not the root of the heap
        while (indexParent < next) {
            // grabs child indices
            int indexLeft = indexParent * 2;
            int indexRight = indexParent * 2 + 1;
            int indexSmallest = indexLeft;

            System.out.println(String.format("Parent: %d, Left: %d, Right: %d, Smallest: %d, Next: %d", indexParent, indexLeft, indexRight, indexSmallest, next));

            System.out.println("[1]indexLeft < next && rideArray[indexLeft].compareTo(rideArray[indexParent]) < 0 = " + (indexLeft < next) + " && " + (rideArray[indexLeft].compareTo(rideArray[indexParent]) < 0));
            System.out.println(String.format("[1]Left = %s, Parent = %s, Result: %d", rideArray[indexLeft].time.toString(), rideArray[indexParent].time.toString(), rideArray[indexLeft].compareTo(rideArray[indexParent])));
            // if the left child is not null and less than the parent
            if (indexLeft < next && rideArray[indexLeft].compareTo(rideArray[indexParent]) < 0) {
                debug(String.format("Swapping left child with parent: Left = %d, Right = %d", indexLeft, indexParent));
                // updates the parent index to match the new parent
                indexSmallest = indexLeft;

            } // end if

            System.out.println("[2]indexRight < next && rideArray[indexRight].compareTo(rideArray[indexParent]) < 0 = " + (indexRight < next) + " && " + (rideArray[indexRight].compareTo(rideArray[indexParent]) < 0));
            System.out.println(String.format("[2]Right = %s, Parent = %s, Result: %d", rideArray[indexRight].time.toString(), rideArray[indexParent].time.toString(), rideArray[indexRight].compareTo(rideArray[indexParent])));
            if (indexRight < next && rideArray[indexRight].compareTo(rideArray[indexParent]) < 0) {
                debug(String.format("Swapping left child with parent: Left = %d, Right = %d", indexRight, indexParent));
                // updates the parent index to match the new parent
                indexSmallest = indexRight;

            } // end if

            // if the parent array isn't the smallest, swap parent with the smallest child
            if (rideArray[indexParent].compareTo(rideArray[indexSmallest]) > 0) {
                System.out.println(String.format("Index parent was not equal to the smallest index! Parent: %d, Smallest: %d", indexParent, indexSmallest));
                swap(indexSmallest, indexParent);
                indexParent = indexSmallest;
            }
            else {
                System.out.println(String.format("Index parent is now the smallest index! Breaking loop... Parent: %d, Smallest: %d", indexParent, indexSmallest));
                break;
            }

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

        debug("Error! Unable to find the passed ride in the heap... Ride ID: " + r.id);
        return false;

    } // end boolean

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
        debug(String.format("Child time: %s was less than parent time: %s", child.time.toString(), parent.time.toString()));

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
     * @param msg
     */
    private void debug(String msg) {
        if (DEBUGGING)
            System.out.println("[DEBUG] " + msg);

    } // end void


} // end class