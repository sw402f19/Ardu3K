grammar Ardu3k;

WS
    : [ \r\n\t]+ -> skip
    ;

program
    : defines=define* start=setup body=loop funcs=functions* #prog
    ;
define
    : op=DEFINE id=identifier (str=string | num=number)             #def
    ;
setup
    : name=SETUP ass=ASSIGN body=block                              #setupbody
    ;
loop
    : name=LOOP ass=ASSIGN body=block                               #loopbody
    ;
functions
    : id=identifier para=parameters ass=ASSIGN body=block
    ;
parameters
    : lpar=LPAR para=parameters_list rpar=RPAR
    ;
parameters_list
    : id=identifier
    | id=identifier seperator=COMMA parameters_list
    ;
block
    : lcur=LCUR bodystmt=block_stmt* RCUR
    ;
block_stmt
    : bodyexpr=expression_stmt
    | bodystmt=stmt
    ;
stmt
    : iterative_stmt
    | selection_stmt
    | function_stmt
    ;
iterative_stmt
    : for=for_stmt
    ;
for_stmt
    : FOR expr=expression TO value=number DO body=block
    ;
selection_stmt
    : switch=switch_stmt                                        #switch
    | if=ifdo_stmt                                              #if
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
    : multiplicative_expr
    | additive_expr PLUS multiplicative_expr
    | additive_expr MINUS multiplicative_expr
    ;
multiplicative_expr
    : primary
    | multiplicative_expr TIMES primary
    | multiplicative_expr DIVIDE primary
    | multiplicative_expr MODULUS primary
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
    : MINUS? integer
    | MINUS? real
    ;
real
    : DIGIT+ DOT DIGIT+
    ;
integer
    : DIGIT+
    ;
bool
    : TRUE
    | FALSE
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




