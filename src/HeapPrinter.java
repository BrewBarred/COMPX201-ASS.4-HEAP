public class HeapPrinter {

    // Function to print the structure of min heap
    public static void print(Ride[] arr) {
        int levels = (int) (Math.log(arr.length) / Math.log(2)) + 1; // Calculate total levels in the heap

        // Calculate the maximum length of the values in the array
        int maxLength = 0;
        for (Ride ride : arr) {
            int length = String.valueOf(ride.time).length();
            if (length > maxLength) {
                maxLength = length;
            }
        }

        int currentIndex = 0;
        for (int level = 0; level < levels; level++) {
            int levelWidth = (int) Math.pow(2, level);
            int spacesBetween = (maxLength + 2) * ((int) Math.pow(2, levels - level - 1)) - 1; // Adjusted spaces between elements

            // Calculate dynamic element spacing
            int elementSpacing = (maxLength + 2) * ((int) Math.pow(2, levels - level - 1)) - maxLength;

            // Print leading spaces
            for (int i = 0; i < spacesBetween / 2; i++) {
                System.out.print(" ");
            }

            // Print elements for this level
            for (int i = 0; i < levelWidth && currentIndex < arr.length; i++) {
                Ride ride = arr[currentIndex++];
                String numStr = String.valueOf(ride.time);
                // Calculate padding spaces for alignment
                int padding = maxLength - numStr.length();
                // Print padding spaces before the number
                for (int j = 0; j < padding / 2; j++) {
                    System.out.print(" ");
                }
                System.out.print(numStr);
                // Print padding spaces after the number
                for (int j = 0; j < padding - padding / 2; j++) {
                    System.out.print(" ");
                }
                // Print spacing between elements
                for (int j = 0; j < elementSpacing; j++) {
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}