package lexical;

public enum TokenType {
    // special tokens
    INVALID_TOKEN,
    UNEXPECTED_EOF,
    END_OF_FILE,

    // symbols
    DOT,        // .
    COMMA,      // ,
    SEMI_COLON, // :
    DOT_COMMA,  // ;
    PAR_OPEN,   // (
    PAR_CLOSE,  // )
    CBRA_OPEN,  // {
    CBRA_CLOSE, // }
    SBRA_OPEN,  // [
    SBRA_CLOSE, // ]
    ARROW,      // ->

    // keywords
    PRINT,     // print
    PRINTLN,   // println
    IF,        // if
    ELSE,      // else
    WHILE,     // while
    LOAD,      // load
    NEW,       // new
    ZERO,      // zero
    RAND,      // rand
    FILL,      // fill
    SHOW,      // show
    SORT,      // sort
    ADD,       // add
    SET,       // set
    FILTER,    // filter
    REMOVE,    // remove
    EACH,      // each
    APPLY,     // apply
    AT,        // at
    SIZE,      // size

    // operators
    AND,       // and
    OR,        // or
    EQUAL,     // ==
    DIFF,      // !=
    LOWER,     // <
    HIGHER,    // >
    LOWER_EQ,  // <=
    HIGHER_EQ, // >=
    PLUS,      // +
    MINUS,     // -
    MUL,       // *
    DIV,       // /
    MOD,       // %

    // others
    VAR,       // variable
    STRING,    // string
    NUMBER     // number
};
