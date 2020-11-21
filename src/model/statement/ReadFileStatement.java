package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.InOutException;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.exceptions.VariableDefinitionException;
import model.expression.ExpressionInterface;
import model.type.IntType;
import model.type.StringType;
import model.value.IntValue;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;
import java.io.IOException;

public class ReadFileStatement implements StatementInterface{
    ExpressionInterface expression;
    String variableName;

    public ReadFileStatement(ExpressionInterface expression, String variableName) {
        this.expression = expression;
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();

        if (!symbolTable.isDefined(variableName))
            throw new VariableDefinitionException("Variable does not exist");

        var variable = symbolTable.get(variableName);

        if (!variable.getType().equals(new IntType()))
            throw new TypeException("Variable is not of int type");

        ValueInterface value = expression.evaluate(symbolTable, state.getMemoryHeap());

        if (!value.getType().equals(new StringType()))
            throw new TypeException("Expresion is not of string type");

        if (!fileTable.isDefined((StringValue)value))
            throw new InOutException("There is no file descriptor associated to " + ((StringValue) value).getValue());

        BufferedReader fileDescriptor = fileTable.get((StringValue) value);
        String line;
        try {
            line = fileDescriptor.readLine();
        } catch (IOException e) {
            throw new InOutException(e.getMessage());
        }
        int number;
        try {
            number = Integer.parseInt(line);
        } catch (Exception e) {
            number = 0;
        }

        symbolTable.update(variableName, new IntValue(number));

        return state;

    }
    @Override
    public String toString() {
        return "readFile("+expression.toString()+")";
    }
}
