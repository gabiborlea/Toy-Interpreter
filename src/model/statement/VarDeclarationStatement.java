package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.exceptions.VariableDefinitionException;
import model.type.TypeInterface;
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
            case "bool", "int", "string" -> value = type.defaultValue();
            default -> throw new TypeException("Wrong type");
        }
        if (symbolTable.isDefined(name)) {
            throw new VariableDefinitionException("Variable already exists");
        }
        symbolTable.add(name, value);
        return state;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
