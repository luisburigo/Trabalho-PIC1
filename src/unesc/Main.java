package unesc;


import unesc.analisador.AnalisadorSintatico;
import unesc.analisador.AnalisadorLexico;
import java.util.Stack;

public class Main {

    //- Programa a ser analisado
    private String programa = 
            "PROGRAM TESTE ; "
            + "VAR "
            + "   X , Y , Z : INTEGER ; "
            + "BEGIN "
            + "   X := 10 ; "
            + "   Y := 20 ; "
            + "   Z := 30 ; "
            + "  REPEAT "
            + "    BEGIN "
            + "      WRITELN ( X , Y , Z ) ; "
            + "    END "
            + "  UNTIL X > 10 ; "
            + "END . "
            ;
    
    /**
     Veja as instruções dentro dos métodos gerarTokens() e analisar()
     */
    public void iniciar() {
        
        //Objetivo 1: Transformar o conteudo do "programa" em uma pilha de tokens.
        Stack<String> tokens = new AnalisadorLexico().gerarTokens(programa);
        
        //Objetivo 2: Dentro do método analisar() escrever o algorítimo de contido no pseudocódigo 
        //            para verificar se a estrutura da linguagem definida na variável programa está correta
        new AnalisadorSintatico().analisar(tokens);
    }

    public static void main(String args[]) {
        new Main().iniciar();
    }
}
