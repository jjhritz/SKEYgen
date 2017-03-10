/**
 * Created by John J. Hritz on 3/10/2017.
 * Generates passwords based on S/KEY algorithm
 */

import java.util.Scanner;

public class SKEYgenDriver
{
    public static void main(String [] args)
    {
        // Set number of iterations in hash chain to 5
        int chainLength = 5;
        //create password generator
        KEYgen generator = new KEYgen(chainLength);
        //create scanner
        Scanner keyboard = new Scanner(System.in);


        /*Generation*/
        // Generate passwordList
        generator.passGen();

        // Print passwordList
        generator.printPassList();

        // Delete H4(W) to H1(W) from passwordList
        generator.clearChain();

        // Print passwordList
        generator.printPassList();

        /*Authorization*/
        //create password authorizer
        KEYauth authorizer = new KEYauth(generator);

        //authorize 2 passwords: H4(W) and H3(W)
        //prompt user for input
        System.out.println("Enter Password H4(W)");
        //authorize user input
        String password = keyboard.nextLine();
        authorizer.authorize(password);
        //print authorizer password list
        authorizer.printRefList();

        //prompt user for input
        System.out.println("Enter Password H3(W)");
        //authorize user input
        password = keyboard.nextLine();
        authorizer.authorize(password);
        //print authorizer password list
        authorizer.printRefList();

    } // end main

} //end class SKEYgenDriver
