import java.sql.Time;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        MinHeap heap = new MinHeap();
        HeapPrinter printer = new HeapPrinter();

        Ride ride1 = new Ride(45, Time.valueOf("4:4:4"), "Test 1, Test 2, Test 3", 420, 6969);
        Ride ride2 = new Ride(32, Time.valueOf("2:2:2"), "Test 4, Test 5, Test 6", 69, 70);
        Ride ride3 = new Ride(44, Time.valueOf("3:3:3"), "Test 7, Test 8, Test 9", 120, 3);
        Ride ride4 = new Ride(67, Time.valueOf("5:5:5"), "Test 10, Test 11, Test 12", 1, 420);
        Ride ride5 = new Ride(89, Time.valueOf("6:6:6"), "Test 13, Test 14, Test 15", 80, 420000);
        Ride ride6 = new Ride(1, Time.valueOf("1:1:1"), "Test 16, Test 17, Test 18", 0, 89);
        Ride ride7 = new Ride(99, Time.valueOf("7:7:7"), "Test 19", 14, 92);

        Ride[] rideArray = {null, ride1, ride2, ride3, ride4, ride5, ride6, ride7};

        //clearConsole("Attempting to Heapify...");

        //Ride[] rides = heap.sort(rideArray);

        heap.insert(ride2);
        heap.insert(ride3);
        heap.insert(ride6);
        heap.sort();

    } // end main

    /**
     * Clears the console with a label detailing the next process
     * @param msg A String to show what process is about to occur
     */
    private static void clearConsole(String msg) {
        for(int i = 0; i < 14; i++) {
            if (i == 7)
                System.out.println(msg);
            else
                System.out.println();

        } // end for

    } // end void

} // end class