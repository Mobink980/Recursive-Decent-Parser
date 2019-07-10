package topDownParsing;
import java.io.BufferedReader; //scanner
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.lang.*;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
/*In this project we implement a decent recursive parser*/

public class Parse {//get the vars and calculate their first, then create the functions
    public static String input;
    public static int index = 0;
    public static int stringNum = 0;
    private static char lookAheadToken;
    private static ArrayList<Integer> indexes = new ArrayList<Integer>();
    private static ArrayList<String> rules = new ArrayList<String>();
    private static ArrayList<String> inputs = new ArrayList<String>();
    private static ArrayList<String> outputs = new ArrayList<String>();

    public static void main(String[] args) throws IOException {
        String address1 = "C:/Users/taban/Desktop/grammar.txt";
        String address2 = "C:/Users/taban/Desktop/strings.txt";
        String address3 = "C:/Users/taban/Desktop/Results.txt";
        String text = fileReader(address1);
        String strings = fileReader(address2);

        grammarCollator(text);//organize the input files
        inputCollator(strings);


//        System.out.println(rules);
//        System.out.println(inputs);
        for (String s : inputs) {
            parse(s); //parse all the input strings
        }
        //writing the outputs in the parsing file
        fileWriter( outputs , address3);

    }

    private static void parse(String str){
        input = str; //initialization
        index = 0;
        stringNum ++;
        lookAheadToken=str.charAt(0); //next token in input program
        S();
        if(lookAheadToken == ';'){
            String string = "parse was successful!";
            outputs.add(string);
        }
        else{
            String string = "parse failed.";
            outputs.add(string);
        }
    }

    //#########################################
    //This function will organize the grammar
    private static void grammarCollator(String text){
        indexes.clear();
        char []textchar = text.toCharArray();
        for(int i=0; i< text.length(); i++){
            if(textchar[i] == '-'){
                for(int j = i; j>=0; j--){
                    if(textchar[j]>= 65 && textchar[j]<=90){
                        indexes.add(j);
                        break;
                    }
                }
            }
            if(textchar[i]==';') indexes.add(i);
        }
        for(int i =1; i <indexes.size(); i++){
            rules.add(text.substring(indexes.get(i-1),indexes.get(i)).trim());
        }
    }
    //This function will organize the input strings
    private static void inputCollator(String input){
        indexes.clear();
        char []inputchar = input.toCharArray();
        indexes.add(0); //the first of input
        for(int i=0; i<input.length(); i++){
            if(inputchar[i] == ';'){
                indexes.add(i+1);

            }
        }
        for(int i =1; i <indexes.size(); i++){
            inputs.add(input.substring(indexes.get(i-1),indexes.get(i)).trim());
        }
    }


//########################################
    //function for variable S
    private static void S(){
        if(lookAheadToken == 'a'){
            match('a'); A();
        }
        else if(lookAheadToken == 'b'){
            match('b'); S();
        }
        else syntaxError();
    }

    //function for variable A
    private static void A(){
        if(lookAheadToken == 'd' || lookAheadToken == 'g'){
            B(); match('b'); match('a');
        }
        else if(lookAheadToken == 'c') match('c');
        else syntaxError();
    }

    //function for variable B
    private static void B(){
        if(lookAheadToken == 'd'){
            match('d'); A(); B();
        }
        else if(lookAheadToken == 'g') match('g');
        else syntaxError();
    }

    //This function is for matching terminals
    private static void match(char token){
        if(token == lookAheadToken) lookAheadToken = nextToken();
        else syntaxError();
    }

    //The error procedure
    private static void syntaxError(){
        String strNum = Integer.toString(stringNum);
        String string = " An error occurred while parsing input string number: "+strNum;
        outputs.add(string);

    }

    private static char nextToken(){
        index ++;
        return input.charAt(index);
    }


//###########################################################
    //a function to read file.
    private static String fileReader(String address) throws IOException{
        FileReader file = new FileReader(address);
        BufferedReader reader = new BufferedReader(file);

        String text = "";
        String line = reader.readLine();

        while (line != null) {
            text += line;
            line = reader.readLine();
        }
        /*
         * reader.close lets know java that we are done with the file and closes
         * all the resources so it prevent from multiple errors.
         */
        reader.close();
        return text;

    }

    private static void fileWriter(ArrayList arr , String address) {
        BufferedWriter buffW;

        File newFile = new File(address);

        if (newFile.exists()) {
            System.out.println("The file already exists.");
        } else {
            try {
                newFile.createNewFile();
            } catch (Exception e) {
                e.printStackTrace();
            }

            try {
                FileWriter fileW = new FileWriter(newFile);
                buffW = new BufferedWriter(fileW);
                /*
                 * now actually we want to write in our file to do this we write
                 */
                for(int i=0; i<arr.size(); i++){
                    buffW.write(arr.get(i).toString());
                    buffW.newLine();
                }

                /* to prevent the program to constantly accessing our file */
                buffW.close();
                // success message
                System.out.println("Compiled Successfully.Please see the Results.txt");
            } catch (IOException e) {
                // it is going to print where we were at inside the code in that
                // exact time.
                e.printStackTrace();
            }
        }
    }
}


