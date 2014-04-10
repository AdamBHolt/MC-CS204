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
	return dictionary.containsKey(wordToCheck);
    }

    @Override
    public ArrayList<String> checkWords(String wordsToCheck)
	    throws InvalidSpellingException
    {
	// TODO Auto-generated method stub
	return null;
    }

    public void addWord(String wordToAdd) throws DuplicateWordException,
	    InvalidSpellingException
    {
	dictionary.put(wordToAdd, wordToAdd);
    }

    public boolean readDictionary(File input) throws DuplicateWordException,
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
	    dictionary.put(word, word);
	}

	//Close the file
	inputFile.close();
	//Return true to indicate that the read process was successful
	return true;
    }

    @Override
    public boolean writeDictionary(File input) throws IOException
    {
	// TODO Auto-generated method stub
	return false;
    }

    public String listDictionary()
    {
	String returnString = "";
	
	for(Map.Entry<String,String> word : dictionary.entrySet())
	    returnString += word.getKey() + "\n";
	return returnString;
    }

    @Override
    public boolean writeTextFile(File output)
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean readTextFile(File input) throws DuplicateWordException,
	    InvalidSpellingException
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean writeBinaryFile(File output)
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean readBinaryFile(File input) throws DuplicateWordException,
	    InvalidSpellingException
    {
	// TODO Auto-generated method stub
	return false;
    }

}
