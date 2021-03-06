package edu.gatech.seclass.assignment3;

public class MyString {

	private String theString;
	public void setString(String str)
	{
		this.theString = str;
	}

	 // Returns the current string.
	public String getString()
	{
		return this.theString;
	}
	// Returns a string that consists of all and only the vowels in the current string.
	// Only letters a, e, i, o, and u (both lower and upper case) are considered vowels.
	// The returned string contains each occurrence of a vowel in the current string.
	public String getVowels() {
		String consonants = "BCDFGHJKLMNPQRSTVWXYZ";
		String vowels = null;
		//creates a string of all lower and upper case consonants
		consonants = consonants.concat(consonants.toLowerCase());
		vowels = this.theString.replaceAll("["+ consonants +"]" , "");
		return vowels;
	}

	// Returns a string that consists of the substring between start and end indexes (both included) in the current string.
	// Index 1 corresponds to the first character in the current string.
	public String getSubstring(int start, int end) {
		return this.theString.substring(start-1, end);
	}

	// Returns the index of the first occurrence of a character in the current string.
	// Index 1 corresponds to the first character in the current string.    
	public int indexOf(char c) {
		if(this.theString.indexOf(c)<0){
			return (this.theString.indexOf(c));
		} else {
			return (this.theString.indexOf(c))+1;			
		}
	}

	// Removes all occurrences of the specified character from the current string.
	public void removeChar(char c) {
		this.theString = this.theString.replaceAll("["+c+"]", "");
	}

	// Invert the current string.
	public void invert() {
		StringBuffer theStringBuffer = new StringBuffer(this.theString);
		this.theString = theStringBuffer.reverse().toString();
	}
}






