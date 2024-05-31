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

// Test class: Ride.java
// Test section: compareTo()

    /**
     * Tests to ensure that compareTo(Ride) returns max integer value when checking if two invalid rides are compared against each other
     */
    @Test
    @DisplayName("Test compareTo(Ride): Compare two invalid rides, check max int")
    public void testCompareToInvalidRides() {

    }

    /**
     * Tests to ensure that compareTo(Ride) returns max integer value when checking if a valid ride is greater than an invalid ride
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride > invalid ride, check max int")
    public void testCompareToGreaterThanInvalid() {

    }

    /**
     * Tests to ensure that compareTo(Ride) returns a value greater than 0 when the extended ride is greater than the passed ride, based on timestamp values
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride > valid ride, check > 0")
    public void testCompareToGreaterThanValid() {

    }

    /**
     * Tests to ensure that compareTo(Ride) returns max integer value when checking if a valid ride is equal to an invalid ride
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride == invalid ride, check max int")
    public void testCompareToEqualToInvalid() {

    }

    /**
     * Tests to ensure that compareTo(Ride) returns 0 when the extended ride is equal to the passed ride, based on timestamp values
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride == valid ride, check 0")
    public void testCompareToEqualToValid() {

    }

    /**
     * Tests to ensure that compareTo(Ride) returns max integer value when checking if a valid ride is less than an invalid ride
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride < invalid ride, check max int")
    public void testCompareToLessThanInvalid() {

    }

    /**
     * Tests to ensure that compareTo(Ride) returns a value less than 0 when the extended ride is less than the passed ride, based on timestamp values
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride < valid ride, check < 0")
    public void testCompareToLessThanValid() {

    }

// Test section: toString()
    /**
     * Tests to ensure that toString() returns an error when attempting to print a null ride
     */
    @Test
    @DisplayName("Test toString(): Print null ride, check error")
    public void testToStringPrintNull() {

    }

    /**
     * Tests to ensure that toString() returns an error when attempting to print an invalid ride
     */
    @Test
    @DisplayName("Test toString(): Print invalid ride, check error")
    public void testToStringPrintInvalid() {

    }

    /**
     * Tests to ensure that toString() correctly overrides the default java implementation to return a fully formatted string containing the ride info
     */
    @Test
    @DisplayName("Test toString(): Print valid ride to console, check format")
    public void testToStringPrintValid() {
        // using the default ride1...

        // define the expected and actual output
        String expectedOutput = String.format("--- Ride %03d -------\n", "%03" + ride1.id + "d") +
                String.format("Time: %tT\n", ride1.time) +
                String.format("Start ID: %d\n", ride1.startId) +
                String.format("End ID: %d\n", ride1.endId) +
                "Passengers:\n" + ride1.fPassengers() +
                "--------------------";
        String actualOutput = ride1.toString();

        // check both outputs match
        assertEquals(expectedOutput, actualOutput);

    }

// Test class: MinHeap.java
// Test section: insert(Ride)

    /**
     * Tests to ensure that the insert(Ride[]) method returns an error when attempting to insert a null array
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

        // check outputs match
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride[]) method does not increment ride count when inserting an empty array
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

        // check list is still empty
        assertEquals(expectedCount, actualCount);
    }

    /**
     * Tests to ensure that insert(Ride[]) does not increment the index pointer when inserting an empty array
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

        // check index values remain unchanged
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

        // check the first passed ride is at index 1 of the heap
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

        // check if the first passed ride is at index 1 of the heap
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

// Test section: isEmpty(Ride[])

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when the passed array is null
     */
    @Test
    @DisplayName("Test isEmpty(Ride[]): Check if a null array is empty, check true")
    public void testIsEmptyArrayNull() {

    }

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when the passed array is empty
     */
    @Test
    @DisplayName("Test isEmpty(Ride[]): Check if an empty array is empty, check true")
    public void testIsEmptyArrayEmpty() {

    }

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when the passed array has a length of 0
     */
    @Test
    @DisplayName("Test isEmpty(Ride[]): Check if array w/size of 0 is empty, check true")
    public void testIsEmptyArraySize0() {

    }

    /**
     * Tests to ensure that isEmpty(Ride[]) returns true when all elements in the passed array are null
     */
    @Test
    @DisplayName("Test isEmpty(Ride[]): Check if array of nulls is empty, check true")
    public void testIsEmptyArrayNulls() {

    }

    /**
     * Tests to ensure that isEmpty(Ride[]) returns false when the passed array only contains a single ride
     */
    @Test
    @DisplayName("Test isEmpty(Ride[]): Check if array w/single ride is empty, check false")
    public void testIsEmptyArraySingle() {

    }

    /**
     * Tests to ensure that isEmpty(Ride[]) returns false when the passed array contains multiple rides
     */
    @Test
    @DisplayName("Test isEmpty(Ride[]): Check if array w/multiple rides is empty, check false")
    public void testIsEmptyArrayMulti() {

    }

