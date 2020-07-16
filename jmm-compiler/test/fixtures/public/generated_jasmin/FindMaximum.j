.source test/fixtures/public/FindMaximum.jmm
.class public FindMaximum
.super java/lang/Object
.field public test_arr_name [I
.field public num_name I
.field public fm_name LFindMaximum;
.field public booL_name Z
.method public find_maximum([I)I
.limit stack 2
.limit locals 5
iconst_1
istore_2
aload_1
iconst_0
iaload
istore_3
WHILE_0:
iload_2
aload_1
arraylength
if_icmpge END_WHILE_0
aload_1
iload_2
iaload
istore 4
iload_3
iload 4
if_icmpge ELSE_0
iload 4
istore_3
goto END_IF_ELSE_0
ELSE_0:
END_IF_ELSE_0:
iinc 2 1
goto WHILE_0
END_WHILE_0:
iload_3
ireturn
.end method
.method public build_test_arr()I
.limit stack 4
.limit locals 1
aload_0
iconst_5
newarray int
putfield FindMaximum/test_arr_name [I
aload_0
getfield FindMaximum/test_arr_name [I
iconst_0
bipush 14
iastore
aload_0
getfield FindMaximum/test_arr_name [I
iconst_1
bipush 28
iastore
aload_0
getfield FindMaximum/test_arr_name [I
iconst_2
iconst_0
iastore
aload_0
getfield FindMaximum/test_arr_name [I
iconst_3
iconst_0
iconst_5
isub
iastore
aload_0
getfield FindMaximum/test_arr_name [I
iconst_4
bipush 12
iastore
iconst_0
ireturn
.end method
.method public get_array()[I
.limit stack 1
.limit locals 1
aload_0
getfield FindMaximum/test_arr_name [I
areturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 2
new FindMaximum
dup
invokespecial FindMaximum/<init>()V
astore_1
aload_1
invokevirtual FindMaximum/build_test_arr()I
pop
aload_1
aload_1
invokevirtual FindMaximum/get_array()[I
invokevirtual FindMaximum/find_maximum([I)I
invokestatic io/println(I)V
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
