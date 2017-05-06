/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Aluno
 */
public class FilterArrayValue extends ArrayValue{
 
    private Value<?> array;
    private Variable var;
    private BoolValue cond;

    public FilterArrayValue(Value<?> array, Variable var, BoolValue cond, int line) {
        super(line);
        this.array = array;
        this.var = var;
        this.cond = cond;
    }
    
    
    public Array value(){
        Array vetor=new Array(0, new int[0]);
        Value<?> val=this.array;
        Array ar=null;
        if (val instanceof Variable) {
                Variable var = (Variable) val;
                val = var.value();
        }
        if(val instanceof ArrayValue){
            ar=(Array)val.value();
            for(int c=0;c<ar.size();c++){
                var.setValue(new ConstIntValue(ar.at(c), -1));
                if(cond.value())
                    vetor=vetor.add(ar.at(c));
            }
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        return vetor;
    }
}
