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
public class EachArrayValue extends ArrayValue{
    private Value<?> array;
    private Variable var;
    private Command cmd;

    public EachArrayValue(Value<?> array, Variable var, Command cmd, int line) {
        super(line);
        this.array = array;
        this.var = var;
        this.cmd = cmd;
    }
    public Array value(){
        Value<?> val=this.array;
        Array ar=null;
        if (val instanceof Variable) {
                Variable var = (Variable) val;
                val = var.value();
        }
        if(val instanceof ArrayValue){
            ar=(Array)val.value();
            for(int c=0;c<ar.size();c++){
                var.setValue(new ConstIntValue(ar.at(c), -1));
                cmd.execute();
            }
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }            
        return ar;
    }
    
}
