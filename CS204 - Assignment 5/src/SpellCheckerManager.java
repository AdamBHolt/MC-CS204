import java.io.*;
import java.util.*;


public class SpellCheckerManager implements SpellCheckerManagerInterface
{
    private TreeMap<String, String> dictionary;
    
    public SpellCheckerManager()
    {
	dictionary = new TreeMap<>();
    }
    
    @Override
    public boolean checkWord(String wordToCheck)
	    throws InvalidSpellingException
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public ArrayList<String> checkWords(String wordsToCheck)
	    throws InvalidSpellingException
    {
	// TODO Auto-generated method stub
	return null;
    }

    @Override
    public void addWord(String wordToAdd) throws DuplicateWordException,
	    InvalidSpellingException
    {
	// TODO Auto-generated method stub
	
    }

    @Override
    public boolean readDictionary(File input) throws DuplicateWordException,
	    InvalidSpellingException
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public boolean writeDictionary(File input) throws IOException
    {
	// TODO Auto-generated method stub
	return false;
    }

    @Override
    public String listDictionary()
    {
	// TODO Auto-generated method stub
	return null;
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
