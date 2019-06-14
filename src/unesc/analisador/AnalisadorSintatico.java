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

        Stack<Integer> X = new Stack<>();
        Stack<String> A = tokens; // Armazena a Stack de Strings na pilha a

        X.push(52); // Adiciona 52(PROGRAM) no topo da pilha X

        while (!A.empty() && !X.empty()) {

            Integer topoX = X.peek(); // Armazena o primeiro item da pilha X
            String topoA = A.peek(); // Busca o codigo do primeiro item da pilha A

            Integer codigo = this.linguagem.get(topoA);

            if (codigo == null) {
                codigo = this.getIdentificadorOuInteiro(topoA);
            }
            System.out.println("Inteiro ou id: [" + topoX + "," + codigo + "]");

            if (topoX != null && topoX < 52) {
                if (topoX.equals(codigo)) {
                    System.out.println("Equals");
                    X.pop();
                    A.pop();
                } else {
                    System.out.println("Erro");
                    break;
                }
            } else {
                String juncao = this.gramatica.get(topoX + "," + codigo);

                if(juncao == null) {
                    X.pop();
                } else {
                    if (!juncao.isEmpty()) {
                        X.pop();

                        Integer[] dadosCruzados = Gramatica.geraDadosCruzamentoTabParsingToken(juncao);

                        for (int i = dadosCruzados.length - 1; i >= 0; i--) {
                            X.push(dadosCruzados[i]);
                        }
                    }
                }
            }
        }
    }

    /**
     * Este método verifica se um token é um IDENTIFICADOR ou um INTEIRO
     */
    private Integer getIdentificadorOuInteiro(String token) {
        //verifica se é um identificador ou inteiro
        char[] cList = token.toCharArray();
        boolean identificador = true;
        for (char c : cList) {
            if (Character.getType(c) != Character.UPPERCASE_LETTER) {
                identificador = false;
            }
        }

        if (identificador) {
            return linguagem.get("IDENTIFICADOR");
        }
        return linguagem.get("INTEIRO");
    }
}
