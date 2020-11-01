package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.statement.StatementInterface;
import model.type.TypeInterface;
import model.value.BoolValue;
import model.value.IntValue;
import model.value.ValueInterface;

public class VarDeclarationStatement implements StatementInterface {
    String name;
    TypeInterface type;

    public VarDeclarationStatement(String name, TypeInterface type){
        this.name = name;
        this.type = type;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        ValueInterface value;
        switch (type.toString()) {
            case "bool" -> value = new BoolValue(false);
            case "int" -> value = new IntValue(0);
            default -> throw new MyException("Wrong type");
        }
        if (symbolTable.isDefined(name)) {
            throw new MyException("Variable already exists");
        }
        symbolTable.add(name, value);
        return state;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}