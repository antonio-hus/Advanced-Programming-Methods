////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
import controller.*;
import domain.PrgState;
import domain.datastructures.dictionary.*;
import domain.datastructures.list.*;
import domain.datastructures.stack.*;
import domain.expressions.*;
import domain.statements.*;
import domain.types.*;
import domain.values.*;
import view.TextMenu;
import view.commands.*;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class Interpreter {
    public static void main(String[] args) {


        // IMPLEMENTATION USING USER INTERFACE
        /**
            // Application startup
            UserInterface app = new UserInterface();

            // Run application until user triggers exit status
            int status = 0;
            do {

                // Get application status
                status = app.menuScreen();

            }while (status != 0);
        **/

        // IMPLEMENTATION USING TEXT MENU
        // Example 1
        IStmt ex1 = new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex1);
        Controller controller1 = new BasicController(1);
        controller1.addPrgState(prg1);

        // Example 2
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)), new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex2);
        Controller controller2 = new BasicController(1);
        controller2.addPrgState(prg2);

        // Example 3
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        PrgState prg3 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex3);
        Controller controller3 = new BasicController(1);
        controller3.addPrgState(prg3);

        // Example 4
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\anton\\OneDrive\\Documents\\GitHub\\Advanced-Programming-Methods\\Interpreter\\files\\test1.in"))), new CompStmt(new OpenRFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));
        PrgState prg4 = new PrgState(new MyStack<>(), new MyDictionary<>(), new MyList<>(), new MyDictionary<>(), ex4);
        Controller controller4 = new BasicController(1);
        controller4.addPrgState(prg4);

        // Text Menu
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),controller1));
        menu.addCommand(new RunExample("2",ex2.toString(),controller2));
        menu.addCommand(new RunExample("3",ex3.toString(),controller3));
        menu.addCommand(new RunExample("4",ex4.toString(),controller4));
        menu.show();
    }
}
