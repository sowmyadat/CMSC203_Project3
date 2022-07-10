import java.awt.dnd.DragGestureEvent;
import java.text.spi.DateFormatProvider;

import javax.imageio.plugins.tiff.FaxTIFFTagSet;

public class CryptoManager {
	
	private static final char LOWER_BOUND = ' ';
	private static final char UPPER_BOUND = '_';
	private static final int RANGE = UPPER_BOUND - LOWER_BOUND + 1;

	/**
	 * This method determines if a string is within the allowable bounds of ASCII codes 
	 * according to the LOWER_BOUND and UPPER_BOUND characters
	 * @param plainText a string to be encrypted, if it is within the allowable bounds
	 * @return true if all characters are within the allowable bounds, false if any character is outside
	 */
	public static boolean stringInBounds (String plainText) {
		boolean end = true;
		
		for(int i = 0; i < plainText.length(); i++) {
			if(!((int)plainText.charAt(i) >= LOWER_BOUND && (int)plainText.charAt(i) <= UPPER_BOUND)) {
				end = false;
			} 
		}
		
		return end;
	}

	/**
	 * Encrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in plainText is replaced by the character \"offset\" away from it 
	 * @param plainText an uppercase string to be encrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the encrypted string
	 */
	public static String encryptCaesar(String plainText, int key) {
		String encryption = "";
		char x;
		int intencrypt;
		
		if(stringInBounds(plainText) == true) {
			for(int i = 0; i < plainText.length(); i++) {
				x = plainText.charAt(i);
				intencrypt = ((int)x + key);
				
				while(intencrypt > UPPER_BOUND) {
					intencrypt -= RANGE;
				}
				
				encryption += (char)intencrypt;
			}
		}
		return encryption;
	}
	
	/**
	 * Encrypts a string according the Bellaso Cipher.  Each character in plainText is offset 
	 * according to the ASCII value of the corresponding character in bellasoStr, which is repeated
	 * to correspond to the length of plainText
	 * @param plainText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the encrypted string
	 */
	public static String encryptBellaso(String plainText, String bellasoStr) {
		String encryption = "";
		char x;
		int intencrypt;
		
		for(int i = 0; i < plainText.length(); i++) {
			x = plainText.charAt(i);
			int charAtValue = i % bellasoStr.length();
			intencrypt = ((int)x + (int)bellasoStr.charAt(charAtValue));
			
			while(intencrypt > (int)UPPER_BOUND) {
				intencrypt -= RANGE;
			}
			encryption += (char)intencrypt;
		}
		return encryption;
	}
	
	/**
	 * Decrypts a string according to the Caesar Cipher.  The integer key specifies an offset
	 * and each character in encryptedText is replaced by the character \"offset\" characters before it.
	 * This is the inverse of the encryptCaesar method.
	 * @param encryptedText an encrypted string to be decrypted.
	 * @param key an integer that specifies the offset of each character
	 * @return the plain text string
	 */
	public static String decryptCaesar(String encryptedText, int key) {
		String decryption = "";
		char x; 
		int intdecrypt;
		
		for(int i = 0; i < encryptedText.length(); i++) {
			x = encryptedText.charAt(i);
			intdecrypt = ((int)x - key);
			
			while(intdecrypt < LOWER_BOUND) {
				intdecrypt += RANGE;
			}
			decryption += (char)intdecrypt;
		}
		
		return decryption;
	}
	
	/**
	 * Decrypts a string according the Bellaso Cipher.  Each character in encryptedText is replaced by
	 * the character corresponding to the character in bellasoStr, which is repeated
	 * to correspond to the length of plainText.  This is the inverse of the encryptBellaso method.
	 * @param encryptedText an uppercase string to be encrypted.
	 * @param bellasoStr an uppercase string that specifies the offsets, character by character.
	 * @return the decrypted string
	 */
	public static String decryptBellaso(String encryptedText, String bellasoStr) {
		String decryption = ""; 
		char x;
		int intdecrypt;
		
		for(int i = 0; i < encryptedText.length(); i++) {
			x = encryptedText.charAt(i);
			int charAtValue = i % bellasoStr.length();
			intdecrypt = ((int)x - (int)bellasoStr.charAt(charAtValue));
			
			while(intdecrypt < (int)LOWER_BOUND) {
				intdecrypt += RANGE;
			}
			decryption += (char)intdecrypt;
		}
		return decryption;
	}
}
