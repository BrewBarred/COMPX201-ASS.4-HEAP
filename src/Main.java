import java.sql.Time;

public class Main {
    public static void main(String[] args) {
        Ride ride1 = new Ride(45, Time.valueOf("00:00:00"), "Test 1, Test 2, Test 3", 420, 6969);
        Ride ride2 = new Ride(32, Time.valueOf("1:1:1"), "Test 4, Test 5, Test 6", 69, 70);
        Ride ride3 = new Ride(44, Time.valueOf("2:30:21"), "Test 7, Test 8, Test 9", 120, 3);
        Ride ride4 = new Ride(67, Time.valueOf("03:03:03"), "Test 10, Test 11, Test 12", 1, 420);
        Ride ride5 = new Ride(89, Time.valueOf("09:90:30"), "Test 13, Test 14, Test 15", 80, 420000);
        Ride ride6 = new Ride(1, Time.valueOf("12:90:90"), "Test 16, Test 17, Test 18", 0, 89);
        Ride ride7 = new Ride(99, Time.valueOf("23:01:02"), "Test 19", 14, 92);

//        System.out.println(ride1);
//        System.out.println(ride2);
//        System.out.println(ride3);
//
//        System.out.println("Ride 1 vs. Ride 2 = " + ride1.compareTo(ride2) + " (" + ride1.time + ", " + ride2.time + ")");
//        System.out.println("Ride 1 vs. Ride 3 = " + ride1.compareTo(ride3) + " (" + ride1.time + ", " + ride3.time + ")");
//        System.out.println("Ride 2 vs. Ride 3 = " + ride2.compareTo(ride3) + " (" + ride2.time + ", " + ride3.time + ")");
//        System.out.println("Ride 2 vs. Ride 1 = " + ride2.compareTo(ride1) + " (" + ride2.time + ", " + ride1.time + ")");

        MinHeap heap = new MinHeap();
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
        Ride[] rideArray6 = {ride1, ride2, ride3, ride4, ride5, ride6, ride7};

        HeapPrinter printer = new HeapPrinter();
        for (Ride ride : rideArray6)
            heap.insert(ride);

        //printer.printTime(heap.rideArray);
        printer.printID(heap.rideArray);
        //System.out.println(String.format("[TEST] Attempting to remove ride1 from the heap... Ride ID: %d, Ride Timestamp: %s", ride1.id, ride1.time));
        //heap.remove(ride1);
        //printer.printTime(heap.rideArray);
        //printer.printID(heap.rideArray);
        //System.out.println("Attempting to remove a ride that's already been removed...");
        //heap.remove(ride1);
        //System.out.println(String.format("Attempting to remove the root ride... Ride ID: %d, Ride Timestamp: %s", ride6.id, ride6.time));
        //heap.remove(ride6);
        //printer.printTime(heap.rideArray);
        //printer.printID(heap.rideArray);
        //heap.heapify(rideArray6, 6);
        printer.printID(heap.rideArray);
        printer.printTime(heap.rideArray);
        System.out.println("Attempting to heapify the passed array...");
        heap.heapify(heap.rideArray, 7);
        printer.printID(heap.rideArray);
        printer.printTime(heap.rideArray);
        System.out.println(ride1.compareTo(ride2));




//        printer.printID(heap.rideArray);
//        printer.printTime(heap.rideArray);
//        printer.printArray(heap.rideArray);

    } // end main

} // end class