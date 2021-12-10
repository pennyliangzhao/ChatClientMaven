package cipher;

//A Java Program to illustrate Caesar Cipher Technique
public class CaesarCipher {

	public static String encrypt (String s, int key) {

		String plaintext = s;
		int shift = key;
		String ciphertext = "";
		char alphabet;
		for(int i=0; i < plaintext.length();i++){
			// Shift one character at a time
			alphabet = plaintext.charAt(i);

			// if alphabet lies between a and z
			if(alphabet >= 'a' && alphabet <= 'z'){
				// shift alphabet
				alphabet = (char) (alphabet + shift);
				// if shift alphabet greater than 'z'
				if(alphabet > 'z') {
					// reshift to starting position
					alphabet = (char) (alphabet+'a'-'z'-1);
				}
				ciphertext = ciphertext + alphabet;
			}

			// if alphabet lies between 'A'and 'Z'
			else if(alphabet >= 'A' && alphabet <= 'Z') {
				// shift alphabet
				alphabet = (char) (alphabet + shift);

				// if shift alphabet greater than 'Z'
				if(alphabet > 'Z') {
					//reshift to starting position
					alphabet = (char) (alphabet+'A'-'Z'-1);
				}
				ciphertext = ciphertext + alphabet;
			}
			else {
				ciphertext = ciphertext + alphabet;
			}

		}
		return ciphertext;
	}

	public static String decrypt(String s, int key) {

		String ciphertext = s;
		int shift = key;
		String decryptMessage = "";
		for (int i = 0; i < ciphertext.length(); i++) {
			// Shift one character at a time
			char alphabet = ciphertext.charAt(i);
			// if alphabet lies between a and z
			if (alphabet >= 'a' && alphabet <= 'z') {
				// shift alphabet
				alphabet = (char) (alphabet - shift);

				// shift alphabet lesser than 'a'
				if (alphabet < 'a') {
					// reshift to starting position
					alphabet = (char) (alphabet - 'a' + 'z' + 1);
				}
				decryptMessage = decryptMessage + alphabet;
			}
			// if alphabet lies between A and Z
			else if (alphabet >= 'A' && alphabet <= 'Z') {
				// shift alphabet
				alphabet = (char) (alphabet - shift);

				// shift alphabet lesser than 'A'
				if (alphabet < 'A') {
					// reshift to starting position
					alphabet = (char) (alphabet - 'A' + 'Z' + 1);
				}
				decryptMessage = decryptMessage + alphabet;
			} else {
				decryptMessage = decryptMessage + alphabet;
			}
		}
		return decryptMessage;
	}
}
