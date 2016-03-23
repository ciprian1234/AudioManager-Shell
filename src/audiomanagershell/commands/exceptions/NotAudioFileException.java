package audiomanagershell.commands.exceptions;

/**
 * Created by Donici Marius on 3/23/2016.
 */
public class NotAudioFileException extends CommandException {

    public NotAudioFileException(String msg) {
        super("File  \"" + msg + "\" type is not supported!");
    }

}
