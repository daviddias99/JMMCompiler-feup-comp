import java.util.ArrayList;
import java.util.List;

import enums.Visibility;

public class JVMHelper {
    private static int lastLabelID = 0;
    private static int lastWhileID = 0;
    private static int lastIfID = 0;

    public static JVMInstruction loadNumber(int value) {
        if (value == -1) {
            return new JVMInstruction("iconst_m1", 1);
        } if (value >= 0 && value < 6)
            return new JVMInstruction("iconst_" + value, 1);
        if (value >= Byte.MIN_VALUE && value <= Byte.MAX_VALUE)
            return new JVMInstruction("bipush " + value, 1);
        if (value >= Short.MIN_VALUE && value <= Short.MAX_VALUE)
            return new JVMInstruction("sipush " + value, 1);
        return new JVMInstruction("ldc " + value, 1);
    }

    public static JVMInstruction loadBoolean(String bool) {
        if (bool.equals("true"))
            return new JVMInstruction( "iconst_1", 1);
        return new JVMInstruction( "iconst_0",1);
    }

    public static String getTypeDescription(String type) {

        if (type.equals("boolean"))
            return "Z";
        else if (type.equals("void"))
            return "V";
        else if (type.equals("int"))
            return "I";
        else if (type.equals("String"))
            return "Ljava/lang/String;";
        else if (type.endsWith("[]")) {
            return "[" + getTypeDescription(type.substring(0, type.length() - 2));
        } else
            return "L" + type + ";";
    }

    public static List<JVMInstruction> getMethodDescription(MethodDescriptor methodDescriptor) {

        ArrayList<JVMInstruction> result = new ArrayList<>();

        result.add(new JVMInstruction(".method " + methodDescriptor.getVisibility() + (methodDescriptor.isStatic() ? " static " : " ") + methodDescriptor.getJVMSignature(), 0));
        result.add(new JVMInstruction(".limit stack #", 0));
        result.add(new JVMInstruction(".limit locals " + methodDescriptor.getLocalsCount(), 0));

        return result;
    }

    public static JVMInstruction getReturnInstruction(String type) {
        if (type.equals("int"))
            return new JVMInstruction("ireturn", -1);
        if (type.equals("boolean"))
            return new JVMInstruction("ireturn", -1);
        if (type.equals("void"))
            return new JVMInstruction("return", 0);
        return new JVMInstruction("areturn", -1);
    }

    public static String getClassDescription(String className) {

        if (className.equals("String")) {
            return "java/lang/String";
        } else
            return className;
    }

    public static JVMInstruction getStoreInstruction(VariableDescriptor descriptor) {

        switch (descriptor.getVisibility()) {
            case LOCAL:
                return getLocalStoreInstruction(descriptor);

            case ARGUMENT:
                return getLocalStoreInstruction(descriptor);

            default:
                return getAttributeStoreInstruction(descriptor);
        }

    }

    public static JVMInstruction getLoadInstruction(VariableDescriptor descriptor) {


        switch (descriptor.getVisibility()) {
            case LOCAL:
                return getLocalLoadInstruction(descriptor);

            case ARGUMENT:
                return getLocalLoadInstruction(descriptor);

            default:
                return getAttributeLoadInstruction(descriptor);
        }

    }

    public static JVMInstruction getArrayStoreInstruction(VariableDescriptor descriptor) {

        if (descriptor.getType().equals("boolean[]"))
            return new JVMInstruction("iastore",-3);
        else if (descriptor.getType().equals("int[]"))
            return new JVMInstruction("iastore",-3);
        else
            return new JVMInstruction("aastore",-3);

    }

    public static JVMInstruction getArrayLoadInstruction(VariableDescriptor descriptor) {

        if (descriptor.getType().equals("boolean[]"))
            return new JVMInstruction("iaload",-1);
        else if (descriptor.getType().equals("int[]"))
            return new JVMInstruction("iaload",-1);
        else
            return new JVMInstruction("aaload",-1);
    }

