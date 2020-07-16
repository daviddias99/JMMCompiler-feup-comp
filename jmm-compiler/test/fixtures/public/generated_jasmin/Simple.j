.source test/fixtures/public/Simple.jmm
.class public Simple
.super java/lang/Object
.method public add(II)I
.limit stack 2
.limit locals 4
iload_1
iload_2
iadd
istore_3
iload_3
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 3
.limit locals 5
bipush 20
istore_1
bipush 10
istore_2
new Simple
dup
invokespecial Simple/<init>()V
astore 4
aload 4
iload_1
iload_2
invokevirtual Simple/add(II)I
istore_3
iload_3
invokestatic io/println(I)V
return
.end method
.method public constInstr()I
.limit stack 1
.limit locals 2
iconst_0
istore_1
iconst_4
istore_1
bipush 8
istore_1
bipush 14
istore_1
sipush 250
istore_1
sipush 400
istore_1
sipush 1000
istore_1
ldc 100474650
istore_1
iconst_0
ireturn
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
