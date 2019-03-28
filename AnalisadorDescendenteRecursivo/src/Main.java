import  java.util.Scanner;

public class Main {

    public static void main(String [] args){
        Parser analisador_descendente = new Parser();
        Scanner input = new Scanner(System.in);

        System.out.println("Insira uma palavra: ");
        String token = input.nextLine();

        analisador_descendente.start(token);
    }
}
