.source test/fixtures/public/top_notch/MathLib.jmm
.class public MathLib
.super java/lang/Object
.method public getDotProduct([I[I)I
.limit stack 4
.limit locals 5
iconst_0
istore 4
aload_0
aload_1
arraylength
aload_2
arraylength
invokevirtual MathLib/integerEquals(II)Z
iconst_1
ixor
ifeq ELSE_0
iconst_0
istore_3
goto END_IF_ELSE_0
ELSE_0:
iconst_0
istore_3
WHILE_0:
iload 4
aload_1
arraylength
if_icmpge END_WHILE_0
iload_3
aload_1
iload 4
iaload
aload_2
iload 4
iaload
imul
iadd
istore_3
iinc 4 1
goto WHILE_0
END_WHILE_0:
END_IF_ELSE_0:
iload_3
ireturn
.end method
.method public getFactorial(I)I
.limit stack 2
.limit locals 3
iconst_1
istore_2
WHILE_1:
iload_1
ifle END_WHILE_1
iload_2
iload_1
imul
istore_2
iinc 1 -1
goto WHILE_1
END_WHILE_1:
iload_2
ireturn
.end method
.method public combinations(II)I
.limit stack 5
.limit locals 3
aload_0
iload_1
invokevirtual MathLib/getFactorial(I)I
aload_0
iload_2
invokevirtual MathLib/getFactorial(I)I
aload_0
iload_1
iload_2
isub
invokevirtual MathLib/getFactorial(I)I
imul
idiv
ireturn
.end method
.method public logicalOR(ZZ)Z
.limit stack 3
.limit locals 3
iload_1
iconst_1
ixor
iload_2
iconst_1
ixor
iand
iconst_1
ixor
ireturn
.end method
.method public logicalXOR(ZZ)Z
.limit stack 4
.limit locals 3
aload_0
iload_1
iload_2
iconst_1
ixor
iand
iload_1
iconst_1
ixor
iload_2
iand
invokevirtual MathLib/logicalOR(ZZ)Z
ireturn
.end method
.method public integerEquals(II)Z
.limit stack 3
.limit locals 3
iload_1
iload_2
if_icmpge l0
iconst_1
goto l1
l0:
iconst_0
l1:
iconst_1
ixor
iload_2
iload_1
if_icmpge l2
iconst_1
goto l3
l2:
iconst_0
l3:
iconst_1
ixor
iand
ireturn
.end method
.method public matrixMultiply(LMatrix;LMatrix;)LMatrix;
.limit stack 8
.limit locals 7
new Matrix
dup
invokespecial Matrix/<init>()V
astore_3
aload_3
aload_1
invokevirtual Matrix/getWidth()I
aload_1
invokevirtual Matrix/getHeight()I
invokevirtual Matrix/create(II)I
pop
iconst_0
istore 4
WHILE_2:
iload 4
aload_1
invokevirtual Matrix/getHeight()I
if_icmpge END_WHILE_2
iconst_0
istore 5
WHILE_3:
iload 5
aload_2
invokevirtual Matrix/getWidth()I
if_icmpge END_WHILE_3
iconst_0
istore 6
WHILE_4:
iload 6
aload_1
invokevirtual Matrix/getWidth()I
if_icmpge END_WHILE_4
aload_3
iload 4
iload 5
aload_3
iload 4
iload 5
invokevirtual Matrix/getElement(II)I
aload_1
iload 4
iload 6
invokevirtual Matrix/getElement(II)I
aload_2
iload 6
iload 5
invokevirtual Matrix/getElement(II)I
imul
iadd
invokevirtual Matrix/setElement(III)I
pop
iinc 6 1
goto WHILE_4
END_WHILE_4:
iinc 5 1
goto WHILE_3
END_WHILE_3:
iinc 4 1
goto WHILE_2
END_WHILE_2:
aload_3
areturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 9
new Matrix
dup
invokespecial Matrix/<init>()V
astore_2
new Matrix
dup
invokespecial Matrix/<init>()V
astore_3
new SquareMatrix
dup
invokespecial SquareMatrix/<init>()V
astore 5
new SquareMatrix
dup
invokespecial SquareMatrix/<init>()V
astore 6
aload_2
iconst_3
iconst_3
invokevirtual Matrix/create(II)I
pop
aload_3
iconst_3
iconst_3
invokevirtual Matrix/create(II)I
pop
aload 6
iconst_3
invokevirtual SquareMatrix/createSquare(I)I
pop
iconst_2
iconst_3
iconst_3
idiv
iadd
newarray int
astore 7
iconst_3
iconst_0
iconst_0
imul
iadd
newarray int
astore 8
new MathLib
dup
invokespecial MathLib/<init>()V
astore_1
aload 7
iconst_0
iconst_1
iastore
aload 7
iconst_1
iconst_2
iastore
aload 7
iconst_2
iconst_3
iastore
aload 8
iconst_0
iconst_2
iastore
aload 8
iconst_1
iconst_2
iastore
aload 8
iconst_2
iconst_2
iastore
aload_1
iconst_4
invokevirtual MathLib/getFactorial(I)I
invokestatic io/println(I)V
aload_1
iconst_4
iconst_4
invokevirtual MathLib/integerEquals(II)Z
invokestatic io/println(Z)V
aload_1
iconst_4
iconst_5
invokevirtual MathLib/integerEquals(II)Z
invokestatic io/println(Z)V
aload_1
iconst_1
iconst_1
invokevirtual MathLib/logicalXOR(ZZ)Z
invokestatic io/println(Z)V
aload_1
iconst_1
iconst_0
invokevirtual MathLib/logicalXOR(ZZ)Z
invokestatic io/println(Z)V
aload_1
iconst_0
iconst_0
invokevirtual MathLib/logicalXOR(ZZ)Z
invokestatic io/println(Z)V
aload_1
iconst_1
iconst_1
invokevirtual MathLib/logicalOR(ZZ)Z
invokestatic io/println(Z)V
aload_1
iconst_1
iconst_0
invokevirtual MathLib/logicalOR(ZZ)Z
invokestatic io/println(Z)V
aload_1
iconst_0
iconst_0
invokevirtual MathLib/logicalOR(ZZ)Z
invokestatic io/println(Z)V
aload_1
iconst_5
iconst_2
invokevirtual MathLib/combinations(II)I
invokestatic io/println(I)V
aload_1
aload 7
aload 8
invokevirtual MathLib/getDotProduct([I[I)I
invokestatic io/println(I)V
aload_2
iconst_0
iconst_0
bipush 10
invokevirtual Matrix/setElement(III)I
pop
aload_2
iconst_0
iconst_1
bipush 20
invokevirtual Matrix/setElement(III)I
pop
aload_2
iconst_0
iconst_2
bipush 10
invokevirtual Matrix/setElement(III)I
pop
aload_2
iconst_1
iconst_0
iconst_4
invokevirtual Matrix/setElement(III)I
pop
aload_2
iconst_1
iconst_1
iconst_5
invokevirtual Matrix/setElement(III)I
pop
aload_2
iconst_1
iconst_2
bipush 6
invokevirtual Matrix/setElement(III)I
pop
aload_2
iconst_2
iconst_0
iconst_2
invokevirtual Matrix/setElement(III)I
pop
aload_2
iconst_2
iconst_1
iconst_3
invokevirtual Matrix/setElement(III)I
pop
aload_2
iconst_2
iconst_2
iconst_5
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_0
iconst_0
iconst_3
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_0
iconst_1
iconst_2
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_0
iconst_2
iconst_4
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_1
iconst_0
iconst_3
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_1
iconst_1
iconst_3
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_1
iconst_2
bipush 9
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_2
iconst_0
iconst_4
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_2
iconst_1
iconst_4
invokevirtual Matrix/setElement(III)I
pop
aload_3
iconst_2
iconst_2
iconst_2
invokevirtual Matrix/setElement(III)I
pop
aload 6
iconst_0
iconst_0
bipush 10
invokevirtual SquareMatrix/setElement(III)I
pop
aload 6
iconst_0
iconst_1
bipush 20
invokevirtual SquareMatrix/setElement(III)I
pop
aload 6
iconst_0
iconst_2
bipush 10
invokevirtual SquareMatrix/setElement(III)I
pop
aload 6
iconst_1
iconst_0
iconst_4
invokevirtual SquareMatrix/setElement(III)I
pop
aload 6
iconst_1
iconst_1
iconst_5
invokevirtual SquareMatrix/setElement(III)I
pop
aload 6
iconst_1
iconst_2
bipush 6
invokevirtual SquareMatrix/setElement(III)I
pop
aload 6
iconst_2
iconst_0
iconst_2
invokevirtual SquareMatrix/setElement(III)I
pop
aload 6
iconst_2
iconst_1
iconst_3
invokevirtual SquareMatrix/setElement(III)I
pop
aload 6
iconst_2
iconst_2
iconst_5
invokevirtual SquareMatrix/setElement(III)I
pop
aload_1
aload_2
aload_3
invokevirtual MathLib/matrixMultiply(LMatrix;LMatrix;)LMatrix;
astore 4
aload 5
iconst_4
invokevirtual SquareMatrix/createIdentity(I)I
pop
aload_2
invokestatic PrintMatrix/printMatrix(LMatrix;)V
aload_3
invokestatic PrintMatrix/printMatrix(LMatrix;)V
aload 4
invokestatic PrintMatrix/printMatrix(LMatrix;)V
aload 5
invokestatic PrintMatrix/printMatrix(LSquareMatrix;)V
aload 6
invokevirtual SquareMatrix/determinantOf3x3()I
invokestatic io/println(I)V
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
