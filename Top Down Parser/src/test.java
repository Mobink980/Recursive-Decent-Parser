public class test {

    public static String input = "bbbagba;";
    public static int index = 0;
    public static char lookAheadToken=input.charAt(0); //next token in input program
    public static void main(String[] args) {
      match('b');
      match('b');
      match('b');
      match('a');
      match('g');
      match('a');
      match('a');
        if(lookAheadToken == ';') print("parse was successful!");
        else print("parse failed");
    }
    public static char nextToken(){
        index ++;
        System.out.println(index);
        System.out.println(input.charAt(index));
        return input.charAt(index);
    }
    //This function is for matching terminals
    public static void match(char token){
        if(token == lookAheadToken)
            lookAheadToken = nextToken();

        else syntaxError();
    }

    //The error procedure
    public static void syntaxError(){
        print("An error has occurred while parsing the input");
    }
    public static void print(String s){
        System.out.println(s);
    }
}
