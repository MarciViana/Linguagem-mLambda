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
public class AddArrayValue extends ArrayValue{
    private Value<?> array;
    private Value<?> value;

    public AddArrayValue(Value<?> array, Value<?> value, int line) {
        super(line);
        this.array = array;
        this.value = value;
    }

    public Array value(){
        Array a=null;
        Variable var;
        Value<?> arra=this.array;
        if (arra instanceof Variable) {
            var = (Variable) arra;
            arra = var.value();
        }
        if(arra instanceof ArrayValue){
            a = (Array) arra.value();
            Value<?> val=this.value;
            if (val instanceof Variable) {
                var = (Variable) val;
                val = var.value();
            }
            if( val instanceof IntValue){
                int v = (Integer) val.value();
                a=a.add(v);
            }else if( val instanceof ArrayValue){
                Array ar = (Array) val.value();
                a=a.add(ar);
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
