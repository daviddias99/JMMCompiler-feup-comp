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
           | "new", "int", "[", Expression, "]", ExpressionFragment;  
           | "new", Identifier, "(", ")", ExpressionFragment;  
           | "!", Expression, ExpressionFragment;  
]           | "(", Expression, ")", ExpressionFragment;

 

ExpressionFragment = ("&&" | "<" | "+" | "-" | "*"| "/"), Expression, ExpressionFragment;  
ExpressionFragment =     | "[", Expression, "]", ExpressionFragment;     
           | ".", "length", ExpressionFragment;    
           | ".", Identifier, "(", [ Expression { ",", Expression} ], ")", ExpressionFragment;   
           | Ɛ
           
 ### Chavascal way
 
 (import (static)? (Identifier ".")+ Identifier "(" ( Type ( "," Type)* )? ")" (Type)? ";")*
 
 ### Doubts
 
 * É necessário ter 2 ou mais identifiers no import?
 * É preciso todos os identifiers separados por pontos para invocar o metodo?
 * Nos métodos nao static qual é o identifier usado para o construtor?
 * Assume-se que o construtor n tem argumentos?