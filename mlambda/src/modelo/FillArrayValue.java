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
public class FillArrayValue extends ArrayValue{
    private Value<?> size;
    private Value<?> value;

    public FillArrayValue(Value<?> size, Value<?> value, int line) {
        super(line);
        this.size = size;
        this.value = value;
    }
    
    public Array value(){
        Value<?> s = size;
        if (s instanceof Variable) {
            Variable v = (Variable) s;
            s = v.value();
        }
        Value<?> val = value;
        if (val instanceof Variable) {
            Variable var = (Variable) val;
            val = var.value();
        }
        if(s instanceof IntValue & val instanceof IntValue){
            int csize=(Integer) s.value();
            int cvalue=(Integer) val.value();
            int array[]=new int[csize];
            for(int i=0;i<csize;i++)
                array[i] = cvalue;
            Array a=new Array(csize,array );
            return a;
            
        } else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
            return null;
    }
}
