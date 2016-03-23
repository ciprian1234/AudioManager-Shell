/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;
import java.io.IOException;
import java.nio.file.Path;

/**
 *
 * @author ciprian
 */
public abstract class Command {
    
    protected  Path pathRef;
    protected  String arg;

    public Command(Path reference) {
        this.pathRef = reference;
    }
    
    public abstract void execute() throws CommandException,IOException;
    public abstract void init(String args);
    public Path getPath(){
        return pathRef.toAbsolutePath();
    }
    
}
