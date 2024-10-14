package domain;

import domain.datastructures.dictionary.*;
import domain.datastructures.list.*;
import domain.datastructures.stack.*;
import domain.statements.*;
import domain.values.*;

public class PrgState {
    MyIStack<IStmt> exeStack;
    MyIDictionary<String, Value> symTable;
    MyIList<Value> out;
    IStmt originalProgram;

    PrgState(MyIStack<IStmt> stk, MyIDictionary<String,Value> symtbl, MyIList<Value>
            ot, IStmt prg){
        exeStack=stk;
        symTable=symtbl;
        out = ot;
        originalProgram=deepCopy(prg);//recreate the entire original prg
        stk.push(prg);
    }

    private IStmt deepCopy(IStmt prg) {
        return prg;
    }

    public MyIStack<IStmt> getExeStack() {
        return this.exeStack;
    }

    public MyIDictionary<String, Value> getSymTable() {
        return this.symTable;
    }
}
