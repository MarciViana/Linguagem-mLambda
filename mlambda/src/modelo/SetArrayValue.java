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
public class SetArrayValue extends ArrayValue{
    private Value<?> array;
    private Value<?> index;
    private Value<?> value;

    public SetArrayValue(Value<?> array, Value<?> index, Value<?> value, int line) {
        super(line);
        this.array = array;
        this.index = index;
        this.value = value;
    }
    
    public Array value(){
        Array a=null;
        Value<?> in = this.index;
        Value<?> val = this.value;
        Variable var;
        if (in instanceof Variable) {
            var = (Variable) in;
            in = var.value();
        }
        if (val instanceof Variable) {
            var = (Variable) val;
            val = var.value();
        }
        if(in instanceof IntValue && val instanceof IntValue){
            int i = (Integer) in.value();
            int v = (Integer) val.value();
            Value<?> arr = this.array;
            if (arr instanceof Variable) {
                var = (Variable) arr;
                arr = var.value();
            }
            if(arr instanceof ArrayValue){
                a = (Array) arr.value();
                a.set(i,v);
            }else{
                System.err.println("Tipos Inválidos");
                System.exit(0);
            }
        }else{
                System.err.println("Tipos Inválidos");
                System.exit(0);
        }
        return a;
    }
}
