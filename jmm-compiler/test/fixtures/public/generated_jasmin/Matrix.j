.source test/fixtures/public/top_notch/Matrix.jmm
.class public Matrix
.super java/lang/Object
.field public storage_name [I
.field public width_name I
.field public height_name I
.method public create(II)I
.limit stack 3
.limit locals 3
aload_0
iload_1
iload_2
imul
newarray int
putfield Matrix/storage_name [I
aload_0
iload_2
putfield Matrix/width_name I
aload_0
iload_1
putfield Matrix/height_name I
iconst_0
ireturn
.end method
.method public getWidth()I
.limit stack 1
.limit locals 1
aload_0
getfield Matrix/width_name I
ireturn
.end method
.method public getHeight()I
.limit stack 1
.limit locals 1
aload_0
getfield Matrix/height_name I
ireturn
.end method
.method public getElement(II)I
.limit stack 3
.limit locals 3
aload_0
getfield Matrix/storage_name [I
iload_1
aload_0
getfield Matrix/width_name I
imul
iload_2
iadd
iaload
ireturn
.end method
.method public setElement(III)I
.limit stack 3
.limit locals 4
aload_0
getfield Matrix/storage_name [I
iload_1
aload_0
getfield Matrix/width_name I
imul
iload_2
iadd
iload_3
iastore
iload_3
ireturn
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
