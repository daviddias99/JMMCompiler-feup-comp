import static org.junit.Assert.*;

import org.junit.Test;
import static org.junit.Assert.*;

import exceptions.SemanticParseException;

import java.io.File;
import java.lang.reflect.Method;


public class ParserTest {

	private static String CLASS_WITH_MAIN = "Main";

    public static void test(String jmmResource, boolean mustFail) {
		test(jmmResource, mustFail, null);
	}

    public static void test(String jmmResource, boolean mustFail, Integer expectedErrorCount) {
        test(jmmResource, mustFail, expectedErrorCount, false);
    }

    public static void test(String jmmResource, boolean mustFail, Integer expectedErrorCount, boolean syntacticalOnly) {
		test(jmmResource, mustFail, expectedErrorCount, syntacticalOnly, null);
	}
	
    public static void test(String jmmResource, boolean mustFail, Integer expectedErrorCount, boolean syntacticalOnly, String expected) {
        // Copy contents of resource to a temporary file
        File tempFolder = CompUtils.getTempFolder("comp_jmm_test");
        File testFile = CompUtils.resourceCopy(jmmResource, tempFolder);

        boolean success = true;

        try {

            String[] mainArgs = { testFile.getAbsolutePath(), "-x" };
			
			
			// Get class with main
            Class<?> mainClass = Class.forName(CLASS_WITH_MAIN);

            // It is expected that class has a main function
            Method mainMethod = mainClass.getMethod("main", String[].class);

            // Invoke main method with file as argument
            Object[] invokeArgs = { mainArgs };
            mainMethod.invoke(null, invokeArgs);
			
            //Main.main(mainArgs);

        } 
		/*
		catch (ParseException e) {

            System.out.println("Parsing failed failed: ");
            e.printStackTrace();

            if (expectedErrorCount != null && e.getErrorCount() != expectedErrorCount && mustFail) {
                success = true;
            } else {
                success = false;
            }

        }
		*/
		/*
		catch (SemanticParseException e) {
            e.printStackTrace();
            if (! syntacticalOnly) {
                if (e.getErrorCount() == 0 && mustFail) {
                    success = true;
                } else {
                    success = false;
                }
            }
        } 
		*/
		catch (Exception e) {
			success = false;
			if(expected == null) {
				expected = e.getMessage();
			}	
			/*
			if(!mustFail) {
				fail(e.getMessage());
			else {
				success = true;
            }
			*/

        } finally {
            // Clean-up
            testFile.delete();
        }

        // Flip result, in case failure is needed
        if (mustFail) {
            success = !success;
        }

        if (!success) {
            if (mustFail) {
                System.out.println("Expected parser to throw exception");
            } else {
                System.out.println("Expected parser to complete successfully");
            }
			
			if(expected == null) {
				expected = mustFail ? "Expected parser to throw exception" : "Expected parser to complete successfully";
			}	
			
			fail(expected);	
        }
    }

    @Test
    public void testPos1() {
        test("jmm/pos_1.jmm", false, 0);
    }

    @Test
    public void testPos2() {
        test("jmm/pos_2.jmm", false, 0);
    }

    @Test
    public void testPos3() {
        test("jmm/pos_3.jmm", false, 0);
    }

    @Test
    public void emptyMethodAndOthers_4() {
        test("jmm/pos_4.jmm", false, 0);
    }

    @Test
    public void variableOrder_5() {
        test("jmm/pos_5.jmm", false, 0);
    }

    @Test
    public void statementOrder_6() {
        test("jmm/pos_6.jmm", false, 0);
    }

    @Test
    public void nvar_nstat_7() {
        test("jmm/pos_7.jmm", false, 0);
    }

    @Test
    public void idvar_idstat_8() {
        test("jmm/pos_8.jmm", false, 0);
    }

    @Test
    public void nvar_idstat_9() {
        test("jmm/pos_9.jmm", false, 0);
    }

    @Test
    public void idvar_nstat_10() {
        test("jmm/pos_10.jmm", false, 0);
    }

    @Test
    public void testNeg1() {
        test("jmm/neg_1.jmm", true, 1);
    }

    @Test
    public void testPos11() {
        test("jmm/pos_11.jmm", false, 0);
    }

    @Test
    public void expressionStartingWithArray() {
        test("jmm/exp_array_dot.jmm", false, 0);
    }

    @Test
    public void expressionStartingWithArray2() {
        test("jmm/exp_array_dot2.jmm", false, 0, true);
    }

    @Test
    public void invalidExpression() {
        test("jmm/exp_fail.jmm", true, 1);
    }

    @Test
    public void invalidExpression2() {
        test("jmm/exp_fail2.jmm", true, 1);
    }

