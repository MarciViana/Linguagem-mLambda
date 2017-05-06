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
public class SizeIntArrayValue extends IntArrayValue{

    public SizeIntArrayValue(int line, Value<?> m) {
        super(m,line);
    }
    public Integer value(){
        int size = -1;
        Value<?> ar=this.array;
        if (ar instanceof Variable) {
                Variable v = (Variable) ar;
                ar = v.value();
        }
        if(ar instanceof ArrayValue){
            Array a  = (Array) ar.value();
            size = a.size();
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        return size;
    }
    
}
