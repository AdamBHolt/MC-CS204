import java.io.*;
import java.util.*;

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
	return dictionary.containsKey(wordToCheck);
	    }

    public ArrayList<String> checkWords(String wordsToCheck)
	    throws InvalidSpellingException
	    {
	ArrayList<String> returnWords = new ArrayList<>();
	StringTokenizer words = new StringTokenizer(wordsToCheck);
	String word = "";

	while(words.hasMoreTokens())
	{
	    word = words.nextToken();
	    if(!isValidWord(word))
		throw new InvalidSpellingException();
	    if(!checkWord(word))
		returnWords.add(word);
	}

	return returnWords;
	    }

    public void addWord(String wordToAdd) throws DuplicateWordException,
    InvalidSpellingException
    {
	if(!isValidWord(wordToAdd))
	    throw new InvalidSpellingException();
	if(checkWord(wordToAdd))
	    throw new DuplicateWordException();
	System.out.println("Adding " + wordToAdd);
	dictionary.put(wordToAdd, wordToAdd);
    }

    public boolean readDictionary(File input) throws DuplicateWordException,
    InvalidSpellingException
    {
	readBinaryFile(input);
	return true;

    }

    public boolean writeDictionary(File input) throws IOException
    {
	writeBinaryFile(input);
	return true;
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
	// TODO Auto-generated method stub
	return false;
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
