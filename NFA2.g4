grammar NFA2;

nfa : 'states' states 'initial' initial 'accepting' accepting 'transitions' transitions;

states : state (',' state)*;
state : ID;
initial : ID;
accepting : ID (',' ID)*;
transitions : transition (',' transition)*;
transition : ID '->' ID 'on' SYMBOL;

SYMBOL : [a-zA-Z0-9];
ID : [a-zA-Z_][a-zA-Z_0-9]*;
WS : [ \t\r\n]+ -> skip;
