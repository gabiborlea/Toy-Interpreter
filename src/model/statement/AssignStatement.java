package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.StackInterface;
import model.exceptions.MyException;
import model.exceptions.VariableDefinitionException;
import model.expression.ExpressionInterface;
import model.type.TypeInterface;
import model.value.ValueInterface;

public class AssignStatement implements StatementInterface{
    String id;
    ExpressionInterface expression;

    public AssignStatement(String id, ExpressionInterface expression){
        this.id = id;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> executionStack = state.getExecutionStack();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();

        if (symbolTable.isDefined(id)) {
            ValueInterface value = expression.evaluate(symbolTable, state.getMemoryHeap());
            TypeInterface type = symbolTable.get(id).getType();
            if (value.getType().equals(type)){
                symbolTable.update(id, value);
            }
            else
                throw new VariableDefinitionException("declared type of variable" + id + " and type of  the assigned expression do not match");
        }

        else throw new VariableDefinitionException("the used variable" +id + " was not declared before");
        return state;
    }

    @Override
    public String toString() {
        return id + "=" + expression.toString();
    }
}
