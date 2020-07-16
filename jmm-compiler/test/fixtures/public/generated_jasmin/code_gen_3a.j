.source test/jmm/code_gen/code_gen_3a.jmm
.class public ArraySort
.super java/lang/Object
.method public static main([Ljava/lang/String;)V
.limit stack 3
.limit locals 3
bipush 17
newarray int
astore_1
new Quicksort
dup
invokespecial Quicksort/<init>()V
astore_2
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
aload_2
aload_1
invokevirtual Quicksort/printL([I)Z
pop
aload_2
aload_1
invokevirtual Quicksort/quicksort([I)Z
pop
aload_2
aload_1
invokevirtual Quicksort/printL([I)Z
pop
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
