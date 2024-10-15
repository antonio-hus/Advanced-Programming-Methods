////////////////////////
// PACKAGES & IMPORTS //
////////////////////////
package domain.expressions;


import domain.datastructures.dictionary.MyIDictionary;
import domain.values.Value;

///////////////////////////
// INTERFACE DESCRIPTION //
///////////////////////////
public interface Exp {

    // Evaluates the given expression given the values in symbolsTable
    Value eval(MyIDictionary<String, Value> symbolsTable) throws ExpException;
}
