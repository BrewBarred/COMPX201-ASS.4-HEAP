/**
 * Creates a printer that can print different parts of a custom 'Ride'
 * object within a 'Ride' array in the form of an array list or heap diagram
 *
 * @Note: Example instantiation:<br>
 * HeapPrinter printer = new HeapPrinter(Ride[])<br>
 * <br>Example method call:<br>
 * printer.printAll(Ride[] heap.rideArray);<br>
 * <br>Available methods:<br>
 * printArray(Ride[] rideArray)<br>
 * printIds(Ride[] rideArray)<br>
 * printTime(Ride[] rideArray)<br>
 * printAll(Ride[] rideArray)<br>
 */
public class HeapPrinter {
    /**
     * Function to print each element of the passed 'Ride' array starting from the 0th item along with its ID and Timestamp if any exist
     * @param rideArray The ride array to iterate through for the Index, ID, and timestamp
     * @Note: Ride array elements must be of type 'Ride', its timestamp field must be named "time"
     * and its ID field must be named "id". Any unused spaces in the rideArray should be set to null
     */
    public static void printArray(Ride[] rideArray) {
        try {
            // iterates through each element in the array
            for (int i = 0; i < rideArray.length; i++) {
                // takes the current 'Ride' object being iterated over
                Ride ride = rideArray[i];
                // fetches the current 'Ride' objects ID value
                String id = (ride == null ? null : String.valueOf(ride.id));
                // fetches the current 'Ride' objects time value
                String timeStamp = (ride == null ? null : ride.time.toString());
                // prints the current 'Ride' objects info to the console
                System.out.println(String.format("%d: Ride ID = %s, Ride Timestamp = %s", i, id, timeStamp));

            } // end for

        } catch (Exception e) {
            // print error msg to console w/stack trace
            System.out.println("[HeapPrinter : printArray] Error processing ride array: " + e);
            // print stack trace to console
            e.printStackTrace();

        } // end try

    } // end void

    /**
     * Function to print each rides ID in the form of a heap structure
     * @param rideArray The ride array to iterate through for the RideID
     * @Note: rideArray elements must be of type 'Ride' and its ID field must be named "id".
     */
    public static void printID(Ride[] rideArray) {
        try {
            // if the passed rideArray is empty
            if (isNullOrEmpty(rideArray))
                throw new IndexOutOfBoundsException();

            System.out.println("\nPrinting Heap...\n");

            // calculates the amount of levels required to display this heap diagram
            int levels = (int) (Math.log(rideArray.length) / Math.log(2)) + 1;
            // stores the max length of each line
            int maxLength = 0;

            // iterates through each 'Ride' object in the array
            for (Ride ride : rideArray)
                // calculates the longest string length in the array
                maxLength = Math.max(maxLength, ride == null ? "null".length() : String.valueOf(ride.id).length());

            // starts printing from index 1 instead of 0 since it is usually not used in heaps
            int currentIndex = 1;
            // for each level in this heap
            for (int level = 0; level < levels; level++) {
                // calculate the level width
                int levelWidth = (int) Math.pow(2, level);
                // calculates the space between subtrees on the given level
                int subtreeSpacing = (maxLength + 2) * ((int) Math.pow(2, levels - level - 1)) - 1;
                // calculates the space between each child of a subtree
                int childSpacing = subtreeSpacing - maxLength + 1;

                // adds blank spaces until the desire subtree spacing has been met
                for (int i = 0; i < subtreeSpacing / 2; i++)
                    System.out.print(" ");

                // adds each child to the diagram including any necessary spacing
                for (int i = 0; i < levelWidth && currentIndex < rideArray.length; i++) {
                    // takes the current 'Ride' object being iterated over
                    Ride ride = rideArray[currentIndex++];
                    // fetches this 'Ride' objects value
                    String numStr = (ride == null ? "null" : String.valueOf(ride.id));
                    // takes the length of this 'Ride' objects value
                    int strLength = numStr.length();
                    // calculates the padding for this 'Ride' object
                    int padding = (maxLength - strLength < 0) ? 0 : maxLength - strLength;

                    // prints this ride object to the console along with its padding
                    System.out.printf("%" + (padding / 2 + strLength + padding - padding / 2) + "s", numStr);
                    // adds child spacing to the console too before moving to the next element
                    for (int j = 0; j < childSpacing; j++)
                        System.out.print(" ");

                } // end for

                // shifts down in the console to print the next level
                System.out.println();

            } // end for

            // prints a blank line in the console for diagram clarity
            System.out.println();

        } catch (Exception e) {
            // prints error msg to the console w/stack trace
            System.out.println("[Printer: printID] Error processing ride array: " + e);
            e.printStackTrace();

        } // end try

    } // end void

