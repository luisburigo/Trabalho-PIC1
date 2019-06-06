package unesc.analisador;


import java.util.Stack;

public class AnalisadorLexico {
    
    public Stack<String> gerarTokens(String programa){
        String[] tokens = programa.split(" ");
        Stack<String> pilhaTokens    = new Stack<>();
        Stack<String> pilhaInvertida = new Stack<>();
        
        for(String p : tokens){
            pilhaTokens.push(p);
        }
        
        for(int i = pilhaTokens.size() - 1; i >= 0; i--){
            pilhaInvertida.push(pilhaTokens.pop());
        }
        
        return pilhaInvertida;
    }
    
}
