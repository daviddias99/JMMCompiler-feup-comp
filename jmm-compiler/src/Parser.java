/* Parser.java */
/* Generated By:JavaCC: Do not edit this line. Parser.java */
public class Parser implements ParserConstants {

  static final public void Program() throws ParseException {
    ImportDeclaration();
    ClassDeclaration();
    jj_consume_token(0);
}

  static final public void ImportDeclaration() throws ParseException {
    label_1:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IMPORT:{
        ;
        break;
        }
      default:
        jj_la1[0] = jj_gen;
        break label_1;
      }
      jj_consume_token(IMPORT);
    }
}

  static final public void ClassDeclaration() throws ParseException {
    jj_consume_token(CLASS);
    jj_consume_token(IDENTIFIER);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case EXTENDS:{
      jj_consume_token(EXTENDS);
      jj_consume_token(IDENTIFIER);
      break;
      }
    default:
      jj_la1[1] = jj_gen;
      ;
    }
    jj_consume_token(LBRACKET);
    label_2:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INT:
      case BOOLEAN:
      case IDENTIFIER:{
        ;
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        break label_2;
      }
      VarDeclaration();
    }
    label_3:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case PUBLIC:{
        ;
        break;
        }
      default:
        jj_la1[3] = jj_gen;
        break label_3;
      }
      MethodDeclaration();
    }
    jj_consume_token(RBRACKET);
}

  static final public void VarDeclaration() throws ParseException {
    Type();
    jj_consume_token(IDENTIFIER);
    jj_consume_token(SCOLON);
}

  static final public void MethodDeclaration() throws ParseException {
    jj_consume_token(PUBLIC);
    Type();
    jj_consume_token(IDENTIFIER);
    jj_consume_token(LPAR);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INT:
    case BOOLEAN:
    case IDENTIFIER:{
      Type();
      jj_consume_token(IDENTIFIER);
      label_4:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case COMMA:{
          ;
          break;
          }
        default:
          jj_la1[4] = jj_gen;
          break label_4;
        }
        jj_consume_token(COMMA);
        Type();
        jj_consume_token(IDENTIFIER);
      }
      break;
      }
    default:
      jj_la1[5] = jj_gen;
      ;
    }
    jj_consume_token(RPAR);
    jj_consume_token(LBRACKET);
    label_5:
    while (true) {
      if (jj_2_1(2)) {
        ;
      } else {
        break label_5;
      }
      VarDeclaration();
    }
    label_6:
    while (true) {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case IF:
      case NEW:
      case TRUE:
      case THIS:
      case FALSE:
      case WHILE:
      case LBRACKET:
      case LPAR:
      case NOT:
      case IDENTIFIER:
      case INTEGERLITERAL:{
        ;
        break;
        }
      default:
        jj_la1[6] = jj_gen;
        break label_6;
      }
      Statement();
    }
    jj_consume_token(RETURN);
    Expression();
    jj_consume_token(SCOLON);
    jj_consume_token(RBRACKET);
}

  static final public void Type() throws ParseException {
    if (jj_2_2(2)) {
      jj_consume_token(INT);
      jj_consume_token(LSQRBRACKET);
      jj_consume_token(RSQRBRACKET);
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case BOOLEAN:{
        jj_consume_token(BOOLEAN);
        break;
        }
      case INT:{
        jj_consume_token(INT);
        break;
        }
      case IDENTIFIER:{
        jj_consume_token(IDENTIFIER);
        break;
        }
      default:
        jj_la1[7] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
}

  static final public void Statement() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case LBRACKET:{
      jj_consume_token(LBRACKET);
      label_7:
      while (true) {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case IF:
        case NEW:
        case TRUE:
        case THIS:
        case FALSE:
        case WHILE:
        case LBRACKET:
        case LPAR:
        case NOT:
        case IDENTIFIER:
        case INTEGERLITERAL:{
          ;
          break;
          }
        default:
          jj_la1[8] = jj_gen;
          break label_7;
        }
        Statement();
      }
      jj_consume_token(RBRACKET);
      break;
      }
    case IF:{
      jj_consume_token(IF);
      jj_consume_token(LPAR);
      Expression();
      jj_consume_token(RPAR);
      Statement();
      jj_consume_token(ELSE);
      Statement();
      break;
      }
    case WHILE:{
      jj_consume_token(WHILE);
      jj_consume_token(LPAR);
      Expression();
      jj_consume_token(RPAR);
      Statement();
      break;
      }
    default:
      jj_la1[9] = jj_gen;
      if (jj_2_3(3)) {
        Expression();
        jj_consume_token(SCOLON);
      } else if (jj_2_4(2)) {
        jj_consume_token(IDENTIFIER);
        jj_consume_token(EQUALS);
        Expression();
        jj_consume_token(SCOLON);
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case IDENTIFIER:{
          jj_consume_token(IDENTIFIER);
          jj_consume_token(LSQRBRACKET);
          Expression();
          jj_consume_token(RSQRBRACKET);
          jj_consume_token(EQUALS);
          jj_consume_token(SCOLON);
          break;
          }
        default:
          jj_la1[10] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
}

  static final public void Expression() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INTEGERLITERAL:{
      jj_consume_token(INTEGERLITERAL);

      ExpressionFragment();
      break;
      }
    case TRUE:{
      jj_consume_token(TRUE);
      ExpressionFragment();
      break;
      }
    case FALSE:{
      jj_consume_token(FALSE);
      ExpressionFragment();
      break;
      }
    case IDENTIFIER:{
      jj_consume_token(IDENTIFIER);
      ExpressionFragment();
      break;
      }
    case THIS:{
      jj_consume_token(THIS);
      ExpressionFragment();
      break;
      }
    default:
      jj_la1[11] = jj_gen;
      if (jj_2_5(2)) {
        jj_consume_token(NEW);
        jj_consume_token(INT);
        jj_consume_token(LSQRBRACKET);
        Expression();
        jj_consume_token(RSQRBRACKET);
        ExpressionFragment();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case NEW:{
          jj_consume_token(NEW);
          jj_consume_token(IDENTIFIER);
          jj_consume_token(LPAR);
          jj_consume_token(RPAR);
          ExpressionFragment();
          break;
          }
        case NOT:{
          jj_consume_token(NOT);
          Expression();
          ExpressionFragment();
          break;
          }
        case LPAR:{
          jj_consume_token(LPAR);
          Expression();
          jj_consume_token(RPAR);
          ExpressionFragment();
          break;
          }
        default:
          jj_la1[12] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
      }
    }
}

  static final public void ExpressionFragment() throws ParseException {
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case AND:
    case LESS:
    case PLUS:
    case MINUS:
    case MUL:
    case DIV:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case AND:{
        jj_consume_token(AND);
        break;
        }
      case LESS:{
        jj_consume_token(LESS);
        break;
        }
      case PLUS:{
        jj_consume_token(PLUS);
        break;
        }
      case MINUS:{
        jj_consume_token(MINUS);
        break;
        }
      case MUL:{
        jj_consume_token(MUL);
        break;
        }
      case DIV:{
        jj_consume_token(DIV);
        break;
        }
      default:
        jj_la1[13] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      Expression();
      ExpressionFragment();
      break;
      }
    case LSQRBRACKET:{
      jj_consume_token(LSQRBRACKET);
      Expression();
      jj_consume_token(RSQRBRACKET);
      ExpressionFragment();
      break;
      }
    default:
      jj_la1[16] = jj_gen;
      if (jj_2_6(2)) {
        jj_consume_token(DOT);
        jj_consume_token(LENGTH);
        ExpressionFragment();
      } else {
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case DOT:{
          jj_consume_token(DOT);
          jj_consume_token(IDENTIFIER);
          jj_consume_token(LPAR);
          switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
          case NEW:
          case TRUE:
          case THIS:
          case FALSE:
          case LPAR:
          case NOT:
          case IDENTIFIER:
          case INTEGERLITERAL:{
            Expression();
            label_8:
            while (true) {
              switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
              case COMMA:{
                ;
                break;
                }
              default:
                jj_la1[14] = jj_gen;
                break label_8;
              }
              jj_consume_token(COMMA);
              Expression();
            }
            break;
            }
          default:
            jj_la1[15] = jj_gen;
            ;
          }
          jj_consume_token(RPAR);
          ExpressionFragment();
          break;
          }
        default:
          jj_la1[17] = jj_gen;

        }
      }
    }
}

  static private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_1()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_2_2(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_2()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(1, xla); }
  }

  static private boolean jj_2_3(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_3()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(2, xla); }
  }

  static private boolean jj_2_4(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_4()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(3, xla); }
  }

  static private boolean jj_2_5(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_5()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(4, xla); }
  }

  static private boolean jj_2_6(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return (!jj_3_6()); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(5, xla); }
  }

  static private boolean jj_3R_16()
 {
    if (jj_scan_token(THIS)) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  static private boolean jj_3R_15()
 {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  static private boolean jj_3R_14()
 {
    if (jj_scan_token(FALSE)) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  static private boolean jj_3R_13()
 {
    if (jj_scan_token(TRUE)) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  static private boolean jj_3R_10()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (!jj_3R_12()) return false;
    jj_scanpos = xsp;
    if (!jj_3R_13()) return false;
    jj_scanpos = xsp;
    if (!jj_3R_14()) return false;
    jj_scanpos = xsp;
    if (!jj_3R_15()) return false;
    jj_scanpos = xsp;
    if (!jj_3R_16()) return false;
    jj_scanpos = xsp;
    if (!jj_3_5()) return false;
    jj_scanpos = xsp;
    if (!jj_3R_17()) return false;
    jj_scanpos = xsp;
    if (!jj_3R_18()) return false;
    jj_scanpos = xsp;
    if (jj_3R_19()) return true;
    return false;
  }

  static private boolean jj_3R_12()
 {
    if (jj_scan_token(INTEGERLITERAL)) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  static private boolean jj_3R_9()
 {
    if (jj_3R_11()) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_3_4()
 {
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(EQUALS)) return true;
    return false;
  }

  static private boolean jj_3_3()
 {
    if (jj_3R_10()) return true;
    if (jj_scan_token(SCOLON)) return true;
    return false;
  }

  static private boolean jj_3R_24()
 {
    return false;
  }

  static private boolean jj_3R_23()
 {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    return false;
  }

  static private boolean jj_3_6()
 {
    if (jj_scan_token(DOT)) return true;
    if (jj_scan_token(LENGTH)) return true;
    return false;
  }

  static private boolean jj_3R_22()
 {
    if (jj_scan_token(LSQRBRACKET)) return true;
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3R_21()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (!jj_scan_token(29)) return false;
    jj_scanpos = xsp;
    if (!jj_scan_token(30)) return false;
    jj_scanpos = xsp;
    if (!jj_scan_token(31)) return false;
    jj_scanpos = xsp;
    if (!jj_scan_token(32)) return false;
    jj_scanpos = xsp;
    if (!jj_scan_token(33)) return false;
    jj_scanpos = xsp;
    if (jj_scan_token(34)) return true;
    if (jj_3R_10()) return true;
    return false;
  }

  static private boolean jj_3R_20()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (!jj_3R_21()) return false;
    jj_scanpos = xsp;
    if (!jj_3R_22()) return false;
    jj_scanpos = xsp;
    if (!jj_3_6()) return false;
    jj_scanpos = xsp;
    if (!jj_3R_23()) return false;
    jj_scanpos = xsp;
    if (jj_3R_24()) return true;
    return false;
  }

  static private boolean jj_3_2()
 {
    if (jj_scan_token(INT)) return true;
    if (jj_scan_token(LSQRBRACKET)) return true;
    return false;
  }

  static private boolean jj_3R_11()
 {
    Token xsp;
    xsp = jj_scanpos;
    if (!jj_3_2()) return false;
    jj_scanpos = xsp;
    if (!jj_scan_token(17)) return false;
    jj_scanpos = xsp;
    if (!jj_scan_token(9)) return false;
    jj_scanpos = xsp;
    if (jj_scan_token(37)) return true;
    return false;
  }

  static private boolean jj_3R_19()
 {
    if (jj_scan_token(LPAR)) return true;
    if (jj_3R_10()) return true;
    if (jj_scan_token(RPAR)) return true;
    return false;
  }

  static private boolean jj_3_1()
 {
    if (jj_3R_9()) return true;
    return false;
  }

  static private boolean jj_3R_18()
 {
    if (jj_scan_token(NOT)) return true;
    if (jj_3R_10()) return true;
    if (jj_3R_20()) return true;
    return false;
  }

  static private boolean jj_3R_17()
 {
    if (jj_scan_token(NEW)) return true;
    if (jj_scan_token(IDENTIFIER)) return true;
    if (jj_scan_token(LPAR)) return true;
    return false;
  }

  static private boolean jj_3_5()
 {
    if (jj_scan_token(NEW)) return true;
    if (jj_scan_token(INT)) return true;
    if (jj_scan_token(LSQRBRACKET)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public ParserTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[18];
  static private int[] jj_la1_0;
  static private int[] jj_la1_1;
  static {
	   jj_la1_init_0();
	   jj_la1_init_1();
	}
	private static void jj_la1_init_0() {
	   jj_la1_0 = new int[] {0x10000,0x40000,0x20200,0x80,0x8000000,0x20200,0x581d20,0x20200,0x581d20,0x180020,0x0,0x1c00,0x400100,0xe0000000,0x8000000,0x401d00,0xe1000000,0x0,};
	}
	private static void jj_la1_init_1() {
	   jj_la1_1 = new int[] {0x0,0x0,0x20,0x0,0x0,0x20,0x70,0x20,0x70,0x0,0x20,0x60,0x10,0x7,0x0,0x70,0x7,0x8,};
	}
  static final private JJCalls[] jj_2_rtns = new JJCalls[6];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Parser(java.io.InputStream stream) {
	  this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Parser(java.io.InputStream stream, String encoding) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser.  ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream) {
	  ReInit(stream, null);
  }
  /** Reinitialise. */
  static public void ReInit(java.io.InputStream stream, String encoding) {
	 try { jj_input_stream.ReInit(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Parser(java.io.Reader stream) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 jj_input_stream = new SimpleCharStream(stream, 1, 1);
	 token_source = new ParserTokenManager(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
	if (jj_input_stream == null) {
	   jj_input_stream = new SimpleCharStream(stream, 1, 1);
	} else {
	   jj_input_stream.ReInit(stream, 1, 1);
	}
	if (token_source == null) {
 token_source = new ParserTokenManager(jj_input_stream);
	}

	 token_source.ReInit(jj_input_stream);
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Parser(ParserTokenManager tm) {
	 if (jj_initialized_once) {
	   System.out.println("ERROR: Second call to constructor of static parser. ");
	   System.out.println("	   You must either use ReInit() or set the JavaCC option STATIC to false");
	   System.out.println("	   during parser generation.");
	   throw new Error();
	 }
	 jj_initialized_once = true;
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(ParserTokenManager tm) {
	 token_source = tm;
	 token = new Token();
	 jj_ntk = -1;
	 jj_gen = 0;
	 for (int i = 0; i < 18; i++) jj_la1[i] = -1;
	 for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  static private Token jj_consume_token(int kind) throws ParseException {
	 Token oldToken;
	 if ((oldToken = token).next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 if (token.kind == kind) {
	   jj_gen++;
	   if (++jj_gc > 100) {
		 jj_gc = 0;
		 for (int i = 0; i < jj_2_rtns.length; i++) {
		   JJCalls c = jj_2_rtns[i];
		   while (c != null) {
			 if (c.gen < jj_gen) c.first = null;
			 c = c.next;
		   }
		 }
	   }
	   return token;
	 }
	 token = oldToken;
	 jj_kind = kind;
	 throw generateParseException();
  }

  @SuppressWarnings("serial")
  static private final class LookaheadSuccess extends java.lang.Error {
    @Override
    public Throwable fillInStackTrace() {
      return this;
    }
  }
  static private final LookaheadSuccess jj_ls = new LookaheadSuccess();
  static private boolean jj_scan_token(int kind) {
	 if (jj_scanpos == jj_lastpos) {
	   jj_la--;
	   if (jj_scanpos.next == null) {
		 jj_lastpos = jj_scanpos = jj_scanpos.next = token_source.getNextToken();
	   } else {
		 jj_lastpos = jj_scanpos = jj_scanpos.next;
	   }
	 } else {
	   jj_scanpos = jj_scanpos.next;
	 }
	 if (jj_rescan) {
	   int i = 0; Token tok = token;
	   while (tok != null && tok != jj_scanpos) { i++; tok = tok.next; }
	   if (tok != null) jj_add_error_token(kind, i);
	 }
	 if (jj_scanpos.kind != kind) return true;
	 if (jj_la == 0 && jj_scanpos == jj_lastpos) throw jj_ls;
	 return false;
  }


/** Get the next Token. */
  static final public Token getNextToken() {
	 if (token.next != null) token = token.next;
	 else token = token.next = token_source.getNextToken();
	 jj_ntk = -1;
	 jj_gen++;
	 return token;
  }

/** Get the specific Token. */
  static final public Token getToken(int index) {
	 Token t = token;
	 for (int i = 0; i < index; i++) {
	   if (t.next != null) t = t.next;
	   else t = t.next = token_source.getNextToken();
	 }
	 return t;
  }

  static private int jj_ntk_f() {
	 if ((jj_nt=token.next) == null)
	   return (jj_ntk = (token.next=token_source.getNextToken()).kind);
	 else
	   return (jj_ntk = jj_nt.kind);
  }

  static private java.util.List<int[]> jj_expentries = new java.util.ArrayList<int[]>();
  static private int[] jj_expentry;
  static private int jj_kind = -1;
  static private int[] jj_lasttokens = new int[100];
  static private int jj_endpos;

  static private void jj_add_error_token(int kind, int pos) {
	 if (pos >= 100) {
		return;
	 }

	 if (pos == jj_endpos + 1) {
	   jj_lasttokens[jj_endpos++] = kind;
	 } else if (jj_endpos != 0) {
	   jj_expentry = new int[jj_endpos];

	   for (int i = 0; i < jj_endpos; i++) {
		 jj_expentry[i] = jj_lasttokens[i];
	   }

	   for (int[] oldentry : jj_expentries) {
		 if (oldentry.length == jj_expentry.length) {
		   boolean isMatched = true;

		   for (int i = 0; i < jj_expentry.length; i++) {
			 if (oldentry[i] != jj_expentry[i]) {
			   isMatched = false;
			   break;
			 }

		   }
		   if (isMatched) {
			 jj_expentries.add(jj_expentry);
			 break;
		   }
		 }
	   }

	   if (pos != 0) {
		 jj_lasttokens[(jj_endpos = pos) - 1] = kind;
	   }
	 }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
	 jj_expentries.clear();
	 boolean[] la1tokens = new boolean[39];
	 if (jj_kind >= 0) {
	   la1tokens[jj_kind] = true;
	   jj_kind = -1;
	 }
	 for (int i = 0; i < 18; i++) {
	   if (jj_la1[i] == jj_gen) {
		 for (int j = 0; j < 32; j++) {
		   if ((jj_la1_0[i] & (1<<j)) != 0) {
			 la1tokens[j] = true;
		   }
		   if ((jj_la1_1[i] & (1<<j)) != 0) {
			 la1tokens[32+j] = true;
		   }
		 }
	   }
	 }
	 for (int i = 0; i < 39; i++) {
	   if (la1tokens[i]) {
		 jj_expentry = new int[1];
		 jj_expentry[0] = i;
		 jj_expentries.add(jj_expentry);
	   }
	 }
	 jj_endpos = 0;
	 jj_rescan_token();
	 jj_add_error_token(0, 0);
	 int[][] exptokseq = new int[jj_expentries.size()][];
	 for (int i = 0; i < jj_expentries.size(); i++) {
	   exptokseq[i] = jj_expentries.get(i);
	 }
	 return new ParseException(token, exptokseq, tokenImage);
  }

  static private boolean trace_enabled;

/** Trace enabled. */
  static final public boolean trace_enabled() {
	 return trace_enabled;
  }

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
	 jj_rescan = true;
	 for (int i = 0; i < 6; i++) {
	   try {
		 JJCalls p = jj_2_rtns[i];

		 do {
		   if (p.gen > jj_gen) {
			 jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
			 switch (i) {
			   case 0: jj_3_1(); break;
			   case 1: jj_3_2(); break;
			   case 2: jj_3_3(); break;
			   case 3: jj_3_4(); break;
			   case 4: jj_3_5(); break;
			   case 5: jj_3_6(); break;
			 }
		   }
		   p = p.next;
		 } while (p != null);

		 } catch(LookaheadSuccess ls) { }
	 }
	 jj_rescan = false;
  }

  static private void jj_save(int index, int xla) {
	 JJCalls p = jj_2_rtns[index];
	 while (p.gen > jj_gen) {
	   if (p.next == null) { p = p.next = new JJCalls(); break; }
	   p = p.next;
	 }

	 p.gen = jj_gen + xla - jj_la; 
	 p.first = token;
	 p.arg = xla;
  }

  static final class JJCalls {
	 int gen;
	 Token first;
	 int arg;
	 JJCalls next;
  }

}
