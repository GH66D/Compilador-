import java.util.ArrayList;

public class ASemExpR {
    public AnalizLexico L;
    public int idAFN;
    String Expresion; 
    public AFN result; 
    public ArrayList<AFN> afn1 = new ArrayList<AFN>();



    public ASemExpR(String sigma, AFD AutFD,int idER){
        Expresion = sigma;
        L = new AnalizLexico(Expresion,AutFD);
        idAFN= idER;


    }


    public void SetER(String sigma){
        Expresion = sigma;
        L.SetSigma(sigma);
    }

    public boolean IniEvalER(){
      
        int Token;
        AFN f= new AFN();
      
        if(E(f)){
            Token= L.yylex();
            if(Token == 0){
                result=f;
                f.IdAFN=idAFN;
                afn1.add(f);
                return true;
            }

        }
        return false;
    }

    public boolean  E(AFN f){ //
        if (T(f)) {// Iden
            if (Ep(f)) {//Eq (=)
                return true;
                 
            }   
        }
        return false;
    }

    public boolean Ep(AFN f){
        int Token;
        AFN f2= new AFN();
        Token= L.yylex();

        if(Token == 10){//or
            if(T(f2)){
                f.UnirAFN(f2);
                if(Ep(f)){
                    return true;
                }
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    public boolean T(AFN f){
        if(C(f)){
            if(Tp(f)){
                return true;
            } 
        }
        return false;
    }
    
    public boolean Tp(AFN f){
        int Token;
        AFN f2 = new AFN()
        ;
        Token = L.yylex();
        if(Token == 20){//&
            if (C(f2)){
                f.ConcAFN(f2);
                if(Tp(f)){
                    return true;
                }
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    public boolean C(AFN f){
        if(F(f)){
            if(Cp(f)){
                return true;
            } 
        }
        return false;
    }
    public boolean Cp(AFN f){
        int Token;
        Token= L.yylex();

        switch (Token) {
            case 30:
                f.CerrPos();
                if(Cp(f)){
                    return true;
                }
                return false;
                
                
            case 40:
                f.CerrKleen();
                if(Cp(f)){
                    return true;
                }
                return false;
            
                
            case 50:
                    f.Opcional();
                    if(Cp(f)){
                        return true;
                    }
                    return false;                
                
        
            default:
                break;
        }
        /* 
        if(Token == 30 ||Token==40|| Token==50){//+ * ?
            if(Cp()){
                return true;
            }
            return false;
        }*/
        L.UndoToken();
        return true;
    }

    public boolean F(AFN f){
        int Token;
        String lexema, lexema2,simbolo;
        Token = L.yylex();
        switch(Token){
            case 60: // 60=(   (E)
                if(E(f)){
                    return (L.yylex() == 70);

                    /* 
                    Token= L.yylex();
                    if(Token == 70){// )
                        return true;
                    }*/
                }
                return false;
            case 80: // [
                
                Token= L.yylex();
                
                if(Token == 110){// CARACTER
                    lexema= (L.Lexema.charAt(0)=='\\')? L.Lexema.substring(1, 2) : L.Lexema.substring(0, 1);
                   // lexema=L.Lexema;
                    Token= L.yylex();
                    
                    if(Token == 100){// -
                        Token= L.yylex();
                        if(Token == 110){// CARACTER
                            lexema2= (L.Lexema.charAt(0)=='\\')? L.Lexema.substring(1, 2) : L.Lexema.substring(0, 1);
                            //lexema2=L.Lexema;
                            Token= L.yylex();
                            
                            if(Token == 90){// ]
                                //f=new AFN();
                                f.CrearAFNBasico(lexema.charAt(0),lexema2.charAt(0));
                                return true;
                            }
                        }
                    }
                }
                
                return false;
            case 110: // CARACTER
                //f=new AFN();
                lexema= (L.Lexema.charAt(0)=='\\')? L.Lexema.substring(1, 2) : L.Lexema.substring(0, 1);
                //lexema=L.Lexema;
                f.CrearAFNBasico(lexema.charAt(0));
                return true;
            default:
                return false;
        }
    }
    
}
