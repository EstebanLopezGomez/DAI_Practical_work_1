package esteban.lopez;

import picocli.CommandLine;
import java.io.File;


public class CLI {
    @CommandLine.Parameters(paramLabel = "-i",description = "input  file")
    File inputFile;

    @CommandLine.Option(names = "-o",description = "output  file")
    File outputFile;

}
