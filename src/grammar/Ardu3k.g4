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
    : SETUP LPAR RPAR ASSIGN block
    ;
loop
    : LOOP LPAR RPAR ASSIGN block
    ;
function
    : id=identifier LPAR para=parameter? RPAR ASSIGN block
    ;
parameter
    : id=identifier COMMA para=parameter
    | id=identifier
    ;
stmt
    : block
    | iterative_stmt
    | selection_stmt
    | function_stmt
    | expression_stmt
    | RETURN expression
    ;
block
    : LCUR body=stmt* RCUR
    ;
iterative_stmt
    : for_stmt
    | while_stmt
    ;
for_stmt
    : FOR expr=expression TO value=number DO body=loop_stmt
    ;
while_stmt
    : WHILE expr=expression DO body=loop_stmt
    ;
loop_stmt
    : loop_block
    | brk=BREAK
    | contin=CONTINUE
    | iterative_stmt
    | selection_stmt
    | function_stmt
    | expression_stmt
    | RETURN expression
    ;
loop_block
    : LCUR body=loop_stmt* RCUR
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
    : assignment_expr
    | left=expression COMMA right=assignment_expr
    ;
assignment_expr
    : conditional_expr
    | assignment
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
    : primary                                                           #unaryExpr
    | left=multiplicative_expr op=TIMES right=unary_expr                #infixMultiplicativeExpr
    | left=multiplicative_expr op=DIVIDE right=unary_expr               #infixMultiplicativeExpr
    | left=multiplicative_expr op=MODULUS right=unary_expr              #infixMultiplicativeExpr
    | left=multiplicative_expr op=EXPONENTIAL right=unary_expr          #infixMultiplicativeExpr
    ;
unary_expr
    : op=MINUS right=primary
    | op=NEGATE right=primary
    | primary
    ;

primary
    : child=literal                                                 #primaryLit
    | child=identifier                                              #primaryId
    | LPAR child=expression RPAR                                    #primaryLexprR
    | child=function_stmt                                           #primaryFuncStmt
    | child=list_expr                                               #primaryListExpr
    | child=list_assignment                                         #primaryListAssignment
    ;
list_assignment
    : id=identifier ASSIGN LBRACKET elements=list_element? RBRACKET
    ;
list_element
    : element=primary COMMA next=list_element
    | element=primary
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
TO: 'to'  ;
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
LETTER: [a-zA-Z];
REAL: '-'?DIGIT+ DOT DIGIT+;
INTEGER: '-'?DIGIT+;
DIGIT: [0-9];
