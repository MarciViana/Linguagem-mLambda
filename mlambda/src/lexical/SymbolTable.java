package lexical;

import java.util.Map;
import java.util.HashMap;

class SymbolTable {

    private Map<String, TokenType> st;

    public SymbolTable() {
        st = new HashMap<String, TokenType>();
        
        // FIXME: Add the tokens here.
        // st.put("???", TokenType.???);
	st.put(",",TokenType.COMMA);
	st.put(":",TokenType.SEMI_COLON);
	st.put(";",TokenType.DOT_COMMA);
	st.put("print",TokenType.PRINT);
	st.put("println",TokenType.PRINTLN);
	st.put("(",TokenType.PAR_OPEN);
	st.put(")",TokenType.PAR_CLOSE);
	st.put("if",TokenType.IF);
	st.put("else",TokenType.ELSE);
	st.put("{",TokenType.CBRA_OPEN);
	st.put("}",TokenType.CBRA_CLOSE);
	st.put("while",TokenType.WHILE);
	st.put(".",TokenType.DOT);
	st.put("and",TokenType.AND);
	st.put("or",TokenType.OR);
	st.put("==",TokenType.EQUAL);
	st.put("!=",TokenType.DIFF);
	st.put("<",TokenType.LOWER);
	st.put(">",TokenType.HIGHER);
	st.put("<=",TokenType.LOWER_EQ);
	st.put(">=",TokenType.HIGHER_EQ);
	st.put("==",TokenType.EQUAL);
	st.put("+",TokenType.PLUS);
	st.put("-",TokenType.MINUS);
	st.put("*",TokenType.MUL);
	st.put("/",TokenType.DIV);
	st.put("%",TokenType.MOD);
	st.put("load",TokenType.LOAD);
	st.put("new",TokenType.NEW);
	st.put("zero",TokenType.ZERO);
	st.put("rand",TokenType.RAND);
	st.put("fill",TokenType.FILL);
	st.put("show",TokenType.SHOW);
	st.put("sort",TokenType.SORT);
	st.put("add",TokenType.ADD);
	st.put("set",TokenType.SET);
	st.put("filter",TokenType.FILTER);
	st.put("remove",TokenType.REMOVE);
	st.put("each",TokenType.EACH);
	st.put("apply",TokenType.APPLY);
	st.put("at",TokenType.AT);
	st.put("size",TokenType.SIZE);
	st.put("{",TokenType.CBRA_OPEN);  
	st.put("}",TokenType.CBRA_CLOSE);
	st.put("[",TokenType.SBRA_OPEN);   
	st.put("]",TokenType.SBRA_CLOSE);    
	st.put("->",TokenType.ARROW);    
    }

    public boolean contains(String token) {
        return st.containsKey(token);
    }

    public TokenType find(String token) {
        return this.contains(token) ?
            st.get(token) : TokenType.INVALID_TOKEN;
    }
}
