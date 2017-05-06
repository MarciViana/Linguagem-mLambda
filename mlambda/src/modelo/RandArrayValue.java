/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Random;

/**
 *
 * @author Aluno
 */
public class RandArrayValue extends ArrayValue{
    private Value<?> size;
    public RandArrayValue(int line,Value<?> size) {
        super(line);
        this.size=size;
    }
    public Array value(){
        Random rand = new Random();
        Value<?> s = size;
        if (size instanceof Variable) {
            Variable v = (Variable) size;
            s = v.value();
        }
        if(s instanceof IntValue){
            int size=(Integer) s.value();
            int array[]=new int[size];
            for(int i=0;i<size;i++)
                array[i]=rand.nextInt(100);
            Array a=new Array(size,array );
            return a;            
        } else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
            return null;
    }
}
