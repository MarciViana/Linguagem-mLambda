
package syntatical;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lexical.Lexeme;
import lexical.LexicalAnalysis;
import lexical.TokenType;
import modelo.Command;
import modelo.AddArrayValue;
import modelo.ApplyEachValue;
import modelo.ArrayValue;
import modelo.AssignCommand;
import modelo.AtArrayIntValue;
import modelo.BoolOp;
import modelo.BoolValue;
import modelo.CommandBlock;
import modelo.CompareBoolValue;
import modelo.ConstIntValue;
import modelo.ConstStringValue;
import modelo.DualBoolExpr;
import modelo.DualIntExpr;
import modelo.EachArrayValue;
import modelo.FillArrayValue;
import modelo.FilterArrayValue;
import modelo.IfCommand;
import modelo.IntOp;
import modelo.IntValue;
import modelo.LoadIntValue;
import modelo.PrintCommand;
import modelo.RandArrayValue;
import modelo.RelOp;
import modelo.RemoveArrayValue;
import modelo.SetArrayValue;
import modelo.ShowArrayValue;
import modelo.SizeIntArrayValue;
import modelo.SortArrayValue;
import modelo.StringConcat;
import modelo.StringValue;
import modelo.Value;
import modelo.Variable;
import modelo.WhileCommand;
import modelo.ZeroArrayValue;

public class SyntaticalAnalysis {
    private Lexeme current;
    private LexicalAnalysis lex;
    private Map<String,Variable> vars= new HashMap<String, Variable>();
    
    public SyntaticalAnalysis(LexicalAnalysis lex) throws IOException{
        this.lex = lex;
        this.current = lex.nextToken();
    }
    private void UnexpectedTokenLexem(String Token){
        if("&".equals(current.token) ||"$".equals(current.token) || "@".equals(current.token) || "~".equals(current.token) || "^".equals(current.token)){
            System.err.println("Lexema inválido " + "[" + current.token + "]");     
            System.err.printf("Linha: %02d ",lex.line());
            System.exit(0);
        }
        else{
            System.err.println("Lexema não esperado " + "[" + current.token + "]");    
            System.err.printf("Linha: %02d ",lex.line());
            System.exit(0);
        }
    }
    private void UnexpectedEOF(){
        System.err.println("Fim de arquivo inesperado");
        System.exit(0);
    }
    private void showError(){
        if(current.type == TokenType.UNEXPECTED_EOF||current.type == TokenType.END_OF_FILE)
            UnexpectedEOF();
        
        else
            UnexpectedTokenLexem(current.token);
    }
    private void matchToken(TokenType type) throws IOException{
        if(type == current.type){
            current = lex.nextToken();
        }else{
            showError();
        }
    }
    public Command init() throws IOException{
        Command c= procStatements();
        matchToken(TokenType.END_OF_FILE);
        return c;
    }
    
