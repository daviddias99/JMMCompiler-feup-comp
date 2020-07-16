import enums.Visibility;
import exceptions.SemanticParseException;
import java.util.Set;
import java.util.HashSet;

/**
 * Class that will have the information needed for a symbol table entry
 */
public class VariableDescriptor {

    private String identifier;
    private String type;
    private Visibility visibility;
    private boolean initialized;
    private int localStackPosition;
    private boolean progression;
    private ClassDescriptor parentClass;

    private int currentAssignmentID; // last incremental ID given to an assignment to this variable
    private Set<Integer> assignmentsForNextUsage; // IDs of assignments that need to be in the code if the variable is used at this moment
    private Set<Integer> usedAssignments; // IDs of this assignment that will be in code

    public VariableDescriptor(String type, Visibility visibility){
        this.identifier = "";
        this.type = type;
        this.visibility = visibility;
        this.initialized = false;
        this.progression = false;
        this.currentAssignmentID = 0;
        this.assignmentsForNextUsage = new HashSet<>();
        this.usedAssignments = new HashSet<>();
    }

    public VariableDescriptor(ASTVarDeclaration varDec, Visibility visibility) throws SemanticParseException {

        if (varDec.jjtGetNumChildren() != 2)
            throw new SemanticParseException("Variable Declaration must have a Type and an Identifier");

        ASTType type = varDec.getType();
        ASTIdentifier id = varDec.getIdentifier();

        this.type = (String) type.jjtGetValue();
        this.identifier = (String) id.jjtGetValue();
        this.visibility = visibility;
        this.initialized = false;
        this.progression = false;
        this.currentAssignmentID = 0;
        this.assignmentsForNextUsage = new HashSet<>();
        this.usedAssignments = new HashSet<>();
    }

    public ClassDescriptor getParentClass() {
        return parentClass;
    }

    public void setParentClass(ClassDescriptor parentClass) {
        this.parentClass = parentClass;
    }

    public void setIdentifier(SimpleNode idNode) {
        this.identifier = (String) idNode.jjtGetValue();
    }

    public Visibility getVisibility() {
        return visibility;
    }

    public void setVisibility(Visibility visibility) {
        this.visibility = visibility;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public void setIdentifier(String id) {
        this.identifier = id;
    }

    public String getType() {
        return this.type;
    }

    public boolean isInitialized() {
        return initialized;
    }

    public void setInitialized() {
        this.initialized = true;
    }

    public void unsetInitialized() {
        this.initialized = false;
    }

    public void setProgression() {
        this.progression = true;
    }

    public boolean hadProgression(){
        return this.progression;
    }

    /* dead code elimination related */

    /**
     * Method called when an assignment is made to this variable.
     * @return id of the assignment
     */
    public int newAssignment() {
        this.currentAssignmentID++;
        this.assignmentsForNextUsage.clear();
        this.assignmentsForNextUsage.add(this.currentAssignmentID);

        return this.currentAssignmentID;
    }

    /**
     * Get ID of last assignment to this variable
     */
    public int getCurrentAssignmentID() {
        return this.currentAssignmentID;
    }

    /**
     * Get assignments needed for the next usage of this variable
     */
    public Set<Integer> getAssignmentsForNextUsage() {
        Set<Integer> result = new HashSet<>();
        result.addAll(this.assignmentsForNextUsage);
        return result;
    }

    /**
     * Check if an assignment needs to be present in the code
     */
    public boolean usedAssignment(int id) {
        return this.usedAssignments.contains(id);
    }

    public void resetAssignmentID() {
        this.currentAssignmentID = 0;
    }

    /**
     * Variable was used. Save variables that will need to be present in the code due to this usage
     */
    public void assignmentUsage() {
        this.usedAssignments.addAll(this.assignmentsForNextUsage);
    }

    /**
     * Add assignments that will need to be present in code if the variable is used
     */
    public void addAssignmentsForNextUsage(Set<Integer> assignmentIDs) {
        this.assignmentsForNextUsage.addAll(assignmentIDs);
    }

    /**
     * Clear assignments needed if variable is used next
     */
    public void resetAssignmentsForNextUsage() {
        this.assignmentsForNextUsage.clear();
    }

    // Code generation specific

    public int getLocalStackPosition() {
        return localStackPosition;
    }

    public void setLocalStackPosition(int localStackPosition) {
        this.localStackPosition = localStackPosition;
    }

    @Override
    public String toString(){

        if(this.visibility == Visibility.PUBLIC )
            return type + " " + identifier + " (visibility: " + visibility + ")";
        else if (this.visibility == Visibility.RETURN)
            return type + " " + " (visibility: " + visibility + ")";
        else
            return type + " " + identifier + " (localStackPosition: " + localStackPosition +")";

    }

}