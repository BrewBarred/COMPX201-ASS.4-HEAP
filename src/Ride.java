import java.sql.Time;
//import java.util.Date.*;

/**
 * Constructs a new Ride object used to store the information of each riders request in the ride share app
 */
public class Ride {
    /**
     * Writes debug messages to the console when enabled (true) - FOR DEVELOPER USE ONLY
     */
    private boolean debugging = true;
    /**
     * The identification number of this ride request
     */
    public int id;
    /**
     * The timestamp of this ride request
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
     * Constructs a new Ride object to store the information of a ride request
     */
    public Ride(int id, Time time, String passengers, int startId, int endId) {
        this.id = id;
        this.time = time;
        this.passengers = fPassengers(passengers);
        this.startId = startId;
        this.endId = endId;

    } // end constructor

    /**
     * Formats the passed passenger string ready for display
     * @param passengers A string value representing each passenger in this ride request with each passenger after the first separated by a comma
     * @return A string value representing each passenger in this ride request on a new line
     */
    public String fPassengers(String passengers) {
        // splits each passenger
        String[] passengerArray = passengers.split(",");
        StringBuilder fPassengers = new StringBuilder();

        // checks if there is at least one valid passenger in the array before iterating
        if (passengerArray.length > 0 && passengers.trim().length() > 0) {
            // iterates through the array of passengers
            for (int i = 0; i < passengerArray.length; i++)
                // formats each passenger in the array ready for display
                fPassengers.append(passengerArray[i].trim()).append("\n");

            return fPassengers.toString();

        }// end if

        // displays error message if no valid passengers were found
        debug("Error! Failed to format passengers, and invalid passenger string was passed!");
        return null + "\n";

    } // end String



    /**
     * Overrides the default java String.toString() method to return the information of this ride request.
     * @return A string value representing the information of this ride request neatly formatted
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append(String.format("--- Ride %03d -------\n", id))
                .append(String.format("Time: %tT\n", time))
                .append(String.format("Start ID: %d\n", startId))
                .append(String.format("End ID: %d\n", endId))
                .append("Passengers:\n" + passengers)
                .append("--------------------");
        return str.toString();

    } // end String override

    /**
     * Compares two 'Ride' objects against each other by their timestamps
     * @param ride The ride object to compare timestamps against
     * @return -1 if this rides timestamp is earlier than that of the compared ride OR<br>
     *          0 if this rides timestamp is the same as that of the compared ride OR<br>
     *          1 if this rides timestamp is later than that of the compared ride
     */
    public int compareTo(Ride ride) {
        return this.time.compareTo(ride.time);

    } // end int


    /**
     * ~ FOR DEVELOPER USE ONLY ~<br><br>
     *
     * Writes debug messages to the console if debugging mode is enabled
     * @param msg The debug message to print to the console
     */
    private void debug(String msg) {
        // if debugging mode has been enabled
        if (debugging)
            System.out.println("DEBUG: " + msg);

    } // end debug

} // end class