import exceptions.SemanticParseException;
import java.util.HashSet; 

/* Generated By:JJTree: Do not edit this line. ASTMul.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTMul extends SimpleNode {
	public ASTMul(int id) {
		super(id);
		this.returnType = "int";
	}

	public ASTMul(Parser p, int id) {
		super(p, id);
		this.returnType = "int";
	}

	@Override
	public String analyze(Descriptor descriptor, HashSet<String> variables) throws SemanticParseException{
		MethodDescriptor functionST = (MethodDescriptor) descriptor;

		SimpleNode lhsNode = this.jjtGetChild(0);
		SimpleNode rhsNode = this.jjtGetChild(1);

		String lhsType = lhsNode.analyze(functionST, variables);
		String rhsType = rhsNode.analyze(functionST, variables);

		if (!lhsType.equals(this.returnType) || !rhsType.equals(this.returnType))
			this.parser.handleSemanticError(new SemanticParseException("Wrong types in multiplication: " + lhsNode.jjtGetValue() + "[" + lhsType
			+ "]" + " and " + rhsNode.jjtGetValue() + "[" + rhsType + "]"), this);

		return this.returnType;
	}

	@Override
	public void generateCode(Descriptor descriptor){

		SimpleNode lhsNode = this.jjtGetChild(0);
		SimpleNode rhsNode = this.jjtGetChild(1);

		// X * 2 ^ s: s max = 5 bits 31
		if(rhsNode instanceof ASTNumeric){
			int den = Integer.parseInt((String) rhsNode.jjtGetValue());
            int s = powerOfTwo(den);
            if( s >= 0 && s <= 31){
				lhsNode.generateCode(descriptor);
                parser.addInstruction(JVMHelper.loadNumber(s));
                parser.addInstruction(new JVMInstruction("ishl",-1));
                return;
            }
		}

		// 2 ^ s * X: s max = 5 bits 31
		if(lhsNode instanceof ASTNumeric){
			int den = Integer.parseInt((String) lhsNode.jjtGetValue());
            int s = powerOfTwo(den);
            if( s >= 0 && s <= 31){
				rhsNode.generateCode(descriptor);
                parser.addInstruction(JVMHelper.loadNumber(s));
                parser.addInstruction(new JVMInstruction("ishl",-1));
                return;
            }
		}

		lhsNode.generateCode(descriptor);
		rhsNode.generateCode(descriptor);
		parser.addInstruction(new JVMInstruction("imul",-1));
	}

	// TODO: refactor, é igual a div
	public int powerOfTwo(int n) 
    { 
        if ((int)(Math.ceil((Math.log(n) / Math.log(2))))  == (int)(Math.floor(((Math.log(n) / Math.log(2))))))
            return (int) (Math.log(n) / Math.log(2));
        return -1;
    } 
}
/*
 * JavaCC - OriginalChecksum=e5abe143d74eea8d5312703bd5001c7a (do not edit this
 * line)
 */
