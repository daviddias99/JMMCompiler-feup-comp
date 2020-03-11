## Grammar

Program = ImportDeclaration, ClassDeclaration, EOF;

ImportDeclaration = { “import” , {Identifier, "."} } ";";

ClassDeclaration = "class", Identifier, [ "extends", Identifier ], "{", { VarDeclaration }, { MethodDeclaration } "}";  

VarDeclaration = Type, Identifier, ";";  

MethodDeclaration = "public", Type, Identifier, "(", [ Type, Identifier, { ",", Type, Identifier } ], ")", "{", { VarDeclaration }, { Statement }, "return", Expression, ";", "}";  

Type = "int", "[", "]" | "boolean" | "int" | Identifier;  

Statement = "{", { Statement }, "}"  
          | "if", "(", Expression, ")", Statement, "else", Statement  
          | "while", "(", Expression, ")", Statement   
          | Identifier, "=", Expression, ";"  
          | Identifier, "[", Expression, "]", ArrayAssignment ; 
          | Expression, ";" 
          
 ArrayAssignment = "=" Expression() ";"
                   | ExpressionFragment()
  
 #### remove left recursion
 Expression = IntegerLiteral, ExpressionFragment;  
           | "true", ExpressionFragment;  
           | "false", ExpressionFragment;    
           | Identifier, ExpressionFragment;  
           | "this", ExpressionFragment;  
           | "new", DynamicAllocation; 
           | "!", Expression, ExpressionFragment;  
]           | "(", Expression, ")", ExpressionFragment;

 DynamicAllocation = "int", "[", Expression, "]", ExpressionFragment;
           | Identifier, "(", ")", ExpressionFragment;  

ExpressionFragment = ("&&" | "<" | "+" | "-" | "*"| "/"), Expression, ExpressionFragment;  
ExpressionFragment =     | "[", Expression, "]", ExpressionFragment;     
           | ".", "length", ExpressionFragment;    
           | ".", Identifier, "(", [ Expression { ",", Expression} ], ")", ExpressionFragment;   
           | Ɛ
           
 # OUTDATED GRAMMAR          
           
 ### Chavascal way
 
 (import (static)? (Identifier ".")+ Identifier "(" ( Type ( "," Type)* )? ")" (Type)? ";")*
 
 ### Doubts
 
 * É necessário ter 2 ou mais identifiers no import?
 * É preciso todos os identifiers separados por pontos para invocar o metodo?
 * Nos métodos nao static qual é o identifier usado para o construtor?
 * Assume-se que o construtor n tem argumentos?
 * Em vez de ter parentesis com um ou lá dentro, nao será preferivel ter uma produção nova?
 * A linguagem permite apenas declarar variaveis antes de todos os statements, e depois um return?
 * A linguagem permite declarar/usar atributos com o this, ou apenas metodos?