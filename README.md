# COMP Class Assignments and Projects

**2019/2020** - 3rd Year, 2nd Semester

**Course:** Compiladores ([COMP](https://sigarra.up.pt/feup/pt/ucurr_geral.ficha_uc_view?pv_ocorrencia_id=436448)) | Compilers

**Projects developed by:** David Silva ([daviddias99](https://github.com/daviddias99)), Eduardo Ribeiro ([EduRibeiro00](https://github.com/EduRibeiro00)), Luís Cunha ([luispcunha](https://github.com/luispcunha)) and Manuel Coutinho ([ManelCoutinho](https://github.com/ManelCoutinho))

---

**Project: JMM Compiler**

* The tool built serves as a compiler of .jmm files, written in the Java-- language, a subset of the Java language. All programs that are valid in the Java-- language are also valid in the Java language. 
* The compiler generates files of the classes with JVM instructions accepted by jasmin, a tool that translates those classes in Java bytecodes (classfiles). The generated classes given Java-- code can be integrated in a Java application. Those classes can invoke Java methods previously compiled to bytecodes.
* The process of generating the instructions and the .j file from the .jmm file is divided in several phases: the compiler first performs a **lexical and syntactic analysis** to the code, matching tokens and expressions to check the validity of the code. This, among other things, generates the abstract syntax tree (AST) of the program. Next, a **semantic analysis** of the program is performed, catching some types of errors that could not be detected in the previous analysis. A **syntax table** is generated in this phase. Next, comes the **code generation** phase, where the compiler, based on the AST and symbol table previously created, generates the code for jasmin to interpret. This code will be stored in a .j file. The compiler also has implemented a series of **optimizations** that improve the quality of the code generated and the program that originates from it, as well as the overall performance of the compiler.
* Languages/technologies used: **Java, JavaCC, jasmin.**

**Grade**: 19.64 / 20

---

**Disclaimer** - This repository was used for educational purposes and I do not take any responsibility for anything related to its content. You are free to use any code or algorithm you find, but do so at your own risk.
