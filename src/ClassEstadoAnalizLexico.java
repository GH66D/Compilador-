import java.util.Stack;

class ClassEstadoAnalizLexico {
    public int token, EdoActual, EdoTransicion;
    public String CadenaSigma;
    public String Lexema; //yytext
    public boolean PasoPorEdoAcept; 
    public int IniLexema, FinLexema, IndiceCaracterActual;
    public char CaracterActual;
    public Stack<Integer> Pila = new Stack<>();



}
