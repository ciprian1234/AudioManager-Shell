/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;
import audiomanagershell.commands.exceptions.FileNotFoundException;
import audiomanagershell.commands.exceptions.NotAFileException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 * @author ciprian
 */
public class FavCommand extends Command{
    
    private final Path appPath;
    public FavCommand(Path appPath, Path reference){
        super(reference);
        this.appPath = Paths.get(appPath.toString());
    }
    
    @Override
    public void execute() throws CommandException {
        Path file = Paths.get(this.pathRef.toString(),this.arg);


        //Trebuie implementata
        if(!Files.exists(file))
            throw new FileNotFoundException(file.getFileName().toString());
        if(Files.isRegularFile(file))
        {
            try(PrintWriter output = new PrintWriter())
        }
        else
            throw new NotAFileException(file.getFileName().toString());
    }

    @Override
    public void init(String args) {
        this.arg = args;
    }
}
