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
public class IfCommand extends Command{
    BoolValue expr;
    Command then;
    Command ielse;

    public IfCommand(BoolValue expr, Command then, int line) {
        super(line);
        this.expr = expr;
        this.then = then;
        this.ielse=null;
    }
        
    public IfCommand(BoolValue expr, Command then, Command ielse, int line) {
        super(line);
        this.expr = expr;
        this.then = then;
        this.ielse = ielse;
    }
    
    public void execute(){
        if(this.expr.value())
            this.then.execute();
        else if(ielse!=null)
            this.ielse.execute();
    }
    
}
