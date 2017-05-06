/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author Aluno
 */
public class CommandBlock extends Command{
    private List<Command> commands;
    
    public CommandBlock(){
        super(-1);
        this.commands = new ArrayList<Command>();
    }
    
    public void AddCommand(Command c){
        commands.add(c);
    }
    public void execute(){
        for(Command c:commands){
            c.execute();
        }
    }
}
