package esteban.lopez;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.util.Objects;


@Command(name = "CLI",sortOptions = false,headerHeading = "%nUsage of this app:%n%n", footer = "%nCopyright(c) 2023 HEIG-VD - Esteban Lopez",
        descriptionHeading = "%nDescription : %n",optionListHeading = "%nOptions : %n",commandListHeading = "%nCommands : %n",description = "Modifies the content of a file and saves it on an output file")

public class CLI implements Runnable{

    @Option(names = {"-i","--inputFile"},required = true, description = "Input file where the text will be retrieved")
    File inputFile;

    @Option(names = {"-ie","--inputEncoding"},defaultValue = "UTF-8", description = "Sets the encoding for the input file. By default : ${DEFAULT-VALUE}")
    private String inputEncoding;

    @Option(names = {"-o","--outputFile"}, required = true,description = "Output file where the modified text will be saved.")
    File outputFile;

    @Option(names = {"-oe","--outputEncoding"},defaultValue = "UTF-8", description = "Sets the encoding for the output file. By default : ${DEFAULT-VALUE}")
    private String outputEncoding;

    @Option(names = "uppercase", description = "Sets the text on the input file to Uppercase and saves it on the output file")
    private boolean toUppercase;

    @Option(names = "lowercase", description = "Sets the text on the input file to Lowercase and saves it on the output file")
    private boolean toLowercase;

    @Option(names = "dictionary" ,description = "Reads the input file and creates a dictionary of it's words with the number of occurrences of each of them")
    private boolean dictionaryOption;

    @Option(names = {"-ds","--dictionarySorting"} ,defaultValue = "Alphabetical",description = "Sets the sorting method for the dictionary. By default : ${DEFAULT-VALUE}. Allowed options : Alphabetical or Occurrences")
    private String dictionarySorting;

    @Option(names = { "-h", "--help" }, usageHelp = true, description = "display a help message")
    private boolean helpRequested;

    @Override
    public void run(){
        if(!validateInput())return;

        System.out.println("Beginning lecture of input file... please wait");

        try {
            if(!outputFile.createNewFile()){
                Files.deleteIfExists(outputFile.toPath());

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile),Charset.forName(inputEncoding)));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), Charset.forName(outputEncoding)));

            Dictionary dictionary = new Dictionary();

            StringBuilder resultToOutput= new StringBuilder();
            String line;
            while ((line= reader.readLine())!= null){
                String[]wordsOfLine = line.split(" ");

                for(String word : wordsOfLine){
                    if(!word.isEmpty()) {

                        if(dictionaryOption)
                        {
                            Word newWord = new Word(word.substring(0, 1).toUpperCase() + word.substring(1));

                            dictionary.addToDictionary(newWord);

                        }
                        else if(toUppercase)
                        {
                            String wordUppercase = word.toUpperCase();
                            resultToOutput.append(wordUppercase).append(" ");
                        }
                        else if(toLowercase){
                            String wordLowercase = word.toLowerCase();
                            resultToOutput.append(wordLowercase).append(" ");
                        }
                    }
                }
            }

            if(dictionaryOption){
                resultToOutput = new StringBuilder(dictionary.showDictionary(dictionarySorting));
            }
            writer.write(resultToOutput.toString());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Input file readed and converted successfully!");
    }


    private boolean validateInput(){

        if(!dictionaryOption&!toUppercase&!toLowercase){
            System.out.println("At least one option must be chosen (dictionary,lowercase,uppercase)");
            return false;
        }

        if(dictionaryOption){
            if(!Objects.equals(dictionarySorting, "Alphabetical") && !Objects.equals(dictionarySorting, "Occurrences")){
                System.out.println("dictionary sorting method not valid, please choose again (Alphabetical or Occurrences)"+dictionarySorting);
                return false;
            }
        }
        return true;
    }
}
