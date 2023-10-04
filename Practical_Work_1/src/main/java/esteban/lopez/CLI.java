package esteban.lopez;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;


@Command(name = "CLI",sortOptions = false,headerHeading = "%nUsage of this app:%n%n", footer = "%nCopyright(c) 2023 HEIG-VD - Esteban Lopez",
        descriptionHeading = "%nDescription : %n",optionListHeading = "%nOptions : %n",commandListHeading = "%nCommands : %n",description = "Modifies the content of a file and saves it on an output file")

public class CLI implements Runnable{
    @Option(names = {"-i","--inputFile"},required = true, description = "Input file where the text will be retrieved")
    File inputFile;

    //@Option(names = {"-ie","-inputFileEncoding"},defaultValue = "UTF-8", description = "Defines the encoding of the input file, default : ")



    @Option(names = {"-o","--outputFile"}, required = true, defaultValue = "output.txt",description = "Output file where the modified text will be saved. Default  value = {DEFAULT_VALUE}")
    File outputFile;

    @Option(names = "uppercase", description = "Sets the text on the input file to Uppercase and saves it on the output file")
    private boolean toUppercase;

    @Option(names = "lowercase", description = "Sets the text on the input file to Lowercase and saves it on the output file")
    private boolean toLowercase;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested;



    @Override
    public void run(){
        System.out.println("test");

        try {
            Files.deleteIfExists(outputFile.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            Scanner scanner = new Scanner(inputFile);
            FileWriter writer = new FileWriter(outputFile);

            Vector<Word> dictionnary = new Vector<Word>();



            while (scanner.hasNextLine()){
                String line = scanner.nextLine();

                String[]wordsOfLine = line.split(" ");

                for(String word : wordsOfLine){
                    Word newWord = new Word(word.substring(0,1).toUpperCase()+word.substring(1));

                    if(dictionnary.equals(newWord))
                    {
                        System.out.println("got it");

                    }
                    else
                    {
                        System.out.println("new");

                        dictionnary.add(newWord);
                    }

                    writer.write(newWord.getText()+" ");

                }
            }
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    public void putToUppercase(){
    }


}
