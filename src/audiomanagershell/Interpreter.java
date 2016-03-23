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
    
    private Path currentPath;
    
    public Interpreter()
    {
        currentPath = Paths.get("");
        currentPath = currentPath.toAbsolutePath();
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
                    throw new InvalidCommandException();
                switch(commandParts[0])
                {
                    case "cd":
                        if(commandParts.length != 2)
                            throw new NotEnoughArgumentsException(command);
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
                            throw new NotEnoughArgumentsException(command);
                        Command playCmd = new PlayCommand(currentPath);
                        playCmd.init(commandParts[1]);
                        playCmd.execute();
                        break;
                    case "info":
                        if(commandParts.length != 2)
                            throw new NotEnoughArgumentsException(command);
                        Command infoCmd = new InfoCommand(currentPath);
                        infoCmd.init(commandParts[1]);
                        infoCmd.execute();
                        break;
                    case "find":
                        System.out.println("Not Implemented!");
                        break;
                    case "fav":
                        System.out.println("Not Implemented!");
                        break;
                    case "report":
                        System.out.println("Not Implemented!");
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
