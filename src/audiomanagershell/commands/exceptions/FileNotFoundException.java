package audiomanagershell.commands.exceptions;

/**
 * Created by Donici Marius on 3/23/2016.
 */
public class FileNotFoundException extends CommandException{

    public FileNotFoundException(String msg) {
        super("File \"" + msg + "\" doesn't exists!");
    }

}
