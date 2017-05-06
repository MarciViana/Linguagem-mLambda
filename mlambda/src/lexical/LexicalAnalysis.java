/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lexical;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.PushbackInputStream;

/**
 *
 * @author Aluno
 */

public class LexicalAnalysis implements AutoCloseable {

    private int line;
    private PushbackInputStream input;
    private SymbolTable st=new SymbolTable();

    public LexicalAnalysis(String filename) throws LexicalException {
        try {
            input = new PushbackInputStream(new FileInputStream(filename));
        } catch (Exception e) {
            throw new LexicalException("Unable to open file");
        }

        line = 1;
    }

    public void close() throws Exception {
        input.close();
    }

    public int line() {
        return this.line;
    }

    public Lexeme nextToken() throws IOException {
         Lexeme lex = new Lexeme("", TokenType.END_OF_FILE);
         int e=1;
         int c;
         
         while(e!=9 && e!=10){
             c = input.read();
             switch(e){
                 case 1:
                     if(c==-1)
                         e=10;
                     else if(c==' '||c=='\t'||c=='\n'||c=='\r'){
                         e=1;
                         if(c=='\n')
                             line++;
                     }
                     else if(c=='#')
                         e=2;
                     else if(Character.isDigit(c)){
                         lex.token+=(char)c;
                         e=3;
                     }else if(c=='='||c=='!'){
                         lex.token+=(char)c;
                         e=4;
                     }else if(c=='<'||c=='>'){
                         lex.token+=(char)c;
                         e=5;
                     }else if(c=='-'){
                        lex.token+=(char)c;
                         e=6;
                     }else if(Character.isLetter(c)){
                         lex.token+=(char)c;
                         e=7;
                     }else if(c=='\"')
                         e=8;
                     else if(c==';'||c==':'||c=='.'||c==','||c=='('||c==')'||c=='{'||c=='}'||c=='['||c==']'||c=='+'||c=='*'||c=='/'||c=='%'){
                         e=9;
                         lex.token+=(char)c;
                     }   
                     else{
                         lex.token+=(char)c;
                         lex.type=TokenType.INVALID_TOKEN;
                         e=10;
                     }
                     break;
                     
                 case 2:
                     if(c== '\n'){
                         e = 1;
                         line++;
                     }else{
                         e=2;
                     }
                     break;
                     
                 case 3:
                     if(Character.isDigit(c)){
                        lex.token+=(char)c;
                        
                     }else{
                         if(c!=-1)
                             input.unread(c);
                         e=10;
                         lex.type=TokenType.NUMBER;
                     }
                     break;
                 
                 case 4:
                     if(c== '='){
                         lex.token += (char)c;
                         e = 9;
                      
                     }else{
                            if(c==-1)
                             lex.type=TokenType.UNEXPECTED_EOF;
                            else
                                lex.type=TokenType.INVALID_TOKEN;                                     
                         e=10;
                     }
                    break;
                    
                 case 5:
                     if(c=='='){
                         lex.token+=(char)c;
                         e=9;
                     }else{
                         if(c!=-1){
                             input.unread(c);
                             e=9;
                         }else{
                             lex.type=TokenType.UNEXPECTED_EOF;
                             e=10;
                         }
                     }
                     break;
                 case 6:
                     if(c=='>'){
                         lex.token+=(char)c;
                         e=9;
                     }else{
                         if(c!=-1){
                             input.unread(c);
                             e=9;
                         }else{
                             lex.type=TokenType.UNEXPECTED_EOF;
                             e=10;
                         }
                     }
                     break;
                     
                 case 7:
                     if(Character.isLetter(c)){
                        lex.token+=(char)c;
                     }else{
                         if(c!=-1)
                             input.unread(c);
                         
                         e=9;
                     }
                     break;
                     
                case 8:
                     if(c== '\"'){
                         lex.type=TokenType.STRING;
                         e = 10;
                      
                     }else{
                            if(c==-1){
                             lex.type=TokenType.UNEXPECTED_EOF;
                             e=10;
                            }
                            else{
                                lex.token += (char)c;
                                e=8;
                                if(c=='\n')
                                    line++;
                            }
                     }
                    break;
                    
             }
         }
         
         if(e==9){
             if(st.contains(lex.token))
                 lex.type=st.find(lex.token);
             else
                 lex.type=TokenType.VAR;
         }
         

         // TODO: implement me.

         // HINT: read a char.
         // int c = input.read();

         // HINT: unread a char.
         // if (c != -1)
         //     input.unread(c);

         return lex;
    }
}
