package esteban.lopez;

import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.Locale;
import java.util.Scanner;
import java.util.Vector;


@Command(name = "CLI",sortOptions = false,headerHeading = "%nUsage of this app:%n%n", footer = "%nCopyright(c) 2023 HEIG-VD - Esteban Lopez",
        descriptionHeading = "%nDescription : %n",optionListHeading = "%nOptions : %n",commandListHeading = "%nCommands : %n",description = "Modifies the content of a file and saves it on an output file")

public class CLI implements Runnable{

    String specialChars = "!@#$%^&*(),.";

    @Option(names = {"-i","--inputFile"},required = true, description = "Input file where the text will be retrieved")
    File inputFile;

    @Option(names = {"-ie","--inputEnconding"},defaultValue = "UTF-8", description = "Sets the encoding for the input file. By default : ${DEFAULT-VALUE}")
    private String inputEncoding;

    //@Option(names = {"-ie","-inputFileEncoding"},defaultValue = "UTF-8", description = "Defines the encoding of the input file, default : ")



    @Option(names = {"-o","--outputFile"}, required = true, defaultValue = "output.txt",description = "Output file where the modified text will be saved. By default : ${DEFAULT_VALUE}")
    File outputFile;

    @Option(names = {"-oe","--outputEnconding"},defaultValue = "UTF-8", description = "Sets the encoding for the output file. By default : ${DEFAULT-VALUE}")
    private String outputEncoding;

    @Option(names = "uppercase", description = "Sets the text on the input file to Uppercase and saves it on the output file")
    private boolean toUppercase;

    @Option(names = "lowercase", description = "Sets the text on the input file to Lowercase and saves it on the output file")
    private boolean toLowercase;

    @Option(names = "dictionnary", description = "Reads the input file and creates a dictionnary of it's words with the number of occurences of each of them")
    private boolean dictionnaryOption;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested;



    @Override
    public void run(){
        System.out.println("Début de la lecture, veuillez patienter...\n");

        try {
            if(!outputFile.createNewFile()){
                Files.deleteIfExists(outputFile.toPath());

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile),Charset.forName(inputEncoding)));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile),Charset.forName(outputEncoding)));


            Dictionnary dictionnary = new Dictionnary();
            Dictionnary specialCharDictionnary = new Dictionnary();


            String line;
            while ((line= reader.readLine())!= null){
                line = line.replaceAll("[^a-zA-Z]"," ");
                String[]wordsOfLine = line.split(" ");

                for(String word : wordsOfLine){
                    if(word.length()>1) {

                        Word newWord = new Word(word.substring(0, 1).toUpperCase() + word.substring(1));

                        if (specialChars.contains(word)) {
                            specialCharDictionnary.addToDictionnary(newWord);
                        } else {
                            dictionnary.addToDictionnary(newWord);
                        }
                    }
                }
            }
            writer.write(dictionnary.showDictionnary());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Fichier lu et converti");


    }

    public void putToUppercase(){
    }


}