    private Command procStatements() throws IOException{
        CommandBlock cb = new CommandBlock();
        Command c = procCmd();
        cb.AddCommand(c);
        while(current.type==TokenType.PLUS||current.type==TokenType.MINUS||current.type==TokenType.NUMBER||current.type==TokenType.LOAD||current.type==TokenType.NEW||current.type==TokenType.VAR||current.type==TokenType.PAR_OPEN||current.type==TokenType.PRINT||current.type==TokenType.PRINTLN||current.type==TokenType.IF||current.type==TokenType.WHILE){
            c=procCmd();
            cb.AddCommand(c);
        }
        return cb;
    }
    private Command procCmd() throws IOException{
        Command c;
        if(current.type==TokenType.PLUS||current.type==TokenType.MINUS||current.type==TokenType.NUMBER||current.type==TokenType.LOAD||current.type==TokenType.NEW||current.type==TokenType.VAR||current.type==TokenType.PAR_OPEN){
            c=procAssign();
        }else if(current.type==TokenType.PRINT||current.type==TokenType.PRINTLN){
            c=procPrint();
        }else if(current.type==TokenType.IF){
            c=procIf();
        }else{
            c=procWhile();
        }
        return c;
    }
    private AssignCommand procAssign() throws IOException{
        int line =  lex.line();
        Value<?> v = procExpr();
        AssignCommand a = new AssignCommand(v, line);
        if(current.type == TokenType.SEMI_COLON){
            matchToken(TokenType.SEMI_COLON);
            Variable vb = procVar();
            a.addVariable(vb);
            while(current.type == TokenType.COMMA){
                matchToken(TokenType.COMMA);
                vb=procVar();
                a.addVariable(vb);
            }
        }
        matchToken(TokenType.DOT_COMMA);
        return a;
    }
    private PrintCommand procPrint() throws IOException{
        boolean newLine;
        int line=lex.line();
        if(current.type == TokenType.PRINT){
            matchToken(TokenType.PRINT);
            newLine=false;
        }else {
            matchToken(TokenType.PRINTLN);     
            newLine=true;
        }
        matchToken(TokenType.PAR_OPEN);
        Value<?> v = procText();
        matchToken(TokenType.PAR_CLOSE);
        matchToken(TokenType.DOT_COMMA);
        return new PrintCommand(v, newLine, line);
    }
    private IfCommand procIf() throws IOException{
        int line=lex.line();
        IfCommand ifC;
        Command c;
        matchToken(TokenType.IF);
        BoolValue bv = procBoolExpr();
        matchToken(TokenType.CBRA_OPEN);
        c=procStatements();
        matchToken(TokenType.CBRA_CLOSE);
        if(current.type == TokenType.ELSE){
            matchToken(TokenType.ELSE);
            matchToken(TokenType.CBRA_OPEN);
            Command celse=procStatements();
            matchToken(TokenType.CBRA_CLOSE);
            ifC=new IfCommand(bv, c, celse, line);
        }else
            ifC=new IfCommand(bv, c, line);
        return ifC;
    }
    private WhileCommand procWhile() throws IOException{ 
        int line = lex.line();
        matchToken(TokenType.WHILE);       
        BoolValue bv =procBoolExpr();
        matchToken(TokenType.CBRA_OPEN);
        Command c=procStatements();
        matchToken(TokenType.CBRA_CLOSE);
        return new WhileCommand(bv, c, line);
    }
    
