// CS 1501 Summer 2014
// Use this interface with Assignment 1 Part A and Part B

public interface DictInterface
{
	public boolean add(String s);
	/* Add a new String to the end of the DictInterface
	 */
	
	/* The method below could be defined with various parameters.
	 * However, in our program, we will only use the version with
	 * the StringBuilder argument shown below.  This is so that we
	 * don't have the overhead of converting back and forth between
	 * StringBuilder and String each time we add a new character
	 */
	public int searchPrefix(StringBuilder s);
	/* Returns 0 if s is not a word or prefix within the DictInterface
	 * Returns 1 if s is a prefix within the DictInterface but not a 
	 *         valid word
	 * Returns 2 if s is a word within the DictInterface but not a
	 *         prefix to other words
	 * Returns 3 if s is both a word within the DictInterface and a
	 *         prefix to other words
	 */

	public int searchPrefix(StringBuilder s, int start, int end);
	/* Same logic as method above.  However, now we can search a substring
	 * from start (inclusive) to end (inclusive) within the StringBuilder.
	 * This version will likely not be needed in your program.  If you do
	 * not use it you can simply implement it as an empty method.
	 */
}