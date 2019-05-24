package unesc.gramatica;


import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;


public class Gramatica {
    public static final Map<String, Integer> LINGUAGEM = new HashMap<String, Integer>();
    public static final Map<String, String> GRAMATICA = new HashMap<String, String>();

    static {
        //palavras reservadas, que podem ser utilizadas (terminais)
        // Código < 52
        LINGUAGEM.put("PROGRAM", 1);
        LINGUAGEM.put("VAR", 4);
        LINGUAGEM.put("BEGIN", 6);
        LINGUAGEM.put("END", 7);
        LINGUAGEM.put("INTEGER", 8);
        LINGUAGEM.put("REPEAT", 18);
        LINGUAGEM.put("UNTIL", 19);
        LINGUAGEM.put("WRITELN", 21);
        LINGUAGEM.put("IDENTIFICADOR", 25);
        LINGUAGEM.put("INTEIRO", 26);
        LINGUAGEM.put("(", 36);
        LINGUAGEM.put(")", 37);
        LINGUAGEM.put(":=", 38);
        LINGUAGEM.put(":", 39);
        LINGUAGEM.put(">", 41);
        LINGUAGEM.put(",", 46);
        LINGUAGEM.put(";", 47);
        LINGUAGEM.put(".", 49);
        
        //simbolos não terminais (que possuem deriações em outros blocos)
        LINGUAGEM.put("PROGRAMA", 52);
        LINGUAGEM.put("BLOCO", 53);
        LINGUAGEM.put("DCLROT", 54);
        LINGUAGEM.put("LID", 55);
        LINGUAGEM.put("REPIDENT", 56);
        LINGUAGEM.put("DCLCONST", 57);
        LINGUAGEM.put("DCLVAR", 59);
        LINGUAGEM.put("LDVAR", 60);
        LINGUAGEM.put("TIPO", 61);
        LINGUAGEM.put("DCLPROC", 62);
        LINGUAGEM.put("CORPO", 64);
        LINGUAGEM.put("REPCOMANDO", 65);
        LINGUAGEM.put("COMANDO", 66);
        LINGUAGEM.put("RCOMID", 67);
        LINGUAGEM.put("RVAR", 68);
        LINGUAGEM.put("VARIAVEL", 72);
        LINGUAGEM.put("VARIAVEL1", 73);
        LINGUAGEM.put("ITEMSAIDA", 75);
        LINGUAGEM.put("REPITEM", 76);
        LINGUAGEM.put("EXPRESSAO", 77);
        LINGUAGEM.put("REPEXPSIMP", 78);
        LINGUAGEM.put("EXPSIMP", 79);
        LINGUAGEM.put("REPEXP", 80);
        LINGUAGEM.put("REPTERMO", 82);
        LINGUAGEM.put("TERMO", 81);
        LINGUAGEM.put("FATOR", 83);
        
        //montagem da gramatica / tabela de parsing
        GRAMATICA.put("52,1", "PROGRAM|IDENTIFICADOR|;|BLOCO|.");
        GRAMATICA.put("53,4", "DCLROT|DCLCONST|DCLVAR|DCLPROC|CORPO");
        GRAMATICA.put("59,4", "VAR|LID|:|TIPO|;|LDVAR");
        GRAMATICA.put("55,25", "IDENTIFICADOR|REPIDENT");
        GRAMATICA.put("56,46", ",|IDENTIFICADOR|REPIDENT");
        GRAMATICA.put("61,8", "INTEGER");
        GRAMATICA.put("64,6", "BEGIN|COMANDO|REPCOMANDO|END");
        GRAMATICA.put("66,25", "IDENTIFICADOR|RCOMID");
        GRAMATICA.put("67,38", "RVAR|:=|EXPRESSAO");
        GRAMATICA.put("77,26", "EXPSIMP|REPEXPSIMP");
        GRAMATICA.put("79,26", "TERMO|REPEXP");
        GRAMATICA.put("81,26", "FATOR|REPTERMO");
        GRAMATICA.put("83,26", "INTEIRO");
        GRAMATICA.put("65,47", ";|COMANDO|REPCOMANDO");
        GRAMATICA.put("66,18", "REPEAT|COMANDO|UNTIL|EXPRESSAO");
        GRAMATICA.put("66,6", "CORPO");
        GRAMATICA.put("66,21", "WRITELN|(|ITEMSAIDA|REPITEM|)");
        GRAMATICA.put("75,25", "EXPRESSAO");
        GRAMATICA.put("77,25", "EXPSIMP|REPEXPSIMP");
        GRAMATICA.put("79,25", "TERMO|REPEXP");
        GRAMATICA.put("81,25", "FATOR|REPTERMO");
        GRAMATICA.put("83,25", "|VARIAVEL");
        GRAMATICA.put("72,25", "IDENTIFICADOR|VARIAVEL1");
        GRAMATICA.put("76,46", ",|ITEMSAIDA|REPITEM");
        GRAMATICA.put("78,41", ">|EXPSIMP");
    }

    public static String getTerminalENaoTerminalByCodigo(Integer codigo) {
        if(codigo == null) {
            return null;
        }
        for(Map.Entry<String, Integer> entry : LINGUAGEM.entrySet()){
            if(entry.getValue().compareTo(codigo) == 0) {
                return entry.getKey();
            }
        }
        return null;
    }

     /**
     * Este mtodo retorna uma lista com os cdigos equivalentes as palavras que
     * foram derivadas
     */
    public static Integer[] geraDadosCruzamentoTabParsingToken(String str) {
        if ((str != null) && (str.length() != 0) && !"null".equals(str)) {
            StringTokenizer strTokenizer = new StringTokenizer(str, "|");
            Integer[] dados = new Integer[strTokenizer.countTokens()];
            int i = 0;

            while (strTokenizer.hasMoreTokens()) {
                String palavra = strTokenizer.nextToken();
                dados[i++] = LINGUAGEM.get(palavra);
            }

            return dados;
        }

        return null;
    }   
}