    // <text> ::= (<string> | <expr>) { ‘,’ (<string> | <expr>) }
    private Value<?> procText()throws IOException{ //StringValue
        Value<?> v=null;
        StringConcat s = null;
        int line = lex.line();
        if(current.type==TokenType.STRING ){
            v=procString();            
        }else {
            v=procExpr();
        }  
        while(current.type==TokenType.COMMA){
            matchToken(TokenType.COMMA);
            if(current.type==TokenType.STRING ){
                s=new StringConcat(v, procString(), line);
            }else {
                s=new StringConcat(v, procExpr(), line);
            }
            v=s;
        }
        return v;
    }
    private BoolValue procBoolExpr() throws IOException{
        BoolValue bv;
        int line = lex.line();
        Value<?> v1=procExpr();
        RelOp ro=procBoolop();
        Value<?> v2=procExpr();
        CompareBoolValue cbv = new CompareBoolValue(ro, v1, v2, line);
        bv=cbv;
        while(current.type==TokenType.AND | current.type==TokenType.OR){
            BoolOp bo;
            if(current.type==TokenType.AND){
                matchToken(TokenType.AND);
                bo= BoolOp.And;
            }else{
                matchToken(TokenType.OR);
                bo= BoolOp.Or;
            }
            BoolValue b2=procBoolExpr();
            bv = new DualBoolExpr(bo,cbv,b2,line);
        }
        return bv;
    }
    private RelOp procBoolop() throws IOException{
        RelOp ro=null;
        if(current.type==TokenType.EQUAL){
            matchToken(TokenType.EQUAL); // '==' | '!=' | '<' | '>' | '<=' | '>='
            ro=RelOp.Equal;
        }else if(current.type==TokenType.DIFF){
            matchToken(TokenType.DIFF);
            ro=RelOp.NotEqual;
        }else if(current.type==TokenType.LOWER){
            matchToken(TokenType.LOWER);
            ro=RelOp.LowerThan;
        }else if(current.type==TokenType.HIGHER){
            matchToken(TokenType.HIGHER);
            ro=RelOp.GreaterThan;
        }else if(current.type==TokenType.LOWER_EQ){
            matchToken(TokenType.LOWER_EQ);
            ro=RelOp.LowerEqual;
        }else {
            matchToken(TokenType.HIGHER_EQ);
            ro=RelOp.GreaterEqual;
        }
        return ro;              
    }
    private Value<?> procExpr() throws IOException{
        Value<?> v1 = procTerm();
        if(current.type==TokenType.PLUS | current.type== TokenType.MINUS){
            IntOp io;
            if(current.type==TokenType.PLUS) {
                matchToken(TokenType.PLUS);
                io = IntOp.Add;
            } else {
                matchToken(TokenType.MINUS);
                io = IntOp.Sub;
            }
            Value<?> v2 = procTerm();
            DualIntExpr dual = new DualIntExpr(io, v1, v2, 0);
            v1=dual;
        }
        return v1;
    }
    private Value<?> procTerm() throws IOException{
        Value<?> v1 = procFactor();
        IntOp op;
        int line=lex.line();
        if(current.type==TokenType.MUL | current.type==TokenType.DIV | current.type==TokenType.MOD){
            Value<?> v2;
            if(current.type==TokenType.MUL){
                matchToken(TokenType.MUL);
                op=IntOp.Mul;
            }else if(current.type==TokenType.DIV){
                matchToken(TokenType.DIV);
                op=IntOp.Div;
            }else{
                matchToken(TokenType.MOD);
                op=IntOp.Mod;
            }
            v2=procFactor();
            v1=new DualIntExpr(op, v1, v2, line);
        }
        return v1;
    }
    private Value<?> procFactor() throws IOException{ // [‘+’ | ‘-‘] <number> | <load> | <value> | '(' <expr> ')'
        Value<?> v=null;
        if(current.type==TokenType.PLUS)
            matchToken(TokenType.PLUS);
        else if(current.type==TokenType.MINUS)
            matchToken(TokenType.MINUS);
        if(current.type==TokenType.NUMBER)
            v=procNumber();
        else if(current.type==TokenType.LOAD)
            v=procLoad();
        else if(current.type==TokenType.VAR | current.type==TokenType.NEW)
            v=procValue();
        else if(current.type==TokenType.PAR_OPEN){
            matchToken(TokenType.PAR_OPEN);
            v=procExpr();
            matchToken(TokenType.PAR_CLOSE);
        }
        else
            showError();
        return v;
    }
    private LoadIntValue procLoad() throws IOException{
        int line = lex.line();
        matchToken(TokenType.LOAD);
        matchToken(TokenType.PAR_OPEN);
        Value<?> v = procText();
        matchToken(TokenType.PAR_CLOSE);  
        return new LoadIntValue(v, line);
    }
    private Value<?> procValue() throws IOException{
        Value<?> v = null;
        if(current.type==TokenType.VAR | current.type==TokenType.NEW ){
            if(current.type==TokenType.NEW){
                v = procNew();
            }else{
                v  = procVar();
            }
            while(current.type==TokenType.DOT){
                matchToken(TokenType.DOT);
                if(current.type==TokenType.AT | current.type==TokenType.SIZE){
                    v=procInt(v);
                    break;
                }else
                    v=procArray(v);
            } 
        }
        return v;
    } 
    private ArrayValue procNew() throws IOException{
        ArrayValue array;
        matchToken(TokenType.NEW);
        if(current.type==TokenType.ZERO){
            array=procNzero();
        }else if(current.type==TokenType.RAND){
            array=procNrand();
        }else {
            array=procNfill();
        }
        return array;
    }
   
    private ZeroArrayValue procNzero() throws IOException{
        int line=lex.line();
        matchToken(TokenType.ZERO);
        matchToken(TokenType.SBRA_OPEN);
        Value<?> size = procExpr();
        matchToken(TokenType.SBRA_CLOSE);
        return new ZeroArrayValue(line, size);
    }
    private RandArrayValue procNrand() throws IOException{
        int line = lex.line();
        matchToken(TokenType.RAND);
        matchToken(TokenType.SBRA_OPEN);
        Value<?> size = procExpr();
        matchToken(TokenType.SBRA_CLOSE);
        return new RandArrayValue(line, size);
    }
    private FillArrayValue procNfill() throws IOException{
        int line = lex.line();
        matchToken(TokenType.FILL);
        matchToken(TokenType.SBRA_OPEN);
        Value<?> size = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> value = procExpr();
        matchToken(TokenType.SBRA_CLOSE);
        return new FillArrayValue(size, value, line);
    }
    
