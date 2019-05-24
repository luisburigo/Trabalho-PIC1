package unesc.analisador;


import java.util.Stack;
import java.util.Collections;

public class AnalisadorLexico {

    public Stack<String> gerarTokens(String programa) {
        String[] tokens = programa.split(" ");
        Stack<String> pilhaTokens = new Stack<>();
        Stack<String> pilhaInvertida = new Stack<>();
        
        for (int i = 0; i < tokens.length; i++) {
            String token = tokens[i];
            pilhaTokens.push(token);
        }
        
        for (int i = pilhaTokens.size(); i > 0; i--) {
            pilhaInvertida.push(pilhaTokens.pop());
        }
        
        return pilhaInvertida;
    }

}
