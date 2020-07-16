import java.util.LinkedHashMap;
import java.util.Map;
import java.util.List;
import java.util.BitSet;
import java.util.ArrayList;
import java.util.Arrays;

import enums.Visibility;
import exceptions.SemanticParseException;

public class MethodDescriptor extends Descriptor {

    private ClassDescriptor parentClass;
    private LinkedHashMap<String, VariableDescriptor> localVariables;
    private LinkedHashMap<String, VariableDescriptor> arguments;
    private VariableDescriptor returnSymbol;
    private String identifier;
    private String visibility;
    private boolean isStatic;
    private int sizeOverride = -1;

    // for register allocation optmimization
    private List<BitSet> def;
    private List<BitSet> use;
    private List<BitSet> succ;
    private List<BitSet> in;
    private List<BitSet> out;


    public MethodDescriptor(String identifier, boolean isStatic, ClassDescriptor parentClass) {

        this.visibility = "public";
        this.isStatic = isStatic;
        this.parentClass = parentClass;
        this.identifier = identifier;
        this.localVariables = new LinkedHashMap<>();
        this.arguments = new LinkedHashMap<>();
    }

    public MethodDescriptor(ASTMethod node, ClassDescriptor parentClass) throws SemanticParseException {
        this((String) node.jjtGetValue(), node.isStatic(), parentClass);
        this.buildTable(node);
    }

    // Imported method
    public MethodDescriptor(ASTImport node, ClassDescriptor parentClass) {

        // Check if method is importted constructor conscructor
        this(node.getMethodName() == null ? "<init>" : node.getMethodName(), node.isStatic(), parentClass);

        if (node.jjtGetNumChildren() == 0)
            return;

        // Get return type
        String returnType = node.jjtGetChild(node.jjtGetNumChildren() - 1).jjtGetNumChildren() == 0 ? "void"
                : (String) node.jjtGetChild(node.jjtGetNumChildren() - 1).jjtGetChild(0).value;
        this.returnSymbol = new VariableDescriptor(returnType, Visibility.RETURN);

        // Get parameters
        SimpleNode paramList = node.jjtGetChild(0);

        for (int i = 0; i < paramList.jjtGetNumChildren(); i++) {
            String type = (String) paramList.jjtGetChild(i).value;
            this.arguments.put(Integer.toString(i), new VariableDescriptor(type, Visibility.ARGUMENT));
        }
    }

    public void buildTable(SimpleNode sNode) throws SemanticParseException {
        ASTMethod node = (ASTMethod) sNode;

        // Main method
        if ("main".equals((String) node.jjtGetValue())) {
            if (node.jjtGetChild(0) instanceof ASTParamList)
                this.parseParamList((ASTParamList) node.jjtGetChild(0));
            if (node.jjtGetChild(1) instanceof ASTBody)
                this.parseBody((ASTBody) node.jjtGetChild(1));

            returnSymbol = new VariableDescriptor("void", Visibility.RETURN);

            return;
        }

        // Invalid number of children
        if (node.jjtGetNumChildren() != 3 && node.jjtGetNumChildren() != 4) {

            SemanticParseException ex = new SemanticParseException(
                    "Function " + node.toString() + " must have [return type] ParamList, Body and Return");
            Main.parser.handleSemanticError(ex, node);

            return;
        }

        int i = 0;

        // Parse return type
        if (node.jjtGetNumChildren() == 3)
            returnSymbol = new VariableDescriptor("void", Visibility.RETURN);
        else {
            ASTType type = (ASTType) node.jjtGetChild(i);
            returnSymbol = new VariableDescriptor((String) type.jjtGetValue(), Visibility.RETURN);
            i++;
        }

        // Barse method body
        for (; i < node.jjtGetNumChildren(); i++) {
            if (node.jjtGetChild(i) instanceof ASTParamList)
                this.parseParamList((ASTParamList) node.jjtGetChild(i));
            else if (node.jjtGetChild(i) instanceof ASTBody)
                this.parseBody((ASTBody) node.jjtGetChild(i));
            else if (node.jjtGetChild(i) instanceof ASTReturn) {

                // Invalid return
                if (node.jjtGetChild(i).jjtGetNumChildren() == 0 && !returnSymbol.getType().equals("void")) {

                    SemanticParseException ex = new SemanticParseException(
                            "Return type was not void and there is no Return");
                    Main.parser.handleSemanticError(ex, node);
                    continue;
                }
                if (returnSymbol.getType().equals("void") && node.jjtGetChild(i).jjtGetNumChildren() != 0) {

                    SemanticParseException ex = new SemanticParseException("Return type was void and "
                            + node.jjtGetChild(i).jjtGetChild(0).toString() + " was returned");
                    Main.parser.handleSemanticError(ex, node);
                    continue;
                }

                if (node.jjtGetChild(i).jjtGetNumChildren() == 0 && returnSymbol.getType().equals("void"))
                    returnSymbol.setIdentifier("");
                else
                    returnSymbol.setIdentifier((SimpleNode) node.jjtGetChild(i).jjtGetChild(0));
            }
        }

    }

