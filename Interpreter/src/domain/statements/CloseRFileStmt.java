////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;


import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.expressions.Exp;
import domain.expressions.ExpException;
import domain.types.IntType;
import domain.types.StringType;
import domain.values.StringValue;
import domain.values.Value;
import java.io.BufferedReader;
import java.io.IOException;

//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class CloseRFileStmt implements IStmt {

    // CLOSE READ FILE STATEMENT STRUCTURE
    // A close read file statement is formed of a string type expression
    Exp expression;

    // CLOSE READ FILE STATEMENT CONSTRUCTORS
    public CloseRFileStmt(Exp exp) { this.expression = exp; }

    // CLOSE READ FILE STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "closeRFile(" + expression.toString()+")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, MyDictionaryException, ExpException {

        // Get the current stack, symTable and file table
        MyIDictionary<String, Value> symTbl = state.getSymbolsTable();
        MyIDictionary<StringValue, BufferedReader> fileTable = state.getFileTable();

        // Evaluate the expression of the RFile
        Value val = expression.eval(symTbl);

        // Check type of the value
        // Must be string
        if(!val.getType().equals(new StringType())) {
            throw new StmtException("Filename must be of type String");
        }

        // Safely cast to StringValue
        StringValue s = (StringValue) val;

        // Check if file is in the file table
        if(!fileTable.containsKey(s))
            throw new StmtException("File is not yet open inside the file table");

        // Close file with Buffered Reader
        try {

            // Get the File Descriptor
            BufferedReader br = fileTable.get(s);

            // Close File
            br.close();

            // Remove File from FileTable
            fileTable.remove(s);

        }catch (IOException e) {
            throw new StmtException("There was an error closing the file: " + e);
        }

        // Return the new state
        return state;
    }
}
