.source test/fixtures/public/top_notch/EISearch.jmm
.class public EISearch
.super java/lang/Object
.method public bogoSort([I)I
.limit stack 2
.limit locals 2
WHILE_0:
aload_0
aload_1
invokevirtual EISearch/isSorted([I)Z
iconst_1
ixor
ifeq END_WHILE_0
aload_0
aload_1
invokevirtual EISearch/shuffle([I)I
pop
goto WHILE_0
END_WHILE_0:
iconst_0
ireturn
.end method
.method public bubbleSort([I)I
.limit stack 5
.limit locals 6
aload_1
arraylength
istore_2
iconst_0
istore 4
WHILE_1:
iload 4
iload_2
iconst_1
isub
if_icmpge END_WHILE_1
iconst_0
istore_3
WHILE_2:
iload_3
iload_2
iload 4
isub
iconst_1
isub
if_icmpge END_WHILE_2
aload_1
iload_3
iconst_1
iadd
iaload
aload_1
iload_3
iaload
if_icmpge ELSE_0
aload_1
iload_3
iaload
istore 5
aload_1
iload_3
aload_1
iload_3
iconst_1
iadd
iaload
iastore
aload_1
iload_3
iconst_1
iadd
iload 5
iastore
goto END_IF_ELSE_0
ELSE_0:
END_IF_ELSE_0:
iinc 3 1
goto WHILE_2
END_WHILE_2:
iinc 4 1
goto WHILE_1
END_WHILE_1:
iconst_0
ireturn
.end method
.method public isSorted([I)Z
.limit stack 3
.limit locals 4
iconst_0
istore_2
iconst_1
istore_3
WHILE_3:
iload_2
aload_1
arraylength
iconst_1
isub
if_icmpge END_WHILE_3
aload_1
iload_2
iconst_1
iadd
iaload
aload_1
iload_2
iaload
if_icmpge ELSE_1
iconst_0
istore_3
goto END_IF_ELSE_1
ELSE_1:
END_IF_ELSE_1:
iinc 2 1
goto WHILE_3
END_WHILE_3:
iload_3
ireturn
.end method
.method public printArray([I)I
.limit stack 2
.limit locals 4
aload_1
arraylength
istore_2
iconst_0
istore_3
WHILE_4:
iload_3
iload_2
if_icmpge END_WHILE_4
aload_1
iload_3
iaload
invokestatic io/println(I)V
iinc 3 1
goto WHILE_4
END_WHILE_4:
iconst_0
ireturn
.end method
.method public shuffle([I)I
.limit stack 4
.limit locals 5
iconst_0
istore 4
WHILE_5:
iload 4
aload_1
arraylength
if_icmpge END_WHILE_5
iconst_0
aload_1
arraylength
invokestatic MathUtils/random(II)I
istore_2
aload_1
iload_2
iaload
istore_3
aload_1
iload_2
aload_1
iload 4
iaload
iastore
aload_1
iload 4
iload_3
iastore
iinc 4 1
goto WHILE_5
END_WHILE_5:
iconst_0
ireturn
.end method
.method public equals(II)Z
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
.method public binarySearch([IIII)I
.limit stack 5
.limit locals 7
iconst_0
istore 5
iload_3
iload_2
if_icmpge l4
iconst_1
goto l5
l4:
iconst_0
l5:
iconst_1
ixor
ifeq ELSE_2
iload_2
iload_3
iload_2
isub
iconst_1
ishr
iadd
istore 5
aload_0
aload_1
iload 5
iaload
iload 4
invokevirtual EISearch/equals(II)Z
ifeq ELSE_3
iload 5
istore 6
goto END_IF_ELSE_3
ELSE_3:
iload 4
aload_1
iload 5
iaload
if_icmpge ELSE_4
aload_0
aload_1
iload_2
iload 5
iconst_1
isub
iload 4
invokevirtual EISearch/binarySearch([IIII)I
istore 6
goto END_IF_ELSE_4
ELSE_4:
aload_0
aload_1
iload 5
iconst_1
iadd
iload_3
iload 4
invokevirtual EISearch/binarySearch([IIII)I
istore 6
END_IF_ELSE_4:
END_IF_ELSE_3:
goto END_IF_ELSE_2
ELSE_2:
iconst_0
iconst_1
isub
istore 6
END_IF_ELSE_2:
iload 6
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 5
.limit locals 6
aload_0
iconst_0
aaload
invokestatic io/stringToInt(Ljava/lang/String;)I
istore 4
aload_0
iconst_1
aaload
invokestatic io/stringToBoolean(Ljava/lang/String;)Z
istore 5
bipush 12
newarray int
astore_3
aload_3
iconst_0
bipush 83
iastore
aload_3
iconst_1
bipush 34
iastore
aload_3
iconst_2
bipush 25
iastore
aload_3
iconst_3
iconst_0
iconst_5
isub
iastore
aload_3
iconst_4
bipush 12
iastore
aload_3
iconst_5
bipush 22
iastore
aload_3
bipush 6
bipush 43
iastore
aload_3
bipush 7
bipush 90
iastore
aload_3
bipush 8
bipush 64
iastore
aload_3
bipush 9
iconst_3
iastore
aload_3
bipush 10
iconst_1
iastore
aload_3
bipush 11
bipush 22
iastore
new EISearch
dup
invokespecial EISearch/<init>()V
astore_1
iload 5
ifeq ELSE_5
aload_1
aload_3
invokevirtual EISearch/bogoSort([I)I
pop
goto END_IF_ELSE_5
ELSE_5:
aload_1
aload_3
invokevirtual EISearch/bubbleSort([I)I
pop
END_IF_ELSE_5:
aload_1
aload_3
invokevirtual EISearch/printArray([I)I
pop
aload_1
aload_3
iconst_0
aload_3
arraylength
iconst_1
isub
iload 4
invokevirtual EISearch/binarySearch([IIII)I
istore_2
iload_2
ifge ELSE_6
iconst_0
invokestatic io/println(Z)V
goto END_IF_ELSE_6
ELSE_6:
iconst_1
invokestatic io/println(Z)V
END_IF_ELSE_6:
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
