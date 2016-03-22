/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;

import java.io.File;
import java.nio.file.Path;

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
        File currentFolder = new File(this.pathRef.toString());
        File[] listOfFiles = currentFolder.listFiles();

            for(int i = 0;i < listOfFiles.length;i++){
                if( listOfFiles[i].isFile()){
                    System.out.println("File " + listOfFiles[i].getName());
                }
                if(listOfFiles[i].isDirectory()){
                    System.out.println("Directory " + listOfFiles[i].getName());
                }
            }
            if(listOfFiles.length == 0)
                System.out.println("This directory is empty!");
    }

    @Override
    public void init(String args) {

    }


}
