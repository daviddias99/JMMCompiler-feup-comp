/* Calculator.java */
/* Generated By:JJTree&JavaCC: Do not edit this line. Calculator.java */
import java.util.HashMap;

public class Calculator/*@bgen(jjtree)*/implements CalculatorTreeConstants, CalculatorConstants {/*@bgen(jjtree)*/
  protected static JJTCalculatorState jjtree = new JJTCalculatorState();public static HashMap<String, Integer> variables = new HashMap<String, Integer>();

 public static void main(String args[]) throws ParseException {
System.out.println("Calculator that accepts expressions with integers, +,-,*,/,(, and ).");
System.out.println("Write an arithmetic expression:");
 Calculator myCalc = new Calculator(System.in);
 SimpleNode root = myCalc.Program();
 root.dump("");

SimpleNode lastExpression = (SimpleNode) root.jjtGetChild(root.jjtGetNumChildren() - 1);

 System.out.println("Expression Value: "+myCalc.eval(lastExpression));
 }

 public static int eval(SimpleNode node) {
    if(node.jjtGetNumChildren() == 0) // leaf node with integer value
        return node.val;
    else if(node.jjtGetNumChildren() == 1) { // only one child
        return Calculator.eval((SimpleNode) node.jjtGetChild(0));
    }

    SimpleNode lhs = (SimpleNode) node.jjtGetChild(0); //left child
    SimpleNode rhs = (SimpleNode) node.jjtGetChild(1); // right child
    switch(node.id) {
        case CalculatorTreeConstants.JJTADD : return eval( lhs ) + eval( rhs );
        case CalculatorTreeConstants.JJTSUB : return eval( lhs ) - eval( rhs );
        case CalculatorTreeConstants.JJTMUL : return eval( lhs ) * eval( rhs );
        case CalculatorTreeConstants.JJTDIV : return eval( lhs ) / eval( rhs );
        default : // abort
            System.out.println("Ilegal operator!");
            System.exit(1);
    }
    return 0;
 }

  static final public SimpleNode Program() throws ParseException {/*@bgen(jjtree) Program */
                        SimpleNode jjtn000 = new SimpleNode(JJTPROGRAM);
                        boolean jjtc000 = true;
                        jjtree.openNodeScope(jjtn000);System.out.println("program");
    try {
      label_1:
      while (true) {
        Instruction();
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case LF:{
          jj_consume_token(LF);
          break;
          }
        default:
          jj_la1[0] = jj_gen;
          ;
        }
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case INTEGER:
        case SYMBOL:
        case 9:
        case 12:{
          ;
          break;
          }
        default:
          jj_la1[1] = jj_gen;
          break label_1;
        }
      }
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
{if ("" != null) return jjtn000;}
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
    throw new Error("Missing return statement in function");
  }

  static final public void Instruction() throws ParseException {System.out.println("instruction");
    if (jj_2_1(2)) {
      Expr4();
    } else {
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case INTEGER:
      case SYMBOL:
      case 9:
      case 12:{
        Expr1(1);
        break;
        }
      default:
        jj_la1[2] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
    }
    jj_consume_token(7);
  }

  static final public SimpleNode Expr1(int sign) throws ParseException {/*@bgen(jjtree) Expression */
  SimpleNode jjtn000 = new SimpleNode(JJTEXPRESSION);
  boolean jjtc000 = true;
  jjtree.openNodeScope(jjtn000);
    try {
      Expr2(sign);
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 8:
      case 9:{
        switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
        case 8:{
          jj_consume_token(8);
SimpleNode jjtn001 = new SimpleNode(JJTADD);
        boolean jjtc001 = true;
        jjtree.openNodeScope(jjtn001);
          try {
            Expr2(sign);
          } catch (Throwable jjte001) {
if (jjtc001) {
          jjtree.clearNodeScope(jjtn001);
          jjtc001 = false;
        } else {
          jjtree.popNode();
        }
        if (jjte001 instanceof RuntimeException) {
          {if (true) throw (RuntimeException)jjte001;}
        }
        if (jjte001 instanceof ParseException) {
          {if (true) throw (ParseException)jjte001;}
        }
        {if (true) throw (Error)jjte001;}
          } finally {
if (jjtc001) {
          jjtree.closeNodeScope(jjtn001,  2);
        }
          }
          break;
          }
        case 9:{
          jj_consume_token(9);
SimpleNode jjtn002 = new SimpleNode(JJTSUB);
         boolean jjtc002 = true;
         jjtree.openNodeScope(jjtn002);
          try {
            Expr2(sign);
          } catch (Throwable jjte002) {
if (jjtc002) {
           jjtree.clearNodeScope(jjtn002);
           jjtc002 = false;
         } else {
           jjtree.popNode();
         }
         if (jjte002 instanceof RuntimeException) {
           {if (true) throw (RuntimeException)jjte002;}
         }
         if (jjte002 instanceof ParseException) {
           {if (true) throw (ParseException)jjte002;}
         }
         {if (true) throw (Error)jjte002;}
          } finally {
if (jjtc002) {
           jjtree.closeNodeScope(jjtn002,  2);
         }
          }
          break;
          }
        default:
          jj_la1[3] = jj_gen;
          jj_consume_token(-1);
          throw new ParseException();
        }
        break;
        }
      default:
        jj_la1[4] = jj_gen;
        ;
      }
