import java.lang.reflect.Field;

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
@SuppressWarnings("CallToPrintStackTrace")
public class HeapPrinter {
    /**
     * Function to print each element of the passed 'Ride' array starting from the 0th item along with its ID and Timestamp if any exist
     * @param rideArray The ride array to iterate through for the Index, ID, and timestamp
     * @Note: Ride array elements must be of type 'Ride', its timestamp field must be named "time"
     * and its ID field must be named "id". Any unused spaces in the rideArray should be set to null
     */
    public static void printArray(Ride[] rideArray) {
        try {
            System.out.println("\nPrinting Ride array elements w/their ID and timestamp..\n");
            // iterates through each element in the array
            for (int i = 0; i < rideArray.length; i++) {
                // takes the current 'Ride' object being iterated over
                Ride ride = rideArray[i];
                // fetches the current 'Ride' objects ID value
                String id = (ride == null ? null : String.valueOf(ride.id));
                // fetches the current 'Ride' objects time value
                String timeStamp = (ride == null ? null : ride.time.toString());
                // prints the current 'Ride' objects info to the console
                System.out.printf("%d: Ride ID = %s, Ride Timestamp = %s%n", i, id, timeStamp);

            } // end for

        } catch (Exception e) {
            // print error msg to console w/stack trace
            System.out.println("[HeapPrinter : printArray] Error processing ride array: " + e);
            e.printStackTrace();

        } // end try

    } // end void

    /**
     * Function to print each element of the passed heap starting from the 0th item along with its ID and Timestamp if any exist
     * @param heap The heap containing the ride array to iterate through for the Index, ID, and timestamp
     * @Note: Heap elements must be of type 'Ride', its timestamp field must be named "time"
     * and its ID field must be named "id". Any unused spaces in the heap should be set to null
     */
    public static void printArray(MinHeap heap) {
        printArray(heap.rides);

    } // end void

    /**
     * Function to print each rides ID in the form of a heap structure
     * @param rideArray The ride array to iterate through for the RideID
     * @Note: rideArray elements must be of type 'Ride' and its ID field must be named "id".
     */
    public static void printID(Ride[] rideArray) {
        try {
            System.out.println("\nPrinting Ride IDs as a heap diagram...\n");
            // calls the print method to print the "id" fields of the ride array elements in a heap diagram format
            print(rideArray, "id", - 1, - 1, null, null);

        } catch (Exception e) {
            // prints error msg to the console w/stack trace
            System.out.println("[HeapPrinter : printID] Error processing ride array: " + e);
            e.printStackTrace();

        } // end try

    } // end void
    /**
     * Function to print each elements Ride ID
     * @param heap The heap containing the ride array to iterate through for the RideID's
     * @Note: Heap elements must be of type 'Ride' and its ID field must be named "id".
     */
    public static void printID(MinHeap heap) {
        printID(heap.rides);

    } // end void


    /**
     * Function to print each rides ID in the form of a heap structure
     * @param rideArray The ride array to iterate through for the RideID
     * @param indexChild The index of the child node being manipulated, used to set color to cyan
     * @param indexParent The index of the parent node being manipulated, used to set color to purple
     * @Note: rideArray elements must be of type 'Ride' and its ID field must be named "id".
     */
    public static void printID(Ride[] rideArray, int indexChild, int indexParent) {
        try {
            System.out.println("\nPrinting Ride IDs as a heap diagram...\n");
            // calls the print method to print the "id" fields of the ride array elements in a heap diagram format
            print(rideArray, "id", indexChild, indexParent, null, null);

        } catch (Exception e) {
            // prints error msg to the console w/stack trace
            System.out.println("[HeapPrinter : printID] Error processing ride array: " + e);
            e.printStackTrace();

        } // end try

    } // end void

    /**
     * Function to print each element of the passed heaps timestamps
     * @param heap The heap containing the ride array to iterate through for the timestamps
     * @Note: Heap elements must be of type 'Ride' and its timestamp field must be named "time".
     */
    public static void printTime(MinHeap heap) {
        printTime(heap.rides);

    } // end void

    /**
     * Function to print each element of a 'Ride' arrays timestamps as they would be positioned in a heap structure
     * @param rideArray The ride array to iterate through for the timestamps
     * @Note: rideArray elements must be of type 'Ride' and its timestamp field must be named "time".
     */
    public static void printTime(Ride[] rideArray) {
        try {
            System.out.println("\nPrinting Ride times as a heap diagram...\n");
            // calls the print method to print the "time" fields of the ride array elements in a heap diagram format
            print(rideArray, "time", -1, -1, null, null);

        } catch (Exception e) {
            // prints error msg to the console w/stack trace
            System.out.println("[HeapPrinter : printTimes] Error processing ride array: " + e);
            // prints the stack trace of the error
            e.printStackTrace();

        } // end try

    } // end void

    /**
     * Function to print each element of a 'Ride' arrays timestamps as they would be positioned in a heap structure
     * @param rideArray The ride array to iterate through for the timestamps
     * @param indexRed The index of the node being manipulated, used to set color to red
     * @Note: rideArray elements must be of type 'Ride' and its timestamp field must be named "time".
     */
    public static void printTime(Ride[] rideArray, int indexRed) {
        System.out.println("\nPrinting Ride times as a heap diagram...\n");
        // sets child to cyan when printing
        String colorRed = "\033[0;31m";
        // calls the print method to print the "time" fields of the ride array elements in a heap diagram format
        print(rideArray, "time", indexRed, -1, colorRed, null);


    } // end void


