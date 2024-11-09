package domain.expressions;

import domain.state.IHeap;
import domain.state.ISymTable;
import domain.values.Value;

public class HeapWriteExp implements Exp {
    @Override
    public Value eval(ISymTable symbolsTable, IHeap heap) throws ExpException {
        return null;
    }

    @Override
    public Exp deepCopy() {
        return null;
    }
}
