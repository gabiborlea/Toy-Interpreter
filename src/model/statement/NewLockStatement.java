package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.expression.ExpressionInterface;
import model.expression.VarExpression;
import model.type.TypeInterface;
import model.value.IntValue;

public class NewLockStatement implements StatementInterface{
    private final String variableName;

    public NewLockStatement(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        var lockTable = state.getLockTable();
        var symbolTable = state.getSymbolTable();
        var newFreeLocation = lockTable.add(-1);
        if(symbolTable.isDefined(variableName))
            symbolTable.update(variableName, new IntValue(newFreeLocation));
        else symbolTable.add(variableName, new IntValue(newFreeLocation));
        return state;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }
    @Override
    public String toString() {
        return "newLock("+variableName+")";
    }
}
