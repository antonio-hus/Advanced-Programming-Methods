////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
import controller.*;
import domain.PrgState;
import domain.datastructures.dictionary.*;
import domain.datastructures.list.*;
import domain.datastructures.stack.*;
import domain.expressions.*;
import domain.state.*;
import domain.statements.*;
import domain.types.*;
import domain.values.*;
import view.TextMenu;
import view.commands.*;

import java.util.Scanner;


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
        // SETUP
        // LOG FILE - C:\\Users\\anton\\OneDrive\\Documents\\GitHub\\Advanced-Programming-Methods\\Interpreter\\logs\\programState.log
        TextMenu.clearScreen();
        String logFilePath;
        // Scanner scanner = new Scanner(System.in);
        // System.out.println("Please enter the log file path: ");
        // logFilePath = scanner.nextLine();
        logFilePath = "C:\\\\Users\\\\anton\\\\OneDrive\\\\Documents\\\\GitHub\\\\Advanced-Programming-Methods\\\\Interpreter\\\\logs\\\\programState.log";
        // TextMenu.proceedEnter();

        // Example 1
        IStmt ex1 = new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));
        PrgState prg1 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex1);
        Controller controller1 = new BasicController(1, logFilePath);
        controller1.addPrgState(prg1);

        // Example 2
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)), new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));
        PrgState prg2 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex2);
        Controller controller2 = new BasicController(1, logFilePath);
        controller2.addPrgState(prg2);

        // Example 3
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));
        PrgState prg3 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex3);
        Controller controller3 = new BasicController(1, logFilePath);
        controller3.addPrgState(prg3);

        // Example 4
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\anton\\OneDrive\\Documents\\GitHub\\Advanced-Programming-Methods\\Interpreter\\files\\test1.in"))), new CompStmt(new OpenRFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));
        PrgState prg4 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex4);
        Controller controller4 = new BasicController(1, logFilePath);
        controller4.addPrgState(prg4);

        // Example 5
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));
        PrgState prg5 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex5);
        Controller controller5 = new BasicController(1, logFilePath);
        controller5.addPrgState(prg5);

        // Example 6
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))), new PrintStmt(new ArithExp(new HeapReadExp(new HeapReadExp(new VarExp("a"))), new ValueExp(new IntValue(5)), 1)))))));
        PrgState prg6 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex6);
        Controller controller6 = new BasicController(1, logFilePath);
        controller6.addPrgState(prg6);

        // Example 7
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))), new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp(new HeapReadExp(new VarExp("v")), new ValueExp(new IntValue(5)), 1))))));
        PrgState prg7 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex7);
        Controller controller7 = new BasicController(1, logFilePath);
        controller7.addPrgState(prg7);

        // Example 8
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelationExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))), new PrintStmt(new VarExp("v")))));
        PrgState prg8 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex8);
        Controller controller8 = new BasicController(1, logFilePath);
        controller8.addPrgState(prg8);

        // Example 9 - shall throw error !
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt("a", new VarExp("v")), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));
        PrgState prg9 = new PrgState(new ExeStack(), new SymTable(), new OutList(), new FileTable(), new Heap(), ex9);
        Controller controller9 = new BasicController(1, logFilePath);
        controller9.addPrgState(prg9);

        // Text Menu
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),controller1));
        menu.addCommand(new RunExample("2",ex2.toString(),controller2));
        menu.addCommand(new RunExample("3",ex3.toString(),controller3));
        menu.addCommand(new RunExample("4",ex4.toString(),controller4));
        menu.addCommand(new RunExample("5",ex5.toString(),controller5));
        menu.addCommand(new RunExample("6", ex6.toString(), controller6));
        menu.addCommand(new RunExample("7", ex7.toString(), controller7));
        menu.addCommand(new RunExample("8", ex8.toString(), controller8));
        menu.addCommand(new RunExample("9", ex9.toString(), controller9));
        menu.show();
    }
}
