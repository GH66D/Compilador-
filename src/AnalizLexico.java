
import java.util.Stack;

public class AnalizLexico {
    
    int token, EdoActual, EdoTransicion;
    String CadenaSigma;
    public String Lexema; //yytext
    boolean PasoPorEdoAcept; //Se pone en true cuando pasa en edoAcept y se actualiza el token
    int IniLexema, FinLexema, IndiceCaracterActual;
    char CaracterActual;
    Stack<Integer> Pila = new Stack<>(); //guardar posiciones de analisis por si tengo que hacer el undotoken
    AFD AutomataFD; //es el archivo, donde esta la representacion tabular, es un arreglo bidimensional, se carga en memoria la info del arvhivo.
    //Podemos hacer la clase o aqui mismo definir el arreglo, cargar el arhivo y meter la info al arreglo
    //Si hacemos la clase, poner la operacion transicion
    public AnalizLexico(){ //constructor sin parametros
        CadenaSigma = "";
        PasoPorEdoAcept = false;
        IniLexema = FinLexema = -1;
        IndiceCaracterActual = -1;
        token = -1;
        Pila.clear();
        AutomataFD = null; //Aqui ponemos el arreglo bidimensional y guardar la info del archivo 
    }
    
    public AnalizLexico(String sigma, String FileAFD, int IdAFD){ //con parametros
        AutomataFD = new AFD();
        CadenaSigma = sigma;
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        AutomataFD.LeerAFDdeArchivo(IdAFD);
        
    }
    
    public AnalizLexico(String sigma, String FileAFD){
        AutomataFD = new AFD();
        CadenaSigma = sigma;
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        AutomataFD.LeerAFDdeArchivo(-1);
        
    }
   
    public AnalizLexico( int IdAFD){ //No paso cadena pero si automata
        AutomataFD = new AFD();
        CadenaSigma = "";
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        AutomataFD.LeerAFDdeArchivo( IdAFD);
   
    }

    public AnalizLexico( AFD AutFD){ //No paso cadena pero si automata
        AutomataFD = AutFD;
        CadenaSigma = "";
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        //AutomataFD.LeerAFDdeArchivo( IdAFD);
   
    }
    
    public AnalizLexico(String sigma, AFD AutFD){
        CadenaSigma = sigma;
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        AutomataFD = AutFD;
    }
    
    public ClassEstadoAnalizLexico GetEsdoAnalizLexico(){ //metodo que saca una foto a toda la info en el proceso del analisis en x momento, guarda la info de variables
        //Tiene variables propias esta clase donde guardaremos toda la info para que restauremos el analizador lexico a ese momento en que guardamos las variables
        ClassEstadoAnalizLexico EdoActualAnaliz = new ClassEstadoAnalizLexico();
        EdoActualAnaliz.CaracterActual = CaracterActual;
        EdoActualAnaliz.EdoActual = EdoActual;
        EdoActualAnaliz.EdoTransicion = EdoTransicion;
        EdoActualAnaliz.FinLexema = FinLexema;
        EdoActualAnaliz.IndiceCaracterActual = IndiceCaracterActual;
        EdoActualAnaliz.IniLexema = IniLexema;
        EdoActualAnaliz.Lexema = Lexema;
        EdoActualAnaliz.PasoPorEdoAcept = PasoPorEdoAcept;
        EdoActualAnaliz.token = token;
        EdoActualAnaliz.Pila = Pila;
        EdoActualAnaliz.CadenaSigma= CadenaSigma;
        return EdoActualAnaliz; //Variable local de ClassAnalizLexico 
    }
    
    public boolean SetEdoAnalizLexico(ClassEstadoAnalizLexico e){ //restaura al analizador a como queramos
        CaracterActual = e.CaracterActual;
        EdoActual = e.EdoActual;
        EdoTransicion = e.EdoTransicion;
        FinLexema = e.FinLexema;
        IndiceCaracterActual = e.IndiceCaracterActual;
        IniLexema = e.IniLexema;
        Lexema = e.Lexema;
        PasoPorEdoAcept = e.PasoPorEdoAcept;
        token = e.token;
        Pila = e.Pila;
        return true;
        
    }
    
    public void SetSigma(String sigma){ //Sirve para poner la cadena que analizare o cambia la cadena 
        CadenaSigma = sigma;
        PasoPorEdoAcept = false;
        IniLexema = 0;
        FinLexema = -1;
        IndiceCaracterActual = 0;
        token = -1;
        Pila.clear();
        
    }
    
    public String CadenaXAnalizar(){
        return CadenaSigma.substring(IndiceCaracterActual, CadenaSigma.length() - IndiceCaracterActual); //visualiza como va el proceso
    }
    
    public int yylex(){
        while(true){
            Pila.push(IndiceCaracterActual); //mi pila tiene la info para restaurar el analizador lexico en un estado precvio para hacer el undotoken
            if(IndiceCaracterActual >= CadenaSigma.length()){
               Lexema = "";
               IniLexema = IndiceCaracterActual;
               return SimbolosEspeciales.FIN; //es el 0
            }
            IniLexema = IndiceCaracterActual;
            EdoActual = 0;
            PasoPorEdoAcept = false;
            FinLexema = -1;
            token = -1;
            
            while (IndiceCaracterActual < CadenaSigma.length()){
            
                CaracterActual = CadenaSigma.charAt(IndiceCaracterActual); //En el codigo del profe tenia CadenaSigma[IndiceCaracterActual];
                EdoTransicion = AutomataFD.TransicionesAFD[EdoActual][CaracterActual]; //en clase AFD hacer metodo TablaAFD, aqui me voy en mi arreglo a la fila y columna indicada
                if(EdoTransicion != -1){
                    if(AutomataFD.TransicionesAFD[EdoTransicion][255] != -1){ //En estado de aceptacion
                        PasoPorEdoAcept = true;
                        token = AutomataFD.TransicionesAFD[EdoTransicion][255];
                        FinLexema = IndiceCaracterActual;
                        //System.out.println("+"+IndiceCaracterActual + CadenaSigma.length()+ PasoPorEdoAcept);
                    }
                    IndiceCaracterActual++;
                    EdoActual = EdoTransicion;
                    
                    continue;
                }
                
               break;
            } //No hay transicion del estado actual con el caracter actual
            if(!PasoPorEdoAcept){
                IndiceCaracterActual = IniLexema + 1;
              
                Lexema = CadenaSigma.substring(IniLexema, IniLexema+1);
                token = SimbolosEspeciales.ERROR;
                return token; //Error
            }
            //No hay transicion con el caracter actual, pero ya se habia pasado por edo de aceptacion
           

            Lexema = CadenaSigma.substring(IniLexema,FinLexema + 1);
           
            IndiceCaracterActual = FinLexema + 1;
            if(token == SimbolosEspeciales.OMITIR)
                continue;
            else
                
                return token;
        }
    }
    
    public boolean UndoToken(){
        if(Pila.isEmpty())
            return false;
        IndiceCaracterActual = Pila.pop();
        return true;
    }
}
