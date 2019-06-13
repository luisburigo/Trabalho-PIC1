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
        Stack<String> pilhaA = new Stack();
        Stack<Integer> pilhaX = new Stack();
        
        pilhaA = tokens;
        pilhaX.add(52);
        
        while(!pilhaA.empty() && !pilhaX.empty())
        {
            //Faça X ser o topo da pilha
            Integer topoX = pilhaX.peek();
            
            //Faça "a' ser o próximo símbolo de entrada
            String topoA  =  pilhaA.peek();
            
            //Pegando o código do primeiro elemento da pilhaA
            Integer codigo = linguagem.get(topoA);
            
            if (codigo == null){
              try {
                    Integer.valueOf(topoA);
                    codigo = 26;
                } catch (Exception e) {
                    codigo = 25;
                }
            }
            
            //Se X é terminal ou $ então
            if(topoX < 52 && topoX != null)
            {
                
                //Se X = A então
                if(codigo.equals(topoX))
                {
                    //Retira X do topo da pilha
                    topoA = pilhaA.pop();
                    //Retira a da entrada
                    topoX = pilhaX.pop();
                }else
                {//senão
                    System.out.println("[ERRO]");
                    break;
                }
            } 
            else //se X não é terminal
            {
                String juntar = Gramatica.GRAMATICA.get(topoX + "," + codigo);
                
                //Retire X da pilha
                pilhaX.pop();
                
                if(juntar != null && !juntar.isEmpty())
                {
                    //coloque Ykyk-1y2y1 na pilha(com y1 no topo)
                    Integer[]  y1y2 = Gramatica.geraDadosCruzamentoTabParsingToken(juntar);
                    for(int i = y1y2.length - 1; i >= 0; i--)
                    {
                        pilhaX.push(y1y2[i]);
                    }
                }
            }
            if((pilhaX.isEmpty() && !pilhaA.isEmpty()) || (!pilhaX.isEmpty() && pilhaA.isEmpty())) {
                System.out.println("[ERRO]");
            }
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