    public static JVMInstruction getArrayLoadInstruction(String type) {

        if (type.equals("boolean[]"))
            return new JVMInstruction("iaload",-1);
        else if (type.equals("int[]"))
            return new JVMInstruction("iaload",-1);
        else
            return new JVMInstruction("aaload",-1);
    }

    public static JVMInstruction getFieldInstruction(VariableDescriptor descriptor) {

        return new JVMInstruction(".field "
                + (descriptor.getVisibility() == Visibility.LOCAL ? ""
                        : descriptor.getVisibility().toString().toLowerCase())
                + " " + descriptor.getIdentifier() + "_name" + " " + getTypeDescription(descriptor.getType()),0);
    }

    private static JVMInstruction getAttributeLoadInstruction(VariableDescriptor descriptor) {
        String instruction = "";

        instruction += "getfield";
        instruction += " " + getClassDescription(descriptor.getParentClass().getIdentifier()) + "/"
                + descriptor.getIdentifier() + "_name";
        instruction += " " + getTypeDescription(descriptor.getType());

        return new JVMInstruction(instruction,0);
    }

    private static JVMInstruction getLocalLoadInstruction(VariableDescriptor descriptor) {

        String instruction = "";

        if (descriptor.getType().equals("boolean"))
            instruction += "iload";
        else if (descriptor.getType().equals("int"))
            instruction += "iload";
        else
            instruction += "aload";

        instruction += ((descriptor.getLocalStackPosition() < 4) ? "_" : " ") + descriptor.getLocalStackPosition();

        return new JVMInstruction(instruction,1);
    }

    private static JVMInstruction getAttributeStoreInstruction(VariableDescriptor descriptor) {
        String instruction = "";

        instruction += "putfield";
        instruction += " " + getClassDescription(descriptor.getParentClass().getIdentifier()) + "/"
                + descriptor.getIdentifier() + "_name";
        instruction += " " + getTypeDescription(descriptor.getType());

        return new JVMInstruction(instruction, -2);
    }

    private static JVMInstruction getLocalStoreInstruction(VariableDescriptor descriptor) {

        String instruction = "";

        if (descriptor.getType().equals("boolean"))
            instruction += "istore";
        else if (descriptor.getType().equals("int"))
            instruction += "istore";
        else
            instruction += "astore";

        instruction += ((descriptor.getLocalStackPosition() < 4) ? "_" : " ") + descriptor.getLocalStackPosition();

        return new JVMInstruction(instruction, -1);
    }

    public static JVMInstruction getNewArrayInstruction(String type) {

        return new JVMInstruction("newarray " + type,0);
    }

    public static JVMInstruction getNewObjectInstruction(String type) {

        return new JVMInstruction("new " + getClassDescription(type),1);
    }

    public static JVMInstruction getIncInstruction(VariableDescriptor var, int inc){
        return new JVMInstruction("iinc " + var.getLocalStackPosition() + " " + inc, 0);
    }

    public static JVMInstruction getMethodInvocation(MethodDescriptor methodDescriptor, MethodDescriptor calledMethodDescriptor) {
        StringBuilder builder = new StringBuilder();
        int diff = 0;
        String calledMethodClassName = calledMethodDescriptor.getParentClass().getIdentifier();

        if (calledMethodDescriptor.isStatic()){

            builder.append("invokestatic");
            diff = (calledMethodDescriptor.getReturn().getType().equals("void") ? 0 : 1) - calledMethodDescriptor.getArgumentCount();
        }
        else{

            builder.append("invokevirtual");
            diff = - calledMethodDescriptor.getArgumentCount();
        }
        builder.append(" ");
        builder.append(calledMethodClassName);
        builder.append("/");
        builder.append(calledMethodDescriptor.getJVMSignature());

        return new JVMInstruction(builder.toString(),diff);
    }

    public static String getLabel() {
        return "l" + lastLabelID++;
    }

    public static int getWhileLabel(){
        return lastWhileID++;
    }

    public static int getIfLabel(){
        return lastIfID++;
    }
}