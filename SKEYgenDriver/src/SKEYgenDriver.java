/**
 * Created by John J. Hritz on 3/10/2017.
 * Generates passwords based on S/KEY algorithm
 */

import java.util.Vector;

public class SKEYgenDriver
{
    public static void main(String [] args)
    {
        // Set number of iterations in hash chain to 5
        int chainLength = 5;

        //create password generator
        KEYgen generator = new KEYgen(chainLength);

        // Generate passwordList
        generator.passGen();

        // Print passwordList
        generator.printPassList();

        // Delete H4(W) to H1(W) from passwordList
        generator.clearChain();

        // Print passwordList
        generator.printPassList();

    } // end main

} //end class SKEYgenDriver
