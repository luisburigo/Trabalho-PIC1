package unesc.analisador;


import java.util.Stack;

public class AnalisadorLexico {

    public Stack<String> gerarTokens(String programa) {
        String[] tokens = programa.split(" ");
        Stack<String> pilhaTokens = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];
            pilhaTokens.push(token);
        }

        return pilhaTokens;
    }

}
