////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;


import domain.datastructures.dictionary.MyIDictionary;
import domain.state.ISymTable;
import domain.values.Value;

///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Exp {

    // Evaluates the given expression given the values in symbolsTable
    Value eval(ISymTable symbolsTable) throws ExpException;

    // Returns a copy of the expression
    Exp deepCopy();
}
