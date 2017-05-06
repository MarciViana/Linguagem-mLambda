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
public class ConstStringValue extends StringValue{
    private String text;
    public ConstStringValue(int line, String text){
        super(line);
        this.text=text;
    }
    public String value(){
        return text;
    }
}
