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
public class WhileCommand extends Command{
    private BoolValue expr;
    private Command cmd;

    public WhileCommand(BoolValue expr, Command cmd, int line) {
        super(line);
        this.expr = expr;
        this.cmd = cmd;
    }
    public void execute(){
        while(this.expr.value())
            this.cmd.execute();
    }
}
