.source test/fixtures/public/MonteCarloPi.jmm
.class public MonteCarloPi
.super java/lang/Object
.method public performSingleEstimate()Z
.limit stack 3
.limit locals 5
iconst_0
bipush 100
isub
bipush 100
invokestatic MathUtils/random(II)I
istore_1
iconst_0
bipush 100
isub
bipush 100
invokestatic MathUtils/random(II)I
istore_2
iload_1
iload_1
imul
iload_2
iload_2
imul
iadd
bipush 100
idiv
istore 4
iload 4
bipush 100
if_icmpge ELSE_0
iconst_1
istore_3
goto END_IF_ELSE_0
ELSE_0:
iconst_0
istore_3
END_IF_ELSE_0:
iload_3
ireturn
.end method
.method public estimatePi100(I)I
.limit stack 2
.limit locals 5
iconst_0
istore_3
iconst_0
istore_2
WHILE_0:
iload_3
iload_1
if_icmpge END_WHILE_0
aload_0
invokevirtual MonteCarloPi/performSingleEstimate()Z
ifeq ELSE_1
iinc 2 1
goto END_IF_ELSE_1
ELSE_1:
END_IF_ELSE_1:
iinc 3 1
goto WHILE_0
END_WHILE_0:
sipush 400
iload_2
imul
iload_1
idiv
istore 4
iload 4
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 3
invokestatic io/read()I
istore_2
new MonteCarloPi
dup
invokespecial MonteCarloPi/<init>()V
iload_2
invokevirtual MonteCarloPi/estimatePi100(I)I
istore_1
iload_1
invokestatic io/println(I)V
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
