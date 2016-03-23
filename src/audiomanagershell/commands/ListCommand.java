/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;

import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ciprian
 */
public class ListCommand extends Command{
    public ListCommand(Path reference){
        super(reference);
    }

    @Override
    public void execute() throws CommandException {
        Path currentFolder = Paths.get(this.pathRef.toString());
        List<Path> files = new ArrayList<>();
         try{
             DirectoryStream<Path> stream = Files.newDirectoryStream(currentFolder);
                for(Path file : stream)
                    files.add(file);
             stream.close();

             for(Path file : files) {
                 if (Files.isDirectory(file))
                     System.out.printf("Dir -> ");
                 if (Files.isRegularFile(file))
                     System.out.printf("File -> ");

                 System.out.println(file.getFileName().toString());

             }
         }
         catch (IOException e){
             e.printStackTrace();
         }

    }

    @Override
    public void init(String args) {

    }


}
