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
    public final int MAX_CAPACITY = 20;
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
     * Adds the specified ride to the default heap (maintaining heap order)
     * @param r The ride object being added to the default heap array
     */
    public void insert(Ride r) {
        // calls the insert method passing it the default heap for insertion
        insert(r, rides, next);

    } // end void

    /**
     * Adds each ride in the passed ride array to the default heap (maintaining heap order)
     * @param arrayInserting The ride array containing the rides that are being inserted into the receiving heap
     */
    public void insert(Ride[] arrayInserting) {
        // calls insert method passing it the default heap for insertion
        insert(arrayInserting, rides);

    } // end void

    /**
     * Adds each ride in the passed ride array to the passed heap (maintaining heap order)
     * @param arrayInserting The ride array containing the rides that are being inserted into the receiving heap
     * @param arrayReceiving The ride array to insert each ride object into
     */
    public void insert(Ride[] arrayInserting, Ride[] arrayReceiving) {
        // gets the next available slot in the receiving array
        int next = getNext(arrayReceiving);
        // loops through the passed array and inserts any rides that are contained
        for(Ride ride : arrayInserting) {
            insert(ride, arrayReceiving, next);
            next++;

        } // end for

    } // end void

    /**
     * Adds the specified ride to the heap (maintaining heap order)
     * @param r The ride object being added to the passed ride array
     * @param rideArray The ride array that the passed ride object will be inserted into
     * @param next The index of the next available slot in the array
     */
    public void insert(Ride r, Ride[] rideArray, int next) {
        if (hasRide(r, rideArray)) {
            debug(String.format("Unable to insert ride! Ride was already contained in the array... Ride ID: %d, Ride Time: %s", r.id, r.getTime()), "insert");
            return;

        } // end if

        if (next >= rideArray.length) {
            debug(String.format("Unable to insert the passed ride! Maximum ride limit has been reached... Ride ID: %d, Ride Time: %s, Max capacity: %d", r.id, r.getTime(), MAX_CAPACITY), "insert");
            return;

        } // end if

        debug(String.format("Attempting to insert ride... Ride ID: %d, Ride Time: %s", r.id, r.getTime()), "insert");

        // sets the next spare slot in the heap to the passed 'Ride' object
        rideArray[next] = r;
        // performs up-heap to maintain heap order
        upHeap(rideArray);

        // test
        printer.printTime(rides, next);

        // if the passed array is the default array, increment next counter
        if (rideArray == rides)
            this.next++;

    } // end void

    /**
     * Checks if the passed ride array contains any ride objects
     * @return A boolean value that is true if this heap contains any rides, else returns false.
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
     * Fetches the index of the first available slot in the passed ride array
     * @param rideArray The ride array being checked for availability
     * @return An integer value denoting the index of the next available slot if any exists, else returns -1
     */
    private int getNext(Ride[] rideArray) {
        // iterates through the passed ride array and returns its first available slot
        return next = getRides(rideArray) + 1;

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
        int indexChild = getRides(rideArray);

        while (indexChild > 1) {
            int indexParent = indexChild / 2;

            if (isSmaller(indexChild, indexParent, rideArray))
                swap(indexChild, indexParent, rideArray);

            indexChild = indexParent;

        } // end while

    } // end void

    /**
     * Swaps the elements at index1 and index2 with each other in the passed ride array
     * @param index1 The index of the element being swapped with that at index2
     * @param index2 The index of the element being swapped with that at index1
     * @param rideArray The array in which the swapped elements are contained
     */
    private void swap(int index1, int index2, Ride[] rideArray) {
        if (index1 >= rideArray.length && index2 >= rideArray.length) {
            debug(String.format("Unable to swap values! Index was out of bounds... Index1 = %d, Index2 = %d, RideArray Length = %d", index1, index2, rideArray.length), "swap");
            return;

        } // end if

        // creates references to the indexed nodes
        Ride child = rideArray[index1];
        Ride parent = rideArray[index2];

        // test
        System.out.println(String.format("Swapping values at index %d and %d", index1, index2));
        printer.printTime(rideArray, index1, index2);

        // swaps child with parent
        Ride tempChild = child;
        rideArray[index1] = parent;
        rideArray[index2] = tempChild;

        // test
        printer.printTime(rideArray, index1, index2);

    } // end void

    private void isBigger() {

    }

    /**
     * Compares the two ride objects in the passed ride array to see if the timestamp for index1 is smaller than index2
     * @param index1 The index of the first ride object
     * @param index2 The index of the second ride object
     * @param rideArray The ride array containing the compared values
     * @return A boolean value that is true if the timestamp at index1 is smaller than index2, else returns false.
     */
    private boolean isSmaller(int index1, int index2, Ride[] rideArray) {
        int next = getNext(rideArray);

        if (index1 >= next || index2 >= next) {
            debug(String.format("Unable to compare timestamps! Index out of bounds error: Index1 = %d, Index2 = %d, RideArray Length = %d, Next = %d", index1, index2, rideArray.length, next), "isSmaller");
            return false;

        } // end if

        // fetches the ride objects being compared
        Ride ride1 = rideArray[index1];
        Ride ride2 = rideArray[index2];

        return ride1.compareTo(ride2) == -1;

    } // end bool

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