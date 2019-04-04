grammar Ardu3k;

WS
    : [ \r\n\t]+ -> skip
    ;

programUnit
    : defines=define* start=setup body=loop funcs=functions*
    ;
define
    : DEFINE id=identifier (str=string | num=number)
    ;
setup
    : SETUP ASSIGN body=block
    ;
loop
    : LOOP ASSIGN body=block
    ;
functions
    : identifier parameters ASSIGN block
    ;
parameters
    : lpar=LPAR para=parameters_list rpar=RPAR
    ;
parameters_list
    : id=identifier
    | id=identifier COMMA parameters_list
    ;
block
    : LCUR block_stmt* RCUR
    ;
block_stmt
    : expression_stmt
    | stmt
    ;
stmt
    : iterative_stmt
    | selection_stmt
    | function_stmt
    ;
iterative_stmt
    : for_stmt
    ;
for_stmt
    : FOR expr=expression TO value=number DO body=block
    ;
selection_stmt
    : switch_stmt
    | ifdo_stmt
    ;
switch_stmt
    : SWITCH identifier LCUR case_stmt* case_default? RCUR
    ;
case_stmt
    : CASE (string | identifier | number) COLON block_stmt*
    ;
case_default
    : DEFAULT COLON block_stmt*
    ;
ifdo_stmt
    : IF expression DO block
    | IF expression DO block ELSE DO block
    | IF expression DO block ELSE ifdo_stmt
    ;
function_stmt
    : identifier args SEMI
    ;
args
    : LPAR args_list? RPAR
    ;
args_list
    : primary_expr
    | primary_expr COMMA args_list
    ;
expression_stmt
    : expression SEMI
    ;
expression
    : assignment_expr
    | expression COMMA assignment_expr
    ;
assignment_expr
    : conditional_expr
    | assignment
    ;
assignment
    : identifier ASSIGN primary_expr
    ;
conditional_expr
    : conditional_or_expr
    ;
conditional_or_expr
    : conditional_and_expr
    | conditional_or_expr OR conditional_and_expr
    ;
conditional_and_expr
    : conditional_xor_expr
    | conditional_and_expr AND conditional_xor_expr
    ;
conditional_xor_expr
    : conditional_equal_expr
    | conditional_xor_expr XOR conditional_equal_expr
    ;
conditional_equal_expr
    : relational_expr
    | conditional_equal_expr EQUALS relational_expr
    | conditional_equal_expr NOT additive_expr
    ;
relational_expr
    : additive_expr
    | relational_expr LESSER additive_expr
    | relational_expr GREATER additive_expr
    | relational_expr LESSEQUAL additive_expr
    | relational_expr GREATEREQUAL additive_expr
    ;
additive_expr
    : multiplicative_expr                                               #multiExpr
    | left=additive_expr op=PLUS right=multiplicative_expr              #plusExpr
    | left=additive_expr op=MINUS right=multiplicative_expr             #minusExpr
    ;
multiplicative_expr
    : primary                                                           #primaryExpr
    | left=multiplicative_expr op=TIMES right=primary                   #multiplicativeExpr
    | left=multiplicative_expr op=DIVIDE right=primary                  #multiplicativeExpr
    | left=multiplicative_expr op=MODULUS right=primary                 #multiplicativeExpr
    ;
primary
    : literal
    | identifier
    | LPAR expression RPAR
    | function_stmt
    ;

primary_expr
    : identifier
    | number
    | string
    | function_stmt
    | assignment_expr
    ;
identifier
    : (UNDERSCORE | LETTER) (LETTER | DIGIT | UNDERSCORE)*
    ;
string
    : DQUOTE (LETTER | DIGIT | UNDERSCORE | SPACE)* DQUOTE
    ;
literal
    : number
    | bool
    ;
number
    : sign=MINUS? type=integer                                       #intType
    | sign=MINUS? type=real                                          #realType
    ;
real
    : (left=DIGIT+ DOT right=DIGIT+)
    ;
integer
    : value=DIGIT+
    ;
bool
    : value=TRUE                                                #true
    | value=FALSE                                               #false
    ;








DEFINE: '#define';
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
OR : 'OR';
AND : 'AND';
NOT : '!';
XOR : '^';
EQUALS : 'EQUALS';
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
TO: 'to'  ;
SEMI: ';';
IF: 'if'  ;
ELSE: 'else';
COMMA : ',';
DOT : '.';
SPACE: ' ';
LETTER: [a-zA-Z];
DIGIT: [0-9];




