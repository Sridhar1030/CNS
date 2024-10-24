import java.util.Scanner;

public class MonoalphabeticCipher {
    // Original alphabet and substitution key
    static final String ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    static final String KEY = "QWERTYUIOPASDFGHJKLZXCVBNM";  // Predefined substitution key

    // Encrypt the plaintext using the substitution key
    public static String encrypt(String plaintext) {
        StringBuilder encryptedText = new StringBuilder();

        for (char c : plaintext.toUpperCase().toCharArray()) {
            if (Character.isLetter(c)) {
                int index = ALPHABET.indexOf(c);
                encryptedText.append(KEY.charAt(index));
            } else {
                encryptedText.append(c);  // Keep non-alphabetic characters unchanged
            }
        }
        return encryptedText.toString();
    }

    // Decrypt the ciphertext using the substitution key
    public static String decrypt(String ciphertext) {
        StringBuilder decryptedText = new StringBuilder();

        for (char c : ciphertext.toCharArray()) {
            if (Character.isLetter(c)) {
                int index = KEY.indexOf(c);
                decryptedText.append(ALPHABET.charAt(index));
            } else {
                decryptedText.append(c);  // Keep non-alphabetic characters unchanged
            }
        }
        return decryptedText.toString();
    }

    // Main method
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the plaintext: ");
        String plaintext = sc.nextLine();

        String encryptedText = encrypt(plaintext);
        System.out.println("Encrypted Text: " + encryptedText);

        String decryptedText = decrypt(encryptedText);
        System.out.println("Decrypted Text: " + decryptedText);

        sc.close();
    }
}
