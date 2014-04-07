//package _solution;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;


/**
 * This interface specifies public methods for the SpellCheckerManager class.
 * This class uses a binary search tree to hold the words of a dictionary  
 * @author Professor Alexander, modified by Professor Myers
 *
 */
public interface SpellCheckerManagerInterface {			

	/**
	 * The checkWord method attempts to find its argument in a dictionary of words.
	 * @param wordToCheck a String
	 * @return true if the argument is found, false otherwise
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 */
	public boolean checkWord(String wordToCheck) throws InvalidSpellingException;
	
	/**
	 * The checkWords method attempts to find all the words in the string in a dictionary of words
	 * @param wordsToCheck a String of words to be checked
	 * @return an ArrayList of Strings of words not found in the dictionary of words, null if all words in dictionary
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 */
	public ArrayList<String> checkWords(String wordsToCheck) throws InvalidSpellingException;

	/**
	 * The addWord method adds its argument to a dictionary of words.  Presumably the user 
	 * has spell-checked the argument and found it missing from the dictionary, and wants
	 * to include it in the dictionary.
	 * @param wordToAdd the word to add to the dictionary
	 * @throws InvalidSpellingException if the word contain invalid characters
	 * @throws DuplicateWordException if the word is already in the dictionary
	 */
	public void addWord(String wordToAdd) throws DuplicateWordException, InvalidSpellingException;

	/**
	 * Reads from a file and populates the binary search tree
	 * Must determine if it is a text file or binary file.  We will use a very trivial
	 * determination - if the file ends in .bin it is a binary file, if it ends
	 * in .txt it is a text file.
	 * Use the private getExtension method
	 * @param input the file to read from
	 * @return true if successful read, false if file could not be read from
	 * @throws IOException if the file is not found or accessible
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 * @throws DuplicateWordException if of the words is already in the dictionary
	 */
	public boolean readDictionary(File input) throws DuplicateWordException, InvalidSpellingException;
	
	/**
	 * Writes binary search tree to a file
	 * Must determine if it is a text file or binary file.  We will use a very trivial
	 * determination - if the file ends in .bin it is a binary file, if it ends
	 * in .txt it is a text file.
	 * Use the private getExtension method
	 * @param output the file to write to
	 * @return true if successful write, false if file could not be written to
	 * @throws IOException if the file is not found or accessible
	 */
	public boolean writeDictionary(File input) throws IOException;
	
	/**
	 * Returns the extension of a file in the form FILENAME.xxx
	 * In this case the extention is the xxx
	 * @param file the name of the file
	 * @return the extention of the file
	 * 
	 * NOTE: This should be a private method, but private methods aren't allowed in
	 * interfaces.  I have given you the code for this method in comments.
	 */	
	//public String getExtension(File file) {
	//    String ext = null;
	//    String s = file.getName();
	//    int i = s.lastIndexOf('.');

	//    if (i > 0 &&  i < s.length() - 1) {
	//        ext = s.substring(i+1).toLowerCase();
	//    }
	//    return ext;
	//}
	    
	
	/**
	 * the listDictionary method creates a String with the words in the current dictionary to the console 
	 * in alphabetic order, one word per line
	 * @return a String that lists all the words in the dictionary in alphabetic order
	 */
	public String listDictionary();	
	
	/**
	 * Stores the binary search tree in text format, alphabetically
	 * @param output the file to write to
	 * @return true if successful write, false if file could not be written to
	 */
	public boolean writeTextFile(File output);
	
	/**
	 * Reads from a text file and populates the binary search tree
	 * @param input the file to read from
	 * @return true if successful read, false if file could not be read from
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 * @throws DuplicateWordException if of the words is already in the dictionary
	 */
	public boolean readTextFile(File input) throws DuplicateWordException, InvalidSpellingException;
	
	/**
	 * Stores the dictionary in a binary format
	 * @param output the location on the hard drive to store binary file
	 * @return true if successful write, false if file could not be written to
	 */
	public boolean writeBinaryFile(File output);
	
	/**
	 * Reads from a binary file and populates the binary search tree
	 * Must use the writeBinaryFile for this file before calling readBinaryFile for this file
	 * @param input the File to be read from
	 * @return true if successful read, false if file could not be read from
	 * @throws InvalidSpellingException if any of the words contain invalid characters
	 * @throws DuplicateWordException if of the words is already in the dictionary
	 */
	public boolean readBinaryFile(File input) throws DuplicateWordException, InvalidSpellingException;

}
