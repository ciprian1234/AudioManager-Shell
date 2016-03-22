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
public class FindCommand extends Command{
    public FindCommand(Path reference){
        super(reference);
    }

    @Override
    public void execute() throws CommandException{
        Path currentFolder = Paths.get(this.pathRef.toString());
        List<Path> files = new ArrayList<>();
        try{
            DirectoryStream<Path> stream = Files.newDirectoryStream(currentFolder);
            for(Path file : stream)
                files.add(file);
            stream.close();


            //Trebuie implementata cautarea
            for(Path file : files) {
                if (Files.isRegularFile(file))
                    System.out.printf("file >");
                if (Files.isDirectory(file)){
                    Command recursiveFolder = new FindCommand(file);
                    recursiveFolder.init(this.arg);
                    recursiveFolder.execute();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void init(String args) {
        this.arg = args;
    }

}
