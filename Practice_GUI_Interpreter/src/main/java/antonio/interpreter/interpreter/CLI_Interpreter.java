////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter;
import antonio.interpreter.interpreter.domain.expressions.*;
import antonio.interpreter.interpreter.domain.statements.*;
import antonio.interpreter.interpreter.domain.types.*;
import antonio.interpreter.interpreter.domain.values.*;
import antonio.interpreter.interpreter.view.TextMenu;
import antonio.interpreter.interpreter.view.commands.*;

import java.util.Scanner;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class CLI_Interpreter {

    public static void main(String[] args) {

        // IMPLEMENTATION USING USER INTERFACE
        //
        //    // Application startup
        //    UserInterface app = new UserInterface();
        //
        //    // Run application until user triggers exit status
        //    int status = 0;
        //    do {
        //
        //        // Get application status
        //        status = app.menuScreen();
        //
        //    }while (status != 0);

        // IMPLEMENTATION USING TEXT MENU
        // SETUP
        // LOG FILE - C:\\Users\\anton\\OneDrive\\Documents\\GitHub\\Advanced-Programming-Methods\\GUI_Interpreter\\logs\\programState.log
        TextMenu.clearScreen();
        String logFilePath;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter the log file path: ");
        logFilePath = scanner.nextLine();
        TextMenu.proceedEnter();

        // Example 1
        IStmt ex1 = new CompStmt(new VarDeclStmt("v",new IntType()), new CompStmt(new AssignStmt("v",new ValueExp(new IntValue(2))), new PrintStmt(new VarExp("v"))));

        // Example 2
        IStmt ex2 = new CompStmt( new VarDeclStmt("a",new IntType()), new CompStmt(new VarDeclStmt("b",new IntType()), new CompStmt(new AssignStmt("a", new ArithExp(new ValueExp(new IntValue(2)),new ArithExp(new ValueExp(new IntValue(3)), new ValueExp(new IntValue(5)), 3), 1)), new CompStmt(new AssignStmt("b",new ArithExp(new VarExp("a"), new ValueExp(new IntValue(1)), 1)), new PrintStmt(new VarExp("b"))))));

        // Example 3
        IStmt ex3 = new CompStmt(new VarDeclStmt("a",new BoolType()), new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new BoolValue(true))), new CompStmt(new IfStmt(new VarExp("a"),new AssignStmt("v",new ValueExp(new IntValue(2))), new AssignStmt("v", new ValueExp(new IntValue(3)))), new PrintStmt(new VarExp("v"))))));

        // Example 4
        IStmt ex4 = new CompStmt(new VarDeclStmt("varf", new StringType()), new CompStmt(new AssignStmt("varf", new ValueExp(new StringValue("C:\\Users\\anton\\OneDrive\\Documents\\GitHub\\Advanced-Programming-Methods\\Interpreter\\files\\test1.in"))), new CompStmt(new OpenRFileStmt(new VarExp("varf")), new CompStmt(new VarDeclStmt("varc", new IntType()), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CompStmt(new ReadFileStmt(new VarExp("varf"), "varc"), new CompStmt(new PrintStmt(new VarExp("varc")), new CloseRFileStmt(new VarExp("varf"))))))))));

        // Example 5
        IStmt ex5 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new VarExp("a")))))));

        // Example 6
        IStmt ex6 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt("a", new VarExp("v")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))), new PrintStmt(new ArithExp(new HeapReadExp(new HeapReadExp(new VarExp("a"))), new ValueExp(new IntValue(5)), 1)))))));

        // Example 7
        IStmt ex7 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v"))), new CompStmt(new HeapWriteStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new ArithExp(new HeapReadExp(new VarExp("v")), new ValueExp(new IntValue(5)), 1))))));

        // Example 8
        IStmt ex8 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(4))), new CompStmt(new WhileStmt(new RelationExp(new VarExp("v"), new ValueExp(new IntValue(0)), ">"), new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), 2)))), new PrintStmt(new VarExp("v")))));

        // Example 9
        IStmt ex9 = new CompStmt(new VarDeclStmt("v", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(20))), new CompStmt(new VarDeclStmt("a", new RefType(new RefType(new IntType()))), new CompStmt(new HeapAllocStmt("a", new VarExp("v")), new CompStmt(new HeapAllocStmt("v", new ValueExp(new IntValue(30))), new PrintStmt(new HeapReadExp(new HeapReadExp(new VarExp("a")))))))));

        // Example 10
        IStmt ex10 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(22))), new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("a", new ValueExp(new IntValue(30))), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(32))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a"))))))), new CompStmt(new PrintStmt(new VarExp("v")), new PrintStmt(new HeapReadExp(new VarExp("a")))))))));

        // Example 11
        IStmt ex11 = new CompStmt(new VarDeclStmt("a", new IntType()), new CompStmt(new VarDeclStmt("b", new IntType()), new CompStmt(new VarDeclStmt("c", new IntType()), new CompStmt(new AssignStmt("a", new ValueExp(new IntValue(1))), new CompStmt(new AssignStmt("b", new ValueExp(new IntValue(2))), new CompStmt(new AssignStmt("c", new ValueExp(new IntValue(5))), new CompStmt(new SwitchStmt(new ArithExp(new VarExp("a"), new ValueExp(new IntValue(10)), "*"), new ArithExp(new VarExp("b"), new VarExp("c"), "*"), new CompStmt(new PrintStmt(new VarExp("a")), new PrintStmt(new VarExp("b"))), new ValueExp(new IntValue(10)), new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new PrintStmt(new ValueExp(new IntValue(200)))), new PrintStmt(new ValueExp(new IntValue(300)))), new PrintStmt(new ValueExp(new IntValue(300))))))))));

        // Example 12
        IStmt ex12 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())), new CompStmt(new VarDeclStmt("cnt", new IntType()), new CompStmt(new HeapAllocStmt("v1", new ValueExp(new IntValue(1))), new CompStmt(new CreateSemaphoreStmt("cnt", new HeapReadExp(new VarExp("v1"))), new CompStmt(new ForkStmt(new CompStmt(new AcquireSemaphoreStmt("cnt"), new CompStmt(new HeapWriteStmt("v1", new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10)), "*")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))), new ReleaseSemaphoreStmt("cnt"))))), new CompStmt(new ForkStmt(new CompStmt(new AcquireSemaphoreStmt("cnt"), new CompStmt(new HeapWriteStmt("v1", new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10)), "*")), new CompStmt(new HeapWriteStmt("v1", new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(2)), "*")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))), new ReleaseSemaphoreStmt("cnt")))))), new CompStmt(new AcquireSemaphoreStmt("cnt"), new CompStmt(new PrintStmt(new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(1)), "-")), new ReleaseSemaphoreStmt("cnt")))))))));

        // Example 13
        IStmt ex13 = new CompStmt(new VarDeclStmt("b", new BoolType()), new CompStmt(new VarDeclStmt("c", new IntType()), new CompStmt(new AssignStmt("b", new ValueExp(new BoolValue(true))), new CompStmt(new ConditionalAssignmentStmt("c", new VarExp("b"), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), new CompStmt(new PrintStmt(new VarExp("c")), new CompStmt(new ConditionalAssignmentStmt("c", new ValueExp(new BoolValue(false)), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), new PrintStmt(new VarExp("c"))))))));

        // Text Menu
        TextMenu menu = new TextMenu();
        menu.addCommand(new ExitCommand("0", "exit"));
        menu.addCommand(new RunExample("1",ex1.toString(),logFilePath,ex1));
        menu.addCommand(new RunExample("2",ex2.toString(),logFilePath,ex2));
        menu.addCommand(new RunExample("3",ex3.toString(),logFilePath,ex3));
        menu.addCommand(new RunExample("4",ex4.toString(),logFilePath,ex4));
        menu.addCommand(new RunExample("5",ex5.toString(),logFilePath,ex5));
        menu.addCommand(new RunExample("6", ex6.toString(),logFilePath,ex6));
        menu.addCommand(new RunExample("7", ex7.toString(),logFilePath,ex7));
        menu.addCommand(new RunExample("8", ex8.toString(),logFilePath,ex8));
        menu.addCommand(new RunExample("9", ex9.toString(),logFilePath,ex9));
        menu.addCommand(new RunExample("10", ex10.toString(),logFilePath,ex10));
        menu.addCommand(new RunExample("11", ex11.toString(),logFilePath,ex11));
        menu.addCommand(new RunExample("12", ex12.toString(),logFilePath,ex12));
        menu.addCommand(new RunExample("13", ex13.toString(),logFilePath,ex13));
        menu.show();
    }
}
