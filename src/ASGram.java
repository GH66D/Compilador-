import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class ASGram {

    public AnalizLexico L;
    public String Expresion;  
    public ElemArreglo[] ArrReglas = new ElemArreglo[100]; //arreglo de reglas
    public int NumReglas = 0; //contador de reglas    

    public HashSet<String> Vn = new HashSet<>();
    public HashSet<String> Vt = new HashSet<>();

    public List<String> LVn = new ArrayList<>();
    public List<String> LVt = new ArrayList<>();


    public ASGram(String sigma, AFD AutFD){
        Expresion = sigma;
        L = new AnalizLexico(Expresion,AutFD);
        Vn.clear();
        Vt.clear();
    }

    public ASGram(AFD AutFD){
        L = new AnalizLexico(AutFD);
        Vn.clear();
        Vt.clear();
    }


    public boolean SetGram(String sigma){
        Expresion = sigma;
        L.SetSigma(sigma);
        return true;
    }

    public boolean IniEvalGram(){
      
        int Token;
      
        if(G()){
            Token= L.yylex();
            if(Token == 0){
               IdentificarTerminales();
               Vn.add("$");
               LVn.add("$");
               for (String t : Vt) {
                    LVt.add(t);
               }
               Vt.add("$");
               LVt.add("$");
                return true;
            }

        }
        return false;
    }

    public boolean  G(){ //
        if (ListaReglas()) {// 
            return true;
        }
        return false;
    }

    public boolean ListaReglas(){
        int Token;
        if (Reglas()) {// 
            Token= L.yylex();
            if(Token == 10){//;
                if(ListaReglasP()){
                    return true;
                }
            }
            return false;
        }
        return false;

    }

   
    public boolean ListaReglasP(){
        ClassEstadoAnalizLexico edoAL = new ClassEstadoAnalizLexico();
        edoAL = L.GetEsdoAnalizLexico();
        int Token;
        Token= L.yylex();
        if(Token == 60){// \n
            if(Reglas()){
                Token= L.yylex();
                if(Token == 10){// ;
                    if(ListaReglasP()){
                        return true;
                    }
                }

            }
            return false;
        }
        L.SetEdoAnalizLexico(edoAL);
        return true;
    }

    public boolean Reglas(){
        int Token;
        Simbolo simbolo = new Simbolo();
        if(LadoI(simbolo)){ //referencia
            Vn.add(simbolo.getSimbolo());
            LVn.add(simbolo.getSimbolo());
            Token= L.yylex();
            if(Token==20){ // ->
                if(LadosDerechos(simbolo)){
                    return true;  
                }
            } 
        }
        return false;
    }
    public boolean LadoI(Simbolo simbolo){
        int Token;
        Token= L.yylex();

        if(Token == 40){//simbolo
            simbolo.setSimbolo(L.Lexema);
            return true;
        }
       
        return false;
    }

    public boolean LadosDerechos(Simbolo simbolo){
        if(LadoDerecho(simbolo)){
            if(LadosDerechosP(simbolo)){
                return true;
            
            }   
        }
        return false;
        
    }

    public boolean LadosDerechosP(Simbolo simbolo){
        int Token;
        Token= L.yylex();

        if(Token == 30){//or
            if(LadoDerecho(simbolo)){
                if(LadosDerechosP(simbolo)){
                    return true;
                }   
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    public boolean LadoDerecho(Simbolo sim){
        List<ClaseNodo> Lis = new ArrayList<>();
        Lis.clear();
        if(SecSimbolos(Lis)){ //(SecSimbolos(ref lista)
            ArrReglas[NumReglas] = new ElemArreglo();
            ArrReglas[NumReglas].InfSimbolo = new ClaseNodo(sim.getSimbolo(),false); //el false es para indicar que no es terminal 
            ArrReglas[NumReglas++].LisD = Lis;
        
            return true;
              
        }
        return false;
    }

    public boolean SecSimbolos(List<ClaseNodo> lis){//SecSimbolos(ref Lista<ClaseNodo> Lista)
        int Token;
        ClaseNodo N;
        Token= L.yylex();
        if(Token==40){//simbolo
            N = new ClaseNodo(L.Lexema, false);
            if(SecSimbolosP(lis)){//SecSimbolosP(ref lista)
                lis.add(0, N); //agregar el nodo al inicio
                return true;   
            }
        }
        return false;
    }

    public boolean SecSimbolosP(List<ClaseNodo> lis){//List<ClaseNodo> Lista){ //se manda por referencia la lista
        int Token;
        ClaseNodo N;
        Token= L.yylex();
        if(Token== 50){// ' '
            Token= L.yylex();
            if(Token== 40){// simbolo
               N = new ClaseNodo(L.Lexema, false);
                if(SecSimbolosP(lis)){//SecSimbolosP(ref Lista)){ //se manda por referencvia la lista
                    lis.add(0, N);
                    return true;   
                }
            }
            return false;    
        }
        L.UndoToken();
        return true;
    }

    public void IdentificarTerminales(){
        String eps="epsilon";
        int i;
        for(i=0; i<NumReglas; i++){
            for (ClaseNodo N : ArrReglas[i].LisD){
                if(!Vn.contains(N.cadena) && !N.cadena.equals(eps)){
                    N.tnt = true;
                    Vt.add(N.cadena);
                }
            }
        }
    }

    public HashSet<String> First(List<ClaseNodo> l){
        int i,j;
        ClaseNodo N;
        HashSet<String> R = new HashSet<String>();
        R.clear();
        if(l.size() == 0)
            return R;
        
            for(j=0; j < l.size(); j++){
                N = l.get(j);
                if(N.tnt == true || N.cadena.equals("epsilon")){
                    R.add(N.cadena);
                    return R;
                }

                //N es no terminal. Se calcula el first de cada lado derecho de este no terminal 
            
                for(i=0; i<NumReglas; i++){
                    if(ArrReglas[i].LisD.get(0).cadena == N.cadena)
                        continue;
                    if(ArrReglas[i].InfSimbolo.cadena.equals(N.cadena))
                        R.addAll(First(ArrReglas[i].LisD));
                }

                if(R.contains("epsilon")){
                    if(j == (l.size() - 1))
                        continue;
                    R.remove("epsilon");
                }
                else
                    break;
        }
        return R;
    }
    public HashSet<String> Follow(String SimbNoTerm){
        HashSet<String> R = new HashSet<String>();
        HashSet<String> Aux = new HashSet<String>();
        List<ClaseNodo> ListaPost = new ArrayList<>();
        R.clear();
        int i,j,k;

        if(ArrReglas[0].InfSimbolo.cadena.equals(SimbNoTerm))
            R.add("$");
        for(i=0; i<NumReglas; i++){ //se busca SimbNoTerm en los lados derechos de todo
            for(j=0; j<ArrReglas[i].LisD.size(); j++){ //se recorre la lista del lado derecho buscando al simbolo SimbNoterm
                if(ArrReglas[i].LisD.get(j).cadena.equals(SimbNoTerm)){
                    ListaPost.clear();
                    for(k=j+1; k < ArrReglas[i].LisD.size(); k++) //Obtenemos la lista que corresponde a los simnolos que estan despues 
                        ListaPost.add(ArrReglas[i].LisD.get(k));
                        //Si no hay mas simbolos despues de SimbNoTerm se calcula el follow

                        if(ListaPost.size() == 0){
                            //Si el simbolo del lado izquierdo es igual al simbolo del que 
                            //el follow, omitimos la regla
                            if(!ArrReglas[i].InfSimbolo.cadena.equals(SimbNoTerm))
                                R.addAll(Follow(ArrReglas[i].InfSimbolo.cadena));
                                break;
                             
                        }

                        //se calcula el First de la lista l que esta despues del elemento j

                        Aux.clear();
                        Aux = First(ListaPost);
                        if(Aux.contains("epsilon")){
                            Aux.remove("epsilon");
                            R.addAll(Aux);
                            if(!ArrReglas[i].InfSimbolo.cadena.equals(SimbNoTerm))
                                R.addAll(Follow(ArrReglas[i].InfSimbolo.cadena));
                        }
                        else 
                            R.addAll(Aux);
                    
                }
            }
        }
        return R;
    }





    
}
