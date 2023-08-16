import java.util.HashSet;

public class ConjIJ {
    public int Idj;
    public HashSet<Estado> ConjI = new HashSet<Estado>();
    //public int [] TransicionesAFD;
    public int [] TransicionesAFD= new int[256];
    //public int j;
    public boolean aceptacion = false;
    public int token;


    public ConjIJ(){
        
        for ( int d=0; d<256;d++) {
            TransicionesAFD[d]=-1;
        }
    }
    
}
