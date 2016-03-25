/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell.commands;

import audiomanagershell.commands.exceptions.CommandException;
import audiomanagershell.Templates;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import net.sf.dynamicreports.jasper.builder.export.JasperPdfExporterBuilder;
import net.sf.dynamicreports.report.datasource.DRDataSource;
import net.sf.dynamicreports.report.exception.DRException;
import net.sf.jasperreports.engine.JRDataSource;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;


import static net.sf.dynamicreports.report.builder.DynamicReports.*;

/**
 *
 * @author ciprian
 */
public class ReportCommand extends Command{
    public ReportCommand(Path appPath){
        super(appPath);
    }

    @Override
    public void execute() throws CommandException, IOException {

        JasperPdfExporterBuilder pdfExporter = export.pdfExporter(this.pathRef.toString() + "\\" + "report.pdf");
        try{
            report()
                    .setTemplate(Templates.reportTemplate)
                    .columns(
                            col.column("Title", "title", type.stringType()),
                            col.column("Artist", "artist",type.stringType()),
                            col.column("Album", "album",type.stringType()),
                            col.column("Genre", "genre",type.stringType()),
                            col.column("Path","path",type.stringType())
                    )
                    .title(Templates.createTitleComponent("Favorite music Report"))
                    .setDataSource(createFavorite())
                    .toPdf(pdfExporter);

        }
        catch(DRException e){
            e.printStackTrace();
        }
    }
    private JRDataSource createFavorite(){
        DRDataSource dataSource = new DRDataSource("title","artist","album","genre","path");




        try{
            Path favorites;
            String OS = System.getProperty("os.name").toLowerCase();
                if(OS.equals("windows"))
                    favorites = Paths.get(this.pathRef.toString() + "\\" + "favList.txt");
                else
                    favorites = Paths.get(this.pathRef.toString() + "/" + "favList");

            InputStream favoriteFile = Files.newInputStream(favorites);
                BufferedReader reader = new BufferedReader(new InputStreamReader(favoriteFile));

                String favItem;
                    while((favItem = reader.readLine())!=null) {
                        Path favFile = Paths.get(favItem);
                            if(Files.isRegularFile(favFile)){
                                FileInputStream input = new FileInputStream(favFile.toFile());
                                Metadata metadata = new Metadata();
                                BodyContentHandler handler = new BodyContentHandler();
                                ParseContext pcontext = new ParseContext();

                                //MP3 Parser

                                Mp3Parser AudioParser = new Mp3Parser();
                                AudioParser.parse(input,handler,metadata,pcontext);

                                dataSource.add(metadata.get("title"),metadata.get("xmpDM:artist"),metadata.get("xmpDM:album"),metadata.get("xmpDM:genre"),favFile.toAbsolutePath().toString());
                                input.close();
                            }
                    }
            reader.close();

        }catch(IOException |SAXException | TikaException e){
                e.printStackTrace();
        }
        return dataSource;
    }
    @Override
    public void init(String args) {

    }
    //random stuff for testing
}
