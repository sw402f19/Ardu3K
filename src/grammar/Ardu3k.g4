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
    | notail=BREAK                                                  #notailStatement
    | notail=CONTINUE                                               #notailStatement
    | comment                                                       #stmtComment
    | timed_stmt                                                    #stmtTimed
    ;
timed_stmt
    : TIMED LPAR time=INTEGER COMMA id=identifier RPAR SEMI
    ;
pin_stmt
    : TOGGLE LPAR pin=pin_index RPAR SEMI                           #pinToggle
    | READ LPAR pin=pin_index RPAR SEMI                             #pinRead
    | WRITE LPAR pin=pin_index COMMA value=bool RPAR SEMI        #pinWrite
    ;
pin_index
    : analog=ANALOG? index=INTEGER
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
    | assignment_expr
    | left=expression COMMA right=unary_expr
    ;
unary_expr
    : NEGATE right=assignment_expr
    ;
assignment_expr
    : conditional_expr
    | assignment
    | list_assignment
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
    | child=list_assignment                         #primaryListAssignment
    ;
list_expr
    : identifier DOT list_stmt
    ;
list_assignment
    : LBRACKET elements=list_element? RBRACKET
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
ANALOG: 'analog';
TIMED: 'timed';