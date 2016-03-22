/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;

import java.awt.*;
import java.io.File;
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

    @Override
    public void execute() throws CommandException,IOException {
        Path file = Paths.get(this.pathRef.toString() + '\\' + this.arg );
        String fileName = file.getFileName().toString();
        List<String> acceptedExtensions = Arrays.asList("wav","mp3","flac","mp4");
            //Get the extension of the file
            String extension = "";
            int i = fileName.lastIndexOf('.');
            int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
            if( i > p){
                extension = fileName.substring(i+1);
            }

            if(Files.isRegularFile(file) && Files.isReadable(file)){
                if(acceptedExtensions.contains(extension)){
                    Desktop desktop = Desktop.getDesktop();
                    desktop.open(file.toFile());
                    System.out.printf("The file %s will open shortly...\n",fileName);
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
