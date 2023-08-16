import java.util.ArrayList;
import java.util.List;

public class nodoArb {
    public String cadena;
    public int id;
    public List<nodoArb> Hijos = new ArrayList<>();
    public boolean hoja;
    public int anchoH;
    public int nivel;
    public int numHijos;

    public nodoArb(int id, String cadena, boolean hoja, int nivel){

        this.cadena=cadena;
        this.id=id;
        this.hoja=hoja;
        this.anchoH=1;
        this.nivel=nivel;
        this.numHijos=0;
    }

    public void insertarHijo(nodoArb hijo){
        this.Hijos.add(hijo);
        this.numHijos+=1;
    }
    
}
