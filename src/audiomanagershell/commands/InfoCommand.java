/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;
import audiomanagershell.commands.exceptions.FileNotFoundException;
import audiomanagershell.commands.exceptions.NotAFileException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.Files;
import java.util.Arrays;
import java.util.List;

import audiomanagershell.commands.exceptions.NotAudioFileException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author ciprian
 */
public class InfoCommand extends Command{
    public InfoCommand(Path reference){
        super(reference);
    }

    @Override
    public void execute() throws CommandException {
        Path file = Paths.get(this.pathRef.toString(),this.arg);
        String fileName = file.getFileName().toString();
        List<String> acceptedExtensions = Arrays.asList("wav","mp3","flac");
        List<String> infoWanted = Arrays.asList("title","xmpDM:artist","xmpDM:genre","xmpDM:album","xmpDM:releaseDate");
        if(!Files.exists(file))
            throw new FileNotFoundException(file.getFileName().toString());
        if(Files.isRegularFile(file)) {
            //Get the extension of the file
                String extension = "";
                int i = fileName.lastIndexOf('.');
                int p = Math.max(fileName.lastIndexOf('/'), fileName.lastIndexOf('\\'));
                if (i > p) {
                    extension = fileName.substring(i + 1);
                }

            if(acceptedExtensions.contains(extension)){
                try{
                    FileInputStream input = new FileInputStream(file.toFile());
                    Metadata metadata = new Metadata();
                    BodyContentHandler handler = new BodyContentHandler();
                    ParseContext pcontext = new ParseContext();

                    //MP3 Parser

                    Mp3Parser AudioParser = new Mp3Parser();
                    AudioParser.parse(input,handler,metadata,pcontext);


                    for(String name : infoWanted) {
                        String[] metadataName = name.split(":");
                        if(metadata.get(name) != null)
                            if(name.equals("title"))
                                System.out.println("TITLE:" + metadata.get(name));
                            else
                                System.out.println(metadataName[1].toUpperCase() + ":" + metadata.get(name));
                    }
                    input.close();
                }
                catch(IOException | TikaException | SAXException e){
                    e.printStackTrace();
                }
            }
            else{
                throw new NotAudioFileException(fileName);
            }
        }
        else
            throw new NotAFileException(file.getFileName().toString());
    }

    @Override
    public void init(String args) {
        this.arg = args;
    }
}
