.source test/fixtures/public/Lazysort.jmm
.class public Lazysort
.super Quicksort
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 5
bipush 10
newarray int
astore_1
iconst_0
istore_2
WHILE_0:
iload_2
aload_1
arraylength
if_icmpge END_WHILE_0
aload_1
iload_2
aload_1
arraylength
iload_2
isub
iastore
iinc 2 1
goto WHILE_0
END_WHILE_0:
new Lazysort
dup
invokespecial Lazysort/<init>()V
astore 4
aload 4
aload_1
invokevirtual Lazysort/quicksort([I)Z
pop
aload 4
aload_1
invokevirtual Quicksort/printL([I)Z
istore_3
return
.end method
.method public quicksort([I)Z
.limit stack 5
.limit locals 3
iconst_0
iconst_5
invokestatic MathUtils/random(II)I
iconst_4
if_icmpge ELSE_0
aload_0
aload_1
invokevirtual Lazysort/beLazy([I)Z
pop
iconst_1
istore_2
goto END_IF_ELSE_0
ELSE_0:
iconst_0
istore_2
END_IF_ELSE_0:
iload_2
ifeq ELSE_1
iload_2
iconst_1
ixor
istore_2
goto END_IF_ELSE_1
ELSE_1:
aload_0
aload_1
iconst_0
aload_1
arraylength
iconst_1
isub
invokevirtual Quicksort/quicksort([III)Z
istore_2
END_IF_ELSE_1:
iload_2
ireturn
.end method
.method public beLazy([I)Z
.limit stack 4
.limit locals 4
aload_1
arraylength
istore_2
iconst_0
istore_3
WHILE_1:
iload_3
iload_2
iconst_1
ishr
if_icmpge END_WHILE_1
aload_1
iload_3
iconst_0
bipush 10
invokestatic MathUtils/random(II)I
iastore
iinc 3 1
goto WHILE_1
END_WHILE_1:
WHILE_2:
iload_3
iload_2
if_icmpge END_WHILE_2
aload_1
iload_3
iconst_0
bipush 10
invokestatic MathUtils/random(II)I
iconst_1
iadd
iastore
iinc 3 1
goto WHILE_2
END_WHILE_2:
iconst_1
ireturn
.end method
.method public <init>()V
   aload_0
   invokenonvirtual Quicksort/<init>()V
   return
.end method
