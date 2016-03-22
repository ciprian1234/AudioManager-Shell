/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package audiomanagershell;

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
            System.out.format("%s>", currentPath.toString());
            
            try {
                if(in.hasNextLine())
                    command = in.nextLine();
                
                //De implementat cazul in care comanda este invalida sintactic
                if(false)
                    throw new InvalidCommandException();
                switch(command)
                {
                    case "cd":
                        Command cmd = new ChangeDirectoryCommand(currentPath);
                        cmd.execute();
                        System.out.println("Not Implemented!");
                        break;
                    case "list":
                        System.out.println("Not Implemented!");
                        break;
                    case "play":
                        System.out.println("Not Implemented!");
                        break;
                    case "info":
                        System.out.println("Not Implemented!");
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
            catch(CommandNotFoundException | InvalidCommandException e) {
                System.out.println( e.getMessage() );
            }
        }
    }
}
