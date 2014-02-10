import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.InputMismatchException;
import java.util.Scanner;

import javax.swing.JFileChooser;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class SudokuBoardManagerTest {

	private SudokuBoardManagerInterface myBoard;
	private SudokuBoardManagerInterface studentBoard;
	private File newFile;
	private File studentFile;
	private PrintWriter output;
	
	@Before
	public void setUp() {
		newFile = new File("sudokuTest");
		studentFile = new File("studentTest");
		
		try {
			output = new PrintWriter(newFile);
			output.println("8,0,0,3,0,9,0,0,5");
			output.println("0,0,0,0,2,0,0,0,0");
			output.println("5,0,0,6,0,8,0,0,3");
			output.println("0,7,5,9,0,3,4,6,0");
			output.println("0,0,1,0,0,0,7,0,0");
			output.println("0,3,8,7,0,4,2,5,0");
			output.println("6,0,0,4,0,1,0,0,2");
			output.println("0,0,0,0,9,0,0,0,0");
			output.println("3,0,0,5,0,7,0,0,4");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		myBoard = new SudokuBoardManager();
		myBoard.newGame(newFile.getAbsoluteFile());
		
		try {
			output = new PrintWriter(studentFile);
			output.println("0,0,0,6,0,7,0,4,1");
			output.println("6,0,0,0,0,2,0,0,0");
			output.println("8,3,7,1,0,0,6,2,9");
			output.println("0,7,0,4,0,0,0,0,0");
			output.println("0,8,5,0,3,0,2,1,0");
			output.println("0,0,0,0,0,6,0,7,0");
			output.println("2,6,3,0,0,1,7,9,5");
			output.println("0,0,0,2,0,0,0,0,8");
			output.println("5,4,0,9,0,3,0,0,0");
			output.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
		studentBoard = new SudokuBoardManager();
		studentBoard.newGame(studentFile.getAbsoluteFile());	
	}


	@Test
	public void testSetValueAt() {
			try {
				assertEquals(myBoard.getValueAt(2,2),0);
				myBoard.setValueAt(2,2,4);
				assertEquals(myBoard.getValueAt(2,2),4);
				myBoard.setValueAt(2,2,8);
				fail("This statement should have thrown a ValueNotValidException");
			} catch (InputOutOfRangeException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("This statement should have thrown a ValueNotValidException");
			} catch (ValueNotValidException e) {
				// TODO Auto-generated catch block
				System.out.println("This is an invalid value");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				fail("This statement should have thrown a ValueNotValidException");
			}
			
			try {
				myBoard.setValueAt(10,2,8);
				fail("This statement should have thrown a InputOutOfRangeException");
			} catch (InputOutOfRangeException e) {
				System.out.println("This is an invalid row");
				// TODO Auto-generated catch block
			} catch (ValueNotValidException e) {
				// TODO Auto-generated catch block
				fail("This statement should have thrown a InputOutOfRangeException");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail("This statement should have thrown a InputOutOfRangeException");
			}
		

	}
	
	@Test
	public void testSetValueAtSTUDENT() {
		try {
			assertEquals(studentBoard.getValueAt(1,1),0);
			studentBoard.setValueAt(1,1,9);
			assertEquals(studentBoard.getValueAt(1,1),9);
			studentBoard.setValueAt(1,1,1);
			fail("This statement should have thrown a ValueNotValidException");
		} catch (InputOutOfRangeException e) {
			e.printStackTrace();
			fail("This statement should have thrown a ValueNotValidException");
		} catch (ValueNotValidException e) {
			System.out.println("This is an invalid value");
		} catch (Exception e) {
			e.printStackTrace();
			fail("This statement should have thrown a ValueNotValidException");
		}
		
		try {
			studentBoard.setValueAt(0,0,5);
			fail("This statement should have thrown a InputOutOfRangeException");
		} catch (InputOutOfRangeException e) {
			System.out.println("This is an invalid location");
		} catch (ValueNotValidException e) {
			fail("This statement should have thrown a InputOutOfRangeException");
		} catch (Exception e) {
			fail("This statement should have thrown a InputOutOfRangeException");
		}
	}

	@Test
	public void testGetValueAt() {
		try {
			assertEquals(myBoard.getValueAt(1,1),8);
			assertEquals(myBoard.getValueAt(7,7),0);
			myBoard.getValueAt(5,10);
			fail("This statement should have thrown an InputOutOfRangeException");
		} catch (InputOutOfRangeException e) {
			// TODO Auto-generated catch block
			System.out.println("This is an invalid column value");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This statement should have thrown an InputOutOfRangeException");
		}
	}

	@Test
	public void testToString() {
		String boardString, resultString;
		boardString = myBoard.toString();
		resultString = boardString.substring(0,17);
		assertEquals(resultString,"8,0,0,3,0,9,0,0,5");
		resultString = boardString.substring(72,89);
		assertEquals(resultString,"0,0,1,0,0,0,7,0,0");
	}

	@Test
	public void testDisplayPossibleValues() {
		int[] results;
		try {
			results = myBoard.displayPossibleValues(2, 2);
			assertEquals(results[0],1);
			assertEquals(results[1],4);
			assertEquals(results[2],6);
			assertEquals(results[3],9);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown an Exception");
		}
		
		try {
			results = myBoard.displayPossibleValues(8, 8);
			assertEquals(results[0],1);
			assertEquals(results[1],3);
			assertEquals(results[2],7);
			assertEquals(results[3],8);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown an Exception");
		}
	}
	
	@Test
	public void testDisplayPossibleValuesSTUDENT() {
		int[] valid;
		try {
			valid = studentBoard.displayPossibleValues(4, 1);
			assertEquals(valid[0],1);
			assertEquals(valid[1],3);
			assertEquals(valid[2],9);
		} catch (Exception e) {
			fail("This statement should not have thrown an Exception");
		}
		
		try {
			valid = studentBoard.displayPossibleValues(9, 9);
			assertEquals(valid[0],2);
			assertEquals(valid[1],6);
		} catch (Exception e) {
			fail("This statement should not have thrown an Exception");
		}
	}

}
