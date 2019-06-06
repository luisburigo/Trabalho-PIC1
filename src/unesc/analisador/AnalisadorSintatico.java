package unesc.analisador;


import unesc.gramatica.Gramatica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AnalisadorSintatico {

    private final Map<String, Integer> linguagem = Gramatica.LINGUAGEM;
    private final Map<String, String> gramatica = Gramatica.GRAMATICA;
    private Stack<Integer> identificadores = new Stack<>();

    public void analisar(Stack<String> tokens) {
        Stack<Integer> pilhaInteiros = new Stack<>();
        
        this.armazenaIdentificadores(tokens);
        this.verificaPrimeiroIdentificador();
        
        System.out.println("É um terminal");
    }

    private void armazenaIdentificadores(Stack<String> tokens){
        for (int i = 0; i < tokens.size(); i++) {
            this.identificadores.add(linguagem.get(tokens.pop()));
        }
    }
    
    private void verificaPrimeiroIdentificador(){
        if(identificadores.get(0) != linguagem.get("PROGRAM")) {
            throw new Error("O identificar não é um terminal 'PROGRAM'");
        }
    }
    
    /**
     * Este método verifica se um token é um IDENTIFICADOR ou um INTEIRO
     */
    private Integer getIdentificadorOuInteiro(String token) {
        //verifica se é um identificador ou inteiro
        char[] cList = token.toCharArray();
        boolean identificador = false;

        for (char c : cList) {
            if (Character.getType(c) != Character.UPPERCASE_LETTER) {
                identificador = true;
            }
        }

        if (identificador) {
            return linguagem.get("IDENTIFICADOR");
        }
        return linguagem.get("INTEIRO");
    }
}
