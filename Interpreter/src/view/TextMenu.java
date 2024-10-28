////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package view;
import view.commands.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class TextMenu {

    // TEXT MENU STRUCTURE
    // Text Menu is based on a list of commands
    private Map<String, Command> commands;

    // TEXT MENU CONSTRUCTOR
    public TextMenu() {
        commands = new HashMap<>();
    }

    // TEXT MENU METHODS
    public void addCommand(Command c){commands.put(c.getKey(), c); }
    private void printMenu() {
        for(Command command: commands.values()) {
            String line = String.format("%4s: %s", command.getKey(), command.getDescription());
            System.out.println(line);
        }
    }

    public void show() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            printMenu();
            System.out.print("Input the option: ");
            String key = scanner.nextLine();
            Command command = commands.get(key);
            if(command == null){
                System.out.println("Invalid Option");
                continue;
            }
            command.execute();
        }
    }
}
