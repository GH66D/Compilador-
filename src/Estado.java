import java.util.HashSet;

public class Estado {
    static int ContadorIdEstado = 0;
    private int idEstado1;
    private boolean EdoAcept1;
    private int Token1;
    private HashSet<Transicion> Trans1 = new HashSet<Transicion>();
    
    public Estado(){
        
        EdoAcept(false);
        Token (-1);
        
        IdEstado(ContadorIdEstado++);
        //Trans1.add(null);
        Trans1.clear();
    }
 
    public void IdEstado(int IdEstadoR){this.idEstado1= IdEstadoR;}
    public int IdEstado(){return  this.idEstado1;}
    public void EdoAcept (boolean EdoAceptR){this.EdoAcept1= EdoAceptR;}
    public boolean EdoAcept (){return this.EdoAcept1;}
    public void Token(int TokenR) { this.Token1= TokenR;}
    public int Token() {return this.Token1;}
    public void Trans(HashSet<Transicion> TransR) { this.Trans1= TransR;}
    public HashSet<Transicion> Trans() { return this.Trans1;}
    public void AddTrans(Transicion NewTrans) {this.Trans1.add(NewTrans);}
    /* 
    public int IdEstado {
    get => idEstado1; 
    set => idEstado1 = value;
    }
    public boolean EdoAcept {
    get => EdoAcept1; 
    set => EdoAcept1 = value;
    }
    public int Token { 
    get => Token1; 
    set=> Token1 = value;
    }
    public HashSet<Transicion> Trans {
    get => Trans1; 
    set => Trans1 = value;
    }
    */
    
}
