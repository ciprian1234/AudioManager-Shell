/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;
import audiomanagershell.commands.exceptions.NotAudioFileException;
import org.apache.commons.io.FilenameUtils;

import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author ciprian
 */
public class PlayCommand extends Command{
    public PlayCommand(Path reference){
        super(reference);
    }
    //random stuff for testing
    @Override
    public void execute() throws CommandException,IOException {
        String OS = System.getProperty("os.name").toLowerCase();
        Path file;
        if(OS.equals("windows"))
            file = Paths.get(this.pathRef.toString() + "\\" + this.arg );
        else
            file = Paths.get(this.pathRef.toString() + "/" + this.arg);
        String fileName = file.getFileName().toString();
        List<String> acceptedExtensions = Arrays.asList("wav","mp3","flac","mp4");
            //Get the extension of the file
            String extension = FilenameUtils.getExtension(fileName);
            if(Files.isRegularFile(file) && Files.isReadable(file)){
                if(acceptedExtensions.contains(extension)){
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file.toFile());
                    System.out.printf("The file %s will open shortly...\n",fileName);
                }
                else{
                    throw new NotAudioFileException(fileName);
                }
            }
            else{
                throw new CommandException(file.toString() + " not a file or can't read");
            }
    }

    @Override
    public void init(String args) {
        this.arg = args;
    }

}
