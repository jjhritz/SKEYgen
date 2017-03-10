/**
 * Created by John J. Hritz on 3/10/2017.
 * Generates passwords based on S/KEY algorithm
 */

import java.security.DigestException;
import java.security.NoSuchAlgorithmException;
import java.util.Vector;
import java.security.SecureRandom;
import java.security.MessageDigest;
import java.math.BigInteger;

public class KEYgen
{
    private int iterations;                                     //Number of passwords to be generated
    private SecureRandom seed ;                                 //random number used to generate secret W
    private Vector<String> passwordList;                        //Vector used to store the hash chain
    private MessageDigest hashFunc;                             //Hash function that will be applied to W

    /*
    *   Default constructor
     */
     public KEYgen()
     {
         this.iterations = 1;
         this.seed = new SecureRandom();
         this.passwordList = new Vector();

         try
         {
             this.hashFunc = MessageDigest.getInstance("SHA-1");
         }
         catch(NoSuchAlgorithmException nsae)
         {
             nsae.printStackTrace();
         }
     }

     /*
     * Constructor that specifies the number of iterations
      */
    public KEYgen(int userIterations)
    {
        this.iterations = userIterations;
        this.seed = new SecureRandom();
        this.passwordList = new Vector();

        try
        {
            this.hashFunc = MessageDigest.getInstance("SHA-1");
        }
        catch(NoSuchAlgorithmException nsae)
        {
            nsae.printStackTrace();
        }
    }

    /**
     * Generates a hash chain of passwords from a secret phrase W
     */
    public void passGen()
    {
        // Generate secret W: length 130, base-32
        String W = new BigInteger(130, seed).toString(32);
        // create string to store the value of the password
        String password = W;
        // Apply hash function to W for the number of iterations specified
        for(int n = 0; n < this.iterations; n++)
        {
            // Hash password
            byte[] byteHash = hashFunc.digest(password.getBytes());
            //reformat to BigInt
            BigInteger bigIntPass = new BigInteger(1, byteHash);
            //Convert to string
            password = bigIntPass.toString(32);
            // Store hashVal in passwordList
            passwordList.add(password);
        } //endfor

        // Delete W
        W = null;
    } // end function passGen


    /**
     * Deletes all passwords in a hash chain except the last member
     */
    public void clearChain()
    {
        // delete all elements in passwordList except last element
        passwordList.subList(0, this.getIterations() - 1).clear();
    } //end function clearChain

    /**
     * Retrieves generator's password list
     */
    public Vector getPasswordList()
    {
        return passwordList;
    }

    /**
    * sets number of iterations for the generator
     */
    public void setIterations(int newIterations)
    {
        this.iterations = newIterations;
    }

    /**
     * Retrieves the number of iterations the generator uses
     */
    public int getIterations()
    {
        return iterations;
    }

    /**
     * Prints the list of passwords, one on each line
     */
    public void printPassList()
    {
        //for all elements in the password list
        for(int i = 0; i < this.passwordList.size(); i++)
        {
            //print out the element at position i
            System.out.println(this.passwordList.elementAt(i));
        }
        //print blank line
        System.out.println();
    }
} //end class KEYgen
