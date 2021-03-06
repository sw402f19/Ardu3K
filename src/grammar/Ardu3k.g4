grammar Ardu3k;

WS
    : [ \r\n\t]+ -> skip
    ;
compileUnit
    : program
    ;
program
    : define* setup loop funcs=function*
    ;
define
    : DEFINE id=identifier value=number
    ;
setup
    : SETUP ASSIGN block
    ;
loop
    : LOOP ASSIGN block
    ;

function
    : id=identifier LPAR para=parameter? RPAR ASSIGN block
    ;
parameter
    : id=identifier COMMA para=parameter
    | id=identifier
    ;
stmt
    : block                                                         #statement
    | pin_stmt SEMI                                                 #statement
    | function_stmt SEMI                                            #statement
    | iterative_stmt                                                #statement
    | selection_stmt                                                #statement
    | expression_stmt                                               #statement
    | notail=RETURN expression_stmt                                 #notailStatement
    | notail=BREAK SEMI                                             #notailStatement
    | notail=CONTINUE SEMI                                          #notailStatement
    | RESET id=identifier SEMI                                      #resetSpecific
    | notail=RESET SEMI                                             #notailStatement
    | comment                                                       #stmtComment
    | time_stmt                                                     #stmtTimed
    ;
time_stmt
    : BEFORE expr=expression IN clockName=identifier DO exec=stmt   #beforeStmt
    | AFTER expr=expression IN clockName=identifier DO exec=stmt    #afterStmt
    | DELAY LPAR expr=expression RPAR SEMI                          #delay
    ;
pin_stmt
    : TOGGLE LPAR pin=pin_index RPAR                                #pinToggle
    | READ LPAR pin=pin_index RPAR                                  #pinRead
    | WRITE LPAR pin=pin_index COMMA value=bool RPAR                #pinWriteBool
    | WRITE LPAR pin=pin_index COMMA value=INTEGER RPAR             #pinWriteInt
    | PINMODE LPAR pin=pin_index COMMA pinMode=pin_mode RPAR        #pinMode
    ;
pin_index
    : analog=ANALOG? index=expression
    ;
pin_mode
    : pinMode=OUTPUT
    | pinMode=INPUT
    | pinMode=TRUE
    | pinMode=FALSE
    ;
comment
    : DOUBLE_SLASH string_val* DOUBLE_SLASH
    ;
block
    : LCUR body=stmt* RCUR
    ;
iterative_stmt
    : while_stmt
    ;
while_stmt
    : WHILE expr=expression DO body=stmt
    ;
selection_stmt
    : switch_stmt
    | ifdo_stmt
    ;
switch_stmt
    : SWITCH LPAR expr=expression RPAR LCUR cases=case_stmt* defaultcase=case_default? RCUR
    ;
case_stmt
    : CASE value=expression COLON stmt*
    ;
case_default
    : DEFAULT COLON stmt*
    ;
ifdo_stmt
    : IF condition=expression DO upperbody=stmt                       #ifNoTrailingElse
    | IF condition=expression DO upperbody=stmt ELSE lowerbody=stmt   #elseTrailingIf
    ;
function_stmt
    : id=identifier LPAR args=argument? RPAR
    ;
argument
    : left=primary
    | left=primary COMMA right=argument
    ;

expression_stmt
    : SEMI
    | expression SEMI
    ;
expression
    : unary_expr
    | assignment_expr
    | left=expression COMMA right=unary_expr
    ;
unary_expr
    : NEGATE right=assignment_expr
    ;
assignment_expr
    : conditional_expr
    | assignment
    | pinread_assignment
    ;
pinread_assignment
    : READ LPAR pin=pin_index RPAR
    ;
assignment
    : left=identifier ASSIGN right=assignment_expr
    ;
conditional_expr
    : conditional_or_expr
    ;
conditional_or_expr
    : conditional_and_expr                                              #conditionalAndExpr
    | left=conditional_or_expr op=OR right=conditional_and_expr         #infixCondtionalOrExpr
    ;
conditional_and_expr
    : conditional_xor_expr                                              #conditionalXorExpr
    | left=conditional_and_expr op=AND right=conditional_xor_expr       #infixConditionalAndExpr
    ;
conditional_xor_expr
    : conditional_equal_expr                                            #conditionalEqualExpr
    | left=conditional_xor_expr op=XOR right=conditional_equal_expr     #infixConditionalXorExpr
    ;
conditional_equal_expr
    : relational_expr                                                   #relationalExpr
    | left=conditional_equal_expr op=EQUALS right=relational_expr       #infixEqualExpr
    | left=conditional_equal_expr op=NOT right=relational_expr          #infixEqualExpr
    ;
