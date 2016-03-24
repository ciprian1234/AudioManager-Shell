package audiomanagershell.commands.exceptions;

/**
 * Created by Donici Marius on 3/22/2016.
 */
public class InvalidNumberOfArgumentsException extends CommandException {

    public InvalidNumberOfArgumentsException(String msg) {
        super("Command \"" + msg + "\" doesn't have enough arguments!");
    }

}
