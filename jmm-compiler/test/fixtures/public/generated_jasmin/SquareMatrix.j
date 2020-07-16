.source test/fixtures/public/top_notch/SquareMatrix.jmm
.class public SquareMatrix
.super Matrix
.method public createSquare(I)I
.limit stack 3
.limit locals 2
aload_0
iload_1
iload_1
invokevirtual Matrix/create(II)I
pop
iconst_0
ireturn
.end method
.method public createIdentity(I)I
.limit stack 4
.limit locals 3
aload_0
iload_1
invokevirtual SquareMatrix/createSquare(I)I
pop
iconst_0
istore_2
WHILE_0:
iload_2
iload_1
if_icmpge END_WHILE_0
aload_0
iload_2
iload_2
iconst_1
invokevirtual Matrix/setElement(III)I
pop
iinc 2 1
goto WHILE_0
END_WHILE_0:
iconst_0
ireturn
.end method
.method public determinantOf3x3()I
.limit stack 7
.limit locals 1
aload_0
iconst_0
iconst_0
invokevirtual Matrix/getElement(II)I
aload_0
iconst_1
iconst_1
invokevirtual Matrix/getElement(II)I
aload_0
iconst_2
iconst_2
invokevirtual Matrix/getElement(II)I
imul
aload_0
iconst_1
iconst_2
invokevirtual Matrix/getElement(II)I
aload_0
iconst_2
iconst_1
invokevirtual Matrix/getElement(II)I
imul
isub
imul
aload_0
iconst_0
iconst_1
invokevirtual Matrix/getElement(II)I
aload_0
iconst_1
iconst_0
invokevirtual Matrix/getElement(II)I
aload_0
iconst_2
iconst_2
invokevirtual Matrix/getElement(II)I
imul
aload_0
iconst_1
iconst_2
invokevirtual Matrix/getElement(II)I
aload_0
iconst_2
iconst_0
invokevirtual Matrix/getElement(II)I
imul
isub
imul
isub
aload_0
iconst_0
iconst_2
invokevirtual Matrix/getElement(II)I
aload_0
iconst_1
iconst_0
invokevirtual Matrix/getElement(II)I
aload_0
iconst_2
iconst_1
invokevirtual Matrix/getElement(II)I
imul
aload_0
iconst_1
iconst_1
invokevirtual Matrix/getElement(II)I
aload_0
iconst_2
iconst_0
invokevirtual Matrix/getElement(II)I
imul
isub
imul
iadd
ireturn
.end method
.method public <init>()V
   aload_0
   invokenonvirtual Matrix/<init>()V
   return
.end method
