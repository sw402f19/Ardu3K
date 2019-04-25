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
    | function_stmt                                                 #statement
    | iterative_stmt                                                #statement
    | selection_stmt                                                #statement
    | expression_stmt                                               #statement
    | notail=RETURN expression_stmt                                 #notailStatement
    | notail=BREAK SEMI                                             #notailStatement
    | notail=CONTINUE SEMI                                          #notailStatement
    ;
block
    : LCUR body=stmt* RCUR
    ;
iterative_stmt
    : for_stmt
    | while_stmt
    ;
/** todo ANTLR recognizes the token TO before identifier toggle.
  * making the function 'toggle' unusable.
  */
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
    : first
//    | left=expression COMMA right=expression // TODO: What is the usecase for this?
//                                                If decided to keep, then it's last step
    ;
first
    : primary
    | second
    ;
primary
    : LPAR child=expression RPAR                    #primaryLexprR
    | child=literal                                 #primaryLit
    | child=identifier                              #primaryId
    | child=function_stmt                           #primaryFuncStmt // TODO: Do we want this as a part of expression?
    | child=list_expr                               #primaryListExpr // TODO: Do we want this as a part of expression?
    | child=list_assignment                         #primaryListAssignment // TODO: Do we want this as a part of expression?
    ;
second
    : unary_expr // If we want to suport casting, then it will be at this stage
    | third
    ;
unary_expr
    : op=MINUS right=primary
    | op=NEGATE right=primary
    ;
third
    : multiplicative_expr
    | fourth
    ;
multiplicative_expr
    : left=primary op=TIMES right=primary           #infixMultiplicativeExpr
    | left=primary op=DIVIDE right=primary          #infixMultiplicativeExpr
    | left=primary op=MODULUS right=primary         #infixMultiplicativeExpr
    | left=primary op=EXPONENTIAL right=primary     #infixMultiplicativeExpr
    ;
fourth
    : additive_expr
    | fifth
    ;
additive_expr
    : left=primary op=PLUS right=primary            #infixAdditiveExpr
    | left=primary op=MINUS right=primary           #infixAdditiveExpr
    ;
fifth
    : relational_expr
    | sixth
    ;
relational_expr
    : left=primary op=LESSER right=primary          #infixRelationalExpr
    | left=primary op=GREATER right=primary         #infixRelationalExpr
    | left=primary op=LESSEQUAL right=primary       #infixRelationalExpr
    | left=primary op=GREATEREQUAL right=primary    #infixRelationalExpr
    ;
sixth
    : conditional_equal_expr
    | seventh
    ;
conditional_equal_expr
    : left=primary op=EQUALS right=primary          #infixEqualExpr
    | left=primary op=NOT right=primary             #infixEqualExpr
    ;
seventh
    : conditional_xor_expr
    | eighth
    ;
conditional_xor_expr // If other bitwise is desired, then they should be before
    : left=primary op=XOR right=primary             #infixConditionalXorExpr
    ;
eighth
    : conditional_and_expr
    | ninth
    ;
conditional_and_expr
    : left=primary op=AND right=primary             #infixConditionalAndExpr
    ;
ninth
    : conditional_or_expr
    | tenth
    ;
conditional_or_expr
    : left=primary op=OR right=primary              #infixCondtionalOrExpr
    ;
tenth
    : assignment
    ;
assignment
    : left=identifier ASSIGN right=primary
    ;
list_assignment
    : LBRACKET elements=list_element? RBRACKET
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