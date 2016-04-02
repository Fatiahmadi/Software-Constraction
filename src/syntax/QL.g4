grammar QL;

form                  : 'Form'  IDENTIFIER '{' question+'}';

question  : IDENTIFIER  ':'    STRINGLITERAL type                                       #NormalQ
          | IDENTIFIER  ':'    STRINGLITERAL type  '(' expression ')'                   #CalculatedQ
          | 'if'  '(' expression ')' '{'  question+ '}'                                 #ifQ
          |  'if' '(' cond=expression ')' 
				'{' thenBranch+=question+ '}'                                         
				'else' '{' elseBranch+=question+ '}' 	                                 #ifElseQ
          ;
expression 
                     : OP=('!'|'+'|'-') expression 					       #unaryExpr
                     |  expression OP=('*' |'/') expression                #TimeDivExpr 
                     |  expression OP=('+' | '-') expression 			   #addSubExpr                        
                     |  expression OP=('>'|'>='|'<'|'<=' ) expression      #COMPExpr  
                     |  expression OP=('==' |'!=') expression               #EQUALExpr 
                     |  expression '&&' expression                          #AndExpr 
                     |   expression '||' expression 					    #ORExpr                                       
                     |  '(' expression ')'                                  #Par 
                     |  BOOLEANLITERAL                                     #BooleanLiteral                                       
                     |  STRINGLITERAL                                      #StringLiteral
                     |  INTEGERLITERAL                                      #IntegerLiteral
                     |  IDENTIFIER                                         #identifier
                   
                     ;                                            
                     
                     
              
STRINGLITERAL        :'"'(ESC | .)*? '"';
BOOLEANLITERAL       :  'true'
                     |  'false'
                     ;
INTEGERLITERAL       : '0'..'9'+ ('.' '0'..'9'+)*;
    
IDENTIFIER          : (LOWERCASE | UPPERCASE) (LOWERCASE | UPPERCASE | DIGIT )*;

 LOWERCASE           : [a-z]; 
 UPPERCASE           : [A-Z]; 
 DIGIT               : [0-9];  
  
type                 :  'boolean'    #booleanQL                 
                     |  'string'     #stringQL
                     |  'integer'    #integerQL
                     ;

      
WS                   :  [ \t\r\n]+ -> skip;
ESC				     : '\\"' | '\\\\';
COMMENT
                     :   '/*' .*? '*/' -> skip
                      ;