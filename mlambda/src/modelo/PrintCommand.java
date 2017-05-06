/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;

/**
 *
 * @author Aluno
 */
public class PrintCommand extends Command{
    private Value<?> value;
    private boolean newLine;
    
    public PrintCommand(Value<?> value, boolean newLine,int line){
        super(line);
        this.value=value;
        this.newLine=newLine;
    }
    
    public void execute (){
        String text= null;
        Value<?> val = value;
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
        }
        
        /*termino de condicoes*/
           System.out.print(text);
           if(newLine)
                System.out.println();
    }
}
