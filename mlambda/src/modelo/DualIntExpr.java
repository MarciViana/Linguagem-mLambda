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
public class DualIntExpr extends IntValue{
    private IntOp op;
    private Value<?> left;
    private Value<?> right;

    public DualIntExpr(IntOp op, Value<?> left, Value<?> right, int line) {
        super(line);
        this.op = op;
        this.left = left;
        this.right = right;
    }
    
    public Integer value(){
        Value<?> lef = left;
        Value<?> ri = right;
        if (lef instanceof Variable) {
            Variable v = (Variable) left;
            lef = v.value();
        }
        if (ri instanceof Variable) {
            Variable v2 = (Variable) right;
            ri = v2.value();
        }
        if(lef instanceof IntValue && ri instanceof IntValue){
            IntValue v1 = (IntValue) lef;
            IntValue v2 = (IntValue) ri;
            int l = v1.value();
            int r = v2.value();
            switch(this.op){
                case Add:
                    return (l+r);
                case Sub:
                    return (l-r);
                case Mul:
                    return (l*r);
                case Div:
                    return (l/r);
                case Mod:
                    return (l%r);
            }
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        return null;
    }
}
