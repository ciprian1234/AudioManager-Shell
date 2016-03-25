/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import audiomanagershell.commands.*;
import audiomanagershell.commands.exceptions.*;

/**
 *
 * @author ciprian
 */

public class Interpreter {
    
    //Pentru fav command
    private final Path appPath;
    private Path currentPath;
    
    public Interpreter()
    {
        currentPath = Paths.get("");
        currentPath = currentPath.toAbsolutePath();
        
        String OS = System.getProperty("os.name").toLowerCase();
            appPath = Paths.get(this.currentPath.toString());
    }
    
    public void run()
    {
        Scanner in = new Scanner(System.in);
        
    loop:while(true)
        {
            String command = "";
            String[] commandParts;
            System.out.format("%s>", currentPath.toString());
            
            try {
                if(in.hasNextLine())
                    command = in.nextLine();
                commandParts = command.split(" ");

                if(commandParts.length > 2)
                    throw new InvalidNumberOfArgumentsException(commandParts[0]);
                switch(commandParts[0])
                {
                    case "cd":
                        if(commandParts.length != 2)
                            throw new InvalidNumberOfArgumentsException(command);
                        Command cmd = new ChangeDirectoryCommand(currentPath);
                        cmd.init(commandParts[1]);
                        cmd.execute();
                        currentPath = cmd.getPath();
                        break;
                    case "list":
                        Command listCmd = new ListCommand(currentPath);
                        listCmd.execute();
                        break;
                    case "play":
                        if(commandParts.length != 2)
                            throw new InvalidNumberOfArgumentsException(command);
                        Command playCmd = new PlayCommand(currentPath);
                        playCmd.init(commandParts[1]);
                        playCmd.execute();
                        break;
                    case "info":
                        if(commandParts.length != 2)
                            throw new InvalidNumberOfArgumentsException(command);
                        Command infoCmd = new InfoCommand(currentPath);
                        infoCmd.init(commandParts[1]);
                        infoCmd.execute();
                        break;
                    case "find":
                        if(commandParts.length != 2)
                            throw new InvalidNumberOfArgumentsException(command);
                        Command findCmd = new FindCommand(currentPath);
                        findCmd.init(commandParts[1]);
                        findCmd.execute();
                        break;
                    case "fav":
                        if(commandParts.length != 2)
                            throw new InvalidNumberOfArgumentsException(command);
                        Command favCmd = new FavCommand(appPath, currentPath);
                        favCmd.init(commandParts[1]);
                        favCmd.execute();
                        break;
                    case "report":

                        Command reportCmd = new ReportCommand(appPath);
                        //reportCmd.init(commandParts[1]);
                        reportCmd.execute();
                        System.out.println("Report finished...");
                        break;
                    case "quit":
                       break loop;
                    default:
                        throw new CommandNotFoundException(command);
                }
                
            }
            catch(CommandException | IOException e) {
                System.out.println( e.getMessage() );
            }
        }
    }
}
