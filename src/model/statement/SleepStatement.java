package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;

public class SleepStatement implements StatementInterface{
    private final ValueInterface number;

    public SleepStatement(ValueInterface number) {
        this.number = number;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        if(!number.getType().equals(new IntType()))
            throw new TypeException(number + "is not of int type");

        int numberValue = ((IntValue) this.number).getValue();
        if(numberValue > 0)
            state.getExecutionStack().push(new SleepStatement(new IntValue(numberValue-1)));
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "sleep(" + number + ")";
    }
}
