import java.math.BigInteger;
import java.math.BigDecimal; // Import BigDecimal
import java.util.Scanner;

class RSA {
    public static void main(String args[]) {
        int p, q, n, z, d = 0, e, i;

        // Using Scanner for user input
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the message to be encrypted (as an integer): ");
        int msg = scanner.nextInt(); // User input for the message

        double c;
        BigInteger msgback;

        // 1st prime number p
        p = 3;
        // 2nd prime number q
        q = 11;

        n = p * q;
        z = (p - 1) * (q - 1);
        System.out.println("The value of z = " + z);
        
        for (e = 2; e < z; e++) {
            // e is for public key exponent
            if (gcd(e, z) == 1) {
                break;
            }
        }
        System.out.println("The value of e = " + e);
        
        for (i = 0; i <= 9; i++) {
            int x = 1 + (i * z);
            // d is for private key exponent
            if (x % e == 0) {
                d = x / e;
                break;
            }
        }
        System.out.println("The value of d = " + d);

        // Encrypt the message
        c = (Math.pow(msg, e)) % n;
        System.out.println("Encrypted message is : " + c);
        
        // Converting int value of n to BigInteger
        BigInteger N = BigInteger.valueOf(n);
        // Converting float value of c to BigInteger
        BigInteger C = BigDecimal.valueOf(c).toBigInteger();
        msgback = (C.pow(d)).mod(N);
        System.out.println("Decrypted message is : " + msgback);

        // Closing the scanner
        scanner.close();
    }

    static int gcd(int e, int z) {
        if (e == 0) {
            return z; 
        } else {
            return gcd(z % e, e);
        }
    }
}
