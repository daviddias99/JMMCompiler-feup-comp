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
 

 ### Checkpoint 1
 
1. LL(2) parser (Lookahead(2) Local!)  
    1.1 LL(1) é bonus  
    1.2 Limpeza de nós desnecessários
    
2. Error recovery  
    2.1 Apenas em while expression  
    ```
   while(<while_expression>){
      while(<while_expression>){
        ...
      }
   }
    ```
    2.2 Mostrar N erros
    
3. Anotação da arvore


 
 ### Doubts
 
 Q: É necessário ter 2 ou mais identifiers no import? 
 A: Não.

 Q: É preciso todos os identifiers separados por pontos para invocar o metodo?
 A: Sim.
 
 Q: Nos métodos nao static qual é o identifier usado para o construtor?
 A: É necessário importar o construtor.
 
 Q: Assume-se que o construtor n tem argumentos?
 A: Mais uma vez, é necessário importar o construtor, nesse import já deve dizer quais são os argumentos (se existirem). No entanto, como o edu mencionou, a gramática não permtite construtores com argumentos.
 
 Q: Em vez de ter parentesis com um ou lá dentro, nao será preferivel ter uma produção nova?
 A: De facto, ter uma produção nova aumenta a legibilidade. Fica ao nosso critério.
 
 Q: A linguagem permite apenas declarar variaveis antes de todos os statements, e depois um return?
 A: Sim. Duh!
 
 Q: A linguagem permite declarar/usar atributos com o this, ou apenas metodos?
 A: O professor vai verificar. Depois digo.