package audiomanagershell.commands.exceptions;

/**
 * Created by Donici Marius on 3/23/2016.
 */
public class NotAFileException extends CommandException {

    public NotAFileException(String msg) {
        super("\"" + msg + "\" is not a file!");
    }

}
