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
public class ShowArrayValue extends ArrayValue{
    public Value<?> array;
    public ShowArrayValue (Value<?> array, int line){
        super(line);
        this.array=array;
    }
    public Array value(){
        Array a=null;
        Value<?> val=this.array;
        if(val instanceof Variable){
            Variable v = (Variable)val;
            val = v.value();
        }
        if(val instanceof ArrayValue){
            a = (Array) val.value();
            a.show();
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        return a;
    }
}
