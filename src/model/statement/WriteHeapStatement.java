package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.HeapInterface;
import model.exceptions.HeapException;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.exceptions.VariableDefinitionException;
import model.expression.ExpressionInterface;
import model.type.ReferenceType;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class WriteHeapStatement implements StatementInterface {
    private final String variableName;
    private final ExpressionInterface expression;

    public WriteHeapStatement(String variableName, ExpressionInterface expression) {
        this.variableName = variableName;
        this.expression = expression;
    }

    @Override
    public ProgramState execute(ProgramState state) throws MyException {
        DictionaryInterface<String, ValueInterface> symbolTable = state.getSymbolTable();
        HeapInterface<ValueInterface> memoryHeap = state.getMemoryHeap();

        if (!symbolTable.isDefined(variableName))
            throw new VariableDefinitionException(variableName + " is not defined");

        ValueInterface variable = symbolTable.get(variableName);
        if (!(variable.getType() instanceof ReferenceType))
            throw new TypeException(variableName + "is not of type RefType");

        int address = ((ReferenceValue) variable).getAddress();

        if (!(memoryHeap.isDefined(address)))
            throw new HeapException(variableName + "is not defined in heap");

        ValueInterface value = expression.evaluate(symbolTable, memoryHeap);

        if (!value.getType().equals(((ReferenceValue) variable).getLocationType()))
            throw new TypeException(value + " is of of " + ((ReferenceValue) variable).getLocationType() + " type");

        memoryHeap.update(address, value);

        return state;
    }

    @Override
    public String toString() {
        return "writeHeap(" + variableName + "," + expression + ")";
    }
}