    /**
     * Function to print each element of a 'Ride' arrays timestamps as they would be positioned in a heap structure
     * @param rideArray The ride array to iterate through for the timestamps
     * @Note: rideArray elements must be of type 'Ride' and its timestamp field must be named "time".
     */
    public static void printTime(Ride[] rideArray) {
        try {
            // if the passed rideArray is empty
            if (isNullOrEmpty(rideArray))
                throw new IndexOutOfBoundsException();

            // calculates the amount of levels required to display this heap diagram
            int levels = (int) (Math.log(rideArray.length) / Math.log(2)) + 1;
            // stores the max length of each line
            int maxLength = 0;

            // iterates through each 'Ride' object in the array
            for (Ride ride : rideArray)
                // calculates the longest string length in the array
                maxLength = Math.max(maxLength, ride == null ? "null".length() :String.valueOf(ride.time).length());

            System.out.println("\nPrinting Heap...\n");

            // starts printing from index 1 instead of 0 since it is usually not used in heaps
            int currentIndex = 1;
            // for each level in this heap
            for (int level = 0; level < levels; level++) {
                // calculate the level width
                int levelWidth = (int) Math.pow(2, level);
                // calculates the space between subtrees on the given level
                int subtreeSpacing = (maxLength + 2) * ((int) Math.pow(2, levels - level - 1)) - 1;
                // calculates the space between each child of a subtree
                int childSpacing = subtreeSpacing - maxLength + 1;

                // adds blank spaces until the desire subtree spacing has been met
                for (int i = 0; i < subtreeSpacing / 2; i++)
                    System.out.print(" ");

                // adds each child to the diagram including any necessary spacing
                for (int i = 0; i < levelWidth && currentIndex < rideArray.length; i++) {
                    // takes the current 'Ride' object being iterated over
                    Ride ride = rideArray[currentIndex++];
                    // fetches this 'Ride' objects value
                    String numStr = (ride == null ? "null" : String.valueOf(ride.time));
                    // takes the length of this 'Ride' objects value
                    int strLength = numStr.length();
                    // calculates the padding for this 'Ride' object
                    int padding = (maxLength - strLength < 0) ? 0 : maxLength - strLength;

                    // prints this ride object to the console along with its padding
                    System.out.printf("%" + (padding / 2 + strLength + padding - padding / 2) + "s", numStr);
                    // adds child spacing to the console too before moving to the next element
                    for (int j = 0; j < childSpacing; j++)
                        System.out.print(" ");

                } // end for

                // shifts down in the console to print the next level
                System.out.println();

            } // end for

        // prints a blank line in the console for diagram clarity
        System.out.println();

        } catch (Exception e) {
            // prints error msg to the console w/stack trace
            System.out.println("[Printer: printTimes] Error processing ride array: " + e);
            // prints the stack trace of the error
            e.printStackTrace();

        } // end try

    } // end void

    /**
     * Returns true if the passed 'Ride' array has no elements in it
     * @param rideArray The 'Ride' array to iterate through
     * @return True if this 'Ride' array is uninitialized or empty
     */
    private static boolean isEmpty(Ride[] rideArray) {
        // ensures the ride array is not null before checking if its length is 0
        return rideArray == null ? true : rideArray.length == 0;

    } // end boolean

    /**
     * Validates the passed 'Ride' array object
     * @param rideArray The 'Ride' array to iterate through
     * @return True if this 'Ride' array is uninitialized, empty or all elements are null, else returns false
     */
    private static boolean isNullOrEmpty(Ride[] rideArray) {
        // returns true if the passed 'Ride' array is empty or not initialized
        if (rideArray == null || isEmpty(rideArray))
            return true;

        // returns true if all elements of the passed array are null
        for (Ride ride : rideArray)
            if (ride != null)
                return false;

        // else returns false
        return false;

    } // end boolean

} // end class