    @Test
    public void testErrorRecovery1() {
        test("jmm/error_rec_1.jmm", true, 1);
    }

    @Test
    public void testErrorRecovery2() {
        test("jmm/error_rec_2.jmm", true, 1);
    }

    @Test
    public void testErrorRecovery3() {
        test("jmm/error_rec_3.jmm", true, 1);
    }

    @Test
    public void testErrorRecovery4() {
        test("jmm/error_rec_4.jmm", true, 3);
    }

    @Test
    public void testErrorRecovery5() {
        test("jmm/error_rec_5.jmm", true, 2);
    }

    @Test
    public void fail_nstat_idvar_2() {
        test("jmm/neg_2.jmm", true, 1);
    }

    @Test
    public void fail_nstat_nvar_3() {
        test("jmm/neg_3.jmm", true, 1);
    }

    @Test
    public void fail_idstat_idvar_4() {
        test("jmm/neg_4.jmm", true, 1);
    }

    @Test
    public void fail_idstat_nvar_5() {
        test("jmm/neg_5.jmm", true, 1);
    }

    @Test
    public void testFindMaximum() {
        test("fixtures/public/FindMaximum.jmm", false, 0);
    }

    @Test
    public void testHelloWorld() {
        test("fixtures/public/HelloWorld.jmm", false, 0);
    }

    @Test
    public void testLazysort() {
        test("fixtures/public/Lazysort.jmm", false, 0);
    }

    @Test
    public void testLife() {
        test("fixtures/public/Life.jmm", false, 0);
    }

    @Test
    public void testMonteCarloPi() {
        test("fixtures/public/MonteCarloPi.jmm", false, 0);
    }

    @Test
    public void testQuickSort() {
        test("fixtures/public/QuickSort.jmm", false, 0);
    }

    @Test
    public void testSimple() {
        test("fixtures/public/Simple.jmm", false, 0);
    }

    @Test
    public void testTicTacToe() {
        test("fixtures/public/TicTacToe.jmm", false, 0);
    }

    @Test
    public void testWhileAndIF() {
        test("fixtures/public/WhileAndIF.jmm", false, 0);
    }

    @Test
    public void testBlowUp() {
        test("fixtures/public/fail/syntactical/BlowUp.jmm", true, 4);
    }

    @Test
    public void testCompleteWhileTest() {
        test("fixtures/public/fail/syntactical/CompleteWhileTest.jmm", true, 11);
    }

    @Test
    public void testLengthError() {
        test("fixtures/public/fail/syntactical/LengthError.jmm", true, 1);
    }

    @Test
    public void testMissingRightPar() {
        test("fixtures/public/fail/syntactical/MissingRightPar.jmm", true, 1);
    }

    @Test
    public void testMultipleSequential() {
        test("fixtures/public/fail/syntactical/MultipleSequential.jmm", true, 2);
    }

    @Test
    public void testNestedLoop() {
        test("fixtures/public/fail/syntactical/NestedLoop.jmm", true, 2);
    }

    @Test
    public void testMethodCall() {

        test("jmm/method_call.jmm", false, 0);
    }

    @Test
    public void testarr_index_not_int() {
        test("fixtures/public/fail/semantic/arr_index_not_int.jmm", true, 1, false);
    }

    @Test
    public void testarr_size_not_int() {
        test("fixtures/public/fail/semantic/arr_size_not_int.jmm", true, 1, false);
    }

    @Test
    public void testbadArguments() {
        test("fixtures/public/fail/semantic/badArguments.jmm", true, 1, false);
    }

    @Test
    public void testbinop_incomp() {
        test("fixtures/public/fail/semantic/binop_incomp.jmm", true, 1, false);
    }

    @Test
    public void testfuncNotFound() {
        test("fixtures/public/fail/semantic/funcNotFound.jmm", true, 1, false);
    }

    @Test
    public void testsimple_length() {
        test("fixtures/public/fail/semantic/simple_length.jmm", true, 1, false);
    }

    @Test
    public void testvar_exp_incomp() {
        test("fixtures/public/fail/semantic/var_exp_incomp.jmm", true, 1, false);
    }

    @Test
    public void testvar_lit_incomp() {
        test("fixtures/public/fail/semantic/var_lit_incomp.jmm", true, 1, false);
    }

    @Test
    public void testvar_undef() {
        test("fixtures/public/fail/semantic/var_undef.jmm", true, 1, false);
    }

    @Test
    public void testvarNotInit() {
        test("fixtures/public/fail/semantic/varNotInit.jmm", true, 1, false);
    }

    @Test
    public void testmiss_type() {
        test("fixtures/public/fail/semantic/extra/miss_type.jmm", true, 1, false);
    }

}
