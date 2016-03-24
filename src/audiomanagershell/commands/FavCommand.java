/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;
import audiomanagershell.commands.exceptions.FileNotFoundException;
import audiomanagershell.commands.exceptions.NotAFileException;
import audiomanagershell.commands.exceptions.NotAudioFileException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import org.apache.commons.io.FilenameUtils;


/**
 *
 * @author ciprian
 */
public class FavCommand extends Command{
    
    private final Path favFile;
    public FavCommand(Path appPath, Path reference){
        super(reference);
        this.favFile = Paths.get(appPath.toString());
    }
    
    @Override
    public void execute() throws CommandException {
        Path file = Paths.get(this.pathRef.toString(),this.arg);

        
        if(!Files.exists(file))
            throw new FileNotFoundException(file.getFileName().toString());
        if(!Files.isRegularFile(file))
            throw new NotAFileException(file.getFileName().toString());
        
        List<String> validExtensions = Arrays.asList("wav", "mp3", "flac", "mp4");
        if(!validExtensions.contains(FilenameUtils.getExtension(file.toString())))
            throw new NotAudioFileException(file.toString());
        
        try ( PrintWriter output = new PrintWriter(new FileWriter(favFile.toFile(), true));
              Scanner input = new Scanner( new FileReader(favFile.toString())) )
        {

            while(input.hasNextLine())
            {
                if( input.nextLine().equals(file.toString()) )
                {
                    System.out.printf("File \"%s\" is already added to favorite List!%n", file);
                    return;
                }
            }
            System.out.printf("Added: \"%s\"%n", file);
            output.println(file);
        } catch (IOException ex) {
            throw new CommandException("Error related from I/O to file:" + favFile.toString() );
        }
    }

    @Override
    public void init(String args) {
        this.arg = args;
    }
}
