.source test/jmm/code_gen/code_gen_4.jmm
.class public PrintArray
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 3
.limit locals 3
bipush 17
newarray int
astore_1
iconst_0
istore_2
aload_1
iconst_0
iconst_3
iastore
aload_1
iconst_1
iconst_5
iastore
aload_1
iconst_2
bipush 9
iastore
aload_1
iconst_3
iconst_4
iastore
aload_1
iconst_4
iconst_3
iastore
aload_1
iconst_5
iconst_3
iastore
aload_1
bipush 6
bipush 21
iastore
aload_1
bipush 7
bipush 8
iastore
aload_1
bipush 8
bipush 7
iastore
aload_1
bipush 9
bipush 6
iastore
aload_1
bipush 10
bipush 12
iastore
aload_1
bipush 11
iconst_1
iastore
aload_1
bipush 12
iconst_0
iastore
aload_1
bipush 13
bipush 6
iastore
aload_1
bipush 14
iconst_1
iastore
aload_1
bipush 15
bipush 23
iastore
aload_1
bipush 16
bipush 12
iastore
WHILE_0:
iload_2
aload_1
arraylength
if_icmpge END_WHILE_0
aload_1
iload_2
iaload
invokestatic io/println(I)V
iinc 2 1
goto WHILE_0
END_WHILE_0:
bipush 16
istore_2
WHILE_1:
iload_2
ifle END_WHILE_1
aload_1
iload_2
iaload
invokestatic io/println(I)V
iinc 2 -1
goto WHILE_1
END_WHILE_1:
iconst_0
iconst_3
isub
istore_2
WHILE_2:
iload_2
ifge END_WHILE_2
aload_1
iconst_2
iaload
invokestatic io/println(I)V
iinc 2 1
goto WHILE_2
END_WHILE_2:
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
