

import java.io.File;

public interface PrimeFinderManagerInterface {
	
	/**
	 * the runPrimeFinder method parses the input string, expecting integers separated
	 * by single spaces, parses each separated string into an integer, adds them to
	 * an ArrayList of integers, and calls the Client constructor with that list
	 * @param strNumbers - a string that expects to be parsed into integers 
	 * each separated by single spaces
	 * @throws NumberFormatException if any of the character strings cannot be
	 * parsed to an integer after parsing them by " "
	 */
	public void runPrimeFinder(String strNumbers) throws NumberFormatException;
	
	/**
	 * the writePrimes method writes the list of primes found to a text file
	 * @param outFile an instance of File to which the list of primes will be written
	 */
	public void writePrimes(File outFile);
	
	

}
