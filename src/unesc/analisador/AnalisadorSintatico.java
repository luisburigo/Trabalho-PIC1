package unesc.analisador;


import unesc.gramatica.Gramatica;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

public class AnalisadorSintatico {

    private final Map<String, Integer> linguagem = Gramatica.LINGUAGEM;
    private final Map<String, String> gramatica = Gramatica.GRAMATICA;

    public void analisar(Stack<String> tokens) {
        List<Integer> identificadores = new ArrayList<Integer>();

        // TODO: Implementar o pseudocódigo utilizando a a "linguagem" e gramática
        for (int i = 0; i < tokens.size(); i++) {
            identificadores.add(this.getIdentificadorOuInteiro(tokens.pop()));
        }

        // Apresentar uma mensagem informando se o programa está correto ou incorreto.
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
