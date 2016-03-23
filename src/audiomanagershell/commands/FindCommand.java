/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            for(Path file : files) {
                if (Files.isRegularFile(file) && Pattern.matches("[a-zA-Z0-9\\ \\-\\.\\[\\]]+.(?i)(mp3|wav|flac)",file.getFileName().toString())){
                    Pattern pattern = Pattern.compile("[a-zA-Z0-9\\ \\-\\.\\[\\]]*(?i)(" + this.arg + ")[a-zA-Z0-9\\ \\-\\.\\[\\]]*.(?i)(mp3|wav|flac)");
                    Matcher matcher = pattern.matcher(file.getFileName().toString());

                    //Cautare in numele fisierelor
                    if(matcher.matches() | Pattern.matches("(?i)("+ this.arg + ")",file.getFileName().toString())) {
                        System.out.println(file.toString());
                        continue;
                    }


                    //Verificare daca exista in metadate informatii cautate
                    FileInputStream input = new FileInputStream(file.toFile());
                    Metadata metadata = new Metadata();
                    BodyContentHandler handler = new BodyContentHandler();
                    ParseContext pcontext = new ParseContext();
                    List<String> infoWanted = Arrays.asList("title","xmpDM:artist","xmpDM:genre","xmpDM:album","xmpDM:releaseDate");

                    //MP3 Parser

                    Mp3Parser AudioParser = new Mp3Parser();
                    AudioParser.parse(input,handler,metadata,pcontext);


                    for(String name : infoWanted) {
                        if(metadata.get(name) != null)
                            if(metadata.get(name).toLowerCase().contains(this.arg.toLowerCase())) {
                                System.out.println(file.toString());
                                break;
                            }
                    }
                    input.close();
                }
                if (Files.isDirectory(file) && Files.isReadable(file)){
                    Command recursiveFolder = new FindCommand(file);
                    recursiveFolder.init(this.arg);
                    recursiveFolder.execute();
                }
            }
        }
        catch (IOException | SAXException | TikaException e){
            e.printStackTrace();
        }
    }

    @Override
    public void init(String args) {
        this.arg = args;
    }

}