    public String getVisibility() {
        return visibility;
    }

    public void setVisibility(String visibility) {
        this.visibility = visibility;
    }

    public boolean isStatic() {
        return isStatic;
    }

    public ClassDescriptor getparentClass() {
        return this.parentClass;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public VariableDescriptor searchVariable(String variableName) {
        VariableDescriptor result = this.getVariable(variableName);
        return result == null ? parentClass.searchAttribute(variableName) : result;
    }

    public ClassDescriptor getParentClass() {

        return this.parentClass;
    }

    public GeneralDescriptor getGeneralDescriptor() {
        return parentClass.getGeneralDescriptor();
    }

    private void parseParamList(ASTParamList node) throws SemanticParseException {
        for (int i = 0; i < node.jjtGetNumChildren(); i++) {

            // Create new argument
            VariableDescriptor newArgument = new VariableDescriptor((ASTVarDeclaration) node.jjtGetChild(i),
                    Visibility.ARGUMENT);

            // Check for duplicates
            if (!this.addArgument(newArgument)) {

                SemanticParseException ex = new SemanticParseException("Duplicate method parameter");
                Main.parser.handleSemanticError(ex, node);
                newArgument.setIdentifier(newArgument.getIdentifier() + "#dup" + i);
                this.addArgument(newArgument);
                continue;
            }

            // Set stack position for code generation
            newArgument.setLocalStackPosition(this.arguments.size() + (this.isStatic ? -1 : 0));
        }
    }

    private void parseBody(ASTBody node) throws SemanticParseException {

        int i = 0;
        while (i < node.jjtGetNumChildren() && node.jjtGetChild(i) instanceof ASTVarDeclaration) {

            // Create local variables
            VariableDescriptor newLocalVariable = new VariableDescriptor((ASTVarDeclaration) node.jjtGetChild(i),
                    Visibility.LOCAL);

            // Check for duplicate variable
            if (!this.addLocalVariable(newLocalVariable)) {

                SemanticParseException ex = new SemanticParseException(
                        "Duplicate local variable in method '" + this.identifier + "'");
                Main.parser.handleSemanticError(ex, node);
                i++;
                continue;
            }
            newLocalVariable.setLocalStackPosition(
                    this.localVariables.size() + this.arguments.size() + (this.isStatic ? -1 : 0));
            i++;
        }
    }

    public boolean addLocalVariable(VariableDescriptor symbol) {
        if (this.arguments.containsKey(symbol.getIdentifier())
                || this.localVariables.containsKey(symbol.getIdentifier()))
            return false;

        this.localVariables.put(symbol.getIdentifier(), symbol);
        return true;
    }

    public VariableDescriptor getVariable(String variableID) {
        VariableDescriptor ret = this.arguments.get(variableID);
        if (ret != null)
            return ret;
        return this.localVariables.get(variableID);
    }

    public VariableDescriptor getLocalVariable(String variableID) {
        return this.localVariables.get(variableID);
    }

    public int getLocalsCount() {

        return sizeOverride == -1 ? this.localVariables.size() + this.arguments.size() + (this.isStatic ? 0 : 1) : sizeOverride;
    }

    public int getLocalsStartIndex(){
        return this.arguments.size() + (this.isStatic ? 0 : 1) ;
    }

    // Convert parameter list to a string. Example: (int,int,boolean)
    public String getParameterList() {

        StringBuilder parameterList = new StringBuilder("(");
        boolean first = true;
        for (Map.Entry<String, VariableDescriptor> entry : this.arguments.entrySet()) {
            if (!first) {
                parameterList.append(",");
            }
            parameterList.append(entry.getValue().getType());
            first = false;
        }

        parameterList.append(")");
        return parameterList.toString();
    }

    public String getParameterListStr() {

        StringBuilder parameterList = new StringBuilder("(");
        boolean first = true;
        for (Map.Entry<String, VariableDescriptor> entry : this.arguments.entrySet()) {
            if (!first) {
                parameterList.append(",");
            }
            parameterList.append(entry.getValue().getType() + " " + entry.getValue().getIdentifier());
            first = false;
        }

        parameterList.append(")");
        return parameterList.toString();
    }

    // Convert a method do it's JVM signature
    public String getJVMSignature() {

        StringBuilder signature = new StringBuilder("(");
        for (Map.Entry<String, VariableDescriptor> entry : this.arguments.entrySet()) {

            signature.append(JVMHelper.getTypeDescription(entry.getValue().getType()));
        }

        signature.append(")");
        signature.append(JVMHelper.getTypeDescription(this.returnSymbol.getType()));
        return this.identifier + signature.toString();
    }

    public boolean addArgument(VariableDescriptor symbol) {
        if (this.arguments.containsKey(symbol.getIdentifier()))
            return false;

        // If it is an argument it is initialized
        symbol.setInitialized();
        this.arguments.put(symbol.getIdentifier(), symbol);
        return true;
    }

    public VariableDescriptor getReturn() {
        return returnSymbol;
    }

    public int getArgumentCount(){
        return this.arguments.size();
    }

    public void setReturn(VariableDescriptor returnSymbol) {
        this.returnSymbol = returnSymbol;
    }

    @Override
    public String toString() {

        String result = "\t> " + visibility + " " + (isStatic ? "static" : "") + " "
                + (this.returnSymbol != null ? this.returnSymbol.getType() + " " : "") + identifier
                + this.getParameterListStr() + "\n\n";

        result += "\t\tLocals:\n";

        if (this.localVariables.size() > 0) {
            for (VariableDescriptor value : this.localVariables.values()) {

                result += "\t\t\t. " + value.toString() + "\n";
            }
        } else {
            result += "\t\t\t. No local variables declared\n";
        }

        result += "\n\t\tReturn:\n";
        if (this.returnSymbol != null) {
            result += "\t\t\t. " + this.returnSymbol.toString() + "\n";
        }
        else {
            result += "\t\t\t. No return symbol declared (assumed void) \n";
        }

        return result;
    }


    public void registerAllocationOpt(SimpleNode sNode) {
        this.use = new ArrayList<>();
        this.def = new ArrayList<>();
        this.succ = new ArrayList<>();

        // Generate cfg (fill use, def, succ)
        sNode.generateCFG(use, def, succ, this);

        // Do liveness analysis (fill in and out and create dependency graph)
        DependencyGraph depGraph = calcLivenessAnalysis(use, def, succ, this.getLocalsCount());

        // Do colorization
        int[] registers = depGraph.colorizeGraph(Main.regAloc);

        // Uncomment to see variables for debug
        // this.printVars(registers);

        // Update stack positions of the variables
        for (Map.Entry<String,VariableDescriptor> entry : this.localVariables.entrySet()) {
            entry.getValue().setLocalStackPosition(registers[entry.getValue().getLocalStackPosition()]);
        }
        
        // Check for errors
        if(depGraph.isError()){
            System.out.println("Method " + this.identifier + " requires more than " + Main.regAloc + " registers. Minimum value is " + depGraph.getUsedColors());
            Main.errorOptR = true;
        }

        this.sizeOverride = 1 + Arrays.stream(registers).max().getAsInt();
    }


    private void printVars(int[] registers){

        System.out.println("Node vars for method " + this.identifier + "\n");
        for (int i = 0; i < use.size(); i++) {
			System.out.println("i: " + i);
			System.out.println("Use: " + use.get(i).toString());
            System.out.println("Def: " + def.get(i).toString());
            System.out.println("Succ: " + succ.get(i).toString());
            System.out.println("In: " + in.get(i).toString());
            System.out.println("Out: " + out.get(i).toString());
			System.out.println();
        }
        System.out.println("Colors: " + Arrays.toString(registers));
        System.out.println("---------\n");
    }

    private DependencyGraph calcLivenessAnalysis(List<BitSet> use, List<BitSet> def, List<BitSet> succ, int localsCount) {
        List<BitSet> inLast = new ArrayList<>();
        List<BitSet> outLast = new ArrayList<>();
        List<BitSet> in = new ArrayList<>();
        List<BitSet> out = new ArrayList<>();

        // Create empty bitsets for in and out
        for (int i = 0; i < use.size(); i++) {
            inLast.add(new BitSet());
            outLast.add(new BitSet());
            in.add(new BitSet());
            out.add(new BitSet());
        }

        // While changes occur, iterate through the nodes
        do {
            cloneLastIteration(inLast, outLast, in, out);

            // Calculate in and out values
            for (int i = use.size() - 1; i >= 0; i--) {
                BitSet nodeSucc = succ.get(i);
                BitSet nodeDef = def.get(i);
                BitSet nodeUse = use.get(i);

                BitSet inSuccUnion = new BitSet();
                for (int j = nodeSucc.nextSetBit(0); j >= 0; j = nodeSucc.nextSetBit(j+1)) {
                    inSuccUnion.or(in.get(j));
                }
                out.set(i, inSuccUnion);

                BitSet inResultNode = new BitSet();
                inResultNode.or(out.get(i));
                inResultNode.andNot(nodeDef);
                inResultNode.or(nodeUse);
                in.set(i, inResultNode);
            }
        } while (livenessChanges(inLast, outLast, in, out));

        this.in = in;
        this.out = out;

        return new DependencyGraph(def,in, out, localsCount, this.getLocalsStartIndex());
    }

    private void cloneLastIteration(List<BitSet> inLast, List<BitSet> outLast, List<BitSet> in, List<BitSet> out) {
        for (int i = 0; i < in.size(); i++) {
            inLast.set(i, (BitSet) in.get(i).clone());
            outLast.set(i, (BitSet) out.get(i).clone());
        }
    }

    private boolean livenessChanges(List<BitSet> inLast, List<BitSet> outLast, List<BitSet> in, List<BitSet> out) {

        // Check if any bit from in or out changed in the past iteration
        for (int i = 0; i < in.size(); i++) {
            BitSet testDifferences = new BitSet();
            testDifferences.or(inLast.get(i));
            testDifferences.xor(in.get(i));
            if (testDifferences.cardinality() > 0)
                return true;

            testDifferences.clear();
            testDifferences.or(outLast.get(i));
            testDifferences.xor(out.get(i));
            if (testDifferences.cardinality() > 0)
                return true;
        }

        return false;
    }
}