package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.ListInterface;
import model.exceptions.MyException;
import model.expression.ExpressionInterface;
import model.value.ValueInterface;

public class PrintStatement implements StatementInterface{
    ExpressionInterface expression;

    public PrintStatement(ExpressionInterface expression){
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ListInterface<ValueInterface> output = state.getOutput();
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        output.add(expression.evaluate(symbolTable, state.getMemoryHeap()));
        return state;
    }

    @Override
    public String toString() {
        return "print(" +expression.toString()+")";
    }
}
