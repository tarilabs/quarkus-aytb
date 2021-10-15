grammar Bot;

parse
  :  ( commands 
     | . )*    
     EOF
  ;

commands: command+ ;

command:
    rgb_command
    | color_command ;

rgb_command: SRGB HASH? rgb=HEX;

color_command: SCOLOR color=WORD;

SRGB
    : FSLASH R G B
    ;
SCOLOR
    : FSLASH C O L O R
    ;
HEX
    : HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT HEX_DIGIT
    ;
WORD
    : [a-zA-Z]+
    ;

HASH : '#';
fragment FSLASH: '/';
fragment HEX_DIGIT : [0-9] | A | B | C | D | E | F | O ; // adding O as Oscar as OCR might interpret zero as an O.
fragment A:[aA];
fragment B:[bB];
fragment C:[cC];
fragment D:[dD];
fragment E:[eE];
fragment F:[fF];
fragment G:[gG];
fragment H:[hH];
fragment I:[iI];
fragment J:[jJ];
fragment K:[kK];
fragment L:[lL];
fragment M:[mM];
fragment N:[nN];
fragment O:[oO];
fragment P:[pP];
fragment Q:[qQ];
fragment R:[rR];
fragment S:[sS];
fragment T:[tT];
fragment U:[uU];
fragment V:[vV];
fragment W:[wW];
fragment X:[xX];
fragment Y:[yY];
fragment Z:[zZ];

EVERYTHING 
    : . -> skip 
    ;