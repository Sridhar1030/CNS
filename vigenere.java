import java.util.*;

public class vigenere {

    // Function to generate the key in a cyclic manner until its length matches the original text
    static String generateKey(String str, String key) {
        int x = str.length();
        StringBuilder keyBuilder = new StringBuilder(key);

        // Repeat the key to match the length of the input string
        for (int i = 0; keyBuilder.length() < str.length(); i++) {
            keyBuilder.append(key.charAt(i % key.length()));
        }
        return keyBuilder.toString();
    }

    // Function to encrypt the plaintext using the key
    static String cipherText(String str, String key) {
        StringBuilder cipher_text = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            // Shift the character by the value of the corresponding character in the key
            int x = (str.charAt(i) - 'A' + key.charAt(i) - 'A') % 26;
            x += 'A'; // Convert back to ASCII
            cipher_text.append((char) x);
        }
        return cipher_text.toString();
    }

    // Function to decrypt the ciphertext using the key
    static String originalText(String cipher_text, String key) {
        StringBuilder orig_text = new StringBuilder();

        for (int i = 0; i < cipher_text.length(); i++) {
            // Reverse the shift to get the original character
            int x = (cipher_text.charAt(i) - key.charAt(i) + 26) % 26;
            x += 'A'; // Convert back to ASCII
            orig_text.append((char) x);
        }
        return orig_text.toString();
    }

    // Function to convert a string to uppercase
    static String LowerToUpper(String s) {
        return s.toUpperCase(); // Directly use String's toUpperCase method
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input the string and keyword
        System.out.println("Enter the plaintext:");
        String Str = sc.nextLine();

        System.out.println("Enter the keyword:");
        String Keyword = sc.nextLine();

        // Convert to uppercase for consistency
        String str = LowerToUpper(Str);
        String keyword = LowerToUpper(Keyword);

        // Generate the key and cipher the text
        String key = generateKey(str, keyword);
        String cipher_text = cipherText(str, key);

        // Output the encrypted and decrypted text
        System.out.println("Ciphertext: " + cipher_text);
        System.out.println("Decrypted Text: " + originalText(cipher_text, key));

        sc.close(); // Close the scanner
    }
}