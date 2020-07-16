import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import enums.Visibility;
import exceptions.SemanticParseException;
import exceptions.UnknownClassException;
import exceptions.UnknownMethodException;

public class ClassDescriptor extends Descriptor {
    private GeneralDescriptor parent;
    private String identifier;
    private String extendedClassName;
    private HashMap<String, HashMap<String, MethodDescriptor>> methodST;
    private HashMap<String, VariableDescriptor> attributeST;

    public ClassDescriptor(String name, GeneralDescriptor parent) throws IllegalArgumentException {
        this.identifier = name;
        this.parent = parent;
        this.methodST = new HashMap<>();
        this.attributeST = new HashMap<>();
        this.extendedClassName = null;
    }

    public ClassDescriptor(ASTClass node, GeneralDescriptor parent) throws SemanticParseException {

        this((String) node.jjtGetValue(),parent);
        this.buildTable(node);
    }

    protected void buildTable(SimpleNode sNode) throws  SemanticParseException{
        ASTClass node = (ASTClass) sNode;

        for (int i = 0; i < node.jjtGetNumChildren(); i++) {
            if (node.jjtGetChild(i) instanceof ASTVarDeclaration) {
                /* Parse the class attributes. Set the parent descriptor and set them as initialized to avoid conflicts. */
                VariableDescriptor se = new VariableDescriptor((ASTVarDeclaration) node.jjtGetChild(i), Visibility.PUBLIC);
                se.setInitialized();
                se.setParentClass(this);

                // Add the attribute to the decriptor if it doesn't exist already
                if (!this.addAttribute(se)){
                    SemanticParseException ex = new SemanticParseException("Duplicate class attribute");
                    Main.parser.handleSemanticError(ex,node.jjtGetChild(i));
                    continue;
                }
            } else if (node.jjtGetChild(i) instanceof ASTMethod) {

                /* Parse the classe methods */
                MethodDescriptor functionST = new MethodDescriptor((ASTMethod) node.jjtGetChild(i), (ClassDescriptor) this);
                if (this.addFunction(functionST) != null) {

                    SemanticParseException ex = new SemanticParseException("Duplicate class method");
                    Main.parser.handleSemanticError(ex,node.jjtGetChild(i));
                    continue;
                }
            }
            /* Extended class */
            else if (node.jjtGetChild(i) instanceof ASTExtends)
                this.extendedClassName = (String) node.jjtGetChild(i).value;

            /* Node not identified: error */
            else
                System.out.println("Node not identified: " + node.jjtGetChild(i).toString());
        }
    }

