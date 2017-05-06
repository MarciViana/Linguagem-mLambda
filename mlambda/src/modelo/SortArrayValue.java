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
public class SortArrayValue extends ArrayValue{
    private Value<?> array;

    public SortArrayValue(Value<?> array, int line) {
        super(line);
        this.array = array;
    }
    
    public Array value(){
        Array a = null;
        Value<?> val=this.array;
        if (val instanceof Variable) {
                Variable var = (Variable) val;
                val = var.value();
        }
        if(val instanceof ArrayValue){
            a=(Array)val.value();
            a=a.sort();
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        return a;
    }
}
