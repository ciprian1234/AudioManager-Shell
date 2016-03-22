package audiomanagershell.commands.exceptions;

/**
 * Created by Donici Marius on 3/22/2016.
 */
public class PathNotFoundException extends CommandException {

    public PathNotFoundException(String msg) {
        super("Path \"" + msg + "\" not found!");
    }

}
