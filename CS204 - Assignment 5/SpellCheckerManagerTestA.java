import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;


public class SpellCheckerManagerTestA {
	private SpellCheckerManager mgr;
	private File testFile;
	private PrintWriter output;
	
	@Before
	public void setUp() throws Exception {
		testFile = new File("dictionaryTest.txt");
		try {
			output = new PrintWriter(testFile);
			output.println("alpha");
			output.println("bravo");
			output.println("charlie");
			output.println("delta");
			output.println("echo");
			output.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		mgr = new SpellCheckerManager();
		mgr.readDictionary(new File("dictionaryTest.txt"));
		
		//STUDENT - use another instance of SpellCheckerManager()
		// create a new file with different values,
		// write the file and then call readDictionary with your file
		// use this instance to test the STUDENT tests below
	}

	@After
	public void tearDown() throws Exception {
		mgr = null;
		testFile = null;
		output = null;
		//STUDENT - tear down your instance of SpellCheckerManager
		// and File objects
	}

	@Test
	public void testCheckWord() {
		try {
			assertEquals(true, mgr.checkWord("alpha"));
			assertEquals(true, mgr.checkWord("bravo"));
			assertEquals(true, mgr.checkWord("delta"));
			//the word "zulu" should not be in the dictionary at this point (see setup method)
			assertEquals(false, mgr.checkWord("zulu"));
		} catch (InvalidSpellingException e) {
			fail("This statement should not have thrown an InvalidSpellingException");
		}	
		
	}
	
	@Test
	public void testCheckWordSTUDENT() {
		fail("Not yet implemented");
	}
	
	/**
	 * testCheckInvalidWord tests for invalid characters in words being checked 
	 * only lower- and upper-case letters are valid for this project
	 */
	@Test
	public void testCheckInvalidWord() {
		try {
			assertEquals(true, mgr.checkWord("this_should_fail"));
			fail("This statement should have thrown an InvalidSpellingException");
		} catch (InvalidSpellingException e) {
			System.out.println("Correctly threw an InvalidSpellingException for an invalid value: "+e);
		}
		try {
			assertEquals(true, mgr.checkWord("this should also fail"));
			fail("This statement should have thrown an InvalidSpellingException");
		} catch (InvalidSpellingException e) {
			System.out.println("Correctly threw an InvalidSpellingException for an invalid value: "+e);
		}
	}
	
	/**
	 * testCheckDuplicateWord tests for attempting to add a duplicate word to the dictionary
	 */
	@Test
	public void testCheckDuplicateWord() {
		try {
			mgr.addWord("testWordOne");
			mgr.addWord("testWordOne"); 
			fail("This statement should have thrown a DuplicateWordException");
		} catch (DuplicateWordException e) {
			System.out.println("Correctly threw a DuplicateWordException: "+e);
		} catch (InvalidSpellingException e) {
			fail("This statement should not have thrown an InvalidSpellingException");
		} 
	}
	
	@Test
	public void testAddWord() {
		try {
			mgr.addWord("testWordOne");
			mgr.addWord("testWordTwo"); 
			mgr.addWord("testWordThree");
			assertEquals(true, mgr.checkWord("testWordOne"));
			assertEquals(true, mgr.checkWord("testWordTwo"));
			assertEquals(true, mgr.checkWord("testWordThree"));
			assertEquals(false, mgr.checkWord("testWordFour"));
		} catch (DuplicateWordException e) {
			fail("This statement should not have thrown a DuplicateWordException");
		} catch (InvalidSpellingException e) {
			fail("This statement should not have thrown an InvalidSpellingException");
		} 
	}

	@Test
	public void testAddWordSTUDENT() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadTextFile() {
		testFile = new File("addDictionaryTest.txt");
		try {
			output = new PrintWriter(testFile);
			//the first five words of the phonetic alphabet are already added in the setup method
			output.println("foxtrot");
			output.println("golf");
			output.println("hotel");
			output.println("india");
			output.println("juliet");
			output.println("kilo");
			output.println("lima");
			output.println("mike");
			output.println("november");
			output.println("oscar");
			output.println("papa");
			output.println("quebec");
			output.println("romeo");
			output.println("sierra");
			output.println("tango");
			output.println("uniform");
			output.println("victor");
			output.println("whiskey");
			output.println("xray");
			output.println("yankee");
			output.println("zulu");
			output.close();
		} catch (FileNotFoundException e) {
			fail("This statement should not have thrown a FileNotFoundException");		
		}
		try {
			assertEquals(true, mgr.readDictionary(new File("addDictionaryTest.txt")));
			assertEquals(true, mgr.checkWord("zulu"));
		} catch (DuplicateWordException e) {
			fail("These statements should not have thrown a DuplicateWordException: "+e);
		} catch (InvalidSpellingException e) {
			fail("These statements should not have thrown an InvalidSpellingException: "+e);
		}
		//this section tests adding invalid characters from the addDictionary method
		testFile = new File("addInvalidWordsDictTest.txt");
		try {
			output = new PrintWriter(testFile);
			output.println("Dvorák");
			output.println("this_should_fail");
			output.println("this should also fail");
			output.close();
		} catch (FileNotFoundException e) {
			fail("This statement should not have thrown a FileNotFoundException");
		}
		try {
			assertEquals(true, mgr.readDictionary(new File("addInvalidWordsDictTest.txt")));
			assertEquals(true, mgr.checkWord("this_should_fail"));
			fail("Should have thrown an InvalidSpellingException");
		} catch (DuplicateWordException e) {
			fail("Should not have thrown a DuplicateWordException");
		} catch (InvalidSpellingException e) {
			System.out.println("This is an invalid value: "+e);
		} catch (Exception e) {
			e.printStackTrace();
			fail("This statement should have thrown an InvalidSpellingException");
		}
	}


	@Test
	public void testListDictionary() {
		String result = mgr.listDictionary();
		Scanner scan = new Scanner(result);
		assertEquals("alpha",scan.nextLine());
		assertEquals("bravo",scan.nextLine());
		assertEquals("charlie",scan.nextLine());
		assertEquals("delta",scan.nextLine());
		assertEquals("echo",scan.nextLine());
		scan.close();
	}

	@Test
	public void testCheckWords() {
		ArrayList<String> result;
		populateDictionary();

		try {
			//all words in dictionary - returns null
			result = mgr.checkWords("kilo india victor alpha yankee");
			assertEquals(null,result);
		} catch (InvalidSpellingException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a InvalidSpellingException");
		}
		
		//check for words not in the dictionary
		try {
			result = mgr.checkWords("victor alpha abbra cadabbra");
			assertEquals("abbra", result.get(0));
			assertEquals("cadabbra", result.get(1));
		} catch (InvalidSpellingException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a InvalidSpellingException");
		}
	}

	@Test
	public void testCheckWordsSTUDENT() {
		fail("Not yet implemented");
	}
	
	@Test
	public void testReadandWriteDictionaryBinary() {
		//test read and write binary files
		ArrayList<String> result;
		File binFile = new File("BinaryTest.bin");
		populateDictionary();
		try {
			assertEquals(true,mgr.writeDictionary(binFile));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a IOException");
		}
		mgr = null;
		mgr = new SpellCheckerManager();
		try {
			mgr.readDictionary(binFile);
			//all words in dictionary - returns null
			result = mgr.checkWords("kilo india victor alpha yankee");
			assertEquals(null,result);
			
		} catch (DuplicateWordException | InvalidSpellingException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a InvalidSpellingException or DuplicateWordException");
		}
	}

	@Test
	public void testReadandWriteDictionaryText() {
		ArrayList<String> result;
		File textFile = new File("TextTest.txt");
		populateDictionary();
		mgr.writeTextFile(textFile);
		mgr = null;
		mgr = new SpellCheckerManager();
		try {
			mgr.readTextFile(textFile);
			result = mgr.checkWords("kilo india victor alpha yankee");
			assertEquals(null,result);
		} catch (DuplicateWordException | InvalidSpellingException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a InvalidSpellingException or DuplicateWordException");
		}
	}
	
	@Test
	public void testWriteandReadTextFile() {
		ArrayList<String> result;
		File textFile = new File("TextTest.txt");
		populateDictionary();
		mgr.writeTextFile(textFile);
		mgr = null;
		mgr = new SpellCheckerManager();
		try {
			mgr.readTextFile(textFile);
			result = mgr.checkWords("kilo india victor alpha yankee");
			assertEquals(null,result);
		} catch (DuplicateWordException | InvalidSpellingException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a InvalidSpellingException or DuplicateWordException");
		}
		
	}

	
	@Test
	public void testWriteAndReadBinaryFile() {
		ArrayList<String> result;
		File binFile = new File("BinaryTest.bin");
		populateDictionary();
		assertEquals(true,mgr.writeBinaryFile(binFile));
		mgr = null;
		mgr = new SpellCheckerManager();
		try {
			mgr.readBinaryFile(binFile);
			//all words in dictionary - returns null
			result = mgr.checkWords("kilo india victor alpha yankee");
			assertEquals(null,result);
			
		} catch (DuplicateWordException | InvalidSpellingException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a InvalidSpellingException or DuplicateWordException");
		}
		
	}

	public void populateDictionary(){
		testFile = new File("addDictionaryTest.txt");
		try {
			output = new PrintWriter(testFile);
			//the first five words of the phonetic alphabet are already added in the setup method
			output.println("foxtrot");
			output.println("golf");
			output.println("hotel");
			output.println("india");
			output.println("juliet");
			output.println("kilo");
			output.println("lima");
			output.println("mike");
			output.println("november");
			output.println("oscar");
			output.println("papa");
			output.println("quebec");
			output.println("romeo");
			output.println("sierra");
			output.println("tango");
			output.println("uniform");
			output.println("victor");
			output.println("whiskey");
			output.println("xray");
			output.println("yankee");
			output.println("zulu");
			output.close();
			assertEquals(true, mgr.readDictionary(new File("addDictionaryTest.txt")));
		} catch (FileNotFoundException e) {
			fail("This statement should not have thrown a FileNotFoundException");		
		} catch (DuplicateWordException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a DuplicateWordException");
		} catch (InvalidSpellingException e) {
			// TODO Auto-generated catch block
			fail("This statement should not have thrown a FileNotFoundException");
		}
	
			

		
	}
}
