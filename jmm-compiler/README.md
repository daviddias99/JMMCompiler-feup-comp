## Grammar

Program = ImportDeclaration, ClassDeclaration, EOF;

ImportDeclaration = { “import” , <Complete> } ";";

ClassDeclaration = "class", Identifier, [ "extends", Identifier ], "{", { VarDeclaration }, { MethodDeclaration } "}";  

VarDeclaration = Type, Identifier, ";";  

MethodDeclaration = "public", Type, Identifier, "(", [ Type, Identifier, { ",", Type, Identifier } ], ")", "{", { VarDeclaration }, { Statement }, "return", Expression, ";", "}";  

Type = "int", "[", "]" | "boolean" | "int" | Identifier;  

Statement = "{", { Statement }, "}"  
          | "if", "(", Expression, ")", Statement, "else", Statement  
          | "while", "(", Expression, ")", Statement  
          | Expression, ";"  
          | Identifier, "=", Expression, ";"  
          | Identifier, "[", Expression, "]", "=", Expression, ";";  
  
 #### remove left recursion
 Expression = IntegerLiteral, ExpressionFragment;  
           | "true", ExpressionFragment;  
           | "false", ExpressionFragment;    
           | Identifier, ExpressionFragment;  
           | "this", ExpressionFragment;  
           | "new", "int", "[", Expression, "]", ExpressionFragment;  
           | "new", Identifier, "(", ")", ExpressionFragment;  
           | "!", Expression, ExpressionFragment;  
           | "(", Expression, ")", ExpressionFragment;
 

ExpressionFragment = ("&&" | "<" | "+" | "-" | "*"| "/"), Expression, ExpressionFragment;  
           | "[", Expression, "]", ExpressionFragment;     
           | ".", "length", ExpressionFragment;    
           | ".", Identifier, "(", [ Expression { ",", Expression} ], ")", ExpressionFragment;   
           | Ɛ