jjtree.closeNodeScope(jjtn000, true);
   jjtc000 = false;
{if ("" != null) return jjtn000;}
    } catch (Throwable jjte000) {
if (jjtc000) {
     jjtree.clearNodeScope(jjtn000);
     jjtc000 = false;
   } else {
     jjtree.popNode();
   }
   if (jjte000 instanceof RuntimeException) {
     {if (true) throw (RuntimeException)jjte000;}
   }
   if (jjte000 instanceof ParseException) {
     {if (true) throw (ParseException)jjte000;}
   }
   {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
     jjtree.closeNodeScope(jjtn000, true);
   }
    }
    throw new Error("Missing return statement in function");
  }

  static final public void Expr2(int sign) throws ParseException {
    Expr3(sign);
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case 10:
    case 11:{
      switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
      case 10:{
        jj_consume_token(10);
SimpleNode jjtn001 = new SimpleNode(JJTMUL);
        boolean jjtc001 = true;
        jjtree.openNodeScope(jjtn001);
        try {
          Expr3(1);
        } catch (Throwable jjte001) {
if (jjtc001) {
          jjtree.clearNodeScope(jjtn001);
          jjtc001 = false;
        } else {
          jjtree.popNode();
        }
        if (jjte001 instanceof RuntimeException) {
          {if (true) throw (RuntimeException)jjte001;}
        }
        if (jjte001 instanceof ParseException) {
          {if (true) throw (ParseException)jjte001;}
        }
        {if (true) throw (Error)jjte001;}
        } finally {
if (jjtc001) {
          jjtree.closeNodeScope(jjtn001,  2);
        }
        }
        break;
        }
      case 11:{
        jj_consume_token(11);
SimpleNode jjtn002 = new SimpleNode(JJTDIV);
         boolean jjtc002 = true;
         jjtree.openNodeScope(jjtn002);
        try {
          Expr3(1);
        } catch (Throwable jjte002) {
if (jjtc002) {
           jjtree.clearNodeScope(jjtn002);
           jjtc002 = false;
         } else {
           jjtree.popNode();
         }
         if (jjte002 instanceof RuntimeException) {
           {if (true) throw (RuntimeException)jjte002;}
         }
         if (jjte002 instanceof ParseException) {
           {if (true) throw (ParseException)jjte002;}
         }
         {if (true) throw (Error)jjte002;}
        } finally {
if (jjtc002) {
           jjtree.closeNodeScope(jjtn002,  2);
         }
        }
        break;
        }
      default:
        jj_la1[5] = jj_gen;
        jj_consume_token(-1);
        throw new ParseException();
      }
      break;
      }
    default:
      jj_la1[6] = jj_gen;
      ;
    }
  }

  static final public void Expr3(int sign) throws ParseException {Token t;
    switch ((jj_ntk==-1)?jj_ntk_f():jj_ntk) {
    case INTEGER:{
      t = jj_consume_token(INTEGER);
SimpleNode jjtn001 = new SimpleNode(JJTINTEGER);
               boolean jjtc001 = true;
               jjtree.openNodeScope(jjtn001);
      try {
jjtree.closeNodeScope(jjtn001, true);
               jjtc001 = false;
jjtn001.val = sign * Integer.parseInt(t.image);
      } finally {
if (jjtc001) {
                 jjtree.closeNodeScope(jjtn001, true);
               }
      }
      break;
      }
    case SYMBOL:{
      t = jj_consume_token(SYMBOL);
SimpleNode jjtn002 = new SimpleNode(JJTSYMBOL);
                boolean jjtc002 = true;
                jjtree.openNodeScope(jjtn002);
      try {
jjtree.closeNodeScope(jjtn002, true);
                jjtc002 = false;
if (! Calculator.variables.containsKey(t.image)) {
        System.out.println("Use of an unassigned symbol!");
        System.exit(1);
      }
     jjtn002.val = sign * Calculator.variables.get(t.image);
      } finally {
if (jjtc002) {
                  jjtree.closeNodeScope(jjtn002, true);
                }
      }
      break;
      }
    case 9:{
      jj_consume_token(9);
      Expr3(-1);
      break;
      }
    case 12:{
      jj_consume_token(12);
      Expr1(sign);
      jj_consume_token(13);
      break;
      }
    default:
      jj_la1[7] = jj_gen;
      jj_consume_token(-1);
      throw new ParseException();
    }
  }

  static final public void Expr4() throws ParseException {/*@bgen(jjtree) Assignment */
                           SimpleNode jjtn000 = new SimpleNode(JJTASSIGNMENT);
                           boolean jjtc000 = true;
                           jjtree.openNodeScope(jjtn000);Token t1; SimpleNode node;
    try {
SimpleNode jjtn001 = new SimpleNode(JJTSYMBOL);
      boolean jjtc001 = true;
      jjtree.openNodeScope(jjtn001);
      try {
        t1 = jj_consume_token(SYMBOL);
      } finally {
if (jjtc001) {
        jjtree.closeNodeScope(jjtn001, true);
      }
      }
      jj_consume_token(14);
      node = Expr1(1);
jjtree.closeNodeScope(jjtn000, true);
      jjtc000 = false;
Calculator.variables.put(t1.image, Calculator.eval(node));
    } catch (Throwable jjte000) {
if (jjtc000) {
        jjtree.clearNodeScope(jjtn000);
        jjtc000 = false;
      } else {
        jjtree.popNode();
      }
      if (jjte000 instanceof RuntimeException) {
        {if (true) throw (RuntimeException)jjte000;}
      }
      if (jjte000 instanceof ParseException) {
        {if (true) throw (ParseException)jjte000;}
      }
      {if (true) throw (Error)jjte000;}
    } finally {
if (jjtc000) {
        jjtree.closeNodeScope(jjtn000, true);
      }
    }
  }

  static private boolean jj_2_1(int xla)
 {
    jj_la = xla; jj_lastpos = jj_scanpos = token;
    try { return !jj_3_1(); }
    catch(LookaheadSuccess ls) { return true; }
    finally { jj_save(0, xla); }
  }

  static private boolean jj_3_1()
 {
    if (jj_3R_2()) return true;
    return false;
  }

  static private boolean jj_3R_2()
 {
    if (jj_scan_token(SYMBOL)) return true;
    if (jj_scan_token(14)) return true;
    return false;
  }

  static private boolean jj_initialized_once = false;
  /** Generated Token Manager. */
  static public CalculatorTokenManager token_source;
  static SimpleCharStream jj_input_stream;
  /** Current token. */
  static public Token token;
  /** Next token. */
  static public Token jj_nt;
  static private int jj_ntk;
  static private Token jj_scanpos, jj_lastpos;
  static private int jj_la;
  static private int jj_gen;
  static final private int[] jj_la1 = new int[8];
  static private int[] jj_la1_0;
  static {
      jj_la1_init_0();
   }
   private static void jj_la1_init_0() {
      jj_la1_0 = new int[] {0x20,0x1250,0x1250,0x300,0x300,0xc00,0xc00,0x1250,};
   }
  static final private JJCalls[] jj_2_rtns = new JJCalls[1];
  static private boolean jj_rescan = false;
  static private int jj_gc = 0;

  /** Constructor with InputStream. */
  public Calculator(java.io.InputStream stream) {
     this(stream, null);
  }
  /** Constructor with InputStream and supplied encoding */
  public Calculator(java.io.InputStream stream, String encoding) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser.  ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    try { jj_input_stream = new SimpleCharStream(stream, encoding, 1, 1); } catch(java.io.UnsupportedEncodingException e) { throw new RuntimeException(e); }
    token_source = new CalculatorTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
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
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor. */
  public Calculator(java.io.Reader stream) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    jj_input_stream = new SimpleCharStream(stream, 1, 1);
    token_source = new CalculatorTokenManager(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  static public void ReInit(java.io.Reader stream) {
    jj_input_stream.ReInit(stream, 1, 1);
    token_source.ReInit(jj_input_stream);
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Constructor with generated Token Manager. */
  public Calculator(CalculatorTokenManager tm) {
    if (jj_initialized_once) {
      System.out.println("ERROR: Second call to constructor of static parser. ");
      System.out.println("       You must either use ReInit() or set the JavaCC option STATIC to false");
      System.out.println("       during parser generation.");
      throw new Error();
    }
    jj_initialized_once = true;
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
    for (int i = 0; i < jj_2_rtns.length; i++) jj_2_rtns[i] = new JJCalls();
  }

  /** Reinitialise. */
  public void ReInit(CalculatorTokenManager tm) {
    token_source = tm;
    token = new Token();
    jj_ntk = -1;
    jjtree.reset();
    jj_gen = 0;
    for (int i = 0; i < 8; i++) jj_la1[i] = -1;
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
  static private final class LookaheadSuccess extends java.lang.Error { }
  static final private LookaheadSuccess jj_ls = new LookaheadSuccess();
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
    if (pos >= 100) return;
    if (pos == jj_endpos + 1) {
      jj_lasttokens[jj_endpos++] = kind;
    } else if (jj_endpos != 0) {
      jj_expentry = new int[jj_endpos];
      for (int i = 0; i < jj_endpos; i++) {
        jj_expentry[i] = jj_lasttokens[i];
      }
      jj_entries_loop: for (java.util.Iterator<?> it = jj_expentries.iterator(); it.hasNext();) {
        int[] oldentry = (int[])(it.next());
        if (oldentry.length == jj_expentry.length) {
          for (int i = 0; i < jj_expentry.length; i++) {
            if (oldentry[i] != jj_expentry[i]) {
              continue jj_entries_loop;
            }
          }
          jj_expentries.add(jj_expentry);
          break jj_entries_loop;
        }
      }
      if (pos != 0) jj_lasttokens[(jj_endpos = pos) - 1] = kind;
    }
  }

  /** Generate ParseException. */
  static public ParseException generateParseException() {
    jj_expentries.clear();
    boolean[] la1tokens = new boolean[15];
    if (jj_kind >= 0) {
      la1tokens[jj_kind] = true;
      jj_kind = -1;
    }
    for (int i = 0; i < 8; i++) {
      if (jj_la1[i] == jj_gen) {
        for (int j = 0; j < 32; j++) {
          if ((jj_la1_0[i] & (1<<j)) != 0) {
            la1tokens[j] = true;
          }
        }
      }
    }
    for (int i = 0; i < 15; i++) {
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

  /** Enable tracing. */
  static final public void enable_tracing() {
  }

  /** Disable tracing. */
  static final public void disable_tracing() {
  }

  static private void jj_rescan_token() {
    jj_rescan = true;
    for (int i = 0; i < 1; i++) {
    try {
      JJCalls p = jj_2_rtns[i];
      do {
        if (p.gen > jj_gen) {
          jj_la = p.arg; jj_lastpos = jj_scanpos = p.first;
          switch (i) {
            case 0: jj_3_1(); break;
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
    p.gen = jj_gen + xla - jj_la; p.first = token; p.arg = xla;
  }

  static final class JJCalls {
    int gen;
    Token first;
    int arg;
    JJCalls next;
  }

}
