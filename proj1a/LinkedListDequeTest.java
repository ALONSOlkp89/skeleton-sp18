public class LinkedListDequeTest {
	/** Performs some basic linked list tests. */
	/* Utility method for printing out empty checks. */
	public static boolean checkEmpty(boolean expected, boolean actual) {
		if (expected != actual) {
			System.out.println("isEmpty() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Utility method for printing out empty checks. */
	public static boolean checkSize(int expected, int actual) {
		if (expected != actual) {
			System.out.println("size() returned " + actual + ", but expected: " + expected);
			return false;
		}
		return true;
	}

	/* Prints a nice message based on whether a test passed. 
	 * The \n means newline. */
	public static void printTestStatus(boolean passed) {
		if (passed) {
			System.out.println("Test passed!\n");
		} else {
			System.out.println("Test failed!\n");
		}
	}

	/** Adds a few things to the list, checking isEmpty() and size() are correct, 
	  * finally printing the results. 
	  *
	  * && is the "and" operation. */
	public static void addIsEmptySizeTest() {
		System.out.println("Running add/isEmpty/Size test.");
		//System.out.println("Make sure to uncomment the lines below" +
		//		"(and delete this print statement).");

		//LinkedListDeque<String> lld1 = new LinkedListDeque<String>();
		ArrayDeque<String> lld1 = new ArrayDeque<String>();
		boolean passed = checkEmpty(true, lld1.isEmpty());

		lld1.addFirst("front");
		
		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
		passed = checkSize(1, lld1.size()) && passed;
		passed = checkEmpty(false, lld1.isEmpty()) && passed;

		lld1.addLast("middle");
		passed = checkSize(2, lld1.size()) && passed;

		lld1.addLast("back");
		passed = checkSize(3, lld1.size()) && passed;

		System.out.println("Printing out deque: ");
		lld1.printDeque();

		printTestStatus(passed);

	}

	/** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
	public static void addRemoveTest() {

		System.out.println("Running add/remove test.");

		//System.out.println("Make sure to uncomment the lines below " +
		//		"(and delete this print statement).");

		//LinkedListDeque<Integer> lld1 = new LinkedListDeque<Integer>();
		ArrayDeque<Integer> lld1 = new ArrayDeque<Integer>();
		// should be empty 
		boolean passed = checkEmpty(true, lld1.isEmpty());

		//lld1.addFirst(10);
		lld1.addLast(10);
		lld1.addLast(20);
		lld1.addLast(30);
		lld1.addLast(40);
		lld1.addLast(50);
		lld1.addLast(60);
		lld1.addLast(70);
		lld1.addLast(80);
		lld1.addLast(90);
		lld1.addLast(100);
		lld1.addLast(110);
		lld1.addLast(120);
		lld1.addLast(130);
		lld1.addLast(140);
		lld1.addLast(150);
		lld1.addLast(160);
		lld1.removeLast();
		lld1.removeLast();
		lld1.removeLast();
		lld1.removeLast();
		lld1.removeLast();
		lld1.removeLast();
		lld1.removeLast();
		lld1.removeLast();



		// should not be empty 
		passed = checkEmpty(false, lld1.isEmpty()) && passed;
		System.out.println(lld1.get(0));

		lld1.removeFirst();
		lld1.removeFirst();
		// should be empty 
		passed = checkEmpty(true, lld1.isEmpty()) && passed;

		printTestStatus(passed);

	}

	public static void main(String[] args) {
		System.out.println("Running tests.\n");
		addIsEmptySizeTest();
		addRemoveTest();
	}
} 