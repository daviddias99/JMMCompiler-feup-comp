.source test/jmm/code_gen/code_gen_2a.jmm
.class public EngFac
.super Fac
.method public ComputeFac(I)I
.limit stack 3
.limit locals 3
iconst_3
istore_2
iload_2
iload_1
iadd
aload_0
bipush 50
invokevirtual Fac/getStudentCount(I)I
iadd
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 2
.limit locals 2
new EngFac
dup
invokespecial EngFac/<init>()V
astore_1
aload_0
iconst_0
aaload
invokestatic io/println(Ljava/lang/String;)V
aload_1
bipush 6
invokevirtual EngFac/ComputeFac(I)I
invokestatic io/println(I)V
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual Fac/<init>()V
   return
.end method
