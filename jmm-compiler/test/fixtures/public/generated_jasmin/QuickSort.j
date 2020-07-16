.source test/fixtures/public/QuickSort.jmm
.class public Quicksort
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 4
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
new Quicksort
dup
invokespecial Quicksort/<init>()V
astore_3
aload_3
aload_1
invokevirtual Quicksort/quicksort([I)Z
pop
aload_3
aload_1
invokevirtual Quicksort/printL([I)Z
pop
return
.end method
.method public printL([I)Z
.limit stack 2
.limit locals 3
iconst_0
istore_2
WHILE_1:
iload_2
aload_1
arraylength
if_icmpge END_WHILE_1
aload_1
iload_2
iaload
invokestatic io/println(I)V
iinc 2 1
goto WHILE_1
END_WHILE_1:
iconst_1
ireturn
.end method
.method public quicksort([I)Z
.limit stack 5
.limit locals 2
aload_0
aload_1
iconst_0
aload_1
arraylength
iconst_1
isub
invokevirtual Quicksort/quicksort([III)Z
ireturn
.end method
.method public quicksort([III)Z
.limit stack 5
.limit locals 5
iload_2
iload_3
if_icmpge ELSE_0
aload_0
aload_1
iload_2
iload_3
invokevirtual Quicksort/partition([III)I
istore 4
aload_0
aload_1
iload_2
iload 4
iconst_1
isub
invokevirtual Quicksort/quicksort([III)Z
pop
aload_0
aload_1
iload 4
iconst_1
iadd
iload_3
invokevirtual Quicksort/quicksort([III)Z
pop
goto END_IF_ELSE_0
ELSE_0:
END_IF_ELSE_0:
iconst_1
ireturn
.end method
.method public partition([III)I
.limit stack 4
.limit locals 8
aload_1
iload_3
iaload
istore 4
iload_2
istore 5
iload_2
istore 6
WHILE_2:
iload 6
iload_3
if_icmpge END_WHILE_2
aload_1
iload 6
iaload
iload 4
if_icmpge ELSE_1
aload_1
iload 5
iaload
istore 7
aload_1
iload 5
aload_1
iload 6
iaload
iastore
aload_1
iload 6
iload 7
iastore
iinc 5 1
goto END_IF_ELSE_1
ELSE_1:
END_IF_ELSE_1:
iinc 6 1
goto WHILE_2
END_WHILE_2:
aload_1
iload 5
iaload
istore 7
aload_1
iload 5
aload_1
iload_3
iaload
iastore
aload_1
iload_3
iload 7
iastore
iload 5
ireturn
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