    public GeneralDescriptor getGeneralDescriptor() {
        return this.parent;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public MethodDescriptor addFunction(MethodDescriptor function) {

        HashMap<String,MethodDescriptor> functionOverloads = this.methodST.getOrDefault(function.getIdentifier(), new HashMap<>());

        // Function doesn't exist already
        if (functionOverloads.isEmpty()) {
            this.methodST.put(function.getIdentifier(), functionOverloads);
        }

        // Main method special case
        if (function.getIdentifier().equals("main")) {
            return functionOverloads.putIfAbsent("main", function);
        }
        // Method already exists. Check if function is a new overload of said method
        else {
            return functionOverloads.putIfAbsent(function.getParameterList(), function);
        }
    }

    public boolean addAttribute(VariableDescriptor attribute){
        return this.attributeST.putIfAbsent(attribute.getIdentifier(), attribute) == null ? true : false;
    }

    public HashMap<String, MethodDescriptor> getAvailableMethodOverloads(String functionID){
        return this.methodST.get(functionID);
    }

    public MethodDescriptor getMethod(String functionID, List<ASTType> parameterTypes) {
        HashMap<String,MethodDescriptor> functionOverloads = this.methodST.get(functionID);

        if (functionOverloads == null)
            return null;

        String params;
        if (functionID.equals("main")) {
            params = "main";
        } else {
            params = this.getParameterListString(parameterTypes);
        }

        return functionOverloads.get(params);
    }

    /* Turn a list of ASTType nodes into a string i.e. (int,int,string)*/
    private String getParameterListString(List<ASTType> parameterTypes) {
        StringBuilder parameterList = new StringBuilder("(");
        boolean first = true;
        for (ASTType typeToken : parameterTypes) {
            if(!first) {
                parameterList.append(",");
            }
            parameterList.append((String)typeToken.jjtGetValue());
            first = false;
        }
        parameterList.append(")");
        return parameterList.toString();
    }

    public MethodDescriptor getMainFunction(){
        HashMap<String,MethodDescriptor> functionOverloads = this.methodST.get("main");
        return functionOverloads.get("main");
    }

    public VariableDescriptor getAttribute(String parameterID){
        return this.attributeST.get(parameterID);
    }

    public VariableDescriptor searchAttribute(String attributeName) {
        return this.getAttribute(attributeName);
    }


    public HashMap<String, MethodDescriptor> getMethodOverloads(String methodName) throws UnknownClassException {
        HashMap<String, MethodDescriptor> overloads = this.methodST.get(methodName);

        // If the class is not a subclass
        if (this.extendedClassName == null)
            return overloads == null ? new HashMap<String, MethodDescriptor> () : overloads;

        // No overloads exist
        if (overloads == null)
            overloads = new HashMap<>();

        GeneralDescriptor genDesc = this.parent.getGeneralDescriptor();

        // Check the parent class for overloads
        HashMap<String, MethodDescriptor> extendedOverloads = genDesc.getMethodOverloads(extendedClassName, methodName);
        for (String paramList : extendedOverloads.keySet()) {
            overloads.putIfAbsent(paramList, extendedOverloads.get(paramList));
        }

        return overloads;
    }

    public HashMap<String, MethodDescriptor> getStaticMethodOverloads(String methodName) throws UnknownMethodException, UnknownClassException {
        HashMap<String, MethodDescriptor> overloads = this.methodST.get(methodName);
        HashMap<String, MethodDescriptor> result = new HashMap<>();

        if (overloads == null) {
            throw new UnknownMethodException("No method called " + methodName + " in class " + this.getIdentifier());
        }

        // Search static overlods in current class
        for (String function : overloads.keySet()) {
            if (overloads.get(function).isStatic()) {
                result.put(function, overloads.get(function));
            }
        }

        // Search the parent class for overloads
        if (this.extendedClassName != null) {
            GeneralDescriptor genDesc = this.parent.getGeneralDescriptor();
            HashMap<String, MethodDescriptor> extendedOverloads = genDesc.getStaticMethodOverloads(this.extendedClassName, methodName);
            for (String paramList : extendedOverloads.keySet()) {
                overloads.putIfAbsent(paramList, extendedOverloads.get(paramList));
            }
        }

        return result;
    }

    public Set<String> getConstructors() {
        HashMap<String, MethodDescriptor> constructors = this.methodST.get("<init>");

        // if no constructor was imported, assume that the constructor with no args was implicitly imported
        if (constructors == null) {
            Set<String> constructorSign = new HashSet<>();
            constructorSign.add("()");

            return constructorSign;
        }

        return constructors.keySet();
    }

    public String getExtendedClassName(){
        return this.extendedClassName;
    }

    @Override
    public String toString(){

        String result = "--- Class " + this.identifier + "---\n\n";

        result += "Extends: " + (this.extendedClassName == null ? "Object" : this.extendedClassName) + "\n";
        result += "Attributes: \n";

        if(this.attributeST.size() > 0){

            for (VariableDescriptor value : this.attributeST.values()) {

                result += "\t. " + value.toString() + "\n";
            }
        }
        else{
            result += "\t. No class attributes declared \n";
        }


        result += "\nMethods: \n\n";

        for (HashMap<String,MethodDescriptor> value : this.methodST.values()) {
            for(MethodDescriptor value2 : value.values()){

                result += value2.toString() + "\n";
            }
        }

        return result;
    }
}