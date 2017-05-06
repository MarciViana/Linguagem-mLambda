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
public class ConstArrayValue extends ArrayValue{
    
    private Array array;

    public ConstArrayValue(Array array) {
        super(-1);
        this.array = array;
    }

    public Array value() {
        return this.array;
    }
    
}
