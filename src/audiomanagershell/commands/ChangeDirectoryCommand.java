/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.Interpreter;
import audiomanagershell.commands.exceptions.CommandException;
import audiomanagershell.commands.exceptions.CommandNotFoundException;
import audiomanagershell.commands.exceptions.PathNotFoundException;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author ciprian
 */
public class ChangeDirectoryCommand extends Command {
    
    
    public ChangeDirectoryCommand(Path reference) {
        super(reference);
    }
    
    
    @Override
        public void execute() throws CommandException{
            File file = new File(this.arg);
            File file2 = new File(this.pathRef.toString() + this.arg );
                if(file.exists() || file2.exists()){
                    this.pathRef = Paths.get(this.arg);
                    System.out.printf("%s exista\n ",this.arg);
                }
                else{
                   throw new PathNotFoundException(arg);
                }
        }

    @Override
    public void init(String args){
        this.arg = args;
    }
    
}
