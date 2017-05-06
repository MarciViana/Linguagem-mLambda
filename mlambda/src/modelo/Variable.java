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
public class Variable extends Value<Value<?>>{
    private String name;
    private Value<?> value;

    public Variable(String name) {
        super(-1);
        this.name = name;
        this.value=null;
    }
    
    public String getName(){
        return this.name;
    }
    
    public void setValue(Value<?> value){
        this.value=value;
    }
    
    public Value<?> value(){
        return this.value;
    }
    
}
