
public class ClaseNodo {
    String cadena;
    boolean tnt;

    public ClaseNodo(){
        cadena = "";
        tnt = false;

    }

    public ClaseNodo(String cade, boolean t){
        this.cadena =  cade ;
        tnt = t;

    }
    public void modTNT(boolean t){
        this.tnt=t;
    }

}