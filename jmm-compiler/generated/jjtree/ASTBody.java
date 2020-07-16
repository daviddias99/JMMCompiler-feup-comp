import exceptions.SemanticParseException;
import java.util.HashSet; 
import java.util.List;
import java.util.BitSet;

/* Generated By:JJTree: Do not edit this line. ASTBody.java Version 6.1 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=true,VISITOR=false,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public class ASTBody extends SimpleNode {
	public ASTBody(int id) {
		super(id);
	}

	public ASTBody(Parser p, int id) {
		super(p, id);
	}

	@Override
	public String analyze(Descriptor descriptor, HashSet<String> variables) throws SemanticParseException{
		MethodDescriptor functionST = (MethodDescriptor) descriptor;
		for(int i = 0; i < this.jjtGetNumChildren(); i++){
			
			try{

				this.jjtGetChild(i).analyze(functionST, variables);
			}
			catch(SemanticParseException ex){

				// TODO: Auto-generated catch block
				// ex.printStackTrace();
			}
		}

		return "";
	}

	public void generateCode(Descriptor descriptor) {

        for (int i = 0; i < this.jjtGetNumChildren(); i++){

			this.jjtGetChild(i).generateCode(descriptor);

			if(! (this.jjtGetChild(i) instanceof ASTVarDeclaration)){

				if(this.jjtGetChild(i).getReturnType() != null && this.jjtGetChild(i).getReturnType() != "void" )
					parser.addInstruction(new JVMInstruction("pop",-1));
			}
		}

        return;
    };

	public void generateCFG(List<BitSet> use, List<BitSet> def, List<BitSet> succ, MethodDescriptor mDescriptor) {
		for (int i = 0; i < this.jjtGetNumChildren(); i++) {			
			this.jjtGetChild(i).generateCFG(use, def, succ, mDescriptor);
		}
	}
}
/*
 * JavaCC - OriginalChecksum=64bd6b901d4f42f5534a82f4a4deb328 (do not edit this
 * line)
 */