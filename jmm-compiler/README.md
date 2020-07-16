**PROJECT TITLE: Compiler of the Java-- language to Java Bytecodes
**GROUP: 5g

(Names, numbers, self assessment, and contribution of the members of the group to the project according to:)
NAME1: David Luís Dias da Silva, NR1: 201705373, GRADE1: 20, CONTRIBUTION1: 25
NAME2: Eduardo Carreira Ribeiro, NR2: 201705421, GRADE2: 20, CONTRIBUTION2: 25
NAME3: Luís Pedro Pereira Lopes Mascarenhas Cunha, NR3: 201706736, GRADE3: 20, CONTRIBUTION3: 25
NAME4: Manuel Monge dos Santos Pereira Coutinho, NR4: 201704211, GRADE4: 20, CONTRIBUTION2: 25

GLOBAL Grade of the project: 20
** SUMMARY: The tool built serves as a compiler of .jmm files, written in the Java-- language, a subset of the Java language. All programs that are valid in the Java-- language are also valid in the Java language. The compiler must generate files of the classes with JVM instructions accepted by _jasmin_, a tool that translates those classes in Java bytecodes (classfiles). The generated classes given Java--code can be integrated in a Java application. Those classes can invoke Java methods previously compiled to bytecodes.

The process of generating the instructions and the .j file from the .jmm file is divided in several phases: the compiler first performs a **lexical and syntactic analysis** to the code, matching tokens and expressions to check the validity of the code. This, among other things, generates the abstract syntax tree of the program. Next, a **semantic analysis** of the program is performed, catching some types of errors that could not be detected in the previous analysis. A syntax table is generated in this phase. Next, comes the **code generation** phase, where the compiler, based on the AST and symbol table previously created, generates the code for _jasmin_ to interpret. This code will be stored in a .j file. The compiler also has implemented a series of optimizations that improve the quality of the code generated and the program that originates from it, as well as the overall performance of the compiler.


** EXECUTE: It can be run using a command with the following notation:

java –jar comp2020-5g.jar [-r=<n>] [-o] [-x] [--ast/-t] [-d <dirName>] [-s] <input_file.jmm>

The possible flags that can be used are the following:

* **-r=<n>** -> Activate the -r optimization, relative to the **liveness analysis and register allocation for local variables**. <n> must be a positive integer, equal or higher than 1 (representing the maximum number of registers that each function can use for local variables), or it can be the string 'min'. In this last case, the compiler will try to use the minimum number of registers for each method.

* **-o** -> Activates the -o optimizations, relative to **constant propagation and templates for while loops**. This optimization performs constant propagation, constant folding (on both variables and arrays) and dead code removal. If both set, it's performed before the -r flag

* **-x** -> To deactivate the generation of code, that is, the .j file.

* **--ast or -t** -> To print the abstract syntax tree of the program.

* **-d <dirName>** -> Specify the destination directory of the .j file that the program will generate.

* **-s** -> To print the symbol table of the program.


**DEALING WITH SYNTACTIC ERRORS: If a syntactic error occurs outside a while condition, the program exits immediately, throwing an exception and describing the error that caused that. If a syntactic error occurs in a while the condition, the error is detected, but the program does not halt its execution, only doing that if the number of errors in while conditions surpasses the maximum number of errors allowed by the program. If the number of errors is lower than that limit, the program only stops its execution after reaching the end of the code in the .jmm file. In the parser, a static constant, named _SYNTACTIC_MAX_NUM_ERRORS_, keeps that maximum number of errors allowed. For each syntactic error detected, the compiler presents to the user the line and column where the error occurred, with a pointer to the exact spot in the code. It also presents the reason for the error.


**SEMANTIC ANALYSIS: The semantic errors that the compiler can detected are the following:
NOTE: The errors are divided into warnings, minor errors that do not stop the execution of the program, and actual errors that do not permit the execution of the rest of the code.
NOTE: If a semantic error is detected, the program will not immediately halt its execution. Instead of that, it will continue analysing the code in order to detected more errors, and will only halt execution and show the errors when it reaches the end of the program, or when the number of semantic errors detected exceed a maximum limit (kept in the parser, in a static constant named _SEMANTIC_MAX_NUM_ERRORS_).

