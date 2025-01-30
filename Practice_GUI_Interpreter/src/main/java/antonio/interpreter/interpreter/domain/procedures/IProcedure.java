package antonio.interpreter.interpreter.domain.procedures;

import antonio.interpreter.interpreter.domain.datastructures.list.MyIList;
import antonio.interpreter.interpreter.domain.statements.IStmt;

public interface IProcedure {
    String getName();
    MyIList<String> getParameters();
    IStmt getStmt();

}
