package model.statement;

import model.ProgramState;
import model.adt.Dictionary;
import model.adt.DictionaryInterface;
import model.adt.ProcTableInterface;
import model.exceptions.MyException;
import model.expression.ExpressionInterface;
import model.type.TypeInterface;
import model.value.ValueInterface;

import java.util.List;

public class CallProcedureStatement implements StatementInterface{
    private final String name;
    private final List<ExpressionInterface> arguments;

    public CallProcedureStatement(String name, List<ExpressionInterface> arguments) {
        this.name = name;
        this.arguments = arguments;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ProcTableInterface procTable = state.getProcTable();
        if(!procTable.isDefined(name))
            throw new MyException("procedure: " + name + " is not defined");

        var procFrame = procTable.get(name);
        List<String> parameters = procFrame.getKey();
        StatementInterface body = procFrame.getValue();

        DictionaryInterface<String, ValueInterface> newSymbolTable = new Dictionary<>();

        for(int i= 0; i<arguments.size(); i++)
        {
            ValueInterface argumentValue = arguments.get(i).evaluate(state.getSymbolTable(), state.getMemoryHeap());
            newSymbolTable.add(parameters.get(i), argumentValue);
        }

        state.getSymbolTableStack().push(newSymbolTable);
        state.getExecutionStack().push(new ReturnStatement());
        state.getExecutionStack().push(body);

        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "call " + name + "(" + arguments + ")";
    }
}
