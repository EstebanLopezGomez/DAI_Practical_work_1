package esteban.lopez;

import picocli.CommandLine;

public class Main {
    public static void main(String[] args) {


        int exitCode = new CommandLine(new CLI()).execute(args);
        System.exit(exitCode);

    }
}