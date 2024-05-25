The JUnit .jar that this application was compiled with has been included in the directory.
In order to compile and run the code please use the following console commands:   

Compile:

    javac -cp "junit-platform-console-standalone-1.8.2.jar" *.java

Run:

    java -jar junit-platform-console-standalone-1.8.2.jar -cp .\ -c MinHeapTest
	

Included in this application is one additional field and 3 additional methods that should 
not be included in the assessment.

Additional Field:

	- debugging: Enables debugging messages during testing when true, and disables them when false

Additional Methods:

	- push(String[] strArray): Takes an array of strings for ease when testing in my Main.java
	- debug(String str): Used to provide informative debug messages while testing my code
	- debug(): An easier way for me to print new lines during tests