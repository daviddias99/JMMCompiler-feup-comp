import java.util.HashMap;
import java.util.Set;
import java.util.List;

import exceptions.SemanticParseException;
import exceptions.UnknownClassException;
import exceptions.UnknownMethodException;

/**
 * Class that will represent a symbol table
 */
public class GeneralDescriptor extends Descriptor {

    private HashMap<String, ClassDescriptor> classST;
    private ClassDescriptor mainClass;

    public GeneralDescriptor() {
        this.classST = new HashMap<>();
    }

    public void buildTable(SimpleNode root) throws SemanticParseException {
        if (root == null || root.jjtGetParent() != null)
            throw new IllegalArgumentException("Root was null or had a parent");

        for (int i = 0; i < root.jjtGetNumChildren(); i++) {

            // Parse imports
            if (root.jjtGetChild(i) instanceof ASTImport) {
                this.addImport((ASTImport) root.jjtGetChild(i));

            // Parse classes
            } else if (root.jjtGetChild(i) instanceof ASTClass) {
                ClassDescriptor newClass = new ClassDescriptor((ASTClass) root.jjtGetChild(i),this);

                // Main class
                this.mainClass = newClass;
                if (!this.addClass(newClass)) {
                    SemanticParseException ex =  new SemanticParseException("Duplicate class");
                    Main.parser.handleSemanticError(ex, root.jjtGetChild(i));
                }
            } else
                System.out.println("Error: " + root.jjtGetChild(i).toString());
        }

    }

    public void addImport(ASTImport importNode) {
        ClassDescriptor importedClass = this.classST.get(importNode.getClassName());

        // If class was not imported already
        if (importedClass == null) {
            importedClass = new ClassDescriptor(importNode.getClassName(), this);
            this.classST.put(importNode.getClassName(), importedClass);
        }

        MethodDescriptor importedMethod = new MethodDescriptor(importNode, importedClass);
        MethodDescriptor duplicateMethod = importedClass.addFunction(importedMethod);

        // Check for all types of duplicate imports
        if(duplicateMethod != null){
            if(importedMethod.getReturn().getType().equals(duplicateMethod.getReturn().getType())){
                if(importedMethod.isStatic() != duplicateMethod.isStatic()){
                    SemanticParseException ex =  new SemanticParseException("Duplicate import with different static status");
                    Main.parser.handleSemanticError(ex, importNode);
                }
                else{
                    SemanticParseException ex =  new SemanticParseException("Duplicate import", false);
                    Main.parser.handleSemanticError(ex, importNode);
                }
            }
            else{
                SemanticParseException ex =  new SemanticParseException("Duplicate import with different return types");
                Main.parser.handleSemanticError(ex, importNode);
            }
        }
    }

    public HashMap<String, MethodDescriptor> getMethodOverloads(String className, String methodName)
            throws UnknownClassException {

        // Search for method overloads in all loaded classes (imports)
        ClassDescriptor searchedClass = this.classST.get(className);
        if (searchedClass == null)
            throw new UnknownClassException("No class called " + className);

        return searchedClass.getMethodOverloads(methodName);
    }

    public HashMap<String, MethodDescriptor> getStaticMethodOverloads(String className, String methodName)
            throws UnknownClassException, UnknownMethodException {

        // Search for method overloads in all loaded classes (imports)
        ClassDescriptor searchedClass = this.classST.get(className);
        if (searchedClass == null)
            throw new UnknownClassException("No class called " + className);


        return searchedClass.getStaticMethodOverloads(methodName);
    }

    public Set<String> getClassConstructors(String className) throws UnknownClassException {
        ClassDescriptor searchedClass = this.classST.get(className);
        if (searchedClass == null)
            throw new UnknownClassException("No class called " + className);

        return searchedClass.getConstructors();
    }

    public boolean addClass(ClassDescriptor classST) {
        return this.classST.putIfAbsent(classST.getIdentifier(), classST) == null ? true : false;
    }

    public ClassDescriptor getClass(String classID) {
        return this.classST.get(classID);
    }

    public void setMainClass(ClassDescriptor mainClass){

        this.mainClass = mainClass;
    }

    public ClassDescriptor getMainClassName(){

        return this.mainClass;
    }

    public boolean classExists(String className) {
        return ! (this.getClass(className) == null);
    }

    @Override
    public String toString() {

        String result = "\n==== Program symbol tables ====\n\n";
        result += "Main class: " + this.mainClass.getIdentifier() + "\n\n";

        for (ClassDescriptor value : this.classST.values()) {

            result += value.toString() + "\n";
        }

        return result;
    }

    @Override
    public GeneralDescriptor getGeneralDescriptor() {
        return this;
    }

    public MethodDescriptor getMethod(String name, List<ASTType> params) {

        return mainClass.getMethod(name, params);
    }
}