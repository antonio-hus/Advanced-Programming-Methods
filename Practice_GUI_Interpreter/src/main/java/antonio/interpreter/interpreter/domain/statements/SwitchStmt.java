////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package antonio.interpreter.interpreter.domain.statements;
import antonio.interpreter.interpreter.domain.PrgState;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyDictionaryException;
import antonio.interpreter.interpreter.domain.datastructures.dictionary.MyIDictionary;
import antonio.interpreter.interpreter.domain.expressions.Exp;
import antonio.interpreter.interpreter.domain.expressions.ExpException;
import antonio.interpreter.interpreter.domain.expressions.RelationExp;
import antonio.interpreter.interpreter.domain.state.IExeStack;
import antonio.interpreter.interpreter.domain.types.Type;


//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class SwitchStmt implements IStmt {

    // SWITCH STATEMENT STRUCTURE
    Exp exp, exp1, exp2;
    IStmt stmt1, stmt2, stmt3;


    // SWITCH STATEMENT CONSTRUCTOR
    public SwitchStmt(Exp exp, Exp exp1, IStmt stmt1, Exp exp2, IStmt stmt2, IStmt stmt3) {
        this.exp = exp;
        this.exp1 = exp1;
        this.exp2 = exp2;
        this.stmt1 = stmt1;
        this.stmt2 = stmt2;
        this.stmt3 = stmt3;
    }

    // SWITCH STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "switch("+ exp.toString()+") (case " + exp1.toString() +": " + stmt1.toString()+ ") (case " + exp2.toString() +": " + stmt2.toString() + ") (default: " + stmt3.toString() + ")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, ExpException, MyDictionaryException {

        // Get the current stack
        IExeStack stk = state.getExecutionStack();

        // Create IF equivalent statement
        IStmt equivalentStatement = new IfStmt(new RelationExp(exp, exp1, "=="), stmt1, new IfStmt(new RelationExp(exp, exp2, "=="), stmt2, stmt3));

        // Push equivalent statement on the stack
        stk.push(equivalentStatement);

        // Return null
        return null;
    }

    // Returns a copy of the statement
    @Override
    public IStmt deepCopy() {
        return new SwitchStmt(exp.deepCopy(), exp1.deepCopy(), stmt1.deepCopy(), exp2.deepCopy(), stmt2.deepCopy(), stmt3.deepCopy());
    }

    // Typechecking mechanism
    // Ensure statement can be run
    @Override
    public MyIDictionary<String, Type> typeCheck(MyIDictionary<String, Type> typeEnv) throws StmtException {
        try{

            // Type check expressions
            // Must be of the same type
            Type type1 = exp.typeCheck(typeEnv);
            Type type2 = exp.typeCheck(typeEnv);
            Type type3 = exp.typeCheck(typeEnv);
            if(!type1.equals(type2) || !type1.equals(type3)) {
                throw new StmtException("SWITCH STATEMENT ERROR - Conditions must be of the same type");
            }

            // Type check statements
            stmt1.typeCheck(typeEnv.deepCopy());
            stmt2.typeCheck(typeEnv.deepCopy());
            stmt3.typeCheck(typeEnv.deepCopy());

            // Return new environment
            return typeEnv;

        } catch(ExpException exp) {
            throw new StmtException("SWITCH STATEMENT ERROR - " + exp);
        }
    }
}
