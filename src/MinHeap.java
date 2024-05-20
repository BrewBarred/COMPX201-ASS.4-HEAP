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
        if (isEqual(rideArray, rides))
            this.next++;

    } // end void

    /**
     * Removes the passed ride from the default heap (maintaining heap order)
     * @param r The ride object being removed from the heap
     * @return The removed ride object if it was found in the heap, else returns null
     */
    public Ride remove(Ride r) {
        return remove(r, rides);

    } // end ride

    /**
     * Removes the passed ride from the specified heap (maintaining heap order)
     * @param r The ride object being removed from the heap
     * @param rideArray The ride array performing the removal
     */
    public Ride remove(Ride r, Ride[] rideArray) {
        // test
        debug(String.format("Attempting to remove ride... RideID = %d, RideTimestamp = %s", r.id, r.getTime()), "remove");
        if (r == null)
            return null;

        // return early if the passed ride is not contained in the heap
        if (!hasRide(r, rideArray)) {
            // test
            debug("Unable to remove the passed ride from the heap! Ride was not found... ", "remove");
            return null;

        } // end if

        // fetches the heap-index of the passed ride
        int indexRide = getIndex(r, rideArray);
        // fetches the index of the last ride in the heap
        int indexLast = getRideCount(rideArray);

        // if the last element is not the root element
        if (indexRide != indexLast)
            // swap the last element with the first
            swap(indexRide, indexLast, rideArray);

        Ride removedRide = rideArray[indexLast];
        // delete the last element
        rideArray[indexLast] = null;
        // down heap to maintain heap order
        downHeap(rideArray);

        // if the passed array is the default heap, decrement next counter
        if (isEqual(rideArray, rides))
            next--;

        debug(("Successfully removed the passed ride from the heap!"),"remove");

        return removedRide;

    } // end void

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
            //System.out.println("Passed ride: \n" + rideArray[i]);
            if (rideArray[i] == r) {
                return i;

            } // end if

        } // end for

        return -1;

    } // end int

    /**
     * Checks if the passed ride array contains any rides or not
     * @return A boolean value that is true if the passed heap contains any rides, else returns false.
     */
    public boolean isEmpty(Ride[] rideArray) {
        // if the passed array is the default heap
        if (isEqual(rideArray, rides))
            // return true if the root is empty
            return next == 1;

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
        if (isEqual(rideArray, rides))
            return next -1;

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
        // if the passed array matches the default heap, returns next value,
        // else iterates through the passed ride array and returns its first available slot (i.e., ride count + 1)
        return isEqual(rideArray, rides) ? next : getRideCount(rideArray) + 1;

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

    private void downHeap(Ride[] rideArray) {
        System.out.println("\n\n\n\nDownheaping...\n\n");
        int next = getNext(rideArray);
        int indexSmallest = -1;
        int indexParent = 1;
        int i = 0;

        while (indexParent < next) {

            System.out.println("Loop count " + i);
            int indexLeftChild = indexParent * 2;
            int indexRightChild = indexLeftChild + 1;

            // checks if value at left child is less than right child
            if (isSmaller(indexRightChild, indexLeftChild, rideArray)) {
                System.out.println("Left Right SWAP!#!@$#@$#!@%");
                swap(indexLeftChild, indexRightChild, rideArray);

            } // end if

            // fetches the index of the child with the smallest timestamp
            indexSmallest = getSmaller(indexLeftChild, indexRightChild, rideArray);

            // if the parent value is not the smallest, swap and continue, else break the loop
            if (isSmaller(indexSmallest, indexParent, rideArray)) {
                swap(indexSmallest, indexParent, rideArray);
                indexParent = indexSmallest;

            } else {
                debug("Downheap Complete! Parent time: " + rideArray[indexParent].time, "Downheap" );
                break;
            }
            i++;

        } // end while


        System.out.println("REPRINTAXSFSADFSDAFDSAFSDAF");
        printer.printTime(rideArray);


    } // end void

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
     * @return A boolean value that is true if the timestamp at index1 is smaller than that at index2, else returns false.
     */
    private boolean isSmaller(int index1, int index2, Ride[] rideArray) {
        int indexLastRide = getRideCount(rideArray);

        if (Math.max(index1, index2) > indexLastRide) {
            debug(String.format("Unable to compare timestamps! Index out of bounds error: Index1 = %d, Index2 = %d, RideArray Length = %d, Next = %d", index1, index2, rideArray.length, next), "isSmaller");
            return false;

        } // end if

        // fetches the ride objects being compared
        Ride ride1 = rideArray[index1];
        Ride ride2 = rideArray[index2];

        return ride1.compareTo(ride2) == -1;

    } // end bool

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
     * Uses the java comparable to compare two ride arrays against each other to see if they are the same
     * @param rides1 The first ride array to compare
     * @param rides2 The second ride array to compare
     * @return A boolean value that is true if the passed arrays match in length
     * and contain the same elements at the same indices, else returns false
     */
    public boolean isEqual(Ride[] rides1, Ride[] rides2) {
        // arrays of different lengths cannot be equal - return false
        if (rides1.length != rides2.length)
            return false;
        // arrays with the same length and no rides must be identical - return true
        if (rides1.length == 0)
            return true;

        // each element in both arrays
        for(int i = 1; i < rides1.length; i++) {
            // if we hit null values in both lists at the same time, lists are equal
            if (rides1[i] == null || rides2[i] == null)
                // returns true if both elements are null, else returns false
                return rides1[i] == rides2[i];

            // if the elements at index 'i' don't match - return false
            if (rides1[i].compareTo(rides2[i]) != 0)
                return false;

        } // end for

       return true;

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