// Test section: hasRide(Ride)

    /**
     * Tests to ensure that hasRide(Ride) returns false when the heap is null
     */
    @Test
    @DisplayName("Test hasRide(Ride): Check null heap for a ride, check false")
    public void testHasRideHeapNull() {

    }

    /**
     * Tests to ensure that hasRide(Ride) returns false when the heap is empty
     */
    @Test
    @DisplayName("Test hasRide(Ride): Check empty heap for a ride, check false")
    public void testHasRideHeapEmpty() {

    }

    /**
     * Tests to ensure that hasRide(Ride) returns false when the passed ride is null
     */
    @Test
    @DisplayName("Test hasRide(Ride): Check heap for null ride, check false")
    public void testHasRideNull() {

    }

    /**
     * Tests to ensure that hasRide(Ride) returns false when the passed ride is not contained within a heap w/single ride
     */
    @Test
    @DisplayName("Test hasRide(Ride): Check single-heap for un-contained ride, check false")
    public void testHasRideUncontainedSingle() {

    }

    /**
     * Tests to ensure that hasRide(Ride) returns true when the passed ride is contained with a heap size of 1
     */
    @Test
    @DisplayName("Test hasRide(Ride): Add ride to single heap and check for it, check true")
    public void testHasRideContainedHeapSingle() {

    }

    /**
     * Tests to ensure that hasRide(Ride) returns false when the passed ride is not contained within a heap w/multiple rides
     */
    @Test
    @DisplayName("Test hasRide(Ride): Check multi heap for un-contained ride, check false")
    public void testHasRideUncontainedMulti() {

    }

    /**
     * Tests to ensure that hasRide(Ride) returns true when the passed ride is contained within a heap w/multiple rides
     */
    @Test
    @DisplayName("Test hasRide(Ride): Check multi heap for contained ride, check true")
    public void testHasRideContainedMulti() {

    }

    /**
     * Tests to ensure that hasRide(Ride) returns false when the passed ride is not contained within a full heap
     */
    @Test
    @DisplayName("Test hasRide(Ride): Check full heap for un-contained ride, check false")
    public void testHasRideUncontainedFull() {

    }

    /**
     * Tests to ensure that hasRide(Ride) returns true when the passed ride is contained within a full heap
     */
    @Test
    @DisplayName("Test hasRide(Ride): Check full heap for contained ride, check true")
    public void testHasRideContainedFull() {

    }

