import java.util.StringTokenizer;

/**
 * This class is part of the "World of Zuul" application.
 * "World of Zuul" is a very simple, text based adventure game.
 *
 * This parser receives a line of user input as a String and tries to
 *  * interpret it as an adventure command. It returns the command as an
 *  * object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author  Michael Kolling and David J. Barnes + D.Bureau + Raphaël Quillaud
 * @version 2008.03.30 + 2013.09.15 + 2026.05.09
 */
public class Parser {
    private CommandWords aValidCW;  // (see CommandWords class)

    /**
     * Default constructor that creates the CommandWords object.
     */
    public Parser() {
        this.aValidCW = new CommandWords();
    } // Parser()

    /**
     * Reads the next command from the user.
     *
     * @param pInputLine the line entered by the user
     * @return the corresponding command
     */
    public Command getCommand(final String pInputLine) {
        String vWord1 = null;
        String vWord2 = null;

        StringTokenizer vTokenizer = new StringTokenizer(pInputLine);

        if (vTokenizer.hasMoreTokens()) {
            vWord1 = vTokenizer.nextToken();
        }
        if (vTokenizer.hasMoreTokens()) {
            vWord2 = vTokenizer.nextToken();
        }

        if (this.aValidCW.isCommand(vWord1)) {
            return new Command(vWord1, vWord2);
        }
        else {
            return new Command(null, vWord2);
        }
    } // getCommand()

    /**
     * Gives access to the set of valid command words.
     *
     * @return the CommandWords instance used by this parser
     */
    public CommandWords getCommandWords() {
        return this.aValidCW;
    }

    /**
     * Returns the list of all valid command words as a single string.
     * This method simply forwards the request to the {@link CommandWords}
     * object used by the parser.
     *
     * @return a space-separated list of all valid commands
     */
    public String getCommandList() {
        return this.aValidCW.getCommandList();
    }
} // Parser
