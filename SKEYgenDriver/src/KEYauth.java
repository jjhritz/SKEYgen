/**
 * Created by John J. Hritz. on 3/10/2017.
 *
 * Authenticates passwords based on S/KEY algorithm
 */

import java.util.Vector;
public class KEYauth
{
    /**
     * Member data
     */
    private KEYgen generator;					//The KEYgen used to generate the passwords
    private Vector<String> reference;			//reference list of all known passwords


    /**
     *  Constructors
     */

	/**
     * Constructor that specifies the generator
     */
    public KEYauth(KEYgen generator)
    {
        // set generator to provided generator
        this.generator = generator;
        // create new Vector for reference
        this.reference = new Vector<String>();
        // copy generator password list into reference
        reference.addAll(this.generator.getPasswordList());
    } //end Constructor KEYgen(userIterations)



    /**
     * Member Functions
     */

    /**
     * Determines if a password the user provides was created by the generator
     */
    public void authorize(String password)
    {
        // hash password, store in hashPass
        String hashPass = generator.hashPass(password);
        // compare hashPass to last element in reference
        // if hashPass is equal to the last element in reference
        if(hashPass.equals(reference.lastElement()))
        {
            // add password to end of reference
            reference.add(password);

            System.out.println("Password authorized.");
        }
        // else, hashes do not match
        else
        {
            // alert failure
            System.out.println("Authorization failed.");
        } //end if

    } // end function authorize

    /**
     * Prints the list of reference passwords, one on each line
     */
    public void printRefList()
    {
        //for all elements in the reference list
        for(int i = 0; i < this.reference.size(); i++)
        {
            //print out the element at position i
            System.out.println(this.reference.elementAt(i));
        }
        //print blank line
        System.out.println();
    } //end function printRefList


} // end class KEYauth
