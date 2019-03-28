/*
    SYMBOLOS:
        | -> "ou"
        & -> "e"
        || -> Semara multiplas regras de derivação para o mesmo simbolo não terminal
        1 -> "Verdadeiro"
        0 -> "Falso"
        ~ -> "not"
        # -> Fim da palavra (Apenas se o usupario não digitar)


    Linguagem:
    bexpr -> btermo bexpr1
    bexpr1 -> "|" btermo bexpr!
            || epslon
    btermo -> bfator btermo1
    btermo1 -> "&" bfator btermo1
             || epslon
    bfator -> "~" bfator
            || "(" bexpr ")"
            || "1"
            || "0"
* */

import java.lang.Exception;

//System.out.println("False");
//
public class Parser {

    private String cadeia;
    private char token;
    private int pos;

    public Parser() {
        this.pos = 0;
        this.cadeia = "";
    }

    public String getCadeia() {
        return cadeia;
    }

    public void setCadeia(String cadeia) {
        this.cadeia = cadeia;
    }

    public char getToken() {
        return token;
    }

    public void setToken(char token) {
        this.token = token;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public void error() throws RuntimeException {
        throw new RuntimeException("Error: Cadeia inválida, o caractere " + cadeia.charAt(pos)
                + " não é um símbolo válido em " +  cadeia.substring(0, cadeia.length() - 1));
    }

    public void errorEndSymbol() throws RuntimeException {
        throw new RuntimeException("Error: Cadeia inválida, caractere " + cadeia.charAt(cadeia.length() - 2)
                + " não é um símbolo válido em " +  cadeia.substring(0, cadeia.length() - 1));
    }

    private boolean validTerminal(char marca){
        if(marca == '1'){
            return true;
        }

        if (marca == '0'){
            return true;
        }

        if(marca == '|'){
            return true;
        }

        if(marca == '~'){
            return true;
        }

        if(marca == '&'){
            return true;
        }

        if(marca == ')'){
            return true;
        }

        if(marca == ')'){
            return true;
        }

        if(marca == '#'){
            return true;
        }
        return false;
    }

    public void march(char marcaAtual){
        if(marcaAtual == this.token){
            pos++;
            token = cadeia.charAt(pos);
        }
    }

    public void bexpr(){
        btermo();
        bexpr1();

    }

    public void bfator(){
        if(token == '~'){
            march('~');
            bfator();
            return;
        }
        else if(token == '('){
            march('(');
            bexpr();
            if(token == ')'){
                march(')');
                return;
            }
            else error();
        }
        else if(token == '0'){
            march('0');
            return;
        }
        else if(token == '1'){
            march('1');
        }
        else
            error();
    }


    public void btermo(){
        bfator();
        btermo1();
    }

    public void btermo1(){
        if(token == '&'){
            march('&');
            bfator();
            btermo1();

        }else{
            if(validTerminal(token))
                return;
            else
                error();
        }
    }

    public void bexpr1(){
        if(token == '|'){
            march('|');
            btermo();
            bexpr1();

        }else{
            if (validTerminal(token)){
                return;
            }
            else
                error();
        }
    }

    public boolean validarEntrada(String cadeia1){
        if(validTerminal(cadeia1.charAt(cadeia1.length() - 1))){
            return true;
        }
        return false;
    }

    public void start(String word){
        boolean tokenEndInvalid = validarEntrada(word);

        this.setCadeia(word + '#');
        token = cadeia.charAt(pos);

        bexpr();

        if(tokenEndInvalid){
            errorEndSymbol();
        }
        else {
            System.out.println("Operação realizada com sucesso");
        }
    }

}
