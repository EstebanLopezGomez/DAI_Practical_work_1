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

    /**
     * Main function of the CLI, executes the reading/writing of the files by the chosen options
     */
    @Override
    public void run(){
        if(!validateInput())return;

        System.out.println("Beginning lecture of input file... please wait");

        //Starts the clock
        long clock_start = System.currentTimeMillis();

        /**
         * Try catch for the creation/delete of the output file if existant
         */
        try {
            if(!outputFile.createNewFile()){
                Files.deleteIfExists(outputFile.toPath());

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /**
         * Writing/reading of files with chosen option
         */
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(inputFile),Charset.forName(inputEncoding)));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(outputFile), Charset.forName(outputEncoding)));

            Dictionary dictionary = new Dictionary();

            //String where the result text will be saved
            StringBuilder resultToOutput= new StringBuilder();
            String line;

            //While there's a line to read...
            while ((line= reader.readLine())!= null){

                //Splits the line by words
                String[]wordsOfLine = line.split(" ");

                //For each word in the line...
                for(String word : wordsOfLine){
                    if(!word.isEmpty()) {

                        //Make dictionary
                        if(dictionaryOption)
                        {
                            Word newWord = new Word(word.substring(0, 1).toUpperCase() + word.substring(1));

                            dictionary.addToDictionary(newWord);

                        }
                        //Put word to uppercase
                        else if(toUppercase)
                        {
                            String wordUppercase = word.toUpperCase();
                            resultToOutput.append(wordUppercase).append(" ");
                        }
                        //Put to lowercase
                        else if(toLowercase){
                            String wordLowercase = word.toLowerCase();
                            resultToOutput.append(wordLowercase).append(" ");
                        }
                    }
                }
            }
            //To show the dictionary we have to call an especial method from the class Dictionary
            if(dictionaryOption){
                resultToOutput = new StringBuilder(dictionary.showDictionary(dictionarySorting));
            }
            writer.write(resultToOutput.toString());
            writer.close();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        long clock_end = System.currentTimeMillis();
        long time_elapsed = clock_end-clock_start;
        System.out.print("Input file readed and converted successfully in : ");
        if(time_elapsed>1000){
            time_elapsed=time_elapsed/1000;
            System.out.println(time_elapsed+" seconds");
        }
        else
        {
            System.out.println(time_elapsed+" miliseconds");
        }

    }


    /**
     * Tests for the CLI inputs to see if at least one of the options is chosen and tests if
     * the dictionary sorting written by usr is valid
     * @return false if there's an error with an input
     */
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
