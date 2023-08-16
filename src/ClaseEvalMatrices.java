

public class ClaseEvalMatrices {
    public AnalizLexico L;
    String Expresion;  



    public ClaseEvalMatrices(String sigma, AFD AutFD){
        Expresion = sigma;
        L = new AnalizLexico(Expresion,AutFD);

    }


    public void SetExpresion(String sigma){
        Expresion = sigma;
        L.SetSigma(sigma);
    }

    public boolean IniEvalMatrices(){
        int Token;
      
        if(Asig()){
            Token= L.yylex();
            if(Token == 0){
                return true;
            }

        }
        return false;
    }

    public boolean  Asig(){ //Iden = E
        int Token;
        Token = L.yylex();

        if (Token==120) {// Iden
            Token = L.yylex();
            if (Token==10) {//Eq (=)
                if (E()) { //E
                    Token= L.yylex();
                    if(Token== 20){//pc (;)
                        return true;
                    }
                }  
            }   
        }
        return false;
    }

    public boolean E(){
        if(T()){
            if(Ep()){
                return true;
            }
        }
        return false;
    }

    public boolean Ep(){
        int Token;
        Token= L.yylex();

        if(Token == 30 ||Token==40){//MMAs o menos
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
        if(F()){
            if(Tp()){
                return true;
            } 
        }
        return false;
    }
    public boolean Tp(){
        int Token;
        Token = L.yylex();
        if(Token == 50){//Prod
            if (F()){
                if(Tp()){
                    return true;
                }
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
            case 120://IDEN
                return true;
            case 80: //CORCH_I
                if(Renglones()){
                    Token = L.yylex();
                    if(Token== 90){//Corch_d
                        return true;
                    }
                }
                return false;
            default:
                return false;
        }
    }

    public boolean Renglones(){
        if(Renglon()){
            if(Renglonesp()){
                return true;
            }
        }
        return false;
    }

    public boolean Renglonesp(){
        ClassEstadoAnalizLexico edoAL = new ClassEstadoAnalizLexico();
        edoAL = L.GetEsdoAnalizLexico();
        if(Renglon()){
            if(Renglonesp()){
                return true;
            }
            return false;
        }
        L.SetEdoAnalizLexico(edoAL);
        return true;
    }
    public boolean Renglon(){
        int Token;
        Token= L.yylex();
        if(Token==80){ //CORCH_I
            if(ListaNumeros()){
                Token = L.yylex();
                if(Token== 90){//crcete D
                    return true;
                }
            }
        }
        return false;
    }

    public boolean ListaNumeros(){
        int Token;
        Token= L.yylex();
        if(Token== 110){//num
            if(ListaNumerosp()){
                return true;
            }
        }
        return false;
    }

    public boolean ListaNumerosp(){
        int Token;
        Token = L.yylex();
        if(Token==100){//coma
            Token = L.yylex();
            if( Token== 110){//num
                if(ListaNumerosp()){
                    return true;
                }

            }
            return false;
        }
        L.UndoToken();
        return true;
    }
}

