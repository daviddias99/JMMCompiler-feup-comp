package jasmin;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

import java.io.File;
import java.lang.reflect.Method;
import pt.up.fe.specs.util.SpecsIo;

public class JmmCompiler {
	
    private static String CLASS_WITH_MAIN = "Main";	


    /**
     * Compiles a .jmm file to .j code with no additional arguments.
     */
    public static String compile(File jmm){
        return compile(jmm, new String[0]);
    }
	
	/**
	 * Compiles a .jmm file to .j code.
     *
     * @param jmm the .jmm file
     * @return a string with the .j code
	 */
    public static String compile(File jmm, String... args){
		// Executes J-- compiler
		try {
            // Get class with main
            Class<?> mainClass = Class.forName(CLASS_WITH_MAIN);

            // It is expected that class has a main function
            Method mainMethod = mainClass.getMethod("main", String[].class);

            // Invoke main method with file as argument
            List<String> allArgs = new ArrayList<>();
            allArgs.add(jmm.getAbsolutePath());
            allArgs.addAll(Arrays.asList(args));
            String[] mainArgs = allArgs.toArray(size -> new String[size]);
            Object[] invokeArgs = { mainArgs };
            mainMethod.invoke(null, invokeArgs);

        } catch (Exception e) {
			throw new RuntimeException("Error with compiling jmm", e);
		}

		//var filename = SpecsIo.removeExtension(jmm.getName()) + ".j";
		var filename = SpecsIo.removeExtension(jmm.getAbsolutePath()) + ".j";		
		
		var jFile = new File(filename);
				
		if(!jFile.isFile()) {
			throw new RuntimeException("Could not find file " + jFile.getAbsolutePath());
		}

		return SpecsIo.read(jFile);
	}
	
}
