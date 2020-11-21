package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.InOutException;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.expression.ExpressionInterface;
import model.type.StringType;
import model.value.StringValue;
import model.value.ValueInterface;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class OpenRFileStatement implements StatementInterface{
    ExpressionInterface expression;

    public OpenRFileStatement(ExpressionInterface expression) {
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        DictionaryInterface<StringValue, BufferedReader> fileTable = state.getFileTable();

        ValueInterface value = expression.evaluate(symbolTable, state.getMemoryHeap());

        if (value.getType().equals(new StringType())){
            if (fileTable.isDefined((StringValue) value)){
                throw new InOutException("The file is already opened");
            }
            BufferedReader fileDescriptor;
            try{
                String fileName = ((StringValue)value).getValue();
                String path = new File(fileName).getAbsolutePath();
                fileDescriptor = new BufferedReader(new FileReader(path));
            } catch (IOException e) {
                throw new InOutException("File does not exist");
            }
            fileTable.add((StringValue)value, fileDescriptor);

            return state;

        }
        else
            throw new TypeException("Expression type is not a string");
    }
    @Override
    public String toString() {
        return "openRFile("+expression.toString()+")";
    }
}