relational_expr
    : additive_expr                                                     #additiveExpr
    | left=relational_expr op=LESSER right=additive_expr                #infixRelationalExpr
    | left=relational_expr op=GREATER right=additive_expr               #infixRelationalExpr
    | left=relational_expr op=LESSEQUAL right=additive_expr             #infixRelationalExpr
    | left=relational_expr op=GREATEREQUAL right=additive_expr          #infixRelationalExpr
    ;
additive_expr
    : multiplicative_expr                                               #multiplicativeExpr
    | left=additive_expr op=PLUS right=multiplicative_expr              #infixAdditiveExpr
    | left=additive_expr op=MINUS right=multiplicative_expr             #infixAdditiveExpr
    ;
multiplicative_expr
    : primary                                                        #primaryExpr
    | left=multiplicative_expr op=TIMES right=primary                #infixMultiplicativeExpr
    | left=multiplicative_expr op=DIVIDE right=primary               #infixMultiplicativeExpr
    | left=multiplicative_expr op=MODULUS right=primary              #infixMultiplicativeExpr
    | left=multiplicative_expr op=EXPONENTIAL right=primary          #infixMultiplicativeExpr
    ;
primary
    : LPAR child=expression RPAR                    #primaryLexprR
    | child=time                                    #primaryTime
    | child=literal                                 #primaryLit
    | child=identifier                              #primaryId
    | child=function_stmt                           #primaryFuncStmt
    ;
identifier
    : value=identifier_val
    ;
identifier_val
    : (UNDERSCORE | LETTER) (LETTER | DIGIT | UNDERSCORE | keyword)*
    ;
string
    : DQUOTE string_val* DQUOTE
    ;
string_val
    : value=(LETTER | INTEGER | UNDERSCORE | SPACE)
    ;
literal
    : number
    | bool
    | string
    ;
time
    : val=INTEGER type=MILI
    | val=INTEGER type=SEC
    | val=INTEGER type=MIN
    ;
number
    : value=INTEGER
    | value=FLOAT
    ;
bool
    : value=TRUE
    | value=FALSE
    ;
keyword
    : DELAY
    | ANALOG
    | DO
    | SETUP
    | LOOP
    | SWITCH
    | CASE
    | DEFAULT
    | OR
    | AND
    | XOR
    | TRUE
    | FALSE
    | WHILE
    | BREAK
    | CONTINUE
    | IF
    | ELSE
    | RETURN
    | READ
    | WRITE
    | TOGGLE
    | SEC
    | MILI
    | MIN
    | BEFORE
    | AFTER
    | IN
    | RESET
    | PINMODE
    | INPUT
    | OUTPUT
;

// =========== //

DELAY: 'delay';
ANALOG: 'A';
FLOAT: '-'?DIGIT+ DOT DIGIT+;
INTEGER: '-'? DIGIT+;
DIGIT: [0-9];
LETTER: [a-zA-Z];
DEFINE: '#'?'define';
DO : 'do';
SETUP : 'setup';
LOOP : 'loop';
SWITCH : 'switch';
CASE : 'case';
DEFAULT : 'default';
UNDERSCORE: '_';
COLON : ':';
LESSER : '<';
GREATER : '>';
LESSEQUAL : '<=';
GREATEREQUAL : '>=';
EXPONENTIAL: '^';
OR : 'OR';
AND : 'AND';
NOT : '!=';
XOR : 'XOR';
EQUALS : '==';
NEGATE: '!';
TRUE : 'true';
FALSE : 'false';
LPAR: '(';
RPAR: ')';
LCUR: '{';
RCUR: '}';
PLUS: '+';
MINUS: '-';
DIVIDE: '/';
TIMES: '*';
MODULUS : '%';
ASSIGN: '=';
DQUOTE : '"';
WHILE: 'while';
BREAK: 'break';
CONTINUE: 'continue';
SEMI: ';';
IF: 'if'  ;
ELSE: 'else';
COMMA : ',';
DOT : '.';
SPACE: ' ';
RETURN: 'return';
LBRACKET: '[';
RBRACKET: ']';
DOUBLE_SLASH: '//';
READ: 'read';
WRITE: 'write';
TOGGLE: 'toggle';
SEC: 'sec';
MILI: 'ms';
MIN: 'min';
BEFORE: 'before';
AFTER: 'after';
IN: 'in';
RESET: 'reset';
PINMODE: 'pinMode';
INPUT: 'INPUT';
OUTPUT: 'OUTPUT';