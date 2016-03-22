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
 */
public abstract class Command {
    
    protected final Path pathRef;

    public Command(Path reference) {
        this.pathRef = reference;
    }
    
    public abstract void execute();
    
}
