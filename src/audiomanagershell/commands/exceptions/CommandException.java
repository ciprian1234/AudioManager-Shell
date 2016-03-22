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
public class CommandException extends Exception {
    
    public CommandException() {
        super("Error: An unknown error has appeared!");
    }
    
    public CommandException(String msg) {
        super("Error: " + msg);
    }
}
