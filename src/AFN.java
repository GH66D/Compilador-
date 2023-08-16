
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class AFN {
    
    public static HashSet<AFN> ConjDeAFNs = new HashSet<AFN>();
    Estado EdoIni;
    HashSet<Estado> EdosAFN = new HashSet<Estado>();
    HashSet<Estado> EdosAcept = new HashSet<Estado>();
    HashSet<Character> Alfabeto = new HashSet<Character>();
    boolean SeAgregoAFNUnionLexico;
    public int IdAFN;
    public List <String> idafns = new ArrayList<String>();

    public AFN(){
        IdAFN = 0;
        EdoIni = null;
        EdosAFN.clear();
        EdosAcept.clear();   
        Alfabeto.clear();
        SeAgregoAFNUnionLexico = false;
    }
    
    public AFN CrearAFNBasico(char s){
        Estado e1 = new Estado();
        Estado e2 = new Estado();
        Transicion t = new Transicion(s,e2);
        e1.AddTrans(t);
        e2.EdoAcept (true);
        Alfabeto.add(s);
        EdoIni = e1;
        EdosAFN.add(e1);
        EdosAFN.add(e2);
        EdosAcept.add(e2);
        SeAgregoAFNUnionLexico = false;
        return this;
    }
    
    public AFN CrearAFNBasico(char s1, char s2){
        //validar que s1 <= s2
        char i;
        Transicion t;
        Estado e1,e2;
        e1 = new Estado();
        e2 = new Estado();
        t = new Transicion(s1,s2,e2);
        e1.AddTrans(t);
        e2.EdoAcept (true);
        for(i=s1; i<=s2;i++)
            Alfabeto.add(i);
        EdoIni = e1;
        EdosAFN.add(e1);
        EdosAFN.add(e2);
        EdosAcept.add(e2);
        SeAgregoAFNUnionLexico = false;
        return this;

    }
    
    public AFN UnirAFN(AFN f2){
        Estado e1 = new Estado();
        Estado e2 = new Estado();
        Transicion t1 = new Transicion(SimbolosEspeciales.EPSILON,this.EdoIni);
        Transicion t2 = new Transicion(SimbolosEspeciales.EPSILON, f2.EdoIni);
        e1.AddTrans(t1);
        e1.AddTrans(t2);
        for(Estado e : this.EdosAcept){
            e.AddTrans(new Transicion(SimbolosEspeciales.EPSILON,e2));
            e.EdoAcept(false);
        }
        for(Estado e : f2.EdosAcept){
            e.AddTrans(new Transicion(SimbolosEspeciales.EPSILON,e2));
            e.EdoAcept(false);
        }
        
        // Ahora e2 es de aceptacion 
        //Se actualiza la infoi del nuevo atomata This
        
        this.EdosAcept.clear();
        f2.EdosAcept.clear();
        this.EdoIni = e1;
        e2.EdoAcept(true);
        this.EdosAcept.add(e2);
        //this.EdosAFN.UnionWith(f2.EdosAFN);
        this.EdosAFN.addAll(f2.EdosAFN);
        this.EdosAFN.add(e1);
        this.EdosAFN.add(e2);
        this.Alfabeto.addAll(f2.Alfabeto);
        return this;
    }
    
    public AFN ConcAFN(AFN f2){
        for(Transicion t : f2.EdoIni.Trans()){
            for(Estado e : this.EdosAcept){
                e.AddTrans(t);
                e.EdoAcept(false);
            }
        }

        f2.EdosAFN.remove(f2.EdoIni);
        this.EdosAcept= f2.EdosAcept;
        this.EdosAFN.addAll(f2.EdosAFN);
        this.Alfabeto.addAll(f2.Alfabeto);

        return this;
    }

    public AFN CerrPos(){
        //se crea un nuevo estado inicial y uno de aceptacion
        Estado e_ini = new Estado();
        Estado e_fin = new Estado();

        e_ini.AddTrans(new Transicion(SimbolosEspeciales.EPSILON, this.EdoIni));

        for(Estado e : this.EdosAcept){
            e.AddTrans(new Transicion(SimbolosEspeciales.EPSILON,e_fin));
            e.AddTrans(new Transicion(SimbolosEspeciales.EPSILON,this.EdoIni));
            e.EdoAcept(false);
        }

        EdoIni = e_ini;
        e_fin.EdoAcept(true);
        EdosAcept.clear();
        EdosAcept.add(e_fin);
        EdosAFN.add(e_fin);
        EdosAFN.add(e_ini);

        return this;
    }

    public AFN CerrKleen(){
        //se crea un nuevo estado inicial y uno de aceptacion
        Estado e_ini = new Estado();
        Estado e_fin = new Estado();

        e_ini.AddTrans(new Transicion(SimbolosEspeciales.EPSILON, this.EdoIni));
        e_ini.AddTrans(new Transicion(SimbolosEspeciales.EPSILON, e_fin));

        for(Estado e : this.EdosAcept){
            e.AddTrans(new Transicion(SimbolosEspeciales.EPSILON,e_fin));
            e.AddTrans(new Transicion(SimbolosEspeciales.EPSILON,this.EdoIni));
            e.EdoAcept(false);
        }

        EdoIni = e_ini;
        e_fin.EdoAcept(true);
        EdosAcept.clear();
        EdosAcept.add(e_fin);
        EdosAFN.add(e_fin);
        EdosAFN.add(e_ini);

        return this;
    }

    public AFN Opcional(){
        //se crea un nuevo estado inicial y uno de aceptacion
        Estado e_ini = new Estado();
        Estado e_fin = new Estado();

        e_ini.AddTrans(new Transicion(SimbolosEspeciales.EPSILON, this.EdoIni));
        e_ini.AddTrans(new Transicion(SimbolosEspeciales.EPSILON, e_fin));

        for(Estado e : this.EdosAcept){
            e.AddTrans(new Transicion(SimbolosEspeciales.EPSILON,e_fin));
            e.EdoAcept(false);
        }

        EdoIni = e_ini;
        e_fin.EdoAcept(true);
        EdosAcept.clear();
        EdosAcept.add(e_fin);
        EdosAFN.add(e_fin);
        EdosAFN.add(e_ini);

        return this;
    }

    public HashSet<Estado> CerraduraEpsilon(Estado e){
        
        HashSet<Estado> R = new HashSet<>();//estados analizados
        Stack<Estado> S = new Stack<>();
        Estado aux,Edo;
        R.clear();
        S.clear();
        
        S.push(e);
        while(!S.empty()){
            aux = S.pop();
            R.add(aux);
            for(Transicion t : aux.Trans())
                if((Edo = t.GetEdoTrans(SimbolosEspeciales.EPSILON)) != null)
                    if(!R.contains(Edo))
                        S.push(Edo);
        }
        return R;
    }
    
    public HashSet<Estado> CerraduraEpsilon(HashSet<Estado> ConjEdos){
        
        HashSet<Estado> R = new HashSet<>();
        Stack<Estado> S = new Stack<>();
        Estado aux,Edo;
        R.clear();
        S.clear();
        
        for (Estado e : ConjEdos)
            S.push(e);
        
        while(!S.empty()){
            aux = S.pop();
            R.add(aux);
            for(Transicion t : aux.Trans())
                if((Edo = t.GetEdoTrans(SimbolosEspeciales.EPSILON)) != null)
                    if(!R.contains(Edo))
                        S.push(Edo);
        }
        return R;
    }
    
    public HashSet<Estado> Mover(Estado Edo, char Simb){
        HashSet<Estado> C = new HashSet<>();
        Estado Aux;
        C.clear();
        
        for(Transicion t : Edo.Trans()){
            Aux = t.GetEdoTrans(Simb);
            if(Aux != null)
                C.add(Aux);
            
        }
        return C;
    }
    
    public HashSet<Estado> Mover(HashSet<Estado> Edos, char Simb){
        HashSet<Estado> C = new HashSet<>();
        Estado Aux;
        C.clear();
        for(Estado Edo : Edos)
            for(Transicion t : Edo.Trans()){
                Aux = t.GetEdoTrans(Simb);
                if( Aux != null)
                    C.add(Aux);   
            }
        return C;   
    }

    public HashSet<Estado> Ir_A(HashSet<Estado> Edos, char Simb){
        HashSet<Estado> C = new HashSet<>();
        C.clear();
        C = CerraduraEpsilon( Mover(Edos, Simb));
        return C;
    }

    public void UnionEspecialAFNs(AFN f,int Token){
        Estado e;
        if(!this.SeAgregoAFNUnionLexico){
            this.EdosAFN.clear();
            this.EdosAcept.clear();
            this.Alfabeto.clear();
            e = new Estado();
            e.Trans().add(new Transicion(SimbolosEspeciales.EPSILON, f.EdoIni));
            this.EdoIni = e;
            this.EdosAFN.add(e);
            this.SeAgregoAFNUnionLexico = true;
        }
        else
            this.EdoIni.Trans().add(new Transicion(SimbolosEspeciales.EPSILON,f.EdoIni));
        for(Estado EdoAcept : f.EdosAcept)
            EdoAcept.Token(Token);
        
        this.EdosAcept.addAll(f.EdosAcept);
        this.EdosAFN.addAll(f.EdosAFN);
        this.Alfabeto.addAll(f.Alfabeto);
        /*this.EdoAcept.UnionWith(f.EdosAcept);
        this.EdoAFN.UnionWith(f.EdosAFN);
        this.Alfabeto.UnionWith(f.Alfabeto); */
        
    }


    private int IndiceCaracter(char[] ArregloAlfabeto, char c){
        int i;
        for(i=0; i< ArregloAlfabeto.length; i++)
            if(ArregloAlfabeto[i] == c)
                return i;
        return -1;
    }

    /**
     * @return
     */
    
    public AFD ConvAFNaAFD(){
        int CardAlfabeto, NumEdosAFD;
        int i,j,r; //j: contador de estadosIj
        char [] ArrAlfabeto;
        ConjIJ Ij, Ik; //ij estado ij, ik estado auxiliar
        boolean existe;
        
        HashSet<Estado> ConjAux = new HashSet<Estado>(); //resultado de operacion IrA
        HashSet<ConjIJ> EdosAFD = new HashSet<ConjIJ>(); //conjunto de los conjuntos ij (C)
        Queue<ConjIJ> EdosSinAnalizar = new LinkedList<ConjIJ>(); //estados ij ahun no analizados
        
        EdosAFD.clear();
        EdosSinAnalizar.clear();
        
        CardAlfabeto = this.Alfabeto.size();
        ArrAlfabeto = new char[CardAlfabeto];
        i = 0;
        for(char c : this.Alfabeto)
            ArrAlfabeto[i++] = c;
        
        j=0; //Contador para los estados del AFD  
        Ij = new ConjIJ();
        Ij.ConjI = CerraduraEpsilon(this.EdoIni);
        Ij.Idj = j;
        Ij.TransicionesAFD[0]=j;
        EdosAFD.add(Ij);
        EdosSinAnalizar.add(Ij);
        //Enqueue(Ij);
        j++;
        while(!EdosSinAnalizar.isEmpty()){ //Mientras se tengan estados Ij sin analizar
            Ij = EdosSinAnalizar.remove();//Dequeue();
            //Calcular el IrA de Ij con cada s[imbolo del alfabeto
            for(char c : this.Alfabeto){
                //System.out.println(c);               
                Ik = new ConjIJ();
                Ik.ConjI = Ir_A(Ij.ConjI, c);                           
                if(Ik.ConjI.isEmpty()) //Si el conjunto fue vacio (no hubo transiciones)
                    continue;
                //Revisemos si el conjunto de estados ya existe, en caso contrario 
                existe = false;
                for(ConjIJ I : EdosAFD){
                    if(I.ConjI.equals(Ik.ConjI)){                       
                        existe = true; //El conjunto ya existe, Entonces la transicion de 
                        r = IndiceCaracter(ArrAlfabeto, c);
                        Ij.TransicionesAFD[c] = I.Idj;
                        break;
                    }                   
                }
                if(!existe){ //Si el conjunto Ik no existia, sera un nuevo estado que debe
                    Ik.Idj = j; //Le ponemos indice al nuevo estado
                    Ik.TransicionesAFD[0]=j;
                    r = IndiceCaracter(ArrAlfabeto, c);
                    Ij.TransicionesAFD[c] = Ik.Idj;
                    EdosAFD.add(Ik);//Se agrega el nuevo estado a la coleccion
                    EdosSinAnalizar.add(Ik); //Al ser un nuevo estado falta por analizar
                    j++;
                }
            }
        }
        NumEdosAFD = j;
        //Como ponerle que es estado de aceptacion y su token
        //foreach para cada elemento que esta en estado del AFD, tomo uno por uno 
        //y voy intersectando su conjunto de estados con los estados de acepaction del AFN
        //Si tiene uno en comun, obtengo el token asignado
        //Si hay mas de uno, tomar el primer token del primer automata y mandar mensaje de error
        for(ConjIJ I : EdosAFD){
            ConjAux.clear();
            ConjAux.addAll(I.ConjI);
           // ConjAux.UnionWith(I.ConjI);
            ConjAux.retainAll(this.EdosAcept);
            //ConjAux.IntersectWith(this.EdosAcept);
            if(ConjAux.size() != 0){
                for(Estado EdoAcept : ConjAux){
                    I.TransicionesAFD[255] = EdoAcept.Token();
                    I.aceptacion=true;
                    break;
                }
            }else{
                I.TransicionesAFD[255] = -1;
            }
        }
        AFD nuevoAFD = new AFD(NumEdosAFD, EdosAFD);  
        return nuevoAFD;   
    }
}