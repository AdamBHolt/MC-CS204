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
    private TreeMap<String, String> dictionary;

    public SpellCheckerManager()
    {
	dictionary = new TreeMap<>();
    }

    public boolean checkWord(String wordToCheck)
	    throws InvalidSpellingException
	    {
	if(!isValidWord(wordToCheck))
	    throw new InvalidSpellingException();
	//System.out.println(wordToCheck + " : " + dictionary.containsKey(wordToCheck));
	return dictionary.containsKey(wordToCheck.toLowerCase());
	    }

    public ArrayList<String> checkWords(String wordsToCheck) throws InvalidSpellingException
    {
	ArrayList<String> returnWords = new ArrayList<>();
	StringTokenizer words = new StringTokenizer(wordsToCheck);
	String word = "";
	int errors = 0;

	while(words.hasMoreTokens())
	{
	    word = words.nextToken();
	    if(!isValidWord(word))
		throw new InvalidSpellingException();
	    if(!checkWord(word))
	    {
		returnWords.add(word);
		errors++;
	    }
	}
	if(errors==0)
	    return null;
	return returnWords;
    }

    public void addWord(String wordToAdd) throws DuplicateWordException,
    InvalidSpellingException
    {
	if(!isValidWord(wordToAdd))
	    throw new InvalidSpellingException();
	if(checkWord(wordToAdd))
	    throw new DuplicateWordException();
	dictionary.put(wordToAdd.toLowerCase(), wordToAdd.toLowerCase());
    }

    public boolean readDictionary(File input) throws DuplicateWordException,
    InvalidSpellingException
    {
	if(input.toString().substring(input.toString().length()-4).equals(".txt"))
	{
	    readTextFile(input);
	    return true;
	}
	else if(input.toString().substring(input.toString().length()-4).equals(".bin"))
	{
	    readBinaryFile(input);
	    return true;
	}
	else return false;
    }

    public boolean writeDictionary(File input) throws IOException
    {
	if(input.toString().substring(input.toString().length()-4).equals(".txt"))
	{
	    writeTextFile(input);
	    return true;
	}
	else if(input.toString().substring(input.toString().length()-4).equals(".bin"))
	{
	    writeBinaryFile(input);
	    return true;
	}
	else return false;
    }

    public String listDictionary()
    {
	String returnString = "";

	for(Map.Entry<String,String> word : dictionary.entrySet())
	    returnString += word.getKey() + "\n";
	return returnString;
    }

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

    public boolean readTextFile(File input) throws DuplicateWordException,
    InvalidSpellingException
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
	    word = inputFile.nextLine();
	    if(!isValidWord(word))
	    {
		inputFile.close();
		throw new InvalidSpellingException();
	    }
	    if(checkWord(word))
	    {
		inputFile.close();
		throw new DuplicateWordException();
	    }
	    
	    addWord(word);
	}

	//Close the file
	inputFile.close();
	//Return true to indicate that the read process was successful
	return true;
    }

    public boolean writeBinaryFile(File output)
    {
	FileOutputStream fileOut = null;
	ObjectOutputStream objectOut = null;

	try
	{
	    fileOut = new FileOutputStream(output);
	    objectOut = new ObjectOutputStream(fileOut);
	    objectOut.writeObject(dictionary);
	    objectOut.close();
	    fileOut.close();
	}
	catch(FileNotFoundException e)
	{
	    return false;
	}
	catch (IOException e)
	{
	    return false;
	}   

	return true;
    }

    public boolean readBinaryFile(File input) throws DuplicateWordException,
    InvalidSpellingException
    {
	FileInputStream fileIn = null;
	ObjectInputStream objectIn = null;

	try
	{
	    fileIn = new FileInputStream(input);
	    objectIn = new ObjectInputStream(fileIn);

	    try
	    {
		dictionary = (TreeMap<String,String>)objectIn.readObject();
	    } catch (ClassNotFoundException e)
	    {
		objectIn.close();
		fileIn.close();
		return false;
	    }
	    objectIn.close();
	    fileIn.close();
	}
	catch (FileNotFoundException e)
	{
	    return false;
	}
	catch (IOException e)
	{
	    return false;
	}

	return true;
    }

    private boolean isValidWord(String word)
    {
	for(int i=0; i<word.length(); i++)
	    if(!Character.isLetter(word.charAt(i)))
		return false;
	return true;
    }
}