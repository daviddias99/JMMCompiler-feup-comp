.source test/fixtures/public/TicTacToe.jmm
.class public TicTacToe
.super java/lang/Object
.field public row0_name [I
.field public row1_name [I
.field public row2_name [I
.field public whoseturn_name I
.field public movesmade_name I
.field public pieces_name [I
.method public init()Z
.limit stack 3
.limit locals 1
aload_0
iconst_3
newarray int
putfield TicTacToe/row0_name [I
aload_0
iconst_3
newarray int
putfield TicTacToe/row1_name [I
aload_0
iconst_3
newarray int
putfield TicTacToe/row2_name [I
aload_0
iconst_2
newarray int
putfield TicTacToe/pieces_name [I
aload_0
getfield TicTacToe/pieces_name [I
iconst_0
iconst_1
iastore
aload_0
getfield TicTacToe/pieces_name [I
iconst_1
iconst_2
iastore
aload_0
iconst_0
putfield TicTacToe/whoseturn_name I
aload_0
iconst_0
putfield TicTacToe/movesmade_name I
iconst_1
ireturn
.end method
.method public getRow0()[I
.limit stack 1
.limit locals 1
aload_0
getfield TicTacToe/row0_name [I
areturn
.end method
.method public getRow1()[I
.limit stack 1
.limit locals 1
aload_0
getfield TicTacToe/row1_name [I
areturn
.end method
.method public getRow2()[I
.limit stack 1
.limit locals 1
aload_0
getfield TicTacToe/row2_name [I
areturn
.end method
.method public MoveRow([II)Z
.limit stack 4
.limit locals 4
iload_2
ifge ELSE_0
iconst_0
istore_3
goto END_IF_ELSE_0
ELSE_0:
iconst_2
iload_2
if_icmpge ELSE_1
iconst_0
istore_3
goto END_IF_ELSE_1
ELSE_1:
aload_1
iload_2
iaload
ifle ELSE_2
iconst_0
istore_3
goto END_IF_ELSE_2
ELSE_2:
aload_1
iload_2
aload_0
getfield TicTacToe/pieces_name [I
aload_0
getfield TicTacToe/whoseturn_name I
iaload
iastore
aload_0
aload_0
getfield TicTacToe/movesmade_name I
iconst_1
iadd
putfield TicTacToe/movesmade_name I
iconst_1
istore_3
END_IF_ELSE_2:
END_IF_ELSE_1:
END_IF_ELSE_0:
iload_3
ireturn
.end method
.method public Move(II)Z
.limit stack 3
.limit locals 4
iload_1
ifge l0
iconst_1
goto l1
l0:
iconst_0
l1:
iconst_1
ixor
iload_1
ifle l2
iconst_1
goto l3
l2:
iconst_0
l3:
iconst_1
ixor
iand
ifeq ELSE_3
aload_0
aload_0
getfield TicTacToe/row0_name [I
iload_2
invokevirtual TicTacToe/MoveRow([II)Z
istore_3
goto END_IF_ELSE_3
ELSE_3:
iload_1
iconst_1
if_icmpge l4
iconst_1
goto l5
l4:
iconst_0
l5:
iconst_1
ixor
iconst_1
iload_1
if_icmpge l6
iconst_1
goto l7
l6:
iconst_0
l7:
iconst_1
ixor
iand
ifeq ELSE_4
aload_0
aload_0
getfield TicTacToe/row1_name [I
iload_2
invokevirtual TicTacToe/MoveRow([II)Z
istore_3
goto END_IF_ELSE_4
ELSE_4:
iload_1
iconst_2
if_icmpge l8
iconst_1
goto l9
l8:
iconst_0
l9:
iconst_1
ixor
iconst_2
iload_1
if_icmpge l10
iconst_1
goto l11
l10:
iconst_0
l11:
iconst_1
ixor
iand
ifeq ELSE_5
aload_0
aload_0
getfield TicTacToe/row2_name [I
iload_2
invokevirtual TicTacToe/MoveRow([II)Z
istore_3
goto END_IF_ELSE_5
ELSE_5:
iconst_0
istore_3
END_IF_ELSE_5:
END_IF_ELSE_4:
END_IF_ELSE_3:
iload_3
ireturn
.end method
.method public inbounds(II)Z
.limit stack 2
.limit locals 4
iload_1
ifge ELSE_6
iconst_0
istore_3
goto END_IF_ELSE_6
ELSE_6:
iload_2
ifge ELSE_7
iconst_0
istore_3
goto END_IF_ELSE_7
ELSE_7:
iconst_2
iload_1
if_icmpge ELSE_8
iconst_0
istore_3
goto END_IF_ELSE_8
ELSE_8:
iconst_2
iload_2
if_icmpge ELSE_9
iconst_0
istore_3
goto END_IF_ELSE_9
ELSE_9:
iconst_1
istore_3
END_IF_ELSE_9:
END_IF_ELSE_8:
END_IF_ELSE_7:
END_IF_ELSE_6:
iload_3
ireturn
.end method
.method public changeturn()Z
.limit stack 3
.limit locals 1
aload_0
iconst_1
aload_0
getfield TicTacToe/whoseturn_name I
isub
putfield TicTacToe/whoseturn_name I
iconst_1
ireturn
.end method
.method public getCurrentPlayer()I
.limit stack 2
.limit locals 1
aload_0
getfield TicTacToe/whoseturn_name I
iconst_1
iadd
ireturn
.end method
.method public winner()I
.limit stack 4
.limit locals 4
iconst_0
iconst_1
isub
istore_2
iconst_3
newarray int
astore_1
aload_0
getfield TicTacToe/row0_name [I
invokestatic BoardBase/sameArray([I)Z
aload_0
getfield TicTacToe/row0_name [I
iconst_0
iaload
ifle l12
iconst_1
goto l13
l12:
iconst_0
l13:
iand
ifeq ELSE_10
aload_0
getfield TicTacToe/row0_name [I
iconst_0
iaload
istore_2
goto END_IF_ELSE_10
ELSE_10:
aload_0
getfield TicTacToe/row1_name [I
invokestatic BoardBase/sameArray([I)Z
aload_0
getfield TicTacToe/row1_name [I
iconst_0
iaload
ifle l14
iconst_1
goto l15
l14:
iconst_0
l15:
iand
ifeq ELSE_11
aload_0
getfield TicTacToe/row1_name [I
iconst_0
iaload
istore_2
goto END_IF_ELSE_11
ELSE_11:
aload_0
getfield TicTacToe/row2_name [I
invokestatic BoardBase/sameArray([I)Z
aload_0
getfield TicTacToe/row2_name [I
iconst_0
iaload
ifle l16
iconst_1
goto l17
l16:
iconst_0
l17:
iand
ifeq ELSE_12
aload_0
getfield TicTacToe/row2_name [I
iconst_0
iaload
istore_2
goto END_IF_ELSE_12
ELSE_12:
iconst_0
istore_3
WHILE_0:
iload_2
iconst_1
if_icmpge l18
iconst_1
goto l19
l18:
iconst_0
l19:
iload_3
iconst_3
if_icmpge l20
iconst_1
goto l21
l20:
iconst_0
l21:
iand
ifeq END_WHILE_0
aload_1
iconst_0
aload_0
getfield TicTacToe/row0_name [I
iload_3
iaload
iastore
aload_1
iconst_1
aload_0
getfield TicTacToe/row1_name [I
iload_3
iaload
iastore
aload_1
iconst_2
aload_0
getfield TicTacToe/row2_name [I
iload_3
iaload
iastore
aload_1
invokestatic BoardBase/sameArray([I)Z
aload_1
iconst_0
iaload
ifle l22
iconst_1
goto l23
l22:
iconst_0
l23:
iand
ifeq ELSE_13
aload_1
iconst_0
iaload
istore_2
goto END_IF_ELSE_13
ELSE_13:
END_IF_ELSE_13:
iinc 3 1
goto WHILE_0
END_WHILE_0:
iload_2
iconst_1
if_icmpge ELSE_14
aload_1
iconst_0
aload_0
getfield TicTacToe/row0_name [I
iconst_0
iaload
iastore
aload_1
iconst_1
aload_0
getfield TicTacToe/row1_name [I
iconst_1
iaload
iastore
aload_1
iconst_2
aload_0
getfield TicTacToe/row2_name [I
iconst_2
iaload
iastore
aload_1
invokestatic BoardBase/sameArray([I)Z
aload_1
iconst_0
iaload
ifle l24
iconst_1
goto l25
l24:
iconst_0
l25:
iand
ifeq ELSE_15
aload_1
iconst_0
iaload
istore_2
goto END_IF_ELSE_15
ELSE_15:
aload_1
iconst_0
aload_0
getfield TicTacToe/row0_name [I
iconst_2
iaload
iastore
aload_1
iconst_1
aload_0
getfield TicTacToe/row1_name [I
iconst_1
iaload
iastore
aload_1
iconst_2
aload_0
getfield TicTacToe/row2_name [I
iconst_0
iaload
iastore
aload_1
invokestatic BoardBase/sameArray([I)Z
aload_1
iconst_0
iaload
ifle l26
iconst_1
goto l27
l26:
iconst_0
l27:
iand
ifeq ELSE_16
aload_1
iconst_0
iaload
istore_2
goto END_IF_ELSE_16
ELSE_16:
END_IF_ELSE_16:
END_IF_ELSE_15:
goto END_IF_ELSE_14
ELSE_14:
END_IF_ELSE_14:
END_IF_ELSE_12:
END_IF_ELSE_11:
END_IF_ELSE_10:
iload_2
iconst_1
if_icmpge l28
iconst_1
goto l29
l28:
iconst_0
l29:
aload_0
getfield TicTacToe/movesmade_name I
bipush 9
if_icmpge l30
iconst_1
goto l31
l30:
iconst_0
l31:
iconst_1
ixor
iand
bipush 9
aload_0
getfield TicTacToe/movesmade_name I
if_icmpge l32
iconst_1
goto l33
l32:
iconst_0
l33:
iconst_1
ixor
iand
ifeq ELSE_17
iconst_0
istore_2
goto END_IF_ELSE_17
ELSE_17:
END_IF_ELSE_17:
iload_2
ireturn
.end method
.method public static main([Ljava/lang/String;)V
.limit stack 4
.limit locals 6
new TicTacToe
dup
invokespecial TicTacToe/<init>()V
astore_1
aload_1
invokevirtual TicTacToe/init()Z
pop
WHILE_1:
aload_1
invokevirtual TicTacToe/winner()I
iconst_0
iconst_1
isub
if_icmpge l34
iconst_1
goto l35
l34:
iconst_0
l35:
iconst_1
ixor
iconst_0
iconst_1
isub
aload_1
invokevirtual TicTacToe/winner()I
if_icmpge l36
iconst_1
goto l37
l36:
iconst_0
l37:
iconst_1
ixor
iand
ifeq END_WHILE_1
iconst_0
istore_3
WHILE_2:
iload_3
iconst_1
ixor
ifeq END_WHILE_2
aload_1
invokevirtual TicTacToe/getRow0()[I
aload_1
invokevirtual TicTacToe/getRow1()[I
aload_1
invokevirtual TicTacToe/getRow2()[I
invokestatic BoardBase/printBoard([I[I[I)V
aload_1
invokevirtual TicTacToe/getCurrentPlayer()I
istore 5
iload 5
invokestatic BoardBase/playerTurn(I)[I
astore 4
aload_1
aload 4
iconst_0
iaload
aload 4
iconst_1
iaload
invokevirtual TicTacToe/inbounds(II)Z
iconst_1
ixor
ifeq ELSE_18
invokestatic BoardBase/wrongMove()V
goto END_IF_ELSE_18
ELSE_18:
aload_1
aload 4
iconst_0
iaload
aload 4
iconst_1
iaload
invokevirtual TicTacToe/Move(II)Z
iconst_1
ixor
ifeq ELSE_19
invokestatic BoardBase/placeTaken()V
goto END_IF_ELSE_19
ELSE_19:
iconst_1
istore_3
END_IF_ELSE_19:
END_IF_ELSE_18:
goto WHILE_2
END_WHILE_2:
aload_1
invokevirtual TicTacToe/changeturn()Z
pop
goto WHILE_1
END_WHILE_1:
aload_1
invokevirtual TicTacToe/getRow0()[I
aload_1
invokevirtual TicTacToe/getRow1()[I
aload_1
invokevirtual TicTacToe/getRow2()[I
invokestatic BoardBase/printBoard([I[I[I)V
aload_1
invokevirtual TicTacToe/winner()I
istore_2
iload_2
invokestatic BoardBase/printWinner(I)V
return
.end method
.method public <init>()V
   aload_0
   invokenonvirtual java/lang/Object/<init>()V
   return
.end method
