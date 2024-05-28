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

    /**
     * Tests to ensure that attempting to insert a null array will trigger an error message
     */
    @Test
    @Order(1)
    @DisplayName("Test insertRide(Ride[]): Insert an array that is null")
    public void testInsertArrayNull() {
        // arrange the test with an array set to null
        Ride[] rides = null;

        // attempt to insert the null array
        heap.insert(rides);
        String expectedString = "Unable to add ride! The passed ride array was either null or empty...";
        String actualOutput = stream.toString().trim();

        // compare expected output against actual output
        assertEquals(expectedString, actualOutput);
    }

    /**
     * Tests to ensure that inserting an empty array will not add any rides or increment the ride count
     */
    @Test
    @Order(2)
    @DisplayName("Test insertRide(Ride[]): Insert empty array, check ride count is unchanged")
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
    @DisplayName("Test insertRide(Ride[]): Insert empty array, check index pointer is unchanged")
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
    @DisplayName("Test insertRide(Ride[]): Insert 1-based array, check root")
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
    @DisplayName("Test insertRide(Ride[]): Insert 0-based array, check root")
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
     *
     */

    /**
     * Tests to ensure that the insert(Ride[]) method can take an array with only null values
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert array of nulls, check result")
    public void testInsertArrayOfNulls() {

    }

    /**
     * Tests to ensure that the insert(Ride[]) method will reject an array size of 0
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert array size of 0, check result")
    public void testInsertArraySize0() {

    }

    /**
     * Tests to ensure that the insert(Ride[]) method will reject an array size greater than the max. capacity<br>
     * (In this case, it is fixed at 20 rides, which is an array size of 21 - including index 0)
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert array size > 21, check result")
    public void testInsertArrayTooBig() {

    }

    /**
     * Tests to ensure that the insert(Ride[]) method will accept a valid array size (i.e., 1-21)
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert valid array, check result")
    public void testInsertArrayValid() {

    }

    /**
     * Tests to ensure that the insert(Ride[]) method will handle random null elements
     */
    @Test
    @DisplayName("Test insert(Ride[]): Insert random null elements, check result")
    public void testInsertArrayRandNulls() {

    }

}
