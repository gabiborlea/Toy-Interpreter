package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.ListInterface;
import model.exceptions.MyException;
import model.expression.ExpressionInterface;
import model.type.TypeInterface;
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
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        expression.typeCheck(typeEnv);
        return typeEnv;
    }

    @Override
    public String toString() {
        return "print(" +expression.toString()+")";
    }
}
