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
    Value eval(MyIDictionary<String, Value> symbolsTable);
}
