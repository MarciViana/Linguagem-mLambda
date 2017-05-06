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
public class ZeroArrayValue extends ArrayValue{
    private Value<?> size;
    public ZeroArrayValue(int line,Value<?> size) {
        super(line);
        this.size=size;
    }
    
    public Array value(){
        Value<?> s = size;
        if (s instanceof Variable) {
            Variable v = (Variable) s;
            s = v.value();
        }
        if(s instanceof IntValue){
            int size=(Integer) s.value();
            int array[]=new int[size];
            for(int i=0;i<size;i++)
                array[i]=0;
            Array a=new Array(size,array );
            return a;
        } else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
            return null;
        }
    }
    
}
