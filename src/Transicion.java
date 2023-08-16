
public class Transicion {
    private char SimbInf1;
    private char SimbSup1;
    private Estado Edo;

    //CONSTRUCTORES

    public Transicion(char simb, Estado e){
        SimbInf1 = simb;
        SimbSup1 = simb;
        Edo = e;
    }

    public Transicion(char simb1,char simb2,  Estado e){
        SimbInf1 = simb1;
        SimbSup1 = simb2;
        Edo = e;
    }


    public Transicion(){
        Edo = null;
    }

    //METODOS

    public void SetTransicion(char s1, char s2, Estado e){ //MODIFICAR INFORMACION
        SimbInf1 = s1;
        SimbSup1 = s2;
        Edo = e;
    }

    public void SetTransicion(char s1, Estado e){
        SimbInf1 = s1;
        SimbSup1 = s1;
        Edo = e;
    }

    public void SimbInf(char SimbInfR){this.SimbInf1= SimbInfR;}
    public char SimbInf(){return  this.SimbInf1;}

    public void SimbSup(char SimbSupR){this.SimbSup1= SimbSupR;}
    public char SimbSup(){return  this.SimbSup1;}

    public Estado GetEdoTrans(char s){
        if(SimbInf1 <= s && s <= SimbSup1){
            return Edo;
        }
        return null;
    }
}
