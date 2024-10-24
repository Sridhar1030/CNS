import java.util.*;

// #24
public class cipher {

    // Encrypts text using a shift cipher
    public static StringBuffer encrypt(String m, int k) {
        StringBuffer encrypto = new StringBuffer();
        
        for (int i = 0; i < m.length(); i++) {
            char ch = m.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                // Wrap around the uppercase letters
                char encryptedChar = (char) (((ch - 'A' + k) % 26) + 'A');
                encrypto.append(encryptedChar);
            } else if (Character.isLowerCase(ch)) {
                // Wrap around the lowercase letters
                char encryptedChar = (char) (((ch - 'a' + k) % 26) + 'a');
                encrypto.append(encryptedChar);
            } else {
                // Keep other characters like spaces or punctuation unchanged
                encrypto.append(ch);
            }
        }

        return encrypto;
    }

    // Decrypts the cipher text
    public static StringBuffer decrypt(String temp, int k) {
        StringBuffer decrypto = new StringBuffer();

        for (int i = 0; i < temp.length(); i++) {
            char ch = temp.charAt(i);
            
            if (Character.isUpperCase(ch)) {
                // Correctly wrap around when decrypting uppercase letters
                char decryptedChar = (char) (((ch - 'A' - k + 26) % 26) + 'A');
                decrypto.append(decryptedChar);
            } else if (Character.isLowerCase(ch)) {
                // Correctly wrap around when decrypting lowercase letters
                char decryptedChar = (char) (((ch - 'a' - k + 26) % 26) + 'a');
                decrypto.append(decryptedChar);
            } else {
                // Keep other characters like spaces or punctuation unchanged
                decrypto.append(ch);
            }
        }

        return decrypto;
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input key
        System.out.println("Enter a key:");
        int k = sc.nextInt();
        sc.nextLine();  // Consume the newline

        // Input message
        System.out.println("Enter the text:");
        String m = sc.nextLine();

        // Encryption
        System.out.println("Text: " + m);
        System.out.println("Key: " + k);
        StringBuffer cipher = encrypt(m, k);
        System.out.println("Cipher: " + cipher);

        // Decryption
        StringBuffer decrypted = decrypt(cipher.toString(), k); // Use the encrypted text directly
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}
