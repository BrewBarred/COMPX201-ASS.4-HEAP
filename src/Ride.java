import java.sql.Time;

/**
 * Constructs a new Ride object used to store the information of each riders request in the ride-share app
 */
@SuppressWarnings("CallToPrintStackTrace")
public class Ride {
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
    public String passengers;
    /**
     * The start location ID
     */
    public int startId;
    /**
     * The end location ID
     */
    public int endId;
    /**
     * ~ FOR DEVELOPER USE ONLY! ~<br><br>
     *
     * True if debugging mode should be enabled, else false
     */
    private final boolean DEBUGGING = true;

    /**
     * Constructs a new Ride object to store the information of a ride request
     * @param id The identification number of this ride request
     * @param time The timestamp of this ride request in 24-hour time format (i.e., 00:00:00 -> 23:59:59)
     * @param passengers The name of each passenger in this ride request<br><br>
     *
     * Note: Each passenger must be separated using a "," and any
     * whitespace on either side of the comma will be trimmed<br>
     *
     * @param startId The start location ID
     * @param endId The end location ID
     */
    public Ride(int id, Time time, String passengers, int startId, int endId) {
            this.id = id;
            this.time = checkTime(time);
            this.passengers = fPassengers(passengers);
            this.startId = startId;
            this.endId = endId;

    } // end constructor

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
                "Passengers:\n" + passengers +
                "--------------------";

    } // end String override

    /**
     * Compares this ride against "ride2" by their timestamps
     * @param ride2 The ride object to compare timestamps against
     * @return -1 if this rides timestamp is earlier than that of ride2<br><br>
     *          0 if this rides timestamp is the same as that of ride2<br><br>
     *          1 if this rides timestamp is later than that of ride2
     */
    public int compareTo(Ride ride2) {
        try {
            if (ride2 == null)
                throw new NullPointerException("Unable to compare 'Ride' objects, the passed 'Ride' object was null!");

            return time.compareTo(ride2.time);

        } catch (Exception e) {
            System.out.println("Error comparing time values: " + e);
            e.printStackTrace();
            return Integer.MIN_VALUE;

        } // end int

    } // end int


    /**
     * Ensures the passed 'Time' is a valid 24-hour time format
     * @param time The 'Time' value to check
     * @return The passed time if it is valid, else returns null
     */
    private Time checkTime(Time time) {
        try {
            debug("Validating time: " + time);
            if (!isValidTime(time))
                throw new IllegalArgumentException("Invalid time \"" + time + "\" value passed! Please ensure time is between 00:00:00 inclusive and 24:00:00 exclusive...");

            return time;

        } catch (Exception e) {
            System.out.println("Error constructor rider object: " + e);
            e.printStackTrace();
            return null;

        } // end try

    } // end time

    /**
     * Ensures the passed 'Time' is a valid 24-hour time format
     * @param time The 'Time' value to check
     * @return True if the  passed time is valid, else returns false
     */
    private boolean isValidTime(Time time) {
        // split passed time into a string array
        String[] splitTime = time.toString().split(":");
        int hour = Integer.parseInt(splitTime[0]);
        int mins = Integer.parseInt(splitTime[1]);
        int secs = Integer.parseInt(splitTime[2]);

        // ensure each time component is valid
        boolean validHour = hour >= 0 && hour < 24;
        boolean validMins = mins >= 0 && mins < 60;
        boolean validSecs = secs >= 0 && secs < 60;

        // return true if all time components are valid
        return validHour && validMins && validSecs;

    } // end time

    /**
     * Formats the passed passenger string ready for display
     * @param passengers A string value representing each passenger in this ride request with each passenger after the first separated by a comma
     * @return A string value representing each passenger in this ride request on a new line
     */
    private String fPassengers(String passengers) {
        // splits each passenger
        String[] passengerArray = passengers.split(",");
        StringBuilder fPassengers = new StringBuilder();

        // checks if there is at least one valid passenger in the array before iterating
        if (passengerArray.length > 0 && !passengers.trim().isEmpty()) {
            // iterates through the array of passengers
            // formats each passenger in the array ready for display
            for (String s : passengerArray)
                fPassengers.append(s.trim()).append("\n");

            return fPassengers.toString();

        }// end if

        // displays error message if no valid passengers were found
        debug("Error! Failed to format passengers, and invalid passenger string was passed!");
        return null + "\n";

    } // end String

    /**
     * ~ FOR DEVELOPER USE ONLY ~<br><br>
     *
     * Writes debug messages to the console if debugging mode is enabled
     * @param msg The debug message to print to the console
     */
    private void debug(String msg) {
        // if debugging mode has been enabled
        if (DEBUGGING)
            System.out.println("[DEBUG] " + msg);

    } // end debug

} // end class