import java.util.ArrayList;
import java.util.List;

public class Arbol {
    public nodoArb raiz;
    public int ancho;
    public int niveles;
    public int indiceAnalisis;
    public List<nodoArb> nodos = new ArrayList<>();
    public int indexid=0;

    public Arbol(nodoArb raiz){
        this.raiz=raiz;
        this.ancho=1;
        this.niveles=0;
        nodos.add(raiz);
    }

    public nodoArb  agregarNodo( String cadena, int nivel){
        this.indexid+=1;
        nodoArb newNod = new nodoArb(this.indexid, cadena, false, nivel);
        nodos.add( newNod);
        if(nivel>this.niveles){
            this.niveles=nivel;
        }
        return newNod;
    }
}
