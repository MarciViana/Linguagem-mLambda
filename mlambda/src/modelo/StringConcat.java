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
public class StringConcat extends StringValue{
    private Value<?> left;
    private Value<?> right;
    
    public StringConcat(Value<?> left, Value<?> right, int line){
        super(line);
        this.left=left;
        this.right=right;
    }
    
    public String value(){
        Value<?> lef= left;
        Value<?> ri= right;
        String text=null;
        if (lef instanceof Variable) {
            Variable v = (Variable) lef;
            lef = v.value();
        }
        if (ri instanceof Variable) {
            Variable v = (Variable) ri;
            ri = v.value();
        }
        if(lef instanceof StringValue){
            StringValue sv = (StringValue)lef;
            text = sv.value();
        }else if (lef instanceof IntValue){
            IntValue iv = (IntValue)lef;
            text = "" +iv.value();
        }else if (lef instanceof ArrayValue){
            ArrayValue av = (ArrayValue)lef;
            text = av.value().toString();
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        
        if(ri instanceof StringValue){
            StringValue sv = (StringValue)ri;
            text += sv.value();
        }else if (ri instanceof IntValue){
            IntValue iv = (IntValue)ri;
            text += "" +iv.value();
        }else if (ri instanceof ArrayValue){
            ArrayValue av = (ArrayValue)ri;
            text = av.value().toString();
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        return (text);
    }

}
