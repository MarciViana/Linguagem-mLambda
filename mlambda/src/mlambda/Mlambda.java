package mlambda;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import lexical.Lexeme;
import lexical.LexicalAnalysis;
import lexical.TokenType;
import lexical.LexicalException;
import modelo.Command;
import syntatical.SyntaticalAnalysis;



/**
 *
 * @author Aluno
 */
public class Mlambda {

    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Usage: java mlambda [MiniLambda File]");
            return;
        }
        try (LexicalAnalysis l = new LexicalAnalysis(args[0])) {
            SyntaticalAnalysis s = new SyntaticalAnalysis(l);
            Command c=  s.init();
            c.execute();
        } catch (Exception e) {
            System.err.println(e.getMessage());
            e.printStackTrace();
        }
    }

    private static boolean checkType(TokenType type) {
        return !(type == TokenType.END_OF_FILE ||
                 type == TokenType.INVALID_TOKEN ||
                 type == TokenType.UNEXPECTED_EOF);
    }
    
}
