import java.sql.Time;

public class Main {
    public static void main(String[] args) {

        MinHeap heap = new MinHeap();
        HeapPrinter printer = new HeapPrinter();

        Ride ride1 = new Ride(1145, Time.valueOf("14:10:00"), "p1", 435, 4);
        Ride ride2 = new Ride(2, Time.valueOf("10:10:10"), new String[] {"p2"}, 2, 3);
        Ride ride3 = new Ride(3, Time.valueOf("03:00:00"), new String[] {"p3"}, 3, 4);
        Ride ride4 = new Ride(4, Time.valueOf("04:00:00"), new String[] {"p4"}, 4, 5);
        Ride ride10 = new Ride(44, Time.valueOf("08:05:00"), new String[] {"p5"}, 420, 6969);
        Ride ride9 = new Ride(42, Time.valueOf("08:00:02"), "pass2", 420, 6969);
        Ride ride5 = new Ride(89, Time.valueOf("6:6:6"), new String[] {"Test 17", "Test 18", "Test 19"}, 80, 420000);
        Ride ride6 = new Ride(78, Time.valueOf("08:09:00"), new String[] {"Test 20", "Test 21", "Test 22"}, 420, 6969);
        Ride ride8 = new Ride(1, Time.valueOf("08:09:00"), new String[] {"Test 223", "Test 221", "Test 220"}, 420, 6969);
        Ride ride7 = new Ride(99, Time.valueOf("07:00:00"), "Test 19", 420, 6969);

        Ride ride11 = new Ride(1145, Time.valueOf("14:10:00"), "p1", 435, 4);
        Ride ride12 = new Ride(1147, Time.valueOf("10:10:10"), "p2", 420, 500);
        Ride ride13 = new Ride(2345, Time.valueOf("10:15:15"), "p3", 420, 500);

        Ride[] rideArray = {ride11, ride12, ride13};

        heap.insert(rideArray);
        printer.printTime(heap);
    }

} // end class