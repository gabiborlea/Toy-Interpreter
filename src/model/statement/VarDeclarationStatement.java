package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.exceptions.VariableDefinitionException;
import model.type.*;
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

        if (symbolTable.isDefined(name)) {
            throw new VariableDefinitionException("Variable already exists");
        }
        symbolTable.add(name, type.defaultValue());
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        typeEnv.add(name, type);
        return typeEnv;
    }

    @Override
    public String toString() {
        return type + " " + name;
    }
}
