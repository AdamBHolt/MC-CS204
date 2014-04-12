import java.io.*;
import java.util.*;

/**
 * This class is the data manager for a program that checks the spelling of words against a dictionary of words
 * @author Adam Holt
 * @date 4/13/14
 * @class CS204
 * @time 12:00 MW
 */
public class SpellCheckerManager implements SpellCheckerManagerInterface
{
    //Binary search tree to store the words in the dictionary
    private TreeMap<String, String> dictionary;

    /**
     * Default constructor
     */
    public SpellCheckerManager()
    {
	//Instantiate the binary search tree
	dictionary = new TreeMap<>();
    }

    /**
     * Check to see if a word is in the dictionary
     * @param wordToCheck Word to search the dictionary for
     * @return True if the word is in the dictionary, otherwise false
     * @throws InvalidSpellingException
     */
    public boolean checkWord(String wordToCheck) throws InvalidSpellingException
    {
	//If the word contains non-letters throw and exception
	if(!isValidWord(wordToCheck))
	    throw new InvalidSpellingException();
	//Return the results of the containsKey method of the dictionary
	return dictionary.containsKey(wordToCheck.toLowerCase());
    }

    /**
     * Check to see if a list of words is in the dictionary
     * @param wordsToCheck String of words to search the dictionary for
     * @return ArrayList containing Strings of any words not found in the dictionary
     * @throws InvalidSpellingException
     */
    public ArrayList<String> checkWords(String wordsToCheck) throws InvalidSpellingException
    {
	//ArrayList to return any misspelled words
	ArrayList<String> returnWords = new ArrayList<>();
	//Tokenizer to split the string of words
	StringTokenizer words = new StringTokenizer(wordsToCheck);
	//String to hold each word token
	String word = "";
	//The number of misspelled words found
	int errors = 0;

	//Repeat while there are still tokens in the String
	while(words.hasMoreTokens())
	{
	    //Set the word to be the next token in the String
	    word = words.nextToken();
	    //If the word contains non-letters throw and exception
	    if(!isValidWord(word))
		throw new InvalidSpellingException();
	    //IF the word is not in the dictionary add it to the ArrayList to be returned
	    //And increment errors
	    if(!checkWord(word))
	    {
		returnWords.add(word);
		errors++;
	    }
	}
	//If no misspelled words were found return null to indicate no errors
	if(errors==0)
	    return null;
	//Otherwise return the ArrayList of words
	return returnWords;
    }

    /**
     * Add a word to the dictionary
     * @param wordToAdd Word to be added to the dictionary
     * @throws DuplicateWordException
     * @throws InvalidSpellingException
     */
    public void addWord(String wordToAdd) throws DuplicateWordException, InvalidSpellingException
    {
	//If the word contains non-letters throw and exception
	if(!isValidWord(wordToAdd))
	    throw new InvalidSpellingException();
	//If the word is already in the dictionary throw an exception
	if(checkWord(wordToAdd))
	    throw new DuplicateWordException();
	//Add the word converted to lower case letters to the dictionary
	dictionary.put(wordToAdd.toLowerCase(), wordToAdd.toLowerCase());
    }

    /**
     * Read a file to add words to the dictionary
     * @param input Name of the file to be read
     * @return True if the file is read successfully
     * @throws DuplicateWordException
     * @throws InvalidSpellingException
     */
    public boolean readDictionary(File input) throws DuplicateWordException,InvalidSpellingException
    {
	//If the extension of the file to be read is txt call the readTextFile method
	if(getExtension(input).equals("txt"))
	{
	    try
	    {
		return readTextFile(input);
	    }
	    //If an exception is thrown from the called method throw is to the gui
	    catch (DuplicateWordException e)
	    {
		throw e;
	    }
	    catch (InvalidSpellingException e)
	    {
		throw e;
	    }
	}
	//If the extension of the file to be read is bin call the readBinaryFile method
	else if(getExtension(input).equals("bin"))
	{
	    try
	    {
		return readBinaryFile(input);
	    }
	    //If an exception is thrown from the called method throw is to the gui
	    catch (DuplicateWordException e)
	    {
		throw e;
	    }
	    catch (InvalidSpellingException e)
	    {
		throw e;
	    }
	}
	//Otherwise return false
	else return false;
    }

    /**
     * Write the current dictionary to a file
     * @param output File to write to
     * @throws IOException
     */
    public boolean writeDictionary(File output) throws IOException
    {
	//If the extension of the file to be written to is txt call the writeTextFile method
	if(getExtension(output).equals("txt"))
	    return writeTextFile(output);

	//If the extension of the file to be written to is bin call the writeBinFile method
	else if(getExtension(output).equals("bin"))
	    return writeBinaryFile(output);

	//Otherwise return false
	else return false;
    }

    /**
     * Get the contents of the current dictionary
     * @return String representation of all words in the current dictionary
     */
    public String listDictionary()
    {
	//String to be returned
	String returnString = "";

	//Iterate through each word in the dictionary and add it to the return String
	for(Map.Entry<String,String> word : dictionary.entrySet())
	    returnString += word.getKey() + "\n";
	//Return the String
	return returnString;
    }

