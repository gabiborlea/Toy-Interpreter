package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.expression.ExpressionInterface;
import model.type.IntType;
import model.type.TypeInterface;
import model.value.IntValue;
import model.value.ValueInterface;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class NewLatchStatement implements StatementInterface{
    private final String variable;
    private final ExpressionInterface expression;
    private final static Lock lock = new ReentrantLock();

    public NewLatchStatement(String variable, ExpressionInterface expression) {
        this.variable = variable;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        lock.lock();
        ValueInterface value = expression.evaluate(state.getSymbolTable(), state.getMemoryHeap());
        if(!value.getType().equals(new IntType()))
            throw new TypeException(expression + " is not an integer");
        int number = ((IntValue) value).getValue();
        int freeAddresses = state.getLatchTable().add(number);
        if(!state.getSymbolTable().isDefined(variable))
            state.getSymbolTable().add(variable, new IntValue(freeAddresses));
        else
            state.getSymbolTable().update(variable, new IntValue(freeAddresses));
        lock.unlock();
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "newLatch(" + variable + "," + expression + ")";
    }
}
