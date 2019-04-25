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
    | pin_stmt                                                      #statement
    | function_stmt                                                 #statement
    | iterative_stmt                                                #statement
    | selection_stmt                                                #statement
    | expression_stmt                                               #statement
    | notail=RETURN expression_stmt                                 #notailStatement
    | notail=BREAK SEMI                                             #notailStatement
    | notail=CONTINUE SEMI                                          #notailStatement
    | comment                                                       #stmtComment
    ;
pin_stmt
    : TOGGLE LPAR RPAR SEMI                                         #pinToggle
    | READ LPAR pin=pin_index RPAR SEMI                             #pinRead
    | WRITE LPAR pin=pin_index COMMA value=INTEGER RPAR SEMI        #pinWrite
    ;
pin_index
    : index=INTEGER
    | analog=A index=INTEGER
    ;
comment
    : COMMENT LETTER* COMMENT
    ;
block
    : LCUR body=stmt* RCUR
    ;
iterative_stmt
    : for_stmt
    | while_stmt
    ;
for_stmt
    : FOR expr=expression TO value=number DO body=stmt
    ;
while_stmt
    : WHILE expr=expression DO body=stmt
    ;
selection_stmt
    : switch_stmt
    | ifdo_stmt
    ;
// todo cases to invividual node
switch_stmt
    : SWITCH LPAR expr=expression RPAR LCUR cases=case_stmt* defaultcase=case_default? RCUR
    ;
// todo add expression , expression to case value
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
    : expression SEMI
    ;
expression
    : unary_expr
//    | left=expression COMMA right=expression // TODO: What is the usecase for this?
//                                                If decided to keep, then it's last step
    ;
unary_expr
    : op=MINUS right=multiplicative_expr
    | op=NEGATE right=multiplicative_expr
    | multiplicative_expr
    ;
multiplicative_expr
    : left=additive_expr op=TIMES right=additive_expr               #infixMultiplicativeExpr
    | left=additive_expr op=DIVIDE right=additive_expr              #infixMultiplicativeExpr
    | left=additive_expr op=MODULUS right=additive_expr             #infixMultiplicativeExpr
    | left=additive_expr op=EXPONENTIAL right=additive_expr         #infixMultiplicativeExpr
    | additive_expr                                                 #additiveExpr
    ;
additive_expr
    : left=relational_expr op=PLUS right=relational_expr            #infixAdditiveExpr
    | left=relational_expr op=MINUS right=relational_expr           #infixAdditiveExpr
    | relational_expr                                               #relationalExpr
    ;
relational_expr
    : left=conditional_equal_expr op=LESSER right=conditional_equal_expr            #infixRelationalExpr
    | left=conditional_equal_expr op=GREATER right=conditional_equal_expr           #infixRelationalExpr
    | left=conditional_equal_expr op=LESSEQUAL right=conditional_equal_expr         #infixRelationalExpr
    | left=conditional_equal_expr op=GREATEREQUAL right=conditional_equal_expr      #infixRelationalExpr
    | conditional_equal_expr                                                        #equalExpr
    ;
conditional_equal_expr
    : left=conditional_xor_expr op=EQUALS right=conditional_xor_expr            #infixEqualExpr
    | left=conditional_xor_expr op=NOT right=conditional_xor_expr               #infixEqualExpr
    | conditional_xor_expr                                                      #xorExpr
    ;
conditional_xor_expr
    : left=primary op=XOR right=primary             #infixConditionalXorExpr
    | conditional_and_expr                          #andExpr
    ;
conditional_and_expr
    : left=conditional_or_expr op=AND right=conditional_or_expr             #infixConditionalAndExpr
    | conditional_or_expr                                                   #orExpr
    ;
conditional_or_expr
    : left=assignment op=OR right=assignment                #infixCondtionalOrExpr
    | assignment                                            #assign
    ;
assignment
    : left=list_assignment ASSIGN right=list_assignment
    | list_assignment
    ;
list_assignment
    : id=identifier ASSIGN LBRACKET elements=list_element? RBRACKET
    | primary
    ;
list_element
    : element=primary COMMA next=list_element
    | element=primary
    ;
primary
    : LPAR child=expression RPAR                    #primaryLexprR
    | child=literal                                 #primaryLit
    | child=identifier                              #primaryId
    | child=function_stmt                           #primaryFuncStmt
    | child=list_expr                               #primaryListExpr
    ;
list_expr
    : identifier DOT list_stmt
    ;
list_stmt
    : GET LPAR argument RPAR
    | REMOVE LPAR argument RPAR
    | ADD LPAR argument RPAR
    | SIZE LPAR RPAR
    ;

identifier
    : value=identifier_val
    ;
identifier_val
    : (UNDERSCORE | LETTER) (LETTER | DIGIT | UNDERSCORE)*
    ;
string
    : DQUOTE string_val* DQUOTE
    ;
string_val
    : value=(LETTER | DIGIT | UNDERSCORE | SPACE)
    ;
literal
    : number
    | bool
    | string
    ;
number
    : value=INTEGER
    | value=REAL
    ;
bool
    : value=TRUE
    | value=FALSE
    ;

// =========== //

LETTER: [a-zA-Z];
REAL: '-'?DIGIT+ DOT DIGIT+;
INTEGER: '-'?DIGIT+;
DIGIT: [0-9];
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
FOR: 'for' ;
WHILE: 'while';
BREAK: 'break;';
CONTINUE: 'continue;';
TO: 'to';
SEMI: ';';
IF: 'if'  ;
ELSE: 'else';
COMMA : ',';
DOT : '.';
SPACE: ' ';
RETURN: 'return';
GET: 'get';
REMOVE: 'remove';
ADD: 'add';
SIZE: 'size';
LBRACKET: '[';
RBRACKET: ']';
COMMENT: '//';
READ: 'read';
WRITE: 'write';
TOGGLE: 'toggle';
A: 'A';