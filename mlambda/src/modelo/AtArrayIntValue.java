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
public class AtArrayIntValue extends IntArrayValue {
    private Value<?> index;
    
    public AtArrayIntValue(Value<?> index, Value<?> array, int line) {
        super(array, line);
        this.index = index;
    }
    
    public Integer value(){
        int i,at=-1;
        Value<?> val= this.index;
        Variable v;
        if (val instanceof Variable) {
            v = (Variable) val;
            val = v.value();
        }
        if(val.value() instanceof Integer){
            i = (Integer) val.value();
            Value<?> ar= this.array;
            if (ar instanceof Variable) {
                v = (Variable) ar;
                ar = v.value();
            }
            if(ar.value() instanceof Array){
                Array a = (Array) ar.value();
                at=a.at(i);
            }else{
                System.err.println("Tipos Inválidos");
                System.exit(0);
            }   
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        return at;
    }
}
