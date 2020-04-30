package uvsq.Commande;

import java.util.HashMap;
import java.util.Map;

public class CommandFactory {

    private final Map<String,Command> commands;

    private CommandFactory(){
        this.commands = new HashMap<>();
    }

    public void addCommand(String name, Command command) {

    }
    public void executeCommand(String name) {

    }

    public static CommandFactory init(){
        CommandFactory cf = new CommandFactory();


        return cf;
    }


}
