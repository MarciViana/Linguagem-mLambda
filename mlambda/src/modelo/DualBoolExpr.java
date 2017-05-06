/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.sun.media.sound.RIFFWriter;

/**
 *
 * @author Aluno
 */
public class DualBoolExpr extends BoolValue{
    private BoolOp op;
    private BoolValue left;
    private BoolValue right;

    public DualBoolExpr(BoolOp op, BoolValue left, BoolValue right, int line) {
        super(line);
        this.op = op;
        this.left = left;
        this.right = right;
    }

    public Boolean value(){
        switch(op){
            case And:
                return (left.value() && right.value());
            case Or:
                return (left.value() || right.value());
        }
            System.err.println("Operação Inválida");
            System.exit(0);
        return null;
    }
}
