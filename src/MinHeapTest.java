import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Time;
import java.util.Arrays;

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
     * Mock ride (invalid): Id = 0, Time = 1:1:1, Passengers = Bic Derek, startId = 0, endId = 0"
     */
    private Ride invalidRide1;
    /**
     * Mock ride (invalid): Id = 420, Time = 1:1:1, Passengers = Ben Jermayne, startId = -1, endId = 1"
     */
    private Ride invalidRide2;
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

        invalidRide1 = new Ride(0, Time.valueOf("1:1:1"), "Bic Derek", 0, 0);
        invalidRide2 = new Ride(420, Time.valueOf("1:1:1"), "Ben Jermayne", -1, 1);

        // initializes some common rides for different tests
        ride1 = new Ride(1, Time.valueOf("01:00:00"), new String[] {"Passenger 1"}, 1, 2);
        ride2 = new Ride(2, Time.valueOf("02:00:00"), new String[] {"Passenger 2"}, 2, 3);
        ride3 = new Ride(3, Time.valueOf("03:00:00"), new String[] {"Passenger 3"}, 3, 4);
        ride4 = new Ride(4, Time.valueOf("04:00:00"), new String[] {"Passenger 4"}, 4, 5);

        // adds the default rides to the default ride array
        defaultRides = new Ride[] {ride1, ride2, ride3, ride4};

        // enables debug mode to retrieve debug error messages
        heap.toggleDebug();
    }

    /**
     * Disables debugging mode after each test to ensure it is not enabled once the test has finished running
     */
    @AfterEach
    public void disableDebug() {
        // disables debug mode to revert min heap class to default
        heap.toggleDebug();
    }

