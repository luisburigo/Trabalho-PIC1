package unesc.analisador;


import java.util.Stack;

public class AnalisadorLexico {

    public Stack<String> gerarTokens(String programa) {
        String[] tokens = programa.split(" ");
        Stack pilhaInvertida = new Stack();
        Stack pilhaAux = new Stack();

       for(String s : tokens){
           if(!s.isEmpty() && !s.equals(" ")){
               pilhaAux.push(s);
           }
       }
       
       while(!pilhaAux.isEmpty()){
           pilhaInvertida.push(pilhaAux.pop());
       }

        return pilhaInvertida;
    }

}
