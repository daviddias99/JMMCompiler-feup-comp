.source test/jmm/code_gen/code_gen_5.jmm
.class public IfTest
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 2
iconst_0
istore_1
iload_1
iconst_2
if_icmpge ELSE_0
aload_0
iconst_0
aaload
invokestatic io/println(Ljava/lang/String;)V
goto END_IF_ELSE_0
ELSE_0:
aload_0
iconst_1
aaload
invokestatic io/println(Ljava/lang/String;)V
END_IF_ELSE_0:
iload_1
ifge ELSE_1
aload_0
iconst_0
aaload
invokestatic io/println(Ljava/lang/String;)V
goto END_IF_ELSE_1
ELSE_1:
aload_0
iconst_1
aaload
invokestatic io/println(Ljava/lang/String;)V
END_IF_ELSE_1:
iload_1
ifle ELSE_2
aload_0
iconst_0
aaload
invokestatic io/println(Ljava/lang/String;)V
goto END_IF_ELSE_2
ELSE_2:
aload_0
iconst_1
aaload
invokestatic io/println(Ljava/lang/String;)V
END_IF_ELSE_2:
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
