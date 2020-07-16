.source test/jmm/code_gen/code_gen_1a.jmm
.class public Scrambler
.super java/lang/Object
.field public var1_name I
.field public var3_name Z
.field public var4_name [I
.field public var5_name LScrambler;
.method public getPIFirstDigit()I
.limit stack 1
.limit locals 1
iconst_3
ireturn
.end method
.method public computeScramble(LEightBall;I)I
.limit stack 4
.limit locals 12
iconst_1
istore 4
iconst_5
newarray int
astore 6
aload 6
iconst_2
bipush 7
iastore
aload_0
iconst_2
putfield Scrambler/var1_name I
aload_0
iconst_1
putfield Scrambler/var3_name Z
aload_0
bipush 8
iload 4
iconst_1
ishl
isub
newarray int
putfield Scrambler/var4_name [I
aload_0
getfield Scrambler/var4_name [I
iconst_2
iconst_5
iastore
aload_0
new Scrambler
dup
invokespecial Scrambler/<init>()V
putfield Scrambler/var5_name LScrambler;
aload_0
astore 7
new FormulaCalculator
dup
invokespecial FormulaCalculator/<init>()V
astore 11
iload 4
aload_0
getfield Scrambler/var4_name [I
iconst_2
iaload
if_icmpge l0
iconst_1
goto l1
l0:
iconst_0
l1:
aload_1
invokevirtual EightBall/getPrediction()Z
iand
aload_0
getfield Scrambler/var3_name Z
aload 6
iconst_2
iaload
iload 4
if_icmpge l2
iconst_1
goto l3
l2:
iconst_0
l3:
iand
iconst_1
ixor
iand
istore 8
aload 11
aload 6
iconst_2
iaload
aload_0
getfield Scrambler/var1_name I
iconst_1
ishl
iconst_1
invokevirtual FormulaCalculator/calculate(IIZ)I
istore 5
aload 6
iconst_2
iaload
bipush 30
iload 4
iconst_3
iadd
idiv
aload_0
getfield Scrambler/var1_name I
imul
isub
aload_0
invokevirtual Scrambler/getPIFirstDigit()I
iadd
aload_0
getfield Scrambler/var5_name LScrambler;
invokevirtual Scrambler/getPIFirstDigit()I
iadd
iload 5
isub
istore_3
iload 8
invokestatic io/println(Z)V
iconst_5
ifle l4
iconst_1
goto l5
l4:
iconst_0
l5:
istore 10
iload 10
invokestatic io/println(Z)V
iconst_5
ifge l6
iconst_1
goto l7
l6:
iconst_0
l7:
istore 10
iload 10
invokestatic io/println(Z)V
bipush 32
istore 4
iload 4
iconst_4
ishr
istore 5
iload 5
invokestatic io/println(I)V
iload_3
iconst_3
iconst_1
ishl
bipush 6
idiv
isub
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 3
.limit locals 3
new EightBall
dup
invokespecial EightBall/<init>()V
astore_2
new Scrambler
dup
invokespecial Scrambler/<init>()V
astore_1
aload_0
iconst_0
aaload
invokestatic io/println(Ljava/lang/String;)V
aload_1
aload_2
iconst_3
invokevirtual Scrambler/computeScramble(LEightBall;I)I
invokestatic io/println(I)V
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