// Test class: Ride.java
// Test section: compareTo()

    /**
     * Tests to ensure that compareTo(Ride) returns max integer value when checking if two invalid rides are compared against each other
     */
    @Test
    @DisplayName("Test compareTo(Ride): Compare two invalid rides, check max int")
    public void testCompareToInvalidRides() {
        // using default invalidRides 1 and 2...

        // define expected and actual output
        int expectedOutput = Integer.MAX_VALUE;
        int actualOutput = invalidRide1.compareTo(invalidRide2);

        // check for max integer
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that compareTo(Ride) returns max integer value when checking if a valid ride is greater than an invalid ride
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride > invalid ride, check max int")
    public void testCompareToGreaterThanInvalid() {
        // using default ride1 and invalidRide1...

        // define expected and actual output
        int expectedOutput = Integer.MAX_VALUE;
        int actualOutput = ride1.compareTo(invalidRide1);

        // check for max integer
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that compareTo(Ride) returns a value greater than 0 when the extended ride is greater than the passed ride, based on timestamp values
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride > valid ride, check > 0")
    public void testCompareToGreaterThanValid() {
        // using default rides 1 and 2...

        // define actual output
        int actualOutput = ride2.compareTo(ride1);

        // check that the actual output is greater than 0
        assertTrue(actualOutput > 0);
    }

    /**
     * Tests to ensure that compareTo(Ride) returns max integer value when checking if a valid ride is equal to an invalid ride
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride == invalid ride, check max int")
    public void testCompareToEqualToInvalid() {
        // using default ride1 and invalid ride1...

        // define actual output
        int expectedOutput = Integer.MAX_VALUE;
        int actualOutput = ride1.compareTo(invalidRide1);

        // check for max integer
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that compareTo(Ride) returns 0 when the extended ride is equal to the passed ride, based on timestamp values
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride == valid ride, check 0")
    public void testCompareToEqualToValid() {
        // using default ride1 and...
        Ride equalRide = new Ride(1, Time.valueOf("01:00:00"), "P5", 1, 1);
        
        // define the expected and actual outputs
        int expectedOutput = 0;
        int actualOutput = ride1.compareTo(equalRide);

        // check that actual output represents a match (value of 0)
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that compareTo(Ride) returns max integer value when checking if a valid ride is less than an invalid ride
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride < invalid ride, check max int")
    public void testCompareToLessThanInvalid() {
        // using default ride2 and invalidRide2...

        // define the expected and actual outputs
        int expectedOutput = Integer.MAX_VALUE;
        int actualOutput = ride2.compareTo(invalidRide2);

        // check for max integer
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that compareTo(Ride) returns a value less than 0 when the extended ride is less than the passed ride, based on timestamp values
     */
    @Test
    @DisplayName("Test compareTo(Ride): Check if valid ride < valid ride, check < 0")
    public void testCompareToLessThanValid() {
        // using default rides 1 and 2...

        // define expected and actual outputs
        int expectedOutput = -1;
        int actualOutput = ride1.compareTo(ride2);

        // check the actual output represents a lesser than (value of -1)
        assertEquals(expectedOutput, actualOutput);
    }

// Test section: toString()
    /**
     * Tests to ensure that toString() returns an error when attempting to print an invalid ride
     */
    @Test
    @DisplayName("Test toString(): Print invalid ride, check error")
    public void testToStringPrintInvalid() {
        // using default invalidRide1...

        // define expected and actual outputs
        String expectedOutput =
                "--- Ride 000 -------\n" +
                "Time: null\n" +
                "Start ID: 0\n" +
                "End ID: 0\n" +
                "Passengers:\n" +
                "--------------------";
        String actualOutput = invalidRide1.toString();

        // check outputs match
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that toString() correctly overrides the default java implementation to return a fully formatted string containing the ride info
     */
    @Test
    @DisplayName("Test toString(): Print valid ride to console, check format")
    public void testToStringPrintValid() {
        // using the default ride1...

        // print a valid ride to teh console
        //System.out.println(ride1.toString());
        // define the expected and actual output
        String expectedOutput = String.format("--- Ride %03d -------\n", ride1.id) +
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
        // no need for arrangement

        // attempt to insert the null array
        heap.insert((Ride[]) null);
        String expectedOutput = "[MinHeap : insert(Ride[])] Unable to add ride array! The passed ride array was null..";
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
        String expectedOutput = "[MinHeap : insert(Ride[])] Unable to add ride array! The passed ride array was empty...";
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
        String expectedOutput = "[MinHeap : insert(Ride[])] Unable to add ride array! The passed ride array length was an invalid size...";
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
        String expectedOutput = "[MinHeap : insert(Ride[])] Unable to add ride array! The passed ride array length was an invalid size...";
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
        // no need for arrangement

        // insert the null ride
        heap.insert((Ride) null);
        // true if the ride was rejected, false if the ride was inserted
        boolean isPointerUnchanged = heap.next == 1;

        // check rejection is true
        assertTrue(isPointerUnchanged);
    }

    /**
     * Tests to ensure that the insert(Ride) method moves pointer for non-null rides
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a non-null ride, check pointer")
    public void testInsertNonNullPointer() {
        // using default ride1...

        // insert a non-null ride
        heap.insert(ride1);
        // check if pointer has moved
        boolean isPointerChanged = heap.next == 2;

        assertTrue(isPointerChanged);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects duplicate rides
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a dupe ride, check index 2")
    public void testInsertRideDupe() {
        // replicate default ride 1
        Ride rideDupe = ride1;

        // insert duplicate rides
        heap.insert(ride1);
        heap.insert(rideDupe);
        // ensure both rides were not added by ensuring index 2 is still null
        boolean isDuped = heap.rides[2] == null;

        // check index 2 is still null
        assertTrue(isDuped);
    }

    /**
     * Tests to ensure that the insert(Ride) method does not increment next pointer after rejecting a duplicate ride
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a dupe ride, check pointer is unchanged")
    public void testInsertRideDupePointer() {
        // using default ride1 and...
        Ride rideDupe = ride1;

        // insert duplicate rides
        heap.insert(ride1);
        heap.insert(rideDupe);
        // ensure pointer is still pointing at 2nd index since only one ride should have been added
        boolean isPointerUnchanged = heap.next == 2;

        // check pointer is correct
        assertTrue(isPointerUnchanged);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects duplicate ride id's
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride with duplicate ride id, check error")
    public void testInsertRideDupeId() {
        // using default ride1 and...
        Ride rideDupeId = new Ride(ride1.id, Time.valueOf("5:0:0"), "Eli", 1, 1);

        // insert rides w/same ID
        heap.insert(ride1);
        heap.insert(rideDupeId);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was already contained in the array...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a ride with a negative value for a ride id
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with id < 1, check error")
    public void testInsertRideIdBelowOne() {
        // create a ride with an id below 1
        Ride rideInvalidId = new Ride(0, Time.valueOf("1:1:1"), "Aaron", 1, 1);

        // insert the invalid ride
        heap.insert(rideInvalidId);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method accepts a positive integer above 0 as an id
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with id > 0, check true")
    public void testInsertRideIdAboveZero() {
        // using default ride1...

        // attempt to insert a ride with a valid id and collect its result
        boolean isInserted = heap.insert(ride1);

        // check for successful insertion
        assertTrue(isInserted);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects empty time strings
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with empty time string, check index 1 is null")
    @SuppressWarnings("unused")
    public void testInsertRideTimeEmpty() {
        // check that a ride w/empty time string throws an illegal argument exception
        assertThrows(IllegalArgumentException.class, () -> {
                    Ride rideInvalidTime = new Ride(420, Time.valueOf(""), "Jameson", 1, 1);
        });
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects invalid time strings
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with an invalid time format, check error")
    @SuppressWarnings("unused")
    public void testInsertRideTimeInvalid() {
        // check that a ride w/invalid time string throws an illegal argument exception
        assertThrows(IllegalArgumentException.class, () -> {
            Ride rideInvalidTime = new Ride(420, Time.valueOf("2:2"), "Jameson", 1, 1);
        });
    }

    /**
     * Tests to ensure that the insert(Ride) method accepts a ride object with a valid timestamp
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with a valid time format, check true")
    public void testInsertRideTimeValid() {
        // using default ride1...

        // insert a ride w/valid time string
        boolean isInserted = heap.insert(ride1);

        // check successful insertion
        assertTrue(isInserted);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a null passenger array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride with a null passenger array, check error")
    public void testInsertRidePassNull() {
        // no need for arrangement

        // create a ride using the null passenger array
        Ride rideNull = new Ride(69, Time.valueOf("1:1:1"), (String[]) null, 1, 1);
        heap.insert(rideNull);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a ride with an empty passenger string
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride w/empty passenger string, check error")
    public void testInsertRidePassEmpty() {
        // creates a ride with an empty passenger string
        Ride rideEmptyPassengers = new Ride(100, Time.valueOf("04:20:00"), "", 1, 1);

        // insert ride with empty passenger string
        heap.insert(rideEmptyPassengers);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a ride with only whitespace for a passenger name
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a ride w/blank passenger string, check error")
    public void testInsertRidePassBlank() {
        // creates a ride with a blank passenger string
        Ride rideEmptyPassengers = new Ride(100, Time.valueOf("04:20:00"), "    ", 1, 1);

        // insert ride with blank passenger string
        heap.insert(rideEmptyPassengers);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects an empty passenger array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/empty pass array, check error")
    public void testInsertRidePassArrayEmpty() {
        // creates an empty passenger array
        String[] passengersEmpty = { };
        // creates a ride with an empty passenger array
        Ride rideEmptyPassengers = new Ride(100, Time.valueOf("04:20:00"), passengersEmpty, 1, 1);

        // insert ride with an empty passenger array
        heap.insert(rideEmptyPassengers);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects a ride array with too many passengers
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/too many pass, check error")
    public void testInsertRidePassArrayTooMany() {
        // create a passenger array w/too many passengers
        String[] passengers = {"Bob", "Marley", "Ziggy", "Stephen", "Smoke", "Mary", "Jane"};
        // create a ride w/too many passengers
        Ride rideTooFull = new Ride(100, Time.valueOf("04:20:00"), passengers, 1, 1);

        // insert the ride w/too many passengers
        heap.insert(rideTooFull);
        // defines expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method accepts a single passenger passed in an array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/single pass array, check true")
    public void testInsertRidePassArraySingle() {
        // creates a ride w/single passenger
        Ride rideSingle = new Ride(10, Time.valueOf("6:5:4"), "Martha Stewart", 2, 3);

        // insert ride w/single passenger and collect result
        boolean isInserted = heap.insert(rideSingle);

        // check insertion is true
        assertTrue(isInserted);
    }

    /**
     * Tests to ensure that the insert(Ride) method accepts multiple passengers passed in an array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/multi pass array, check true")
    public void testInsertRidePassArrayMulti() {
        // creates a passenger array w/multiple passengers
        String[] passengers = {"Martha", "Stewart", "Sucks", "Derek"};
        // creates a ride w/multiple passengers
        Ride rideMulti = new Ride(10, Time.valueOf("6:5:4"), passengers, 2, 3);

        // insert ride w/multiple passengers and collect result
        boolean isInserted = heap.insert(rideMulti);

        // check insertion is true
        assertTrue(isInserted);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects multiple passengers passed as a string separated by commas
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/multi comma sep. string, check error")
    public void testInsertRidePassTooMany() {
        // creates a string of multiple passengers separated w/commas
        String passengers = "Donald, Trump, Bic, Boy";
        // creates a ride w/comma separated passenger string
        Ride rideMultiComma = new Ride(999, Time.valueOf("6:59:59"), passengers, 6, 6);

        // inserts the invalid ride
        heap.insert(rideMultiComma);
        // defines the expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method accepts a single passenger passed as a string
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/single pass string, check true")
    public void testInsertRidePassSingle() {
        // creates a ride w/single passenger string
        Ride rideSingle = new Ride(40, Time.valueOf("0:0:1"), "Jesus Christ", 1, 6);

        // insert the valid single ride and collect the result
        boolean isInserted = heap.insert(rideSingle);

        // check insertion is true
        assertTrue(isInserted);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects rides with a start location value less than or equal to 0
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/start id < 0, check error")
    public void testInsertRideStartInvalid() {
        // creates a ride w/start id less than 0
        Ride rideInvalidStart = new Ride(12, Time.valueOf("0:0:1"), "Jesus", -1, 6);

        // inserts the ride w/invalid start id
        heap.insert(rideInvalidStart);
        // defines expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method rejects rides with an end location value less than or equal to 0
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/end id < 0, check error")
    public void testInsertRideEndInvalid() {
        // creates a ride w/end id less than 0
        Ride rideInvalidEnd = new Ride(12, Time.valueOf("0:0:1"), "Jesus",8, -10);

        // inserts the ride w/invalid end id
        heap.insert(rideInvalidEnd);
        // defines expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride r)] Unable to insert ride! Ride was invalid...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the insert(Ride) method accepts rides with a start id and end id that is greater than 0
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/valid location ids, check true")
    public void testInsertRideLocationValid() {
        // creates a ride w/valid location ids
        Ride rideValidLocation = new Ride(55, Time.valueOf("3:6:3"), "Gina", 50,192);

        // inserts valid ride and collects result
        boolean isInserted = heap.insert(rideValidLocation);

        // check insertion is true
        assertTrue(isInserted);
    }

    /**
     * Tests to ensure that the insert(Ride) method fails to optimize
     * rides with a time difference that is greater than 10 minutes
     */
    @Test
    @DisplayName("Test insert(Ride): Insert two rides w/time diff > 10, check pointer")
    public void testInsertRideInvalidTime() {
        // creates two rides greater than 10 minutes apart
        Ride ride1 = new Ride(40, Time.valueOf("12:00:00"), "Riley", 42, 0);
        Ride ride2 = new Ride(20, Time.valueOf("12:11:00"), "Christopher Robins", 42, 0);

        // insert rides w/greater than 10 minutes time diff
        heap.insert(ride1);
        heap.insert(ride2);
        // define expected and actual pointer values
        int expectedPointer = 3;
        int actualPointer = heap.next;

        // check pointer
        assertEquals(expectedPointer, actualPointer);
    }


    /**
     * Tests to ensure that the insert(Ride) method fails to optimize rides with a time
     * difference of 10 minutes or less, matching start ids, but an un-matching end ids
     */
    @Test
    @DisplayName("Test insert(Ride): Insert two rides w/o matching end ids, check pointer")
    public void testInsertRideInvalidEnd() {
        // creates two rides greater than 10 minutes apart
        Ride ride1 = new Ride(110, Time.valueOf("12:00:00"), "Lionel", 420, 500);
        Ride ride2 = new Ride(103, Time.valueOf("12:01:00"), "Richie", 420, 13);

        // insert rides w/greater than 10 minutes time diff
        heap.insert(ride1);
        heap.insert(ride2);
        // define expected and actual pointer values
        int expectedPointer = 3;
        int actualPointer = heap.next;

        // check pointer
        assertEquals(expectedPointer, actualPointer);
    }

    /**
     * Tests to ensure that the insert(Ride) method fails to optimize rides with a time
     * difference of 10 minutes or less, matching end ids, but un-matching start ids
     */
    @Test
    @DisplayName("Test insert(Ride): Insert ride w/invalid start and end ids, check insertion")
    public void testInsertRideInvalidLocation() {
        // creates two rides greater than 10 minutes apart
        Ride ride1 = new Ride(110, Time.valueOf("12:00:00"), "Lionel", 4201, 13);
        Ride ride2 = new Ride(103, Time.valueOf("12:01:00"), "Richie", 3422, 13);

        // insert rides w/greater than 10 minutes time diff
        heap.insert(ride1);
        heap.insert(ride2);
        // define expected and actual pointer values
        int expectedPointer = 3;
        int actualPointer = heap.next;

        // check pointer
        assertEquals(expectedPointer, actualPointer);
    }

    /**
     * Tests to ensure that the insert(Ride) method successfully optimizes a pair of valid rides in the array
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a pair of optimizable rides, check pointer")
    public void testInsertRideOptimizePair() {
        // creates two rides greater than 10 minutes apart
        Ride ride1 = new Ride(11, Time.valueOf("12:00:00"), "Ronaldo", 420, 500);
        Ride ride2 = new Ride(10, Time.valueOf("12:05:00"), "Christiano", 420, 500);

        // insert rides w/greater than 10 minutes time diff
        heap.insert(ride1);
        heap.insert(ride2);
        // define expected and actual pointer values
        int expectedPointer = 2;
        int actualPointer = heap.next;

        // check pointer
        assertEquals(expectedPointer, actualPointer);
    }

    /**
     * Tests to ensure that the insert(Ride) method successfully optimizes multiple rides w/valid number of passengers
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a few optimizable rides, check insertion")
    public void testInsertRideOptimizeMulti() {
        // create multiple optimizable rides w/valid total number of passengers
        Ride ride1 = new Ride(1145, Time.valueOf("12:00:00"), "Ronald", 420, 500);
        Ride ride2 = new Ride(2345, Time.valueOf("12:03:15"), "McDonald", 420, 500);
        Ride ride3 = new Ride(1234, Time.valueOf("12:00:00"), "King", 420, 500);
        // create a ride array for easier insertion
        Ride[] rideArray = {ride1, ride2, ride3};

        // insert optimizable rides
        heap.insert(rideArray);
        // define expected and actual pointer values
        int expectedPointer = 2;
        int actualPointer = heap.next;

        // check pointer
        assertEquals(expectedPointer, actualPointer);
    }

    /**
     * Tests to ensure that the insert(Ride) method successfully handles multiple optimizable rides w/too many total passengers
     * (Each ride will be added until adding another ride would break the passenger threshold, the remaining rides will be separately added and unchanged)
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a few optimizable rides w/too many total pass, check insertion")
    public void testInsertRideOptimizeTooManyPass() {
        // create passenger arrays totalling to too many passengers
        String[] passengers1to4 = {"p1", "p2", "p3", "p4"};
        String[] passengers5to8 = {"p5", "p6", "p7", "p8"};
        // create multiple optimizable rides w/valid total number of passengers
        Ride ride1 = new Ride(1145, Time.valueOf("12:00:00"), passengers1to4, 420, 500);
        Ride ride2 = new Ride(2345, Time.valueOf("12:03:15"), passengers5to8, 420, 500);
        // create a ride array for easier insertion
        Ride[] rideArray = {ride1, ride2};

        // insert optimizable rides
        heap.insert(rideArray);
        // define expected and actual pointer values (3 since rides are un-optimizable and added separately)
        int expectedPointer = 3;
        int actualPointer = heap.next;

        // check pointer
        assertEquals(expectedPointer, actualPointer);
    }

    /**
     * Tests to ensure that the insert(Ride) method successfully up-heaps after an optimizable insertion
     */
    @Test
    @DisplayName("Test insert(Ride): Insert an optimizable ride, check time @ index1")
    public void testInsertRideOptimizeUpHeap() {
        // creates a pair of optimizable rides
        Ride ride1 = new Ride(1145, Time.valueOf("14:10:00"), "p1", 435, 4);
        Ride ride2 = new Ride(1147, Time.valueOf("10:10:10"), "p2", 420, 500);
        Ride ride3 = new Ride(2345, Time.valueOf("10:15:15"), "p3", 420, 500);

        // insert optimizable rides
        heap.insert(ride1);
        heap.insert(ride2);
        heap.insert(ride3);
        // define expected ride at index 1
        String expectedTime = "10:15:15";
        String actualTime = heap.rides[1].getTime();

        // check times match
        assertEquals(expectedTime, actualTime);
    }

    /**
     * Tests to ensure that the insert(Ride) method successfully inserts a pair of valid but non-optimizable rides individually
     */
    @Test
    @DisplayName("Test insert(Ride): Insert two non-optimizable rides, check insertion")
    public void testInsertRideNotOptimizable() {
        // create a valid, non-optimizable ride
        Ride ride1 = new Ride(4, Time.valueOf("03:06:09"), "p1", 9, 10);
        Ride ride2 = new Ride(10, Time.valueOf("10:10:10"), "p2", 90, 100);

        // insert the valid non-optimizable rides and collect the result
        boolean isInserted = heap.insert(ride1) && heap.insert(ride2);

        // check insertions are true
        assertTrue(isInserted);
    }

    /**
     * Tests to ensure that the insert(Ride) method successfully up-heaps after a non-optimizable insertion
     */
    @Test
    @DisplayName("Test insert(Ride): Insert a non-optimizable ride, check up-heap")
    public void testInsertRideNotOptimizableUpHeap() {
        // using default rides 1 and 2...

        // insert valid, non-optimizable rides
        heap.insert(ride2);
        heap.insert(ride1);
        // define expected and actual time at index 1
        String expectedTime = ride1.getTime();
        String actualTime = heap.rides[1].getTime();

        // check times match
        assertEquals(expectedTime, actualTime);
    }

    /**
     * Tests to ensure that the insert(Ride) method successfully up-heaps the highest of 3 values to the correct indices in the heap
     */
    @Test
    @DisplayName("Test insert(Ride): Insert 3 values in staggered order, check index")
    public void testInsertRide() {
        // using default rides 1, 2 and 3...

        // create a ride array for easier insertion
        Ride[] rideArray = {ride2, ride1, ride3};

        // insert rides
        heap.insert(rideArray);
        // define expected and actual time at index 1
        String expectedTime = ride1.getTime();
        String actualTime = heap.rides[1].getTime();

        // check times match
        assertEquals(expectedTime, actualTime);
    }

// Test section: remove(Ride)

    /**
     * Tests to ensure that remove(Ride) fails to remove when passed a null parameter
     */
    @Test
    @DisplayName("Test remove(Ride): Attempt to remove null, check false")
    public void testRemoveRideNull() {
        // no need for arrangement

        // attempt to remove null ride and collect result
        boolean isRemoved = heap.remove(null);

        // check removal is unsuccessful
        assertFalse(isRemoved);
    }

    /**
     * Tests to ensure that remove(Ride) returns false when the heap array is null
     */
    @Test
    @DisplayName("Test remove(Ride): Remove a ride from a null heap array, check false")
    public void testRemoveRideNullHeap() {
        // using default ride1...
        heap.insert(ride1);

        // set heap array to null
        heap.rides = null;
        // attempt to remove a ride from a null heap array and collect result
        boolean isRemoved = heap.remove(ride1);

        // check error message
        assertFalse(isRemoved);
    }

    /**
     * Tests to ensure that remove(Ride) returns false when the passed ride is not contained in the heap
     */
    @Test
    @DisplayName("Test remove(Ride): Remove an un-contained ride, check error")
    public void testRemoveRideNotContained() {
        // using default ride1...

        // attempt to remove the ride without inserting it and collect result
        heap.remove(ride1);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : remove(Ride r)] Unable to remove the passed ride! Ride was not contained in the heap...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that remove(Ride) targets the correct index
     */
    @Test
    @DisplayName("Test remove(Ride): Remove contained ride, check ride at old index")
    public void testRemoveRideIndex() {
        // insert ride array
        heap.insert(defaultRides);
        // using default rideArray...

        // remove ride3 at index 3
        heap.remove(ride3);
        //define expected and actual ride at index 3 after removal
        Ride expectedRide = ride4;
        Ride actualRide = heap.rides[3];

        // check ride at index 3
        assertEquals(expectedRide, actualRide);
    }

    /**
     * Tests to ensure that remove(Ride) decrements the next index pointer
     */
    @Test
    @DisplayName("Test remove(Ride): Remove contained ride, check pointer")
    public void testRemoveRidePointer() {
        // insert ride array
        heap.insert(defaultRides);
        // using default rideArray...

        // remove ride2 at index 2
        heap.remove(ride2);
        //define expected and actual next index pointer values
        int expectedPointer = 4;
        int actualPointer = heap.next;

        // check pointer value
        assertEquals(expectedPointer, actualPointer);
    }

    /**
     * Tests to ensure that remove(Ride) can remove the root node
     */
    @Test
    @DisplayName("Test remove(Ride): Remove root node, check root")
    public void testRemoveRideRoot() {
        // using defaultRides array...
        heap.insert(defaultRides);

        // remove root ride
        heap.remove(defaultRides[0]);
        // define expected and actual rides at root node
        Ride expectedRide = defaultRides[1];
        Ride actualRide = heap.rides[1];

        // check root value
        assertEquals(expectedRide, actualRide);
    }

    /**
     * Tests to ensure that remove(Ride) can remove the last leaf node
     */
    @Test
    @DisplayName("Test remove(Ride): Remove last leaf node, check last leaf node")
    public void testRemoveRideLastLeaf() {
        // using defaultRides array...
        heap.insert(defaultRides);

        // remove last leaf node
        heap.remove(defaultRides[3]);
        // define expected and actual ride as the last leaf node
        Ride expectedRide = defaultRides[2];
        Ride actualRide = heap.rides[heap.next - 1];

        // check last leaf node
        assertEquals(expectedRide, actualRide);
    }

    /**
     * Tests to ensure that remove(Ride) can remove a value from the middle of the heap
     */
    @Test
    @DisplayName("Test remove(Ride): Remove a value in the middle of the heap, check index")
    public void testRemoveRideMiddle() {
        // using defaultRides array...
        heap.insert(defaultRides);

        // remove a ride from the middle of the heap
        heap.remove(ride2);
        // define expected and actual rides
        Ride expectedRide = ride4;
        Ride actualRide = heap.rides[2];

        // check last leaf node
        assertEquals(expectedRide, actualRide);
    }

// Test section: isEmpty()

    /**
     * Tests to ensure that isEmpty() returns true when the passed array is null
     */
    @Test
    @DisplayName("Test isEmpty(): Check if a null array is empty, check true")
    public void testIsEmptyArrayNull() {
        // set heap to null
        heap.rides = null;

        // check if a null array is empty and collect the result
        boolean isEmpty = heap.isEmpty();

        // check empty returns true
        assertTrue(isEmpty);
    }

    /**
     * Tests to ensure that isEmpty() returns true when the passed array is empty
     */
    @Test
    @DisplayName("Test isEmpty(): Check if an empty array is empty, check true")
    public void testIsEmptyArrayEmpty() {
        // no need for arrangement since heap is empty by default

        // checks if empty heap is empty and collects result
        boolean isEmpty = heap.isEmpty();

        // checks isEmpty returns true
        assertTrue(isEmpty);
    }

    /**
     * Tests to ensure that isEmpty() returns true when the passed array has a length of 0
     */
    @Test
    @DisplayName("Test isEmpty(): Check if array w/size of 0 is empty, check true")
    public void testIsEmptyArraySize0() {
        // set heap to a size of 0
        heap.rides = new Ride[0];

        // check if heap w/size of 0 returns empty and collect the result
        boolean isEmpty = heap.isEmpty();

        // check isEmpty returns true
        assertTrue(isEmpty);
    }

    /**
     * Tests to ensure that isEmpty() returns false when some elements in the passed array are null and some aren't
     */
    @Test
    @DisplayName("Test isEmpty(): Check if array w/random nulls is empty, check false")
    public void testIsEmptyArrayNulls() {
        // create a ride array w/random nulls
        Ride[] rideArray = {null, null, ride1, null, ride2, null, ride3, null, null, null, ride4};
        // set heap to match random null ride array
        heap.rides = rideArray;

        // check if random null array returns empty and collect result
        boolean isEmpty = heap.isEmpty();

        // check isEmpty returns false
        assertFalse(isEmpty);
    }

    /**
     * Tests to ensure that isEmpty() returns false when the passed array only contains a single ride
     */
    @Test
    @DisplayName("Test isEmpty(): Check if array w/single ride is empty, check false")
    public void testIsEmptyArraySingle() {
        // using default ride1...
        heap.insert(ride1);

        // check is single heap is empty and collect result
        boolean isEmpty = heap.isEmpty();

        // check isEmpty returns false
        assertFalse(isEmpty);
    }

    /**
     * Tests to ensure that isEmpty() returns false when the passed array contains multiple rides
     */
    @Test
    @DisplayName("Test isEmpty(): Check if array w/multiple rides is empty, check false")
    public void testIsEmptyArrayMulti() {
        // using defaultRides array...
        heap.insert(defaultRides);

        // check if multi-heap is empty and collect result
        boolean isEmpty = heap.isEmpty();

        // check isEmpty returns false
        assertFalse(isEmpty);
    }

// Test section: heapify(int, Ride[])

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a ride number that is less than 1
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/ride num < 1, check error")
    public void testHeapifyRideNumTooLow() {
        // using defaultRides array...

        // attempt to heapify w/invalid ride num
        heap.heapify(-1, defaultRides);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : heapify(int, Ride[])] Unable to process ride number! An invalid number of rides was detected...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a ride number greater than the array length
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/ride num >= array length")
    public void testHeapifyRideNumTooHigh() {
        // using defaultRides array...
        heap.insert(defaultRides);

        // attempt to heapify array w/ride num too high
        heap.heapify(defaultRides.length + 1, defaultRides);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : heapify(int, Ride[])] Unable to process ride number! An invalid number of rides was detected...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) returns a null Ride[] parameter since it is already heapified
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/null array, check unchanged")
    public void testHeapifyArrayNull() {
        // no arrangement needed

        // attempt to heapify null array and store result in the heap array
        heap.rides = heap.heapify(1, (Ride[]) null);
        // check heap array remains null
        boolean isUnchanged = heap.rides == null;

        // check heap array is unchanged
        assertTrue(isUnchanged);
    }
    /**
     * Tests to ensure that heapify(int, Ride[]) returns the Ride[] unchanged since it is unable to be heapified
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/array of nulls, check error")
    public void testHeapifyArrayNulls() {
        // create an array of nulls
        Ride[] rideArray = {null, null, null, null, null};

        // heapify array of nulls
        heap.heapify(5, rideArray);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : insert(Ride[])] Unable to add ride array! The passed ride array was empty...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a passed ride w/array size of 0
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/array size of 0, check error")
    public void testHeapifyArraySize0() {
        // create array w/size of 0
        Ride[] rideArray = new Ride[0];

        // heapify array w/size 0
        heap.heapify(0, rideArray);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : heapify(int, Ride[])] Unable to process ride array! An invalid array size was detected, array has been returned unchanged...";
        String actualOutput = getStream();

        // check unchanged output
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) successfully heapifies a passed ride w/array size of 1
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify w/array size of 1, check root")
    public void testHeapifyArraySize1() {
        // create array w/one ride in it
        Ride[] rideArray = {ride1};

        // heapify array w/one ride
        heap.heapify(1, rideArray);
        // define expected and actual root values
        Ride expectedRoot = ride1;
        Ride actualRoot = heap.rides[1];

        // check unchanged output
        assertEquals(expectedRoot, actualRoot);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an empty array w/size greater than 0
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify empty array w/size > 0, check error")
    public void testHeapifyArrayEmpty() {
        // create an empty array w/size > 0
        Ride[] rideArray = new Ride[21];

        // heapify empty array w/size > 0
        heap.heapify(0, rideArray);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : heapify(int, Ride[])] Unable to process ride number! An invalid number of rides was detected...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) rejects a passed ride array size that is greater than the max. capacity value
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify an array w/array size > max. capacity, check error")
    public void testHeapifyArrayTooBig() {
        // create an array w/size > max. capacity
        Ride[] rideArray = new Ride[heap.MAX_CAPACITY + 1];

        // heapify array w/size > max. capacity
        heap.heapify(21, rideArray);
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : heapify(int, Ride[])] Unable to process ride array! An invalid array size was detected, array has been returned unchanged...";
        String actualOutput = getStream();

        // check unchanged output
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) correctly coverts a 0-based ride array into a 1-based array and heapifies
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify a zero-based array, check root")
    public void testHeapifyBase0() {
        // using default rides 1-4, create a base-0 array...
        Ride[] rideArray = {ride4, ride2, ride1, ride3};

        // heapify base-0 array
        heap.heapify(4, rideArray);
        // check root equals lowest ride time value (ride1)
        boolean isHeapified = heap.rides[1] == ride1;

        // check root value matches ride1
        assertTrue(isHeapified);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a 1-based ride array
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify a one-based array, check root")
    public void testHeapifyBase1() {
        // using default rides 1-4, create a base-1 array...
        Ride[] rideArray = {null, ride3, ride2, ride4, ride1};

        // heapify base-1 array
        heap.heapify(4, rideArray);
        // check root equals lowest ride time value (ride1)
        boolean isHeapified = heap.rides[1] == ride1;

        // check root value matches ride1
        assertTrue(isHeapified);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an array with a single value when the heap is empty
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify single-array, check root")
    public void testHeapifyArraySingle() {
        // using default ride1...
        Ride[] rideArray = {ride1};

        // attempt to heapify single array
        heap.heapify(1, rideArray);
        // define expected root
        boolean isExpectedRoot = heap.rides[1] == ride1;

        // check root
        assertTrue(isExpectedRoot);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) can heapify an array with multiple values when the heap is empty
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify multi-array, check root")
    public void testHeapifyArrayMulti() {
        // create a multi-array
        Ride[] rideArray = {ride2, ride3, ride1, ride4};

        // heapify multi-array
        heap.heapify(4, rideArray);
        // define expected and actual root values
        Ride expectedRoot = ride1;
        Ride actualRoot = heap.rides[1];

        // check root values
        assertEquals(expectedRoot, actualRoot);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a full array when the heap is empty
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify full array w/full array, check root")
    public void testHeapifyArrayFull() {
        // using defaultRides array... copy default rides array to make it a "full" heap rather than creating 20 odd rides here
        defaultRides = Arrays.copyOf(defaultRides, 21);

        // heapify full array
        heap.heapify(20, defaultRides);
        // define expected and actual root values
        Ride expectedRoot = ride1;
        Ride actualRoot = heap.rides[1];

        // check root values
        assertEquals(expectedRoot, actualRoot);
    }

    /**
     * Tests to ensure that heapify(int, Ride[]) accepts a full array when the heap already has a single ride
     */
    @Test
    @DisplayName("Test heapify(int, Ride[]): Heapify full array w/single heap, check last leaf")
    public void testHeapifyArrayFullHeapSingle() {
        // insert a single ride in to the heap
        heap.insert(ride4);
        // using defaultRides array... copy default rides array to make it a "full" heap rather than creating 20 odd rides here
        defaultRides = Arrays.copyOf(defaultRides, heap.MAX_CAPACITY);

        // heapify full array
        heap.heapify(heap.MAX_CAPACITY - 1, defaultRides);
        // define expected and actual root values
        Ride expectedRoot = ride1;
        Ride actualRoot = heap.rides[1];

        // check root values
        assertEquals(expectedRoot, actualRoot);
    }

// Test section: peek()

    /**
     * Tests to ensure that the peek() method returns null when the heap is null
     */
    @Test
    @DisplayName("Test peek(): Peek at null heap, check null")
    public void testPeekHeapNull() {
        // set heap to null
        heap.rides = null;

        // call peek and check against null
        boolean isNull = heap.peek() == null;

        // check peek returns null
        assertTrue(isNull);
    }

    /**
     * Tests to ensure that the peek() method returns null when peeking at an empty heap
     */
    @Test
    @DisplayName("Test peek(): Peek at empty heap, check null")
    public void testPeekHeapEmpty() {
        // heap is empty by default, no need for further arrangement...

        // check peek returns null for empty heaps
        boolean isNull = heap.peek() == null;

        // check null
        assertTrue(isNull);
    }

    /**
     * Tests to ensure that the peek() method returns the root value when peeking at a heap with a single value
     */
    @Test
    @DisplayName("Test peek(): Peek at single-heap, check outputs")
    public void testPeekHeapSingle() {
        // insert a single ride into heap
        heap.insert(ride1);

        // define expected and actual outputs
        Ride expectedOutput = ride1;
        Ride actualOutput = heap.peek();

        // check outputs
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the peek() method returns the root value when peeking at a heap with multiple values
     */
    @Test
    @DisplayName("Test peek(): Peek at multi-heap, check root")
    public void testPeekHeapMulti() {
        // using defaultRides array...

        // insert multi-array
        heap.insert(defaultRides);
        // define expected and actual outputs
        Ride expectedOutput = ride1;
        Ride actualOutput = heap.peek();

        // check outputs
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the peek() method does not remove the value after peeking at it
     */
    @Test
    @DisplayName("Test peek(): Peek after a valid peek is performed, check root")
    public void testPeekAfterPeek() {
        // using default ride1... insert valid ride into heap
        heap.insert(ride1);

        // peek once
        heap.peek();
        // define expected and actual outputs, peeking again to confirm the root isn't removed after first peek
        Ride expectedOutput = ride1;
        Ride actualOutput = heap.peek();

        // check outputs
        assertEquals(expectedOutput, actualOutput);
    }

// Test section: dump()


    /**
     * Tests to ensure that the dump() method returns an error when attempting to dump a null heap
     */
    @Test
    @DisplayName("Test dump(): Attempt to dump a null heap, check error")
    public void testDumpHeapNull() {
        // set the heap to null
        heap.rides = null;

        // attempt to dump the null heap
        heap.dump();
        // define the expected and actual outputs
        String expectedOutput = "[MinHeap : dump()] Unable to dump heap! Heap was null or empty...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the dump() method returns an error when attempting to dump an empty heap
     */
    @Test
    @DisplayName("Test dump(): Attempt to dump an empty heap, check error")
    public void testDumpHeapEmpty() {
        // heap is empty by default, no need for further arrangement...

        // attempt to dump the null heap
        heap.dump();
        // define the expected and actual outputs
        String expectedOutput = "[MinHeap : dump()] Unable to dump heap! Heap was null or empty...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the dump() method correctly dumps a heap w/single ride
     */
    @Test
    @DisplayName("Test dump(): Dump a single-heap, check output")
    public void testDumpHeapSingle() {
        // insert a single ride into the heap
        heap.insert(ride1);

        // dump the single-heap
        heap.dump();
        // define expected and actual outputs
        String expectedOutput = String.format("--- Ride %03d -------\n", ride1.id) +
                String.format("Time: %tT\n", ride1.time) +
                String.format("Start ID: %d\n", ride1.startId) +
                String.format("End ID: %d\n", ride1.endId) +
                String.format("Passengers:\n%s", ride1.fPassengers()) +
                "--------------------";
        String actualOutput = getStream();

        // check output
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the dump() method correctly dumps a heap w/multiple rides
     */
    @Test
    @DisplayName("Test dump(): Dump a multi-heap, check output")
    public void testDumpHeapMulti() {
        // using defaultRides array... insert ride array
        heap.insert(defaultRides);

        // dump multi-heap
        heap.dump();
        // define expectedOutput
        String expectedOutput =
                String.format("--- Ride %03d -------\n", ride1.id) +
                String.format("Time: %tT\n", ride1.time) +
                String.format("Start ID: %d\n", ride1.startId) +
                String.format("End ID: %d\n", ride1.endId) +
                String.format("Passengers:\n%s", ride1.fPassengers()) +
                "--------------------\n" +
                String.format("--- Ride %03d -------\n", ride2.id) +
                String.format("Time: %tT\n", ride2.time) +
                String.format("Start ID: %d\n", ride2.startId) +
                String.format("End ID: %d\n", ride2.endId) +
                String.format("Passengers:\n%s", ride2.fPassengers()) +
                "--------------------\n" +
                String.format("--- Ride %03d -------\n", ride3.id) +
                String.format("Time: %tT\n", ride3.time) +
                String.format("Start ID: %d\n", ride3.startId) +
                String.format("End ID: %d\n", ride3.endId) +
                String.format("Passengers:\n%s", ride3.fPassengers()) +
                "--------------------\n" +
                String.format("--- Ride %03d -------\n", ride4.id) +
                String.format("Time: %tT\n", ride4.time) +
                String.format("Start ID: %d\n", ride4.startId) +
                String.format("End ID: %d\n", ride4.endId) +
                String.format("Passengers:\n%s", ride4.fPassengers()) +
                "--------------------";
        // define actual output replacing any carriage returns with standard new lines
        String actualOutput = getStream().replace("\r\n", "\n");

        // check output
        assertEquals(expectedOutput, actualOutput);
    }

// Test section sort()

    /**
     * Tests to ensure that the sort() method returns an error when attempting to sort a null heap
     */
    @Test
    @DisplayName("Test sort(): Attempt to sort a null heap, check error")
    public void testSortHeapNull() {
        // set heap to null
        heap.rides = null;

        // heap null heap
        heap.sort();
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : sort()] Unable to sort heap! Default ride array was null or empty...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the sort() method returns an unchanged heap when attempting to sort an empty heap
     */
    @Test
    @DisplayName("Test sort(): Sort an empty heap, check unchanged")
    public void testSortHeapEmpty() {
        // heap is empty by default, no need for further arrangement...

        // sort empty heap
        heap.sort();
        // define expected and actual outputs
        String expectedOutput = "[MinHeap : sort()] Unable to sort heap! Default ride array was null or empty...";
        String actualOutput = getStream();

        // check error message
        assertEquals(expectedOutput, actualOutput);
    }

    /**
     * Tests to ensure that the sort() method correctly sorts a heap w/single ride
     */
    @Test
    @DisplayName("Test sort(): Sort a single-heap, check root")
    public void testSortHeapSingle() {
        // using default ride1... insert a single ride
        heap.insert(ride1);

        // sort single heap
        heap.sort();
        // define expected and actual roots
        Ride expectedRoot = ride1;
        Ride actualRoot = heap.rides[1];

        // check output array
        assertEquals(expectedRoot, actualRoot);
    }

    /**
     * Tests to ensure that the sort() method correctly sorts a heap w/multiple rides
     */
    @Test
    @DisplayName("Test sort(): Sort a multi-heap, check order")
    public void testSortHeapMulti() {
        // create a multi-ride array unordered
        Ride[] rideArray = {ride3, ride2, ride4, ride1};
        // insert multi-ride array
        heap.insert(rideArray);

        // sort multi-heap
        heap.sort();
        // define expected order (only first and last nodes for simplicity)
        boolean isOrdered = heap.rides[1].compareTo(ride1) == 0 && heap.rides[4].compareTo(ride4) == 0;

        // check first and last nodes match expected order
        assertTrue(isOrdered);
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
