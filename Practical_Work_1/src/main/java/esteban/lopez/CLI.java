package esteban.lopez;

import picocli.CommandLine.Option;
import picocli.CommandLine.Parameters;
import picocli.CommandLine.Command;
import java.io.File;
import java.io.InputStream;

public class CLI implements Runnable{
    @Parameters(paramLabel = "-i",description = "input  file")
    File inputFile;

    @Option(names = "-o",description = "output  file")
    File outputFile;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested;

    @Override
    public void run(){
        System.out.println("test");
    }


}
