import java.util.Scanner;

public class HillCipher {
    public static int[][] keymat = new int[2][2];  // 2x2 key matrix
    public static int[][] invkeymat = new int[2][2];  // 2x2 inverse matrix
    public static String key = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";

    // Encoding method
    private static String encode(char c1, char c2) {
        int v1 = c1 - 'A';
        int v2 = c2 - 'A';

        int e1 = (v1 * keymat[0][0] + v2 * keymat[0][1]) % 26;
        int e2 = (v1 * keymat[1][0] + v2 * keymat[1][1]) % 26;

        return "" + key.charAt(e1) + key.charAt(e2);
    }

    // Decoding method
    private static String decode(char c1, char c2) {
        int v1 = c1 - 'A';
        int v2 = c2 - 'A';

        int d1 = (v1 * invkeymat[0][0] + v2 * invkeymat[0][1]) % 26;
        int d2 = (v1 * invkeymat[1][0] + v2 * invkeymat[1][1]) % 26;

        d1 = (d1 + 26) % 26;  // Ensure positive result
        d2 = (d2 + 26) % 26;

        return "" + key.charAt(d1) + key.charAt(d2);
    }

    // Method to calculate the inverse matrix (mod 26)
    private static void calculateInverseMatrix() {
        int det = keymat[0][0] * keymat[1][1] - keymat[0][1] * keymat[1][0];
        det = (det % 26 + 26) % 26;  // Ensure positive determinant mod 26

        int invDet = modInverse(det, 26);  // Find modular inverse of determinant
        if (invDet == -1) {
            System.out.println("Inverse does not exist for the given key matrix.");
            System.exit(1);
        }

        // Calculate the inverse matrix (mod 26)
        invkeymat[0][0] = (keymat[1][1] * invDet) % 26;
        invkeymat[0][1] = (-keymat[0][1] * invDet + 26) % 26;
        invkeymat[1][0] = (-keymat[1][0] * invDet + 26) % 26;
        invkeymat[1][1] = (keymat[0][0] * invDet) % 26;
    }

    // Helper method to calculate modular inverse
    private static int modInverse(int a, int m) {
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) return x;
        }
        return -1;  // No modular inverse exists
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Take user input for the plain text
        System.out.println("Enter the plain text:");
        String plainText = scanner.nextLine().toUpperCase().replaceAll("\\s", "");

        // Pad the message if its length is not a multiple of 2
        if (plainText.length() % 2 != 0) {
            plainText += "X";
            System.out.println("Padded message: " + plainText);
        }

        // Take user input for the 2x2 key matrix
        System.out.println("Enter the 2x2 key matrix (4 integers):");
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                keymat[i][j] = scanner.nextInt();
            }
        }

        // Calculate the inverse matrix
        calculateInverseMatrix();

        // Encode the message
        String encodedMessage = "";
        char[] plainTextChars = plainText.toCharArray();
        for (int i = 0; i < plainTextChars.length; i += 2) {
            encodedMessage += encode(plainTextChars[i], plainTextChars[i + 1]);
        }
        System.out.println("Encoded message: " + encodedMessage);

        // Decode the message
        String decodedMessage = "";
        char[] encodedChars = encodedMessage.toCharArray();
        for (int i = 0; i < encodedChars.length; i += 2) {
            decodedMessage += decode(encodedChars[i], encodedChars[i + 1]);
        }
        System.out.println("Decoded message: " + decodedMessage);

        scanner.close();
    }
}
