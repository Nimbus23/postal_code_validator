package postalcode;

public class OotumliaPostalCode extends PostalCode {

    public OotumliaPostalCode(String code) throws PostalCodeException
    {
        super(code);
    }

    //Instance methods **************************************************
    public String getCountry()
    {
        return "Ootumlian";
    }

    /**
     * This method will verify the validity of the postal code.
     *
     * @throws PostalCodeException If the code is found to be invalid.
     */
    protected void validate() throws PostalCodeException
    {
        String postCode = getCode();

        if(postCode.length() < 4)
            throwException("Postal code too short");

        int pos = 0; // character position within the postal code

        // STAGE 1: Expecting one or two letters
        if(!Character.isLetter(postCode.charAt(0)))
            throwException("Expecting letter at position 1");
        pos++;

        if(Character.isLetter(postCode.charAt(1)))
            pos++;

        // STAGE 2: Expecting whitespace
        if(!Character.isWhitespace(postCode.charAt(pos)))
            throwException("Expecting space at position "+(pos+1));
        pos++;

        // STAGE 3: Expecting two digits
        if(!Character.isDigit(postCode.charAt(pos)))
            throwException("Expecting number at position "+(pos+1));
        pos++;

        if(postCode.length() > pos
                && Character.isDigit(postCode.charAt(pos)))
            pos++;

        // STAGE 4: Expecting nothing
        if(postCode.length() > pos)
            throwException("Unexpected character at end of code");

        // Set destination
        setDestination("outside the capital of Ootumlia.");
        String[] capitalOotiamba = {"OO", "TU", "LI"};
        for(int i=0; i<capitalOotiamba.length; i++)
        {
            if(postCode.startsWith(capitalOotiamba[i]))
            {
                setDestination("in the capital of Ootumlia");
                return;
            }
        }
    }
}

