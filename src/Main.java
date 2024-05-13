import java.sql.Time;

public class Main {
    public static void main(String[] args) {
        Ride ride1 = new Ride(1, Time.valueOf("15:3:00"), "Test 1, Test 2, Test 3", 420, 6969);
        Ride ride2 = new Ride(69, Time.valueOf("2:5:1"), "Test 4, Test 5, Test 6", 69, 420000);
        Ride ride3 = new Ride(420, Time.valueOf("5:5:5"), "Test 7, Test 8, Test 9", 69, 420000);
//        System.out.println(ride1);
//        System.out.println(ride2);
//        System.out.println(ride3);
//
//        System.out.println("Ride 1 vs. Ride 2 = " + ride1.compareTo(ride2) + " (" + ride1.time + ", " + ride2.time + ")");
//        System.out.println("Ride 1 vs. Ride 3 = " + ride1.compareTo(ride3) + " (" + ride1.time + ", " + ride3.time + ")");
//        System.out.println("Ride 2 vs. Ride 3 = " + ride2.compareTo(ride3) + " (" + ride2.time + ", " + ride3.time + ")");
//        System.out.println("Ride 2 vs. Ride 1 = " + ride2.compareTo(ride1) + " (" + ride2.time + ", " + ride1.time + ")");

//        MinHeap heap = new MinHeap(10);
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
        Ride[] rideArray = {ride1, ride2, ride3, ride1, ride2, ride3};
        HeapPrinter printer = new HeapPrinter();
        printer.print(rideArray);
    } // end main

} // end class