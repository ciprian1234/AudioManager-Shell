/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;
import audiomanagershell.commands.exceptions.PathNotFoundException;
import java.io.IOException;

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
        public void execute() throws CommandException {
            
            Path testPath;
            Path tempPath;
            /* Test pentru calea absoluta */
            if(arg.charAt(0) == '/' || arg.contains(":"))
                testPath = Paths.get(this.arg);
            else    
                testPath = Paths.get(this.pathRef.toString() + "/" + this.arg);
            
            try {
                tempPath = testPath.toRealPath();
                if( !Files.isDirectory(testPath))
                    throw new PathNotFoundException(arg);
            } 
            catch (IOException ex) {
                throw new PathNotFoundException(arg);
            }
            
            this.pathRef = Paths.get(tempPath.toString());
        }

    @Override
    public void init(String args){
        this.arg = args;
    }
}
