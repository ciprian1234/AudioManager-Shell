/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands.exceptions;

/**
 *
 * @author ciprian
 */
public class CommandNotFoundException extends CommandException {

    public CommandNotFoundException(String msg) {
        super("Command \"" + msg + "\" not recognized!");
    }
    
}
