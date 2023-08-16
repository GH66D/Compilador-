public boolean analizarSin(AFD afd, String sigma){
    int i=0;
    L = new AnalizLexico(sigma,afd);
    Stack<ClaseNodo> Pila = new Stack<>();
    int token;
    int indiceX;
    int indiceY;
    int regla=0;
    

    Pila.push(this.a.ArrReglas[0].InfSimbolo);
    
    token = L.yylex();

    while(!Pila.isEmpty()){
        //JOptionPane.showMessageDialog(null,"analizando" );
        indiceX= indexlex.indexOf(token);
        if (Pila.peek().tnt==false) { //si es terminal
            //JOptionPane.showMessageDialog(null,"no terminal" );
            indiceY=a.LVn.indexOf( Pila.peek().cadena);
            //JOptionPane.showMessageDialog(null,"indice y"+indiceY+"  indice x  " + indiceX);
            if (TabLL1[indiceY][indiceX+1]!= "-1") {

                //JOptionPane.showMessageDialog(null,"si pasa, es diferente de -1");
                regla=Integer.parseInt(TabLL1[indiceY][indiceX+1]);
               // JOptionPane.showMessageDialog(null,"regla: "+regla);

                
                //JOptionPane.showMessageDialog(null,Spila+"  "+ sigma.substring(L.IniLexema, sigma.length())+"$  " + Sregla );
                Pila.pop();

                if (!a.ArrReglas[regla-1].LisD.get(0).cadena.equals("epsilon")) {
                    for (int j = 0; j < a.ArrReglas[regla-1].LisD.size(); j++) {
                        Pila.push(a.ArrReglas[regla-1].LisD.get(a.ArrReglas[regla-1].LisD.size()-1-j));
                        //JOptionPane.showMessageDialog(null,Pila.peek().cadena + Pila.size());
                    }   
                }   
            } else { 
                return false;
            } 
        }else{// terminal
            //JOptionPane.showMessageDialog(null,"  terminal" +Pila.peek().cadena+indexT.get(indexlex.indexOf(token)));

            
            if (Pila.peek().cadena.equals(indexT.get(indexlex.indexOf(token)))) {
                
                

                
                Pila.pop();
                token = L.yylex();
            } else {
                //JOptionPane.showMessageDialog(null," no terminal son diferentes " );
                return false;
            }

        }

    }

    if (token==0 && Pila.size()==0) {
        return true;                    
    }

    return false;
}