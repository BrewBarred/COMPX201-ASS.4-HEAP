import java.sql.Time;

public class Main {
    public static void main(String[] args) {
        Ride ride1 = new Ride(1, Time.valueOf("15:3:00"), "Test 1, Test 2, Test 3", 420, 6969);
        Ride ride2 = new Ride(69, Time.valueOf("2:5:1"), "Test 4, Test 5, Test 6", 69, 70);
        Ride ride3 = new Ride(420, Time.valueOf("42:42:42"), "Test 7, Test 8, Test 9", 120, 3);
        Ride ride4 = new Ride(123, Time.valueOf("04:20:00"), "Test 10, Test 11, Test 12", 1, 420);
        Ride ride5 = new Ride(6912, Time.valueOf("12:00:00"), "Test 13, Test 14, Test 15", 80, 420000);
        Ride ride6 = new Ride(421, Time.valueOf("24:00:00"), "Test 16, Test 17, Test 18", 0, 89);

//        System.out.println(ride1);
//        System.out.println(ride2);
//        System.out.println(ride3);
//
//        System.out.println("Ride 1 vs. Ride 2 = " + ride1.compareTo(ride2) + " (" + ride1.time + ", " + ride2.time + ")");
//        System.out.println("Ride 1 vs. Ride 3 = " + ride1.compareTo(ride3) + " (" + ride1.time + ", " + ride3.time + ")");
//        System.out.println("Ride 2 vs. Ride 3 = " + ride2.compareTo(ride3) + " (" + ride2.time + ", " + ride3.time + ")");
//        System.out.println("Ride 2 vs. Ride 1 = " + ride2.compareTo(ride1) + " (" + ride2.time + ", " + ride1.time + ")");

        MinHeap heap = new MinHeap(10);
//        heap.dump();
//
//        heap.insert(ride1);
//        heap.insert(ride2);
//        heap.insert(ride3);
//        heap.insert(ride2);
//        heap.insert(ride2);
//        heap.insert(ride3);
//        heap.insert(ride1);
//
//        heap.dump();

        Ride[] rideArray = {null, ride1, ride2, ride3, ride4, ride5, ride6};
        Ride[] rideArray2 = {ride1, ride2, null, ride3, null, ride4, null, ride5, ride6, null};
        Ride[] rideArray3 = {null, null, null, null, null, null};
        Ride[] rideArray4 = {};
        Ride[] rideArray5 = {ride1, ride2, null, ride3, null, ride4, null, ride5, ride6, null, ride1, ride2, null, ride3, null, ride4, null, ride5, ride6, null};

        HeapPrinter printer = new HeapPrinter();
        for (Ride ride : rideArray2)
            heap.insert(ride);

        printer.printID(heap.rideArray);
        printer.printTime(heap.rideArray);
        printer.printArray(heap.rideArray);

    } // end main

} // end class