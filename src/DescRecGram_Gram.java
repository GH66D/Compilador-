import java.util.HashSet;
import java.util.List;

import com.jtattoo.plaf.texture.TextureLabelUI;

import java.util.ArrayList;


public class DescRecGram_Gram {
    public String Gramatica;
    public AnalizLexico L;
    public ElemArreglo[] ArrReglas = new ElemArreglo[100];
    public int NumReglas = 0;

    public HashSet<String> Vn = new HashSet<>();
    public HashSet<String> Vt = new HashSet<>();

    public DescRecGram_Gram(String sigma, int IdentiAFD){
        Gramatica = sigma;
        L = new AnalizLexico(Gramatica, IdentiAFD);
        Vn.clear();
        Vt.clear();
}

    public DescRecGram_Gram(int IdentiAFD){
        L = new AnalizLexico(IdentiAFD);
        Vn.clear();
        Vt.clear();
    }

    public boolean SetGramatica(String sigma){
        Gramatica = sigma;
        L.SetSigma(sigma);
        return true;
    }

    public boolean AnalizarGramatica(){
        int token;
        if(G()){
           token = L.yylex();
           if(token == 0 ){
                IdentificarTerminales();
                return true;
           }
        }
        return false;
    }

    private boolean G(){
        if(ListaReglas())
            return true;
        return false;
    }

    private boolean ListaReglas(){
        int token; 
        if(Reglas()){
            token = L.yylex();
            if(token == TokensGram_Gram.PC)
                if(ListaReglasP())
                return true;
        }
        return false;
    }

    private boolean ListaReglasP(){
        int token;
        ClassEstadoAnalizLexico e;
        e = L.GetEsdoAnalizLexico();
        if(Reglas()){
            token = L.yylex();
            if(token == TokensGram_Gram.PC){
                if(ListaReglasP())
                    return true;
            }
            return false;
        }
        //epsilon
        L.SetEdoAnalizLexico(e);
        return true;
    }

    private boolean Reglas(){
        int token;
        String Simbolo = "";
        if(LadoIzquierdo(Simbolo)){ //como referencia Simbolo
            Vn.add(Simbolo);
            token = L.yylex();
            if(token == TokensGram_Gram.FLECHA)
                if(LadosDerechos(Simbolo))
                    return true;
        }
        return false;
    }

    private boolean LadoIzquierdo(String Simbolo){ //simbolo se manda por referencia
        int token;
        token = L.yylex();
        if(token == Integer.parseInt(TokensGram_Gram.SIMBOLO)){ //pasar SIMBOLO tipo String a int
            Simbolo = L.Lexema;
            return true;
        }
        return false;
    }

    private boolean LadosDerechos(String Simbolo){
        if(LadoDerecho(Simbolo)){
            if(LadosDerechosP(Simbolo))
                return true;
        }
        return false;

    }

    private boolean LadosDerechosP(String Simbolo){
        int token;
        token = L.yylex();
        if(token == TokensGram_Gram.OR){
            if(LadosDerechosP(Simbolo))
                if(LadosDerechosP(Simbolo))
                    return true;
                return false;
        }
        L.UndoToken();
        return true;
    }

    private boolean LadoDerecho(String Simbolo){ //Simbolo es el lado izquierdo de la regla
        List<ClaseNodo> Lista = new ArrayList<>();
        Lista.clear();
        if(SecSimbolos(Lista)){ //se llama Lista por referencia
            ArrReglas[NumReglas] = new ElemArreglo();
            ArrReglas[NumReglas].InfSimbolo = new ClaseNodo(Simbolo,false); //el false es para indicar que no es terminal 
            ArrReglas[NumReglas++].ListaLadoDerecho = Lista;
            return true;
        }
        return false;
    } 

    private boolean SecSimbolos(List<ClaseNodo> Lista){ //se llama por referencia la lista
        int token;
        ClaseNodo N;
        token  = L.yylex();
        if(token == Integer.parseInt(TokensGram_Gram.SIMBOLO)){
            N = new ClaseNodo(L.Lexema, false);
            if(SecSimbolosP(Lista)){ //se manda por referencia 
                Lista.add(0, N); //agregar el nodo al inicio
                return true;
            }
        }
        return false;
    }

    boolean SecSimbolosP(List<ClaseNodo> Lista){ //se manda por referencia la lista
        int token;
        ClaseNodo N;
        token = L.yylex();
        if(token == Integer.parseInt(TokensGram_Gram.SIMBOLO)){
            N = new ClaseNodo(L.Lexema, false);
            if(SecSimbolosP(Lista)){ //se manda por referencvia la lista
                Lista.add(0, N);
                return true;
            }
            return false;
        }
        L.UndoToken();
        return true;

    }

    private void IdentificarTerminales(){
        int i;
        for(i=0; i<NumReglas; i++)
            for (ClaseNodo N : ArrReglas[i].ListaLadoDerecho) 
                if(!Vn.contains(N.Simbolo) && !N.Simbolo.Equals("epsilon")){
                    N.Terminal = true;
                    Vt.add(N.Simbolo);
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
                if(N.Terminal || N.Simbolo.Equals("epsilon")){
                    R.add(N.Simbolo);
                    return R;
                }

                //N es no terminal. Se calcula el first de cada lado derecho de este no terminal 
            
                for(i=0; i<NumReglas; i++){
                    if(ArrReglas[i].ListaLadoDerecho[0].Simbolo == N.Simbolo)
                        continue;
                    if(ArrReglas[i].InfSimbolo.Simbolo.Equals(N.Simbolo))
                        R.addAll(First(ArrReglas[i].ListaLadoDerecho));
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

            if(ArrReglas[0].InfSimbolo.Simbolo.Equals(SimbNoTerm))
                R.add("$");
            for(i=0; i<NumReglas; i++){ //se busca SimbNoTerm en los lados derechos de todo
                for(j=0; j<ArrReglas[i].ListaLadoDerecho.size(); j++){ //se recorre la lista del lado derecho buscando al simbolo SimbNoterm
                    if(ArrReglas[i].ListaLadoDerecho[j].Simbolo.Equals(SimbNoTerm)){
                        ListaPost.clear();
                        for(k=j+1; k < ArrReglas[i].ListaLadoDerecho.size(); k++) //Obtenemos la lista que corresponde a los simnolos que estan despues 
                            ListaPost.add(ArrReglas[i].ListaLadoDerecho[k]);
                            //Si no hay mas simbolos despues de SimbNoTerm se calcula el follow

                            if(ListaPost.size() == 0){
                                //Si el simbolo del lado izquierdo es igual al simbolo del que 
                                //el follow, omitimos la regla
                                if(!ArrReglas[i].InfSimbolo.Simbolo.Equals(SimbNoTerm))
                                    R.addAll(Follow(ArrReglas[i].InfSimbolo.Simbolo));
                                    break;
                                 
                            }

                            //se calcula el First de la lista l que esta despues del elemento j

                            Aux.clear();
                            Aux = First(ListaPost);
                            if(Aux.contains("epsilon")){
                                Aux.remove("epsilon");
                                R.addAll(Aux);
                                if(!ArrReglas[i].InfSimbolo.Simbolo.Equals(SimbNoTerm))
                                    R.addAll(Follow(ArrReglas[i].InfSimbolo.Simbolo));
                            }
                            else 
                                R.addAll(Aux);
                        
                    }
                }
            }
            return R;
        }

    



}