* **Type mismatch** (error) -> When a variable of a certain type is given a value which type does not match the variable's;
* **Operator type checking** (error) -> Verifies the type of the variables that are part of an aritmetic operation (like an addition);
* **Unknown variable checking** (error) -> All variables that are used in the program must be declared beforehand;
* **Condition type checking** (error) -> A condition must have a boolean value (true or false);
* **Extended class verification** (error) -> If a class extends another, it is verified if the extended class exists or not;
* **Use of uninitialized variables** (error/warning) -> It is always checked if a variable that is used has been initialized beforehand. This verification is done even through if/else blocks, and therefore it can be an error or warning (error if the compiler is sure that the variables is uninitialized when it was used, or warning if there is a if/else block and in one of the execution branches the variables is used and has not been initialized, and if there is the possibility of the program entering that execution branch);
* **Return type checking** (error) -> The return of a method always has to be the same return type declared in the method;
* **Incorrect use of the length property** (error) -> It is checked if a variable using the length property does in fact have it. In other words, the variable needs to be an array;
* **Method calling on non-objects** (error) -> If a method is called on an integer or boolean, the compiler will throw an error;
* **Use of unknown class/method** (error) -> If an unknown class or method is referenced in the program, the compiler will throw an error;
* **Unknown overload** (error) -> The method and class exist, but the arguments passed to the method call do not match any of the overloads of the function;
* **Static method calling** (error) -> If a method is called without an object instance of the class, and the method is not static, the compiler will throw an error;
* **Check if array length is int in array initialization** (error) -> When initializing an array with its size, the value of the size must be an integer value;
* **Check object constructor** (error) -> If a new object is created with the _new_ keyword, an existing constructor for that class needs to be used;
* **Use of this in static method** (error) -> If the _this_ keyword is used in a static method, the compiler will throw an error;
* **Duplicate attributes and methods** (error) -> The compiler will generate an error if an attribute or method (with the same parameters) is declared twice;
* **Duplicate import** (error) -> The compiler will generate an error if a duplicate import is present in the code. An error is also thrown if there is imports for the same method, but one of them is static and the other one not, or if they have different return types.


**INTERMEDIATE REPRESENTATIONS (IRs): The representations used in a normal execution of the compiler are the abstract syntax tree (generated in the syntactic analysis) and the symbol table (generated in the semantic analysis). The -r optimization can be considered to use an intermediate representation: in order to calculate the liveness analysis of the local variables in each of the methods, the compiler generates a CFG containing as nodes all the instructions of the method, in order to calculate the variables that are "live" in each of the nodes of the CFG, thus finding out which variables exist at the same time, and as such cannot use the same register. This information is later used in the register allocation part of the optimization.

**CODE GENERATION: Our approach for the code generation was based on the use of templates. We iterated through the tree that was generated in the syntactic analysis and generated the code accordingly, also using information that was contained in the Symbol Tables as a result of the semantic analysis. We created a class called JVMHelper that helped to create the needed JVM instructions extracting the needed information from method and/or variable descriptors. This step also includes the calculation of the locals and stack limit. The locals limit, without any optimizations, is done during the semantic analysis but can be improved using the -r option of the compiler that used a liveness analysis technique and graph coloring algorithm to determine the minimum number of JVM variables that must be used. The stack limit is calculated during code generation through the use of the JVM instruction class. We also insert pop instructions during the generation when needed to avoid the accumulation of stack values during the runtime that would increase the stack limit unnecessarily.

**OVERVIEW: As mentioned previously, the compiler's execution is divided into different phases. First, the lexical and syntactic analysis are done using a JJT parser, where the grammar of the Java-- language is defined, using tokens and instructions in a LL1 context free grammar. An abstract syntax tree is generated by the parser in this stage of the process. The semantic analysis is done after, generating a symbol table using the AST of the program. The AST is iterated using a depth first search, in order to calculate the information that is going to be stored in the symbol table. The information stored and the error checking depends on the type of node of the AST that is being analyzed. After this, comes the code generation part, in which the AST is iterated again, and code is generated using code templates, this time with the help of the information stored in the symbol table. As it was said previously, if any errors occur in any part of the program, they are correctly identified and reported to the user. Regarding the optimizations, the -r optimization uses the liveness analysis algorithm and the graph coloring algorithm for register allocation. The compiler does not use any third-party tools and packages, except for Jasmin. 
The -o optimization does constant propagation in variables but also in arrays (including its length). We also have done constant folding to improve on previous results, which allowed us to not only remove impossible loops and remove "goto" instructions with do-while loop templates, but also deterministic if-else branches. We gave special emphasis on the while loops and simplification of its condition. This optimization also contains the dead code removal like unused assignments due to the previous optimizations (this optimization is not present in the individual array elements, unlike the other). Finally, all of this helped us use all the default optimization more efficiently using more shifts (right and left).

**TASK DISTRIBUTION: The development of the project was done in a collaborative manner using platforms such as Discord and VSCode live share. Although the code was developed by individually or in a pair-programming environment, the discussion about the used algorithms, data-structures and architecture was made by all elements of the group.


**DEFAULT OPTIMIZATIONS:
* Usage of most efficient instructions such as iinc, bipush, sipush..; 
* Multiplications and divisions of powers of 2 are done with bitwise shifts;
* Usage of "comparison with zero" instructions like "if<cond>" instead of "if_icmp<cond>";
* Remove of duplicate "if" instructions in conditions using the less operation.

**PROS: 
* Comprehensive errors both for syntax and semantic.
* Optimized instruction usage.
* Use of LL1 grammar.
* Implementation of several optimizations that improve the performance of the compiler and the quality of the generated code.
* Robustness of the compiler.
* Constructors with arguments (and its import)

**CONS:
* Some decisions implemented by the compiler are based on the simplicity of the Java-- language, therefore the transition of this compiler to a more complex language could be more difficult. We could also apply the -o dead code optimization to the individual items of an array like we do with the constant propagation (this is not really a cons but more of an improvement that we could make).