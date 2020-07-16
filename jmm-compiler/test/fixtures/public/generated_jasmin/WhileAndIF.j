.source test/fixtures/public/WhileAndIF.jmm
.class public WhileAndIf
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 5
bipush 20
istore_1
bipush 10
istore_2
bipush 10
newarray int
astore 4
iload_1
iload_2
if_icmpge ELSE_0
iload_1
iconst_1
isub
istore_3
goto END_IF_ELSE_0
ELSE_0:
iload_2
iconst_1
isub
istore_3
END_IF_ELSE_0:
WHILE_0:
iconst_0
iconst_1
isub
iload_3
if_icmpge END_WHILE_0
aload 4
iload_3
iload_1
iload_2
isub
iastore
iinc 3 -1
iinc 1 -1
iinc 2 -1
goto WHILE_0
END_WHILE_0:
iconst_0
istore_3
WHILE_1:
iload_3
aload 4
arraylength
if_icmpge END_WHILE_1
aload 4
iload_3
iaload
invokestatic io/println(I)V
iinc 3 1
goto WHILE_1
END_WHILE_1:
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
