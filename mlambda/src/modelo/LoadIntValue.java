/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author Aluno
 */
public class LoadIntValue extends IntValue{
    private Value<?> texto;

    public LoadIntValue(Value<?> text, int line) {
        super(line);
        this.texto = text;
    }
    
    public Integer value(){
        String text= null;
        Value<?> val= texto;
        if (val instanceof Variable) {
            Variable v = (Variable) val;
            val = v.value();
        }
        if(val instanceof StringValue){
            StringValue sv = (StringValue)val;
            text = sv.value();
        }else if (val instanceof IntValue){
            IntValue iv = (IntValue)val;
            text = "" +iv.value();
        }else if (val instanceof ArrayValue){
            ArrayValue av = (ArrayValue)val;
            text = av.value().toString();
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        System.out.print(text);
        Scanner s = new Scanner(System.in);
        int i = s.nextInt();
        return i;
    }
}
