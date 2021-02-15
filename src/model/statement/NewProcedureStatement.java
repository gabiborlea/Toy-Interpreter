package model.statement;

import javafx.util.Pair;
import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.ProcTableInterface;
import model.exceptions.MyException;
import model.exceptions.RunTimeException;
import model.type.TypeInterface;

import java.util.List;

public class NewProcedureStatement implements StatementInterface{
    private final String name;
    private final List<String> parameters;
    private final StatementInterface body;

    public NewProcedureStatement(String name, List<String> parameters, StatementInterface body) {
        this.name = name;
        this.parameters = parameters;
        this.body = body;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        ProcTableInterface procTable = state.getProcTable();
        if(procTable.isDefined(name))
            throw new RunTimeException("procedure " + name + " is already defined");

        procTable.add(name, new Pair<>(parameters, body));
        return null;
    }

    @Override
    public DictionaryInterface<String, TypeInterface> typeCheck(DictionaryInterface<String, TypeInterface> typeEnv) throws MyException {
        return typeEnv;
    }

    @Override
    public String toString() {
        return "procedure " + name + "(" + parameters.toString() + ") " + body;
    }
}
