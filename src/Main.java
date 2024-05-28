import java.sql.Time;

public class Main {
    public static void main(String[] args) {

        MinHeap heap = new MinHeap();
        HeapPrinter printer = new HeapPrinter();

        Ride ride1 = new Ride(45, Time.valueOf("08:00:00"), new String[] {"pass1"}, 420, 6969);
        Ride ride9 = new Ride(45, Time.valueOf("08:00:02"), "pass2", 420, 6969);
        Ride ride10 = new Ride(45, Time.valueOf("08:05:00"), new String[] {"pass3"}, 420, 6969);
        Ride ride2 = new Ride(32, Time.valueOf("2:2:2"), new String[] {"Test 4", "Test 5", "Test 6"}, 69, 70);
        Ride ride3 = new Ride(44, Time.valueOf("3:3:3"), new String[] {"Test 7", "Test 8", "Test 9", "Test 10", "Test 11", "Test 12"}, 120, 3);
        Ride ride4 = new Ride(67, Time.valueOf("5:5:5"), new String[] {"Test 10", "Test 11", "Test 12", "Test 13", "Test 14", "Test 15", "Test 16"}, 1, 420);
        Ride ride5 = new Ride(89, Time.valueOf("6:6:6"), new String[] {"Test 17", "Test 18", "Test 19"}, 80, 420000);
        Ride ride6 = new Ride(78, Time.valueOf("08:09:00"), new String[] {"Test 20", "Test 21", "Test 22"}, 420, 6969);
        Ride ride8 = new Ride(1, Time.valueOf("08:09:00"), new String[] {"Test 223", "Test 221", "Test 220"}, 420, 6969);
        Ride ride7 = new Ride(99, Time.valueOf("07:00:00"), "Test 19", 420, 6969);

        Ride[] rideArray = {null, ride1, ride2, ride3, ride4, ride5, ride6, ride7};

        heap.insert(ride1);
        heap.insert(ride9);
        heap.insert(ride10);
        //heap.sort();

        printer.printTime(heap);
        System.out.println(heap.getTimeDiff(ride6, ride1));
        heap.dump();

    }

} // end class