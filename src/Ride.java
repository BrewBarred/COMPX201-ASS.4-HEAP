import java.awt.*;
import java.sql.Time;
import java.util.Arrays;

/**
 * Constructs a new Ride object used to store the information of each riders request in the ride-share app
 */
public class Ride implements Comparable<Ride> {
    /**
     * ~ FOR DEVELOPER USE ONLY! ~<br><br>
     *
     * True if debugging mode should be enabled, else false
     */
    private final boolean DEBUGGING = true;
    /**
     * The maximum number of passengers per vehicle
     */
    private final int MAX_PASSENGERS = 6;
    /**
     * The identification number of this ride request
     */
    public int id;
    /**
     * The timestamp of this ride request in 24-hour time format (i.e., 00:00:00 -> 23:59:59)
     */
    public Time time;
    /**
     * The name of each passenger in this ride request
     * @Note: Each passenger must be separated using a "," and
     * whitespaces on either side of the comma will be trimmed
     */
    public String[] passengers = new String[MAX_PASSENGERS];
    /**
     * The start location ID
     */
    public int startId;
    /**
     * The end location ID
     */
    public int endId;
    /**
     * The total number of passengers in this ride and also acts as a pointer to the next index in the passenger list
     */
    private int pCount = 0;

    /**
     * Constructs a new Ride object to store the information of a ride request
     * @param id The identification number of this ride request
     * @param time The timestamp of this ride request in 24-hour time format (i.e., 00:00:00 -> 23:59:59)
     * @param passenger The name of the passenger in this ride request
     * @param startId The start location ID
     * @param endId The end location ID
     */
    public Ride(int id, Time time, String passenger, int startId, int endId) {
        // initializes this ride if the passed passenger name is not null and not an empty string
        if (passenger != null || passenger.trim().length() != 0)
            init(id, time, new String[]{passenger}, startId, endId);
        else
            System.out.println("Unable to create ride! Passenger was invalid...");
    }

    /**
     * Constructs a new Ride object to store the information of a ride request
     * @param id The identification number of this ride request
     * @param time The timestamp of this ride request in 24-hour time format (i.e., 00:00:00 -> 23:59:59)
     * @param passengers An array containing the names of the passengers in this ride request
     * @param startId The start location ID
     * @param endId The end location ID
     */
    public Ride(int id, Time time, String[] passengers, int startId, int endId) {
        // initialize this ride if there is at least one and no more than the maximum number of passengers
        if (!(passengers.length < 1 || passengers.length > MAX_PASSENGERS))
            init(id, time, passengers, startId, endId);
        else
            System.out.println("Unable to create ride! One or more passengers were invalid...");
    }

    private void init(int id, Time time, String[] passengers, int startId, int endId) {
        this.id = id;
        this.time = time;
        // add each passenger individually to ensure passenger count is taken
        addPassenger(passengers);
        // ensures start and end i.d's are greater than or equal to 1
        this.startId = startId > 0 ? startId : 1;
        this.endId = endId > 0 ? endId : 1;
    }

    /**
     * Converts and returns this rides timestamp as a String
     * @return A String value equivalent to this rides timestamp
     */
    public String getTime() {
        return time.toString();
    }

    /**
     * Overrides the default java String.toString() method to return the information of this ride request.
     * @return A string value representing the information of this ride request neatly formatted
     */
    @Override
    public String toString() {
        return String.format("--- Ride %03d -------\n", id) +
                String.format("Time: %tT\n", time) +
                String.format("Start ID: %d\n", startId) +
                String.format("End ID: %d\n", endId) +
                "Passengers:\n" + fPassengers() +
                "--------------------";
    }

    /**
     * Compares the extended ride (ride1) against the passed ride (ride2) by their timestamps
     * @param ride2 The ride object to compare against ride1 by timestamp
     * @return -1 if the timestamp of ride1 is less than that of ride2<br><br>
     *          0 if the timestamp of ride1 is equal to that of ride2<br><br>
     *          1 if the timestamp of ride1 is greater than that of ride2
     */
    public int compareTo(Ride ride2) {
        return time.compareTo(ride2.time);
    }

    /**
     * Adds the passed passengers to this rides passenger list
     * @param passengers The names of the passengers being added to this ride
     * @return A boolean value that is true if the passengers are successfully added to this ride, else returns false
     */
    public boolean addPassenger(String[] passengers) {
        // if string is null or empty, do not add a passenger
        if (passengers == null || passengers.length == 0)
            return false;

        // temporarily stores the current passenger list in case of any processing errors
        String[] temp = this.passengers;

        // iterates through the passed array adding each passenger
        for (String p : passengers) {
            if (p == null)
                continue;
            // if any processing errors occur
            if (!addPassenger(p)) {
                // restores original passengers and returns false
                this.passengers = temp;
                System.out.println("returning false");
                return false;
            }
        }
        return true;
    }

    /**
     * Adds the passed passenger to this rides passenger list
     * @param passenger The name of the passenger being added to this ride
     * @return A boolean value that is true if the passenger is successfully added to this ride, else returns false
     */
    public boolean addPassenger(String passenger) {
        // if string is null or empty, do not add a passenger
        if (passenger == null || passenger.trim().length() == 0)
            return false;

        // trims white space from the passed string
        passenger = passenger.trim();

        // if the passenger list is at, or will exceed max capacity, do not proceed
        if (pCount >= MAX_PASSENGERS) {
            System.out.println(String.format("Unable to add passengers to ride! Insufficient ride capacity... pCount: %d, passengers.length: %d, Max passengers: %d", pCount, passengers.length, MAX_PASSENGERS));
            return false;
        }

        if (passenger.contains(",")) {
            System.out.println("Unable to add multiple passengers! Please use: addPassenger(String[]) instead...");
            return false;
        }

        // adds the passed passenger to this rides passenger list
        passengers[pCount++] = passenger;
        debug("Successfully added passenger \"" + passenger + "\". RideId: " + id, "addPassenger");
        return true;
    }

    /**
     * Formats the passenger array ready for printing
     * @return A string value representing each passenger in this ride request on a new line
     */
    private String fPassengers() {
        // creates a string builder object for efficient string concatenation
        StringBuilder fPassengers = new StringBuilder();

        // checks if there is at least one valid passenger in the array before iterating
        if (passengers.length > 0) {
            // iterates through the array of passengers
            for (String passenger : passengers)
                if (passenger != null)
                    fPassengers.append(passenger.trim()).append("\n");

            return fPassengers.toString();
        }

        // displays error message if no valid passengers were found
        debug("Error! Failed to format passengers, an invalid passenger string was passed!", "fPassengers");
        return null;
    }

    /**
     * ~ FOR DEVELOPER USE ONLY ~<br><br>
     *
     * Prints debug messages to console if debugging mode is enabled
     * @param msg The debug message to be printed to the console
     * @param function The name of the function in which the debugging message is executed
     *
     * Prints debug messages to console if debugging mode is enabled
     * @param msg The debug message to be printed to the console
     * @param function The name of the function in which the debugging message is executed
     */
    private void debug(String msg, String function) {
        if (DEBUGGING)
            System.out.println(String.format("[Ride : %s] %s", function.toUpperCase(), msg));
    }

} // end class