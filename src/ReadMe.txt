The JUnit .jar that this application was compiled with has been included in the directory.
In order to compile and run the code please use the following console commands:   

Compile:

    javac -cp "junit-platform-console-standalone-1.8.2.jar" *.java

Run:

    java -jar junit-platform-console-standalone-1.8.2.jar -cp .\ -c MinHeapTest



-- NOTES ABOUT THE IMPLEMENTATION --

-> If you decide to enable debugging in the MinHeap class for whatever reason, please ensure it is set to false prior to running the test suite as a lot of tests will fail if you don't.

-> It was unclear if we should be ensuring any passed arrays (such as those passed to the insert/heapify methods), naturally I
   would add code to ensure that the array either matches the maximum capacity or extend the passed array with null objects to meet
   the capacity requirements, but I have not done this to minimize any (potentially) unnecessary code.

-> For the heap sort method, I reversed the array after the sorting process has been completed, I'm not sure if this was necessary or not
   but it seemed wrong sorting the heap into ascending order, as that would form a max heap as opposed to a min heap. I was also unsure
   how to do this without another loop, but I figured the complexity was not too bad since there is a maximum of 20 items in the heap and they are
   partially sorted already.

-> Since we weren't explicitly asked to use the comparable interface to compare location ID's or ride ID's I chose not to, but I have used the comparable interface
   to compare rides by timestamps for down-heaping etc., (as required)

-> I didn't like the way you have to pass a Date to the Ride class so, I intentionally stuck to the first assignment sheet for this and used java.sql.Time class instead,
   this way, the time is a lot easier to pass through the constructor and don't have the added hassle of including/excluding a date in its input/output.

-> I have two constructors for a Ride object, these are identical apart from the passengers parameter and the way that is validated.
   This was done so that if there was a ride with only one passenger, you can pass a String instead of a String array.

-> For consistency and since you can't nullify an object if its parameters are invalid (well, not that I know of), I made an init() method which
   takes all parameters that a ride object needs and validates them before any fields are filled out. This is as close as I could get to rejecting
   the creation of an invalid ride, but they still exist. I have a boolean to confirm whether this validation process was successful or not.

-> The tests for the optimizeRide function have been done as a part of the Ride.insert functions tests because that is where the optimization function is called

-> If an array with a size of less than 2 is heapified, it will return the array without overriding the heap because I consider this to be un-heapifiable

-> Heapify assumes that a valid 0-based or 1-based array will be passed, and will not work if an array with random null elements
throughout it are passed since this wasn't an explicit requirement for the assignment