    /**
     * Function to print each element of a 'Ride' arrays timestamps as they would be positioned in a heap structure
     * @param rideArray The ride array to iterate through for the timestamps
     * @param indexCyan The index of the node being manipulated, used to set color to cyan (Usually used to show child)
     * @param indexPurple The index of the node being manipulated, used to set color to purple (Usually used to show parent)
     * @Note: rideArray elements must be of type 'Ride' and its timestamp field must be named "time".
     */
    public static void printTime(Ride[] rideArray, int indexCyan, int indexPurple) {
            System.out.println("\nPrinting Ride times as a heap diagram...\n");
            // sets child to cyan when printing
            String colorCyan = "\033[0;36m";
            // sets parent to purple when printing
            String colorPurple = "\033[0;35m";
            // calls the print method to print the "time" fields of the ride array elements in a heap diagram format
            print(rideArray, "time", indexCyan, indexPurple, colorCyan, colorPurple);

    } // end void

    /**
     * Function to print each element of a 'Ride' arrays timestamps as they would be positioned in a heap structure
     * @param heap The heap containing the ride array to iterate through for the timestamps
     * @param indexCyan The index of the node being manipulated, used to set color to cyan
     * @param indexPurple The index of the node being manipulated, used to set color to purple
     * @Note: rideArray elements must be of type 'Ride' and its timestamp field must be named "time".
     */
    public static void printTime(MinHeap heap, int indexCyan, int indexPurple) {
        try {
            System.out.println("\nPrinting Ride times as a heap diagram...\n");
            // calls the print method to print the "time" fields of the ride array elements in a heap diagram format
            printTime(heap.rides, indexCyan, indexPurple);

        } catch (Exception e) {
            // prints error msg to the console w/stack trace
            System.out.println("[HeapPrinter : printTimes] Error processing ride array: " + e);
            // prints the stack trace of the error
            e.printStackTrace();

        } // end try

    } // end void

    /**
     * Function to print each element of a 'Ride' arrays ID's + timestamps as they would be positioned in a heap structure,
     * as well as each element in the array along with their index positions.
     * @param rideArray The 'Ride' array object being printed
     */
    public static void printAll(Ride[] rideArray) {
        // calls each custom print method one after the other
        printArray(rideArray);
        printID(rideArray);
        printTime(rideArray);

    } // end void

    /**
     * Function to print each element of a heaps ride ID's + timestamps
     * as well as each element in the array along with their index positions.
     * @param heap The heap containing the ride array whose elements are being printed
     */
    public static void printAll(MinHeap heap) {
        printAll(heap.rides);

    } // end void

    /**
     * Prints the field value (rideField) of each element in the passed 'Ride' array in a heap diagram format
     * @param rideArray The ride array to iterate through
     * @param rideField The field in the 'Ride' class being retrieved
     * @param index1 The index of the first value to be colored
     * @param index2 The index of the second value to be colored
     * @param indexColor1 The color to paint the value at index1
     * @param indexColor2 The color to paint the value at index2
     *
     */
    private static void print(Ride[] rideArray, String rideField, int index1, int index2, String indexColor1, String indexColor2) {
        try {
            // the default color, used to reset the color after a change
            String colorDefault = "\033[0m";
            // the current color being used
            String colorCurrent = colorDefault;
            // sets the color for the first passed index
            String color1 = indexColor1 == null ? colorDefault : indexColor1;
            // sets the second color
            String color2 = indexColor2 == null ? colorDefault : indexColor2;

            // if the passed rideArray is empty
            if (isNullOrEmpty(rideArray))
                throw new IndexOutOfBoundsException();

            Field field = Ride.class.getDeclaredField(rideField);
            // calculates the amount of levels required to display this heap diagram
            int levels = (int) (Math.log(rideArray.length) / Math.log(2)) + 1;
            // stores the max length of each line
            int maxLength = 0;

            // iterates through each 'Ride' object in the array
            for (Ride ride : rideArray) {
                // fetches the Ride objects field value based on the passed "rideField" string
                rideField = (ride == null ? "null" : field.get(ride).toString());
                // calculates the longest string length in the array
                maxLength = Math.max(maxLength, rideField.length());

            } // end for

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
                    rideField = (ride == null ? "null" : field.get(ride).toString());
                    // takes the length of this 'Ride' objects value
                    int strLength = rideField.length();
                    // calculates the padding for this 'Ride' object
                    int padding = Math.max(maxLength - strLength, 0);

                    // set text color
                    if (currentIndex == index1 + 1)
                        colorCurrent = color1;
                    else if (currentIndex == index2 + 1)
                        colorCurrent = color2;
                    else
                        colorCurrent = colorDefault;

                    // prints this ride object to the console along with its padding
                    System.out.printf(colorCurrent + " %" + (padding / 2 + strLength + padding - padding / 2) + "s ", rideField);
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
            System.out.println("[HeapPrinter : print] Error processing ride array: " + e);
            e.printStackTrace();

        } // end try

    } // end void

    /**
     * Returns true if the passed 'Ride' array has no elements in it
     * @param rideArray The 'Ride' array to iterate through
     * @return True if the passed 'Ride' array is uninitialized or empty
     */
    private static boolean isEmpty(Ride[] rideArray) {
        // ensures the ride array is not null before checking if its length is 0
        return rideArray == null || rideArray.length == 0;

    } // end boolean

    /**
     * Returns true if the passed 'Ride' array is null or empty
     * @param rideArray The 'Ride' array to iterate through
     * @return True if the passed 'Ride' array is uninitialized, empty or all elements are null, else returns false
     */
    private static boolean isNullOrEmpty(Ride[] rideArray) {
        // returns true if the passed 'Ride' array is empty or not initialized
        if (rideArray == null || isEmpty(rideArray))
            return true;

        // returns false if at least one of the elements in the rideArray and not false
        for (Ride ride : rideArray)
            if (ride != null)
                return false;

        // else returns false
        return false;

    } // end boolean

} // end class