    /**
     * Write the current dictionary to a .txt file
     * @param output File to be written to
     * @return true if the write operation is successful, otherwise false
     */
    public boolean writeTextFile(File output)
    {
	//PrintWriter to write to file
	PrintWriter writer = null;

	//Try to create a PrintWriter based on the passed File object
	try
	{
	    writer = new PrintWriter(output.toString());
	} 
	catch (FileNotFoundException e)
	{
	    //If the PrintWriter cannot be created return false
	    return false;
	}

	//Write each Person object to the text file
	for(Map.Entry<String,String> word : dictionary.entrySet())
	    writer.println(word.getKey());

	//Close the print writer
	writer.close();

	//Return true to indicate that the write process was successful
	return true;
    }

    /**
     * Read from a .txt file of words to add to the dictionary
     * @param input File to be read
     * @return True if the read process was successful
     * @throws DuplicateWordException
     * @throws InvalidSpellingException
     */
    public boolean readTextFile(File input) throws DuplicateWordException,InvalidSpellingException
    {
	//Scanner to read from the selected file
	Scanner inputFile=null;
	//Temporary String
	String word = "";

	//Try to create a new Scanner
	try
	{
	    inputFile = new Scanner(input);
	}
	catch (FileNotFoundException e)
	{
	    //Return false if an exception is thrown
	    return false;
	}

	//Read each line in the text file
	while(inputFile.hasNext())
	{
	    //Set the word to the next line in the text file
	    word = inputFile.nextLine();

	    //If the word is not valid close the file and throw and exception
	    if(!isValidWord(word))
	    {
		inputFile.close();
		throw new InvalidSpellingException();
	    }
	    //If the word is already in the dictionary close the file and throw an exception
	    if(checkWord(word))
	    {
		inputFile.close();
		throw new DuplicateWordException();
	    }
	    //Add the word to the dictionary
	    addWord(word);
	}

	//Close the file
	inputFile.close();
	//Return true to indicate that the read process was successful
	return true;
    }

    /**
     * Write the current dictionary to a .bin file
     * @param output File to be written to
     * @return true if the write operation is successful, otherwise false
     */
    public boolean writeBinaryFile(File output)
    {
	//FileOutputStream object to write the dictionary to
	FileOutputStream fileOut = null;
	//OutputObjectStream for the dictionary
	ObjectOutputStream objectOut = null;

	try
	{
	    //Instantiate fileOut based on the output file
	    fileOut = new FileOutputStream(output);
	    //Instantiate objectOut based on fileOut
	    objectOut = new ObjectOutputStream(fileOut);
	    //Write the dictionary to the output stream
	    objectOut.writeObject(dictionary);
	    //Close the output streams
	    objectOut.close();
	    fileOut.close();
	}
	//If an exception is thrown return false to indicate that the write process failed
	catch(FileNotFoundException e)
	{
	    return false;
	}
	catch (IOException e)
	{
	    return false;
	}   

	//Otherwise return true
	return true;
    }

    /**
     * Read from a .bin file of words to add to the dictionary
     * @param input File to be read
     * @return True if the read process was successful
     * @throws DuplicateWordException
     * @throws InvalidSpellingException
     */
    public boolean readBinaryFile(File input) throws DuplicateWordException,InvalidSpellingException
    {
	//FileInputStream object to read the binary file
	FileInputStream fileIn = null;
	//ObjectInputStream object to read the dictionary object
	ObjectInputStream objectIn = null;
	//Temporary dictionary to hold the contents of the file
	TreeMap<String, String> tempDict;

	try
	{
	    //Instantiate fileIn object based on the input file
	    fileIn = new FileInputStream(input);
	    //Instantiate objectIn based on fileIn
	    objectIn = new ObjectInputStream(fileIn);

	    try
	    {
		//Set the temporary dictionary to the new tree object
		tempDict = (TreeMap<String,String>)objectIn.readObject();

		//Check through each word of the temporary dictionary
		for(Map.Entry<String,String> word : tempDict.entrySet())
		{
		    //If the word is not valid close the file and throw and exception
		    if(!isValidWord(word.getKey()))
		    {
			objectIn.close();
			fileIn.close();
			throw new InvalidSpellingException();
		    }
		    //If the word is already in the dictionary close the file and throw an exception
		    if(checkWord(word.getKey()))
		    {
			objectIn.close();
			fileIn.close();
			throw new DuplicateWordException();
		    }
		    else
			//Add the word to the dictionary
			addWord(word.getKey());
		}
	    }
	    //If an exception is thrown close the input stream objects and return false
	    catch (ClassNotFoundException e)
	    {
		objectIn.close();
		fileIn.close();
		return false;
	    }
	    //Close the input streams
	    objectIn.close();
	    fileIn.close();
	}
	//If an exception is thrown return false
	catch (FileNotFoundException e)
	{
	    return false;
	}
	catch (IOException e)
	{
	    return false;
	}
	//If the read procedure is successful return true
	return true;
    }

    /**
     * Determine if a word only contains letters
     * @param word Word to be checked
     * @return True if the word is valid, otherwise false
     */
    private boolean isValidWord(String word)
    {
	//Check each character in the word, if any non-letter is found return false
	for(int i=0; i<word.length(); i++)
	    if(!Character.isLetter(word.charAt(i)))
		return false;
	//If no non-letter is found return true
	return true;
    }

    /**
     * Returns the extension of a file in the form FILENAME.xxx
     * In this case the extension is the xxx
     * @param file the name of the file
     * @return the extension of the file
     */
    private String getExtension(File file) 
    {
	String ext = null;
	String s = file.getName();
	int i = s.lastIndexOf('.');

	if (i > 0 &&  i < s.length() - 1) {
	    ext = s.substring(i+1).toLowerCase();
	}
	return ext;
    }
}