/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import java.nio.file.Path;

/**
 *
 * @author ciprian
 * 
 * Merged
 */
public class ChangeDirectoryCommand extends Command {
    
    
    public ChangeDirectoryCommand(Path reference) {
        super(reference);
    }
    
    
    @Override
    public void execute() {
        
    }
    
}
