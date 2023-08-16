

public class ASExpR {

    public AnalizLexico L;
    String Expresion;  



    public ASExpR(String sigma, AFD AutFD){
        Expresion = sigma;
        L = new AnalizLexico(Expresion,AutFD);

    }


    public void SetExpresionR(String sigma){
        Expresion = sigma;
        L.SetSigma(sigma);
    }

    public boolean IniEvalER(){
      
        int Token;
      
        if(E()){
            Token= L.yylex();
            if(Token == 0){
                return true;
            }

        }
        return false;
    }

    public boolean  E(){ //
        if (T()) {// Iden
            if (Ep()) {//Eq (=)
                return true;
                 
            }   
        }
        return false;
    }

    public boolean Ep(){
        int Token;
        Token= L.yylex();

        if(Token == 10){//or
            if(T()){
                if(Ep()){
                    return true;
                }
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    public boolean T(){
        if(C()){
            if(Tp()){
                return true;
            } 
        }
        return false;
    }
    
    public boolean Tp(){
        int Token;
        Token = L.yylex();
        if(Token == 20){//&
            if (C()){
                if(Tp()){
                    return true;
                }
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    public boolean C(){
        if(F()){
            if(Cp()){
                return true;
            } 
        }
        return false;
    }
    public boolean Cp(){
        int Token;
        Token= L.yylex();

        if(Token == 30 ||Token==40|| Token==50){//+ * ?
            if(Cp()){
                return true;
            }
            return false;
        }
        L.UndoToken();
        return true;
    }

    public boolean F(){
        int Token;
        Token = L.yylex();
        switch(Token){
            case 60: // 60=(   (E)
                if(E()){
                    Token= L.yylex();
                    if(Token == 70){// )
                        return true;
                    }
                }
                return false;
            case 80: // [
                
                Token= L.yylex();
                if(Token == 110){// CARACTER
                    Token= L.yylex();
                    if(Token == 100){// -
                        Token= L.yylex();
                        if(Token == 110){// CARACTER
                            Token= L.yylex();
                            if(Token == 90){// ]
        
                                return true;
                            }
                        }
                    }
                }
                
                return false;
            case 110: // CARACTER
                
                return true;
            default:
                return false;
        }
    }

    
    
}
