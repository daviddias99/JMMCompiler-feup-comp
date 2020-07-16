import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import exceptions.SemanticParseException;

public class Main {
    static String filePath = null;
    static String destDir = "";
    static boolean genCode = true;
    static boolean showAst = false;
    static boolean showST = false;
    static int regAloc = 0;
    static boolean errorOptR = false;
    static boolean genCodeAlways = false;
    static boolean optimizationO = false;
    static Parser parser;
    
    private static void init(){
         filePath = null;
         destDir = "";
         genCode = true;
         showAst = false;
         showST = false;
         regAloc = 0;
         errorOptR = false;
         genCodeAlways = false;
         optimizationO = false;
         parser = null;
    }

    public static void main(String[] args) throws ParseException, SemanticParseException, RuntimeException {
        init();
        parseArgs(args);

        InputStream in = null;
        String fileName = null;
        try {
            in = new FileInputStream(filePath);
            File f = new File(filePath);
            fileName = f.getName();
        } catch (FileNotFoundException e) {
            System.out.println(filePath + " file not found");
            return;
        }

        String generatedFileName = fileName.substring(0, fileName.indexOf(".", 0)) + ".j";

        parser = new Parser(in);
        parser.initFile(filePath);

        // Sintatic Analysis
        SimpleNode root = parser.Program();
        if (showAst)
            root.dump("");

        // Semantic Analysis
        parser.startSemanthicAnalysis();

        // Do -o optimization
        if (optimizationO) {
            parser.setOptimizationO();
            parser.optimizeO();
        }

        // Do -r optimization
        if (regAloc > 0)
            parser.optimizeR();

        if (showAst && optimizationO)
            root.dump("");

        // Error if -r optimization fails with given register value
        if (errorOptR && !genCodeAlways)
            throw new RuntimeException("Error in some methods; maximum number of registers not enough");

        if (genCode)
            parser.startCodeGeneration(generatedFileName, destDir);
    }

    /** Pass arguments */
    public static void parseArgs(String[] args) {

        if (args.length > 6) {
            System.out.println("Invalid number of arguments.");
            System.exit(-1);
        }

        for (int i = 0; i < args.length; i++) {
            switch (args[i].charAt(0)) {
                case '-':
                    if (args[i].equals("-d")) {
                        if (args.length <= i + 1) {
                            System.out.println("Invalid value for option -d");
                            System.exit(-1);
                        } else {
                            destDir = args[i + 1];
                            if (destDir.charAt(destDir.length() - 1) != '/') {
                                destDir += "/";
                            }
                            i++;
                        }
                    } else if (args[i].equals("-o") || args[i].equals("-O")) {
                        optimizationO = true;
                    } else if (args[i].equals("-x")) {
                        genCode = false;
                    } else if (args[i].equals("--ast") || args[i].equals("-t")) {
                        showAst = true;
                    } else if (args[i].equals("-s")) {
                        showST = true;
                    } else if (args[i].length() >= 4 && args[i].charAt(1) == 'r') {
                        if (!(args[i].charAt(2) == '=')) {
                            System.out.println("Invalid value for option -r (correct use is -r=<n>, n being an integer equal or higher than 1), or n being 'min'");
                            System.exit(-1);
                        }

                        String argR = args[i].substring(3);

                        try {
                            int value = Integer.parseInt(argR);
                            if (value < 1) {
                                System.out.println("Invalid value for option -r (correct use is -r=<n>, n being an integer equal or higher than 1), or n being 'min'");
                                System.exit(-1);
                            }
                            regAloc = value;
                        } catch(NumberFormatException e) {
                            if (argR.equals("min")) {
                                regAloc = 1;
                                genCodeAlways = true;
                            }
                            else {
                                System.out.println("Invalid value for option -r (correct use is -r=<n>, n being an integer equal or higher than 1), or n being 'min'");
                                System.exit(-1);
                            }
                        }
                    } else {
                        System.out.println("Invalid option " + args[i]);
                        System.exit(-1);
                    }
                    break;
                default:
                    filePath = args[i];
                    break;
            }
        }

        if (regAloc < 1 && genCodeAlways) {
            System.out.println("Invalid option -b: can only be used alongside -r=<num>");
            System.exit(-1);
        }

    }
}