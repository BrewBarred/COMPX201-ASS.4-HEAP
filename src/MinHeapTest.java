import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Time;

/**
 * Test suite for the MinHeap class, used to test each function is operating correctly and producing the expected outputs
 */
public class MinHeapTest {
    /**
     * Stores console output to help with testing
     */
    ByteArrayOutputStream stream = new ByteArrayOutputStream();
    /**
     * Stores the stream output as a string
     */
    private String consoleOutput;
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
     * Mock ride to test null rides
     */
    private Ride rideNull = null;

    private final PrintStream originalOut = System.out;

    @AfterEach
    public void restoreStreams() {
        System.setOut(originalOut);
    }

    /**
     * Re-initializes the minimum heap being tested before running each test and
     * creates some of the common ride objects required for this test suite
     */
    @BeforeEach
    public void resetHeap() {
        System.setOut(new PrintStream(stream));
        // converts the console output to a string for tidier referencing
        consoleOutput = stream.toString().trim();

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
     * Tests to ensure that inserting a null array will do nothing
     */
    @Test
    @DisplayName("insertRide(Ride[]) test: Null stack insertion")
    public void testInsertNullArray() {
        // arrange the test with an array set to null
        Ride[] rides = null;

        // attempt to insert the null array
        heap.insert(rides);
        String expectedString = "Unable to add ride! The passed ride array was either null or empty...";

        assertEquals(expectedString, consoleOutput);
    }

    /**
     * Tests to ensure that inserting an empty array will not increment the index pointer since no rides have been added
     */
    @Test
    @DisplayName("insertRide(Ride[]) test: Empty stack insertion")
    public void testInsertEmptyArray() {
        // arrange the test with an empty array to add
        Ride[] rides = new Ride[4];

        // insert the empty array
        heap.insert(rides);
        // set the expected and fetch the actual index value
        int expectedIndex = 1;
        int actualIndex = heap.next;

        //assert that index values are as expected
        assertEquals(expectedIndex, actualIndex);
    }

    /**
     * Tests to ensure that the insert(Ride[]) method can take an array containing a null 0 index
     */
    @Test
    @DisplayName("insertRide(Ride[]) test: Index 0 is null")
    public void testInsertNullZero() {
        // arrange
        Ride[] rides = new Ride[] {null, ride1, ride2, ride3, ride4};
        // act
        boolean output = heap.insert(rides);
        // assert
        assertTrue(output);
    }

    /**
     * Tests to ensure that inserting a null ride will be rejected
     */
    public void testInsertNull() {

    }

}
