import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Time;

/**
 * Test suite for the MinHeap class, used to test each function is operating correctly and producing the expected outputs
 */
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MinHeapTest {
    /**
     * Stores console output to help with testing
     */
    private final ByteArrayOutputStream stream = new ByteArrayOutputStream();
    /**
     * Creates an instance of the MinHeap class in order to validate its implementation
     */
    private MinHeap heap;
    /**
     * Ride array to hold the default rides
     */
    private Ride[] defaultRides;
    /**
     * Ride array that stores a custom selection of rides
     */
    private Ride[] selectedRides;
    /**
     * Mock ride: Id = 1, Time = 01:00:00, Passengers = "Passenger 1", startId = 1, endId = 2
     */
    private Ride ride1;
    /**
     * Mock ride: Id = 2, Time = 02:00:00, Passengers = "Passenger 2", startId = 2, endId = 3
     */
    private Ride ride2;
    /**
     * Mock ride: Id = 3, Time = 03:00:00, Passengers = "Passenger 3", startId = 3, endId = 4
     */
    private Ride ride3;
    /**
     * Mock ride: Id = 4, Time = 04:00:00, Passengers = "Passenger 4", startId = 4, endId = 5
     */
    private Ride ride4;

    /**
     * Re-initializes the minimum heap being tested before running each test and
     * creates some of the common ride objects required for this test suite
     */
    @BeforeEach
    public void reset() {
        // captures any console output
        System.setOut(new PrintStream(stream));

        // resets the heap
        heap = new MinHeap();

        // initializes some common rides for different tests
        ride1 = new Ride(1, Time.valueOf("01:00:00"), new String[] {"Passenger 1"}, 1, 2);
        ride2 = new Ride(2, Time.valueOf("02:00:00"), new String[] {"Passenger 2"}, 2, 3);
        ride3 = new Ride(3, Time.valueOf("03:00:00"), new String[] {"Passenger 3"}, 3, 4);
        ride4 = new Ride(4, Time.valueOf("04:00:00"), new String[] {"Passenger 4"}, 4, 5);

        // adds the default rides to the default ride array
        defaultRides = new Ride[] {ride1, ride2, ride3, ride4};
    }

// Test section: insert(Ride)

    /**
     * Tests to ensure that attempting to insert a null array will trigger an error message
     */
    @Test
    @Order(1)
    @DisplayName("Test insert(Ride[]): Insert an array that is null")
    public void testInsertArrayNull() {
        // arrange the test with an array set to null
        Ride[] rides = null;

        // attempt to insert the null array
        heap.insert(rides);
        String expectedOutput = "Unable to add ride array! The passed ride array was null..";
        String actualOutput = getStream();

        // compare expected output against actual output
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that inserting an empty array will not add any rides or increment the ride count
     */
    @Test
    @Order(2)
    @DisplayName("Test insert(Ride[]): Insert empty array, check ride count is unchanged")
    public void testInsertArrayEmptyCount() {
        // create an empty array to test the insert array method
        Ride[] rides = new Ride[4];

        // insert the empty array
        heap.insert(rides);
        // set the expected and fetch the actual index value
        int expectedCount = 0;
        int actualCount = heap.next - 1;

        // list should still be empty
        assertEquals(expectedCount, actualCount);
    }

    /**
     * Tests to ensure that inserting an empty array will not increment the index pointer (next)
     */
    @Test
    @Order(3)
    @DisplayName("Test insert(Ride[]): Insert empty array, check index pointer is unchanged")
    public void testInsertArrayEmptyIndex() {
        // arrange the test with an empty array to add
        Ride[] rides = new Ride[4];

        // get current pointer location as this shouldn't change
        int expectedIndex = heap.next;
        // insert the empty array
        heap.insert(rides);
        // pointer should not have moved
        int actualIndex = heap.next;

        // assert that index values remain unchanged
        assertEquals(expectedIndex, actualIndex);
    }

    /**
     * Tests to ensure that the insert(Ride[]) method can insert a 1-based array
     */
    @Test
    @Order(4)
    @DisplayName("Test insert(Ride[]): Insert 1-based array, check root is index 1")
    public void testInsertArrayOneBased() {
        // creates a 1-based array
        Ride[] rides = new Ride[] {null, ride1, ride2, ride3, ride4};

        // inserts the 1-based array
        heap.insert(rides);
        // stores the expected value and the actual value of the first ride in the list
        Ride expectedRide = ride1;
        Ride actualRide = heap.rides[1];

        // ensure the first passed ride is at index 1 of the heap
        assertEquals(expectedRide, actualRide);
    }

    /**
     * Tests to ensure that the insert(Ride[]) method can insert a 0-based array
     */
    @Test
    @Order(5)
    @DisplayName("Test insert(Ride[]): Insert 0-based array, check root is index 1")
    public void testInsertArrayZeroBased() {
        // stores 4 rides starting from index 0 into the heap
        heap.insert(defaultRides);

        // checks index of first ride (ride 1)
        Ride expectedRide = ride1;
        Ride actualRide = heap.rides[1];

        // check if the 1st index is equal to the first ride
        assertEquals(expectedRide, actualRide);
    }

    /**
     * Tests to ensure that the insert(Ride[]) method can take an array with only null values
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert array of nulls, check error")
    public void testInsertArrayOfNulls() {
        // create a ride array filled with null ride objects
        Ride[] rides = {null, null, null, null, null};

        // attempt to insert the null array
        heap.insert(rides);
        // define the expected output
        String expectedOutput = "Unable to add ride array! The passed ride array was empty...";
        // fetch the actual output from the stream
        String actualOutput = getStream();

        // check the correct error message is thrown
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride[]) method will reject an array size of 0
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert array size of 0, check error")
    public void testInsertArraySize0() {
        // create a ride array with a size of 0
        Ride[] rideArray = new Ride[0];

        // attempt to insert the size 0 array
        heap.insert(rideArray);
        String expectedOutput = "Unable to add ride array! The passed ride array length was an invalid size...";
        String actualOutput = getStream();

        // check the correct error message is thrown
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride[]) method will reject an array size greater than the current max. capacity<br>
     * Note: Max capacity is currently fixed at 20 rides, which is an array size of 21 to include the unused index 0
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert array w/size of 22 > 21 (max. capacity), check error")
    public void testInsertArraySize22() {
        // create a ride array that is too big
        Ride[] rides = new Ride[22];

        // attempt to insert the array
        heap.insert(rides);
        // define the expected output
        String expectedOutput = "Unable to add ride array! The passed ride array length was an invalid size...";
        String actualOutput = getStream();

        // check the correct error message is thrown
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride[]) method can handle null elements randomly scattered through a passed array
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert random null elements, check index of last ride")
    public void testInsertArrayRandNulls() {
        // create an array with null objects in random places
        Ride[] rides = {null, ride1, null, null, ride2, null, ride3, null, null, null, ride4};

        // attempt to insert the passed array
        heap.insert(rides);
        // compare output of that in index 4 with ride 4 as they should match
        String expectedOutput = ride4.toString();
        String actualOutput = heap.rides[4].toString();

        // check last ride and ride at index 4 of the heap match each other
        assertEquals(expectedOutput, actualOutput);
    }

// Test section: insert(Ride)

    /**
     * Tests to ensure that the insert(Ride) method doesn't move index pointer for null insertions
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a null ride, check pointer")
    public void testInsertNullPointer() {

    }

    /**
     * Tests to ensure that the insert(Ride) method moves pointer for non-null rides
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a non-null ride, check pointer")
    public void testInsertNonNullPointer() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects duplicate rides
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a dupe ride, check error")
    public void testInsertRideDupe() {

    }

    /**
     * Tests to ensure that the insert(Ride) method does not increment next pointer after rejecting a duplicate ride
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a dupe ride, check pointer is unchanged")
    public void testInsertRideDupePointer() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects duplicate ride id's
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride with duplicate ride id, check error")
    public void testInsertRideDupeId() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a ride with a negative value for a ride id
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with id < 1, check error")
    public void testInsertRideIdBelowOne() {

    }

    /**
     * Tests to ensure that the insert(Ride) method accepts a positive integer above 0 as an id
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with id > 0, check true")
    public void testInsertRideIdAboveZero() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects empty time strings
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with empty time string, check error")
    public void testInsertRideTimeEmpty() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects invalid time strings
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with an invalid time format, check error")
    public void testInsertRideTimeInvalid() {
        
    }

    /**
     * Tests to ensure that the insert(Ride) method accepts a ride object with a valid timestamp
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with a valid time format, check true")
    public void testInsertRideTimeValid() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a null passenger array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with a null passenger array, check error")
    public void testInsertRidePassNull() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a ride with an empty passenger string
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride w/empty passenger string, check error")
    public void testInsertRidePassEmpty() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a ride with only whitespace for a passenger name
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride w/blank passenger string, check error")
    public void testInsertRidePassBlank() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects an empty passenger array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/empty pass array, check error")
    public void testInsertRidePassArrayEmpty() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a ride array with too many passengers
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/too many pass, check error")
    public void testInsertRidePassArrayTooMany() {

    }

    /**
     * Tests to ensure that the insert(Ride) method accepts a single passenger passed in an array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/single pass array, check true")
    public void testInsertRidePassArraySingle() {

    }

    /**
     * Tests to ensure that the insert(Ride) method accepts multiple passengers passed in an array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/multi pass array, check true")
    public void testInsertRidePassArrayMulti() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects multiple passengers passed as a string separated by commas
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/multi comma sep. string, check error")
    public void testInsertRidePassTooMany() {

    }

    /**
     * Tests to ensure that the insert(Ride) method accepts a single passenger passed as a string
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/single pass string, check true")
    public void testInsertRidePassSingle() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects rides with a start location value less than or equal to 0
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/start id <= 0, check error")
    public void testInsertRideStartInvalid() {

    }

    /**
     * Tests to ensure that the insert(Ride) method rejects rides with an end location value less than or equal to 0
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/end id <= 0, check error")
    public void testInsertRideEndInvalid() {

    }

    /**
     * Tests to ensure that the insert(Ride) method accepts rides with a start id and end id that is greater than 0
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/valid location ids")
    public void testInsertRideLocationValid() {

    }

    /**
     * Tests to ensure that the insert(Ride) method fails to optimize rides with a time difference that is greater than 10 minutes
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride w/time diff > 10, check heap length")
    public void testInsertRideInvalidTime() {

    }

    /**
     * Tests to ensure that the insert(Ride) method fails to optimize rides with a time difference of 10 minutes or less, valid end id, but an invalid start id
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride w/invalid start id, check heap length")
    public void testInsertRideInvalidStart() {

    }

    /**
     * Tests to ensure that the insert(Ride) method fails to optimize rides with a time difference of 10 minutes or less, valid start id, but an invalid end id
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride w/invalid end id")
    public void testInsertRideInvalidEnd() {

    }

    /**
     * Tests to ensure that the insert(Ride) method fails to optimize rides with a time difference of 10 minutes or less, but invalid start and end ids
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/invalid start and end ids, check insertion")
    public void testInsertRideInvalidLocation() {

    }

    /**
     * Tests to ensure that the insert(Ride) method fails to optimize rides with an invalid time but valid location id's
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride w/invalid time AND location, check insertion")
    public void testInsertRideInvalidTimeLocation() {

    }

    /**
     * Tests to ensure that the insert(Ride) method successfully optimizes a pair of rides in the array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a pair of optimizable rides, check insertion")
    public void testInsertRideOptimizePair() {

    }

    /**
     * Tests to ensure that the insert(Ride) method successfully optimizes multiple rides w/valid number of passengers
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a few optimizable rides, check insertion")
    public void testInsertRideOptimizeMulti() {

    }

    /**
     * Tests to ensure that the insert(Ride) method successfully handles multiple optimizable rides w/too many total passengers
     * (Each ride will be added until adding another ride would break the passenger threshold, the remaining rides will be separately added and unchanged)
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a few optimizable rides w/too many total pass, check insertion")
    public void testInsertRideOptimizeTooManyPass() {

    }

    /**
     * Tests to ensure that the insert(Ride) method successfully up-heaps after an optimizable insertion
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a optimizable ride, check up-heap")
    public void testInsertRideOptimizeUpHeap() {

    }

    /**
     * Tests to ensure that the insert(Ride) method successfully inserts a valid but non-optimizable ride individually
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a non-optimizable ride, check insertion")
    public void testInsertRideNotOptimizable() {

    }

    /**
     * Tests to ensure that the insert(Ride) method successfully up-heaps after a non-optimizable insertion
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a non-optimizable ride, check up-heap")
    public void testInsertRideNotOptimizableUpHeap() {

    }

    /**
     * Tests to ensure that the insert(Ride) method successfully up-heaps the highest of 3 values to the correct indices in the heap
     */
    @Test
    @DisplayName("Test insert(Ride): Insert 3 values in staggered order, check index")
    public void testInsertRide() {

    }

// Test section: remove(Ride)

    /**
     * Tests to ensure that remove(Ride) a null parameter
     */
    @Test
    @DisplayName("Test remove(Ride): Attempt to remove null, check boolean result")
    public void testRemoveRideNull() {

    }

    /**
     * Tests to ensure that remove(Ride) returns false when the heap is null
     */
    @Test
    @DisplayName("Test remove(Ride): Remove a ride from a null heap, check false")
    public void testRemoveRideNullHeap() {

    }

    /**
     * Tests to ensure that remove(Ride) returns false when the passed ride is not contained in the heap
     */
    @Test
    @DisplayName("Test remove(Ride): Remove an un-contained ride, check false")
    public void testRemoveRideNotContained() {

    }

    /**
     * Tests to ensure that remove(Ride) targets the correct index
     */
    @Test
    @DisplayName("Test remove(Ride): Remove contained ride, check value at old index")
    public void testRemoveRideIndex() {

    }

    /**
     * Tests to ensure that remove(Ride) decrements the next index pointer
     */
    @Test
    @DisplayName("Test remove(Ride): Remove contained ride, check pointer")
    public void testRemoveRidePointer() {

    }

    /**
     * Tests to ensure that remove(Ride) can remove the root node
     */
    @Test
    @DisplayName("Test remove(Ride): Remove root node, check root")
    public void testRemoveRideRoot() {
        // set heap to match pre-constructed default array

    }

    /**
     * Tests to ensure that remove(Ride) can remove the last leaf node
     */
    @Test
    @DisplayName("Test remove(Ride): Remove last leaf node, check last leaf node")
    public void testRemoveRideLastLeaf() {
        // set heap to match pre-constructed default array

    }

    /**
     * Tests to ensure that remove(Ride) can remove a value from the middle of the heap
     */
    @Test
    @DisplayName("Test remove(Ride): Remove a value in the middle of the heap, check index")
    public void testRemoveRideMiddle() {
        // set heap to match pre-constructed default array

    }

    /**
     * Tests to ensure that remove(Ride) down-heaps after a removal
     */
    @Test
    @DisplayName("Test remove(Ride): Remove a ride, check heap order")
    public void testRemoveRide() {
        // set heap to match pre-constructed default array

        // remove a value from the heap
        // define expected output

    }

// Test section: isEmpty()

    /**
     * Tests to ensure that isEmpty() returns true when the heap is null
     */
    @Test
    @DisplayName("Test isEmpty(): Check null heap is empty, check true")
    public void testIsEmptyHeapNull() {

    }

    /**
     * Tests to ensure that isEmpty() returns true when the heap is empty
     */
    @Test
    @DisplayName("Test isEmpty(): Check empty heap is empty, check true")
    public void testIsEmptyHeapEmpty() {

    }

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when the heap has a length of 0
     */
    @Test
    @DisplayName("Test isEmpty(): Check length 0 array is empty, check true")
    public void testIsEmptyHeapSize0() {

    }

    /**
     * Tests to ensure that isEmpty() returns false when the heap only contains one ride
     */
    @Test
    @DisplayName("Test isEmpty(): ")
    public void testIsEmpty() {

    }

    /**
     * Tests to ensure that isEmpty() returns false when the heap is full
     */
    @Test
    @DisplayName("Test isEmpty(): ")
    public void testIsEmpty() {

    }

// Test section: isEmpty(Ride[])

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when the passed array is null
     */
    @Test
    @DisplayName("Test isEmpty(Ride[]): ")
    public void testIsEmpty() {

    }

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when the passed array is empty
     */

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when the passed array has a length of 0
     */

    /**
     * Tests to ensure that isEmpty(Ride[]) returns false when the heap only contains one ride
     */

    /**
     * Tests to ensure that isEmpty(Ride[]) returns false when the heap is full
     */

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when all elements in the passed array are null
     */

// Test section: hasRide(Ride)

    /**
     * Tests to ensure that hasRide(Ride) returns false when the passed ride is null
     */

    /**
     * Tests to ensure that hasRide(Ride) returns false when the heap is null
     */

    /**
     * Tests to ensure that hasRide(Ride) returns false when the heap is empty
     */

    /**
     * Tests to ensure that hasRide(Ride) returns false when the passed ride is not contained within the heap
     */

    /**
     * Tests to ensure that hasRide(Ride) returns true when the passed ride is contained with a heap size of 1
     */

    /**
     * Tests to ensure that hasRide(Ride) returns true when the passed ride is contained within a full heap
     */

// Test section: heapify(int, Ride[])

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a ride number that is less than 1
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a ride number greater than the array length
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a null Ride[] parameter
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a passed ride array size of 0
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a passed ride array size that is greater than the max. capacity value
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) correctly coverts a 0-based ride array into a 1-based array and heapifies
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a 1-based ride array
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an array containing random null elements
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an empty array
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an array with a single value
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an array with multiple values
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a full array when the heap is not empty
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a full array when the heap is empty
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a valid ride number and ride object
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) will still heapify with an invalid ride number passed
     */

    /**
     * Tests to ensure that heapify(int, Ride[]) will still heapify with a valid but unexpected ride number passed
     */

    /**
     * Tests to ensure that the default heap is updated with the heapified array
     */

// Test section: peek()

    /**
     * Tests to ensure that the peek() method returns null when the heap is null
     */

    /**
     * Tests to ensure that the peek() method returns null when peeking at an empty heap
     */

    /**
     * Tests to ensure that the peek() method returns the root value when peeking at a heap with a single value
     */

    /**
     * Tests to ensure that the peek() method returns the root value when peeking at a heap with multiple values
     */

// Test section:






    /**
     * Reads the console, trims the string of new lines and whitespace and returns the output message as a string
     */
    private String getStream() {
        return getStreamUntrimmed().trim();
    }

    /**
     * Reads the console and returns the output message without any trimming
     */
    private String getStreamUntrimmed() {
        return stream.toString();
    }

}
