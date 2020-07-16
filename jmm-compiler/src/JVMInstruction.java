public class JVMInstruction {

    int stackDiff;
    String instructionRep;

    public JVMInstruction(String instructionRep, int stackDiff){

        this.stackDiff = stackDiff;             // stack difference caused by the instruction
        this.instructionRep = instructionRep;   // instruction representation
    }

    public String getInstruction(){

        return instructionRep;
    }


    public void setInstruction(String newInstruction){

        this.instructionRep = newInstruction;

    }

    public int getStackDiff(){

        return stackDiff;
    }
    
}