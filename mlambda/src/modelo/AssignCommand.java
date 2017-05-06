/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class AssignCommand extends Command{
    private List<Variable> vars;
    private Value<?> value;
    
    public AssignCommand(Value<?> value, int line){
        super(line);
        this.vars = new ArrayList<Variable>();
        this.value=value;
    }
    
    public void addVariable(Variable var){
        vars.add(var);
    }
    
    public void execute(){
        Value<?> val = value;
        if (val instanceof Variable) {
            Variable v = (Variable) value;
            val = v.value();
        }
        if(val instanceof StringValue){
            StringValue sv = (StringValue)val;
            String text = sv.value();
            val = new ConstStringValue(-1, text);
        }else if (val instanceof IntValue){
            IntValue iv = (IntValue)val;
            int n = iv.value();
            val = new ConstIntValue(n, -1);
        }else if (val instanceof ArrayValue){
            ArrayValue av = (ArrayValue)val;
            Array a = (Array) av.value();
            val = new ConstArrayValue(a);
        }
        for(Variable v:vars){
            v.setValue(val);
        }
    }
}
