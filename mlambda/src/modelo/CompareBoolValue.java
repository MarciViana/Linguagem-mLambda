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
public class CompareBoolValue extends BoolValue {//left e right so podem ser inteiros?
    private RelOp op;
    private Value<?> left;
    private Value<?> right;

    public CompareBoolValue(RelOp op, Value<?> left, Value<?> right, int line) {
        super(line);
        this.op = op;
        this.left = left;
        this.right = right;
    }
    public Boolean value(){
        Value<?> le = left;
        Value<?> ri = right;
        if (le instanceof Variable) {
            Variable v = (Variable) left;
            le = v.value();
        }
        if (ri instanceof Variable) {
            Variable v2 = (Variable) right;
            ri = v2.value();
        }
        if(le.value() instanceof Integer && ri.value() instanceof Integer){
            int l = (Integer) le.value();
            int r = (Integer) ri.value();
            switch(op){
                case Equal:
                    return (l == r);
                case NotEqual:
                    return (l != r);
                case LowerThan:
                    return (l < r);
                case LowerEqual:
                    return (l <= r);
                case GreaterThan:
                    return (l > r);
                case GreaterEqual:
                    return (l >= r);
            }
        }else{
            System.err.println("Tipos Inválidos");
            System.exit(0);
        }
        return null;
    }
}
