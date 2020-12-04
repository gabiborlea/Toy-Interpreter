package model.statement;

import model.ProgramState;
import model.adt.Dictionary;
import model.adt.DictionaryInterface;
import model.adt.Stack;
import model.exceptions.MyException;
import model.value.ValueInterface;

import java.util.AbstractMap;
import java.util.Map;
import java.util.stream.Collectors;

public class ForkStatement implements StatementInterface{
    private final StatementInterface blockStatement;

    public ForkStatement(StatementInterface blockStatement) {
        this.blockStatement = blockStatement;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> newSymbolTable = new Dictionary<>();
        newSymbolTable.setContent(
                state.getSymbolTable().getContent().entrySet().stream()
                .map( (Map.Entry<String, ValueInterface> entry) -> new AbstractMap.SimpleEntry<>(entry.getKey(), entry.getValue().copy()))
                        .collect(Collectors.toMap(Map.Entry::getKey,Map.Entry::getValue)));

        return new ProgramState(new Stack<>(), newSymbolTable, state.getOutput(), state.getFileTable(), state.getMemoryHeap(), blockStatement);
    }

    @Override
    public String toString() {
        return "fork(" + blockStatement.toString() +")";
    }
}
