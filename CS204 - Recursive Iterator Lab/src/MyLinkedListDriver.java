public class MyLinkedListDriver {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyLinkedList myLinkedList = new MyLinkedList();
		myLinkedList.add("Sarah");
		myLinkedList.add("Barbara");
		myLinkedList.add("Tom");
		myLinkedList.add("George");
		String largest = myLinkedList.findLargest();
		System.out.println(largest);
		
		MyLinkedList studentLinkedList = new MyLinkedList();
		studentLinkedList.add("Ford");
		studentLinkedList.add("Arthur");
		studentLinkedList.add("Zaphod");
		studentLinkedList.add("Trillian");
		String studentLargest = studentLinkedList.findLargest();
		System.out.println(studentLargest);
	}

}