/**
 * CommandWords class - stores all valid command words for
 * the "Henriette's Feast" game and provides a way to check them.
 * It is used by the parser to recognise which words are valid commands.
 *
 * @author Michael Kolling and David J. Barnes + D. Bureau + Raphaël Quillaud
 * @version 2008.03.30 + 2019.09.25 + 2026.02.18
 */
public class CommandWords  {
    // a constant array that will hold all valid command words
    private final String[] aValidCommands = { "go", "help", "quit" };

    /**
     * Checks whether a given string is a valid command word.
     *
     * @param pString the word to test
     * @return true if the given string is a valid command, false otherwise
     */
    public boolean isCommand( final String pString ) {
        for ( int vI=0; vI<this.aValidCommands.length; vI++ ) {
            if ( this.aValidCommands[vI].equals( pString ) )
                return true;
        } // for
        // if we get here, the string was not found in the commands :
        return false;
    } // isCommand()
} // CommandWords
