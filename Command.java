/**
 * Command class - represents a single user command in the game.
 * A command is made of a main command word (e.g. "go", "help")
 * and an optional second word (e.g. a direction like "north").
 *
 * @author Raphaël Quillaud
 * @version 2026.05.08
 */
public class Command {
    private String aCommandWord;
    private String aSecondWord;

    /**
     * Creates a command with the given main word and second word.
     * Either of them may be null to represent an unknown or incomplete command.
     *
     * @param pCommandWord the main command word, or null if unknown
     * @param pSecondWord  the second word, or null if there is none
     */
    public Command(final String pCommandWord, final String pSecondWord) {
        this.aCommandWord = pCommandWord;
        this.aSecondWord = pSecondWord;
    }

    /**
     * Returns the main command word.
     *
     * @return the command word, or null if the command is unknown
     */

    public String getCommandWord() {
        return this.aCommandWord;
    }

    /**
     * Returns the second word of this command.
     *
     * @return the second word, or null if there is none
     */
    public String getSecondWord() {
        return this.aSecondWord;
    }

    /**
     * Checks whether this command has a second word.
     *
     * @return true if the second word is not null, false otherwise
     */
    public boolean hasSecondWord() {
        return (this.aSecondWord != null);
    }

    /**
     * Checks whether this command is unknown (no valid command word).
     *
     * @return true if the command word is null, false otherwise
     */

    public boolean isUnknown() {
        return (this.aCommandWord == null);
    }
} // Command