// Test section: heapify(int, Ride[])

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a ride number that is less than 1
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/ride num < 1, check error")
    public void testHeapifyRideNumTooLow() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a ride number greater than the array length
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/ride num >= array length")
    public void testHeapifyRideNumTooHigh() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) returns a null Ride[] parameter since it is already heapified
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/null array, check unchanged")
    public void testHeapifyArrayNull() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) returns a null Ride[] parameter since it is already heapified
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/array of nulls, check unchanged")
    public void testHeapifyArrayNulls() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) returns a passed ride w/array size of 0
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/array size of 0, check unchanged")
    public void testHeapifyArraySize0() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a passed ride w/array size of 0
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/array size of 0, check unchanged")
    public void testHeapifyArraySize1() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an empty array
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify empty array, check unchanged")
    public void testHeapifyArrayEmpty() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a passed ride array size that is greater than the max. capacity value
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): ")
    public void testHeapifyArrayTooBig() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) correctly coverts a 0-based ride array into a 1-based array and heapifies
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify a zero-based array, check root")
    public void testHeapifyBase0() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a 1-based ride array
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify a one-based array, check root")
    public void testHeapifyBase1() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an array containing random null elements
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify array w/random nulls, check last leaf")
    public void testHeapifyRandNulls() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an array with a single value when the heap is empty
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify single-array, check root")
    public void testHeapifyArraySingle() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an array with multiple values when the heap is empty
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify multi-array, check root")
    public void testHeapifyArrayMulti() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a full array when the heap is empty
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify full array w/empty heap, check root")
    public void testHeapifyArrayFull() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a full array when the heap already has a single ride
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify full array w/single heap, check last leaf")
    public void testHeapifyArrayFullHeapSingle() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a full array when the heap already has multiple rides
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify full array w/multi heap")
    public void testHeapifyArrayFullHeapMulti() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a full array when the heap is already full
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify full array w/full heap")
    public void testHeapifyArrayFullHeapFull() {

    }

    /**
     * Tests to ensure that heapify(int, Ride[]) updates the default heap with the heapified array
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/valid params, check heap")
    public void testHeapifyHeapUpdate() {

    }

// Test section: peek()

    /**
     * Tests to ensure that the peek() method returns null when the heap is null
     */
    @Test
    @DisplayName("Test peek(): Peek at null heap, check null")
    public void testPeekHeapNull() {

    }

    /**
     * Tests to ensure that the peek() method returns null when peeking at an empty heap
     */
    @Test
    @DisplayName("Test peek(): Peek at empty heap, check null")
    public void testPeekHeapEmpty() {

    }

    /**
     * Tests to ensure that the peek() method returns the root value when peeking at a heap with a single value
     */
    @Test
    @DisplayName("Test peek(): Peek at single-heap, check root")
    public void testPeekHeapSingle() {

    }

    /**
     * Tests to ensure that the peek() method returns the root value when peeking at a heap with multiple values
     */
    @Test
    @DisplayName("Test peek(): Peek at multi-heap, check root")
    public void testPeekHeapMulti() {

    }

    /**
     * Tests to ensure that the peek() method returns the root value when peeking at a full heap
     */
    @Test
    @DisplayName("Test peek(): Peek at full heap, check root")
    public void testPeekHeapFull() {

    }

    /**
     * Tests to ensure that the peek() method does not remove the value after peeking at it
     */
    @Test
    @DisplayName("Test peek(): Peek after a valid peek is performed, check root")
    public void testPeekAfterPeek() {

    }

// Test section: dump()


    /**
     * Tests to ensure that the dump() method returns an error when attempting to dump a null heap
     */
    @Test
    @DisplayName("Test dump(): Attempt to dump a null heap, check error")
    public void testDumpHeapNull() {

    }

    /**
     * Tests to ensure that the dump() method returns an error when attempting to dump an empty heap
     */
    @Test
    @DisplayName("Test dump(): Attempt to dump an empty heap, check error")
    public void testDumpHeapEmpty() {

    }

    /**
     * Tests to ensure that the dump() method correctly dumps a heap w/single ride
     */
    @Test
    @DisplayName("Test dump(): Dump a single-heap, check output")
    public void testDumpHeapSingle() {

    }

    /**
     * Tests to ensure that the dump() method correctly dumps a heap w/multiple rides
     */
    @Test
    @DisplayName("Test dump(): Dump a multi-heap, check output")
    public void testDumpHeapMulti() {

    }

// Test sectionL sort()

    /**
     * Tests to ensure that the sort() method returns an error when attempting to sort a null heap
     */
    @Test
    @DisplayName("Test sort(): Attempt to sort a null heap, check error")
    public void testSortHeapNull() {

    }

    /**
     * Tests to ensure that the sort() method returns an unchanged heap when attempting to sort an empty heap
     */
    @Test
    @DisplayName("Test sort(): Sort an empty heap, check unchanged")
    public void testSortHeapEmpty() {

    }

    /**
     * Tests to ensure that the sort() method correctly sorts a heap w/single ride
     */
    @Test
    @DisplayName("Test sort(): Sort a single-heap, check output")
    public void testSortHeapSingle() {

    }

    /**
     * Tests to ensure that the sort() method correctly sorts a heap w/multiple rides
     */
    @Test
    @DisplayName("Test sort(): Sort a multi-heap, check output")
    public void testSortHeapMulti() {

    }

    /**
     * Tests to ensure that the sort() method correctly sorts a full heap
     */
    @Test
    @DisplayName("Test sort(): Sort a full heap, check output")
    public void testSortHeapFull() {

    }

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