    //as funções abaixo provavelmente recebem um arrayValue
    private ArrayValue procArray(Value<?> a) throws IOException{
        ArrayValue array;
        if(current.type==TokenType.SHOW )
            array=procShow(a);
        else if(current.type==TokenType.SORT )
            array=procSort(a);
        else if( current.type==TokenType.ADD )
            array=procAdd(a);
        else if( current.type==TokenType.SET )
            array=procSet(a);
        else if( current.type==TokenType.FILTER )
            array=procFilter(a);
        else if(current.type==TokenType.REMOVE )
            array=procRemove(a);
        else if(current.type==TokenType.EACH)
            array=procEach(a);
        else 
            array=procApply(a);
        return array;
    }
    private ShowArrayValue procShow(Value<?> array) throws IOException{
        int line = lex.line();
        matchToken(TokenType.SHOW);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
        return new ShowArrayValue(array, line);
    }
    private SortArrayValue procSort(Value<?> array) throws IOException{
        int line = lex.line();
        matchToken(TokenType.SORT);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
        return new SortArrayValue(array, line);
    }
    private AddArrayValue procAdd(Value<?> array) throws IOException{
        int line = lex.line();
        matchToken(TokenType.ADD);
        matchToken(TokenType.PAR_OPEN);
        Value<?> value=procExpr();
        matchToken(TokenType.PAR_CLOSE);
        return new AddArrayValue(array, value, line);
    }
    private SetArrayValue procSet(Value<?> array) throws IOException{
        int line=lex.line();
        matchToken(TokenType.SET);
        matchToken(TokenType.PAR_OPEN);
        Value<?> index = procExpr();
        matchToken(TokenType.COMMA);
        Value<?> value = procExpr();
        matchToken(TokenType.PAR_CLOSE);
        return new SetArrayValue(array, index, value, line);
    }
    private FilterArrayValue procFilter(Value<?> array) throws IOException{
        int line = lex.line();
        matchToken(TokenType.FILTER);
        matchToken(TokenType.PAR_OPEN);
        Variable var = procVar();
        matchToken(TokenType.ARROW);
        BoolValue cond = procBoolExpr();
        matchToken(TokenType.PAR_CLOSE);
        return new FilterArrayValue(array, var, cond, line);
    }
    private RemoveArrayValue procRemove(Value<?> array) throws IOException{
        int line = lex.line();
        matchToken(TokenType.REMOVE);
        matchToken(TokenType.PAR_OPEN);
        Variable var = procVar();
        matchToken(TokenType.ARROW);
        BoolValue cond = procBoolExpr();
        matchToken(TokenType.PAR_CLOSE);
        return new RemoveArrayValue(array, var, cond, line);
    }
    private EachArrayValue procEach(Value<?> array) throws IOException{
        int line = lex.line();
        matchToken(TokenType.EACH);
        matchToken(TokenType.PAR_OPEN);
        Variable var = procVar();
        matchToken(TokenType.ARROW);
        Command cmd = procStatements();
        matchToken(TokenType.PAR_CLOSE);
        return new EachArrayValue(array, var, cmd, line);
    }
    private ApplyEachValue procApply(Value<?> array) throws IOException{
        int line = lex.line();
        matchToken(TokenType.APPLY);
        matchToken(TokenType.PAR_OPEN);
        Variable var = procVar();
        matchToken(TokenType.ARROW);
        Command cmd = procStatements();
        matchToken(TokenType.PAR_CLOSE);
        return new ApplyEachValue(array, var, cmd, line);
    }
    private IntValue procInt(Value<?> array) throws IOException{
        IntValue i;
        if(current.type==TokenType.AT)
            i=procAt(array);
        else 
            i=procSize(array);
        return i;
    }
    private AtArrayIntValue procAt(Value<?> array) throws IOException{
        int line = lex.line();
        String text = current.token;//acho q essa linha n precisa
        matchToken(TokenType.AT);
        matchToken(TokenType.PAR_OPEN);
        Value<?> index = procExpr();
        matchToken(TokenType.PAR_CLOSE);
        return new AtArrayIntValue(index, array, line);
    }
    private SizeIntArrayValue procSize(Value<?> array) throws IOException{
        int line = lex.line();
        matchToken(TokenType.SIZE);
        matchToken(TokenType.PAR_OPEN);
        matchToken(TokenType.PAR_CLOSE);
        return new SizeIntArrayValue(line, array);
    }

    private Variable procVar() throws IOException{
        String text = current.token;
        matchToken(TokenType.VAR);
        Variable v;
        if(vars.containsKey(text)){
            v=vars.get(text);
        }else{
            v=new Variable(text);
            vars.put(text, v);
        }
        return v;
    }
    private ConstIntValue procNumber() throws IOException{
        int line = lex.line();
        String text = current.token;
        matchToken(TokenType.NUMBER);
        int value = Integer.parseInt(text);
        return new ConstIntValue(value, line);
    }
    private ConstStringValue procString() throws IOException{
        int line = lex.line();
        String text=current.token;
        matchToken(TokenType.STRING);
        return new ConstStringValue(line, text);
    }
   
}
