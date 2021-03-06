package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.InOutException;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.expression.ExpressionInterface;
import model.type.StringType;
import model.type.TypeInterface;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class CloseRFileStatement implements StatementInterface{
    ExpressionInterface expression;

    public CloseRFileStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();

        ValueInterface value = expression.evaluate(symbolTable, state.getMemoryHeap());

        if (!value.getType().equals(new StringType()))
            throw new TypeException("Expresion is not of string type");

        if (!fileTable.isDefined((StringValue)value))
            throw new InOutException("There is no file descriptor associated to " + ((StringValue) value).getValue());

        BufferedReader fileDescriptor = fileTable.get((StringValue) value);

        try {
            fileDescriptor.close();
        } catch (IOException e) {
            throw new InOutException(e.getMessage());
        }

        fileTable.remove((StringValue) value);

        return null;

    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        TypeInterface typeExpression = expression.typeCheck(typeEnv);
        if(!typeExpression.equals(new StringType()))
            throw new TypeException("Expresion is not of string type");

        return typeEnv;
    }


    @Override
    public String toString() {
        return "closeRFile("+expression.toString()+")";
    }

}
