package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.StackInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.expression.ExpressionInterface;
import model.type.BoolType;
import model.value.BoolValue;
import model.value.ValueInterface;

public class IfStatement implements StatementInterface{
    ExpressionInterface expression;
    StatementInterface thenStatement;
    StatementInterface elseStatement;

    public IfStatement(ExpressionInterface expression, StatementInterface thenStatement, StatementInterface elseStatement){
        this.expression = expression;
        this.thenStatement = thenStatement;
        this.elseStatement = elseStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        StackInterface<StatementInterface> executionStack = state.getExecutionStack();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();

        ValueInterface value = expression.evaluate(symbolTable, state.getMemoryHeap());
        BoolValue bool = new BoolValue(true);

        if (value.getType().equals(new BoolType())){
            if(value.toString().equals(bool.toString()))
                executionStack.push(thenStatement);
            else
                executionStack.push(elseStatement);
        }
        else
            throw new TypeException("Expression type is not a boolean");

        return null;
    }

    @Override
    public String toString() {
        return "(IF("+ expression.toString()+") THEN(" +thenStatement.toString() +")ELSE("+elseStatement.toString()+"))";
    }
}
