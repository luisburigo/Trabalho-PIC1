package unesc.analisador;


import java.util.Stack;

public class AnalisadorLexico {

    public Stack<String> gerarTokens(String programa) {
        String[] tokens = programa.split(" ");
        Stack<String> pilhaTokens = new Stack<>();

        for (int i = tokens.length - 1; i >= 0; i--) {
            String token = tokens[i];

            if (!token.isEmpty()){
                pilhaTokens.push(token);
            }
        }
        
//        while(!pilhaTokens.isEmpty()) {
//            System.out.println("[" + pilhaTokens.pop() + "]");
//        }

        return pilhaTokens;
    }

}
