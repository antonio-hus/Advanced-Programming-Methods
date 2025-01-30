////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter;
import antonio.interpreter.interpreter.domain.expressions.*;
import antonio.interpreter.interpreter.domain.procedures.IProcedure;
import antonio.interpreter.interpreter.domain.procedures.Procedure;
import antonio.interpreter.interpreter.domain.statements.*;
import antonio.interpreter.interpreter.domain.types.BoolType;
import antonio.interpreter.interpreter.domain.types.IntType;
import antonio.interpreter.interpreter.domain.types.RefType;
import antonio.interpreter.interpreter.domain.types.StringType;
import antonio.interpreter.interpreter.domain.values.BoolValue;
import antonio.interpreter.interpreter.domain.values.IntValue;
import antonio.interpreter.interpreter.domain.values.StringValue;
import antonio.interpreter.interpreter.services.ProgramControllerServices;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class GUI_Interpreter extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        // Create example statements
        List<IStmt> stmtList = new ArrayList<>();
        List<IProcedure> procedureList = new ArrayList<>();

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

        // Example 12 - Semaphore Table
        IStmt ex12 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())), new CompStmt(new VarDeclStmt("cnt", new IntType()), new CompStmt(new HeapAllocStmt("v1", new ValueExp(new IntValue(1))), new CompStmt(new CreateSemaphoreStmt("cnt", new HeapReadExp(new VarExp("v1"))), new CompStmt(new ForkStmt(new CompStmt(new AcquireSemaphoreStmt("cnt"), new CompStmt(new HeapWriteStmt("v1", new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10)), "*")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))), new ReleaseSemaphoreStmt("cnt"))))), new CompStmt(new ForkStmt(new CompStmt(new AcquireSemaphoreStmt("cnt"), new CompStmt(new HeapWriteStmt("v1", new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10)), "*")), new CompStmt(new HeapWriteStmt("v1", new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(2)), "*")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))), new ReleaseSemaphoreStmt("cnt")))))), new CompStmt(new AcquireSemaphoreStmt("cnt"), new CompStmt(new PrintStmt(new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(1)), "-")), new ReleaseSemaphoreStmt("cnt")))))))));

        // Example 13 -  Conditional Assignment
        IStmt ex13 = new CompStmt(new VarDeclStmt("b", new BoolType()), new CompStmt(new VarDeclStmt("c", new IntType()), new CompStmt(new AssignStmt("b", new ValueExp(new BoolValue(true))), new CompStmt(new ConditionalAssignmentStmt("c", new VarExp("b"), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), new CompStmt(new PrintStmt(new VarExp("c")), new CompStmt(new ConditionalAssignmentStmt("c", new ValueExp(new BoolValue(false)), new ValueExp(new IntValue(100)), new ValueExp(new IntValue(200))), new PrintStmt(new VarExp("c"))))))));

        // Example 14 - Countdown Latch
        IStmt ex14 = new CompStmt(new VarDeclStmt("v1", new RefType(new IntType())), new CompStmt(new VarDeclStmt("v2", new RefType(new IntType())), new CompStmt(new VarDeclStmt("v3", new RefType(new IntType())), new CompStmt(new VarDeclStmt("cnt", new IntType()), new CompStmt(new HeapAllocStmt("v1", new ValueExp(new IntValue(2))), new CompStmt(new HeapAllocStmt("v2", new ValueExp(new IntValue(3))), new CompStmt(new HeapAllocStmt("v3", new ValueExp(new IntValue(4))), new CompStmt(new CreateLatchStmt("cnt", new HeapReadExp(new VarExp("v2"))), new CompStmt(new ForkStmt(new CompStmt(new HeapWriteStmt("v1", new ArithExp(new HeapReadExp(new VarExp("v1")), new ValueExp(new IntValue(10)), "*")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v1"))), new CompStmt(new CountDownLatchStmt("cnt"), new ForkStmt(new CompStmt(new HeapWriteStmt("v2", new ArithExp(new HeapReadExp(new VarExp("v2")), new ValueExp(new IntValue(10)), "*")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v2"))), new CompStmt(new CountDownLatchStmt("cnt"), new ForkStmt(new CompStmt(new HeapWriteStmt("v3", new ArithExp(new HeapReadExp(new VarExp("v3")), new ValueExp(new IntValue(10)), "*")), new CompStmt(new PrintStmt(new HeapReadExp(new VarExp("v3"))), new CountDownLatchStmt("cnt")))))))))))), new CompStmt(new AwaitLatch("cnt"), new CompStmt(new PrintStmt(new ValueExp(new IntValue(100))), new CompStmt(new CountDownLatchStmt("cnt"), new PrintStmt(new ValueExp(new IntValue(100)))))))))))))));

        // Example 15 - Repeat Until
        IStmt ex15 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new RepeatUntilStmt(new CompStmt(new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), "-")))), new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), "+"))), new RelationExp(new VarExp("v"), new ValueExp(new IntValue(3)), "==")), new PrintStmt(new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), "*"))));

        // Example 16 - For
        IStmt ex16 = new CompStmt(new VarDeclStmt("a", new RefType(new IntType())), new CompStmt(new HeapAllocStmt("a", new ValueExp(new IntValue(20))), new CompStmt(new ForStmt("v", new ValueExp(new IntValue(0)), new ValueExp(new IntValue(3)), new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), "+"), new ForkStmt(new CompStmt(new PrintStmt(new VarExp("v")), new AssignStmt("v", new ArithExp(new VarExp("v"), new HeapReadExp(new VarExp("a")), "*"))))), new PrintStmt(new HeapReadExp(new VarExp("a"))))));

        // Example 17 - Sleep
        IStmt ex17 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(10))), new CompStmt(new ForkStmt(new CompStmt(new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), "-")), new CompStmt(new AssignStmt("v", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(1)), "-")), new PrintStmt(new VarExp("v"))))), new CompStmt(new SleepStmt(10), new PrintStmt(new ArithExp(new ValueExp(new IntValue(10)), new VarExp("v"), "*"))))));

        // Example 18 - Procedures
        IStmt ex18 = new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new VarDeclStmt("w", new IntType()), new CompStmt(new AssignStmt("v", new ValueExp(new IntValue(2))), new CompStmt(new AssignStmt("w", new ValueExp(new IntValue(5))), new CompStmt(new ProcedureCallStmt("sum", new ArithExp(new VarExp("v"), new ValueExp(new IntValue(10)), "*"), new VarExp("w")), new CompStmt(new PrintStmt(new VarExp("v")), new ForkStmt(new CompStmt(new ProcedureCallStmt("product", new VarExp("v"), new VarExp("w")), new ForkStmt(new ProcedureCallStmt("sum", new VarExp("v"), new VarExp("w")))))))))));

        // Procedures
        IProcedure proc1 = new Procedure("sum", new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ArithExp(new VarExp("a"), new VarExp("b"), "+")), new PrintStmt(new VarExp("v")))), "a", "b");
        IProcedure proc2 = new Procedure("product", new CompStmt(new VarDeclStmt("v", new IntType()), new CompStmt(new AssignStmt("v", new ArithExp(new VarExp("a"), new VarExp("b"), "*")), new PrintStmt(new VarExp("v")))), "a", "b");

        // Adding examples to list
        stmtList.add(ex1);
        stmtList.add(ex2);
        stmtList.add(ex3);
        stmtList.add(ex4);
        stmtList.add(ex5);
        stmtList.add(ex6);
        stmtList.add(ex7);
        stmtList.add(ex8);
        stmtList.add(ex9);
        stmtList.add(ex10);
        stmtList.add(ex11);
        stmtList.add(ex12);
        stmtList.add(ex13);
        stmtList.add(ex14);
        stmtList.add(ex15);
        stmtList.add(ex16);
        stmtList.add(ex17);
        stmtList.add(ex18);

        // Adding procedures to the list
        procedureList.add(proc1);
        procedureList.add(proc2);

        // Load root layout from FXML File
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/antonio/interpreter/interpreter/program-controller.fxml"));
        ProgramControllerServices controller = new ProgramControllerServices();
        fxmlLoader.setController(controller);
        controller.setStmtList(stmtList);
        controller.setProcedureList(procedureList);

        // Create the root node
        AnchorPane root = fxmlLoader.load();

        // Create & Configure Main Scene
        Scene scene = new Scene(root, 800, 500);
        stage.setTitle("Toy Language Interpreter - Antonio Hus");
        stage.setScene(scene);

        // Apply the CSS stylesheet
        scene.getStylesheets().add(Objects.requireNonNull(getClass().getResource("/antonio/interpreter/interpreter/styles.css")).toExternalForm());

        // Launch Stage
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}