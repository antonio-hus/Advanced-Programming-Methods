////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.statements;


import domain.PrgState;
import domain.datastructures.dictionary.MyDictionaryException;
import domain.datastructures.dictionary.MyIDictionary;
import domain.expressions.Exp;
import domain.expressions.ExpException;
import domain.state.IFileTable;
import domain.state.ISymTable;
import domain.types.IntType;
import domain.types.StringType;
import domain.values.IntValue;
import domain.values.StringValue;
import domain.values.Value;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

//////////////////////////
// CLASS IMPLEMENTATION //
//////////////////////////
public class ReadFileStmt implements IStmt {

    // READ FILE STATEMENT STRUCTURE
    // A read file statement is formed of a string type expression and a variable's name to read into
    Exp expression;
    String variableName;

    // READ FILE STATEMENT CONSTRUCTORS
    public ReadFileStmt(Exp exp, String varName) {
        this.expression = exp;
        this.variableName = varName;
    }

    // READ FILE STATEMENT METHODS
    // To String Method
    @Override
    public String toString() {
        return "readFile(" + expression.toString() + "," + variableName +")";
    }

    // Executes the statement of the program defined by Program State
    @Override
    public PrgState execute(PrgState state) throws StmtException, MyDictionaryException, ExpException {

        // Get the current stack, symTable and file table
        ISymTable symTbl = state.getSymbolsTable();
        IFileTable fileTable = state.getFileTable();

        // Check if variable exists inside the symTable
        if(!symTbl.containsKey(variableName))
            throw new StmtException("Variable does not exist in the symbolic table");

        // Check the type of variable
        // Must be IntType
        Value v = symTbl.get(variableName);
        if(!v.getType().equals(new IntType()))
            throw new StmtException("Variable must be of type Integer");

        // Evaluate the expression of the RFile
        Value val = expression.eval(symTbl);

        // Check type of the value
        // Must be string
        if(!val.getType().equals(new StringType())) {
            throw new StmtException("Filename must be of type String");
        }

        // Safely cast to StringValue
        StringValue s = (StringValue) val;

        // Check if file is not already in the file table
        if(!fileTable.containsKey(s))
            throw new StmtException("File is not open inside the file table");

        // Read from file with Buffered Reader into variable
        try {

            // Read a line from the file
            BufferedReader br = fileTable.get(s);
            String line = br.readLine();

            // Get the contents of the new line
            // Empty line
            IntValue readValue;
            if(line == null)
                readValue = new IntValue(0);

            // Integer Value
            else
                readValue = new IntValue(Integer.parseInt(line));

            // Update the value inside the symbolic table
            symTbl.update(variableName, readValue);

        }catch (IOException e) {
            throw new StmtException("There was an error opening the file: " + e);
        }

        // Return the new state
        return state;
    }

    // Returns a copy of the type
    @Override
    public IStmt deepCopy() {
        return new ReadFileStmt(this.expression.deepCopy(), this.variableName);
    }
}
