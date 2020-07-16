package jasmin;


import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class JasminTest {
	@Test
    public void testFindMaximum() {
		List<String> fileDependencies = new ArrayList<>();
		fileDependencies.add("io.class");

		JasminUtils.testJmm("fixtures/public/FindMaximum.jmm", "28", fileDependencies);
    }

	@Test
    public void testHelloWorld() {
		List<String> fileDependencies = new ArrayList<>();
		fileDependencies.add("ioPlus.class");

		JasminUtils.testJmm("fixtures/public/HelloWorld.jmm", "Hello World!", fileDependencies);
    }

	@Test
    public void testMonteCarloPi() {
		List<String> fileDependencies = new ArrayList<>();
		fileDependencies.add("io.class");

		JasminUtils.testJmm("fixtures/public/MonteCarloPi.jmm", " 0", "-1\n", fileDependencies);
    }

	@Test
    public void testQuickSort() {
		List<String> fileDependencies = new ArrayList<>();
		fileDependencies.add("io.class");

		JasminUtils.testJmm("fixtures/public/QuickSort.jmm", JasminUtils.getResource("fixtures/public/QuickSort.txt"), fileDependencies);
    }

	@Test
    public void testSimple() {
		List<String> fileDependencies = new ArrayList<>();
		fileDependencies.add("io.class");

		JasminUtils.testJmm("fixtures/public/Simple.jmm", "30", fileDependencies);
    }
	

	@Test
    public void testTicTacToe() {
		List<String> fileDependencies = new ArrayList<>();
		fileDependencies.add("io.class");
		fileDependencies.add("BoardBase.class");

		JasminUtils.testJmm("fixtures/public/TicTacToe.jmm", JasminUtils.getResource("fixtures/public/TicTacToe.txt"), JasminUtils.getResource("fixtures/public/TicTacToe.input"), fileDependencies);
	}

	@Test
    public void testWhileAndIF() {
		List<String> fileDependencies = new ArrayList<>();
		fileDependencies.add("io.class");

		JasminUtils.testJmm("fixtures/public/WhileAndIF.jmm", JasminUtils.getResource("fixtures/public/WhileAndIF.txt"), fileDependencies);
    }


}
