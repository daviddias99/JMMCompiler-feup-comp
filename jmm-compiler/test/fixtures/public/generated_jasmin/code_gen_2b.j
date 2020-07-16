.source test/jmm/code_gen/code_gen_2b.jmm
.class public Fac
.super java/lang/Object
.method public getStudentCount(I)I
.limit stack 3
.limit locals 2
sipush 800
iload_1
iconst_3
imul
iadd
ireturn
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
