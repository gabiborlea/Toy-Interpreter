package model.statement;

import model.ProgramState;
import model.adt.DictionaryInterface;
import model.adt.HeapInterface;
import model.exceptions.MyException;
import model.exceptions.TypeException;
import model.exceptions.VariableDefinitionException;
import model.expression.ExpressionInterface;
import model.type.ReferenceType;
import model.value.ReferenceValue;
import model.value.ValueInterface;

public class HeapAllocationStatement implements StatementInterface {
    private final String variableName;
    private final ExpressionInterface expression;

    public HeapAllocationStatement(String variableName, ExpressionInterface expression) {
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

        ValueInterface value = expression.evaluate(symbolTable, state.getMemoryHeap());

        if (!value.getType().equals(((ReferenceValue) variable).getLocationType()))
            throw new TypeException(value + " is of of " + ((ReferenceValue) variable).getLocationType() + " type");

        int address = memoryHeap.add(value);
        symbolTable.update(variableName, new ReferenceValue(address, ((ReferenceValue) variable).getLocationType()));

        return null;

    }

    @Override
    public String toString() {
        return "new(" + variableName + "," + expression + ")";
    }
}
