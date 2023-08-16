import java.util.List;
import java.util.Stack;

import javax.swing.JOptionPane;

import java.util.ArrayList;
import java.util.HashSet;

public class TLL1 {
    public static List<TLL1> ConjDeLL1 = new ArrayList<>();
    public String [][] TabLL1;
    public int idTLL;
    public int row1;
    public int column1;
    public List<String> indexT = new ArrayList<>();
    public List<Integer> indexlex = new ArrayList<>();
    ASGram a;
    AnalizLexico L;

    public List<String> TabAnLL1 = new ArrayList<>();

    public TLL1(ASGram a){
        TabLL1= new String[a.LVn.size()][a.LVt.size()+1];
        row1=a.LVn.size();
        column1=a.LVt.size()+1;
        indexT=a.LVt;
        this.a=a;

        for(int i=0; i<this.row1;i++){
            for (int j=0; j<this.column1;j++) {
                if (j==0) {
                    TabLL1[i][j]= a.LVn.get(i);
                }else{
                    TabLL1[i][j]="-1";
                }
            }
        }

        
    }

    public void llenarTabla(){
        for (int j = 0; j < a.NumReglas; j++) {
            for (String st : a.First(a.ArrReglas[j].LisD)) {
                if (st.equals("epsilon")) {
                    
                    for (String st2 : a.Follow(a.ArrReglas[j].InfSimbolo.cadena)) {
                        int xi2 =indexT.indexOf(st2);
                        if(xi2 != -1){
                            int ij2 = a.LVn.indexOf(a.ArrReglas[j].InfSimbolo.cadena);
                            if(ij2 != -1){
                                TabLL1[ij2][xi2+1]= String.valueOf(j+1);
                            }
                        }
                        
                    }
                }
                int xi =indexT.indexOf(st);
                if(xi != -1){
                    int ij = a.LVn.indexOf(a.ArrReglas[j].InfSimbolo.cadena);
                    if(ij != -1){
                        TabLL1[ij][xi+1]= String.valueOf(j+1);
                    }
                }
                
                
            }
             
            
        }
    }

    public boolean analizarSin(AFD afd, String sigma){
        int i=0;
        L = new AnalizLexico(sigma,afd);
        Stack<ClaseNodo> Pila = new Stack<>();
        
        int token;
        int indiceX;
        int indiceY;
        int regla=0;
        String Spila="";
        String Sregla="";

        Stack<nodoArb> PilaArbol = new Stack<>();
        

        TabAnLL1.clear();
        

        Pila.push(this.a.ArrReglas[0].InfSimbolo);
        nodoArb raiz= new nodoArb(0, this.a.ArrReglas[0].InfSimbolo.cadena, false, 0);
        Arbol newArbol = new Arbol(raiz);
        nodoArb auxNA;
        PilaArbol.push(raiz);
        
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

                    Spila="$";
                    for (int k = 0; k < Pila.size(); k++) {
                        //JOptionPane.showMessageDialog(null,"for 1: "+k);
                        Spila= Spila+" "+Pila.elementAt(k).cadena;
                       // JOptionPane.showMessageDialog(null,"spila: "+Spila);
                    }
                    Sregla ="R "+ String.valueOf(regla)+","+ a.ArrReglas[regla-1].InfSimbolo.cadena+"->";
                    for (int w = 0; w < a.ArrReglas[regla-1].LisD.size(); w++) {
                        //JOptionPane.showMessageDialog(null,"for 2: "+w);

                        Sregla= Sregla+" "+ a.ArrReglas[regla-1].LisD.get(w).cadena;

                        //JOptionPane.showMessageDialog(null,"sregla: "+Sregla);
                    }

                    TabAnLL1.add(Spila);

                    TabAnLL1.add(sigma.substring(L.IniLexema, sigma.length())+"$");
                    TabAnLL1.add(Sregla);
                    //JOptionPane.showMessageDialog(null,Spila+"  "+ sigma.substring(L.IniLexema, sigma.length())+"$  " + Sregla );
                    Pila.pop();
                    auxNA= PilaArbol.pop();

                    if (!a.ArrReglas[regla-1].LisD.get(0).cadena.equals("epsilon")) {
                        for (int j = 0; j < a.ArrReglas[regla-1].LisD.size(); j++) {
                            Pila.push(a.ArrReglas[regla-1].LisD.get(a.ArrReglas[regla-1].LisD.size()-1-j));

                            PilaArbol.push(newArbol.agregarNodo(a.ArrReglas[regla-1].LisD.get(a.ArrReglas[regla-1].LisD.size()-1-j).cadena, auxNA.nivel+1));
                            auxNA.insertarHijo(PilaArbol.peek());
                            //JOptionPane.showMessageDialog(null,Pila.peek().cadena + Pila.size());
                        }   
                    }else{
                        auxNA.insertarHijo(newArbol.agregarNodo(a.ArrReglas[regla-1].LisD.get(0).cadena, auxNA.nivel+1));
                        auxNA.Hijos.get(auxNA.Hijos.size()-1).hoja=true;
                    } 
                } else { 
                    return false;
                } 
            }else{// terminal
                //JOptionPane.showMessageDialog(null,"  terminal" +Pila.peek().cadena+indexT.get(indexlex.indexOf(token)));

                
                if (Pila.peek().cadena.equals(indexT.get(indexlex.indexOf(token)))) {
                    
                    Spila="$";
                    for (int k = 0; k < Pila.size(); k++) {
                        //JOptionPane.showMessageDialog(null,"for 1: "+k);
                        Spila= Spila+" "+Pila.elementAt(k).cadena;
                       // JOptionPane.showMessageDialog(null,"spila: "+Spila);
                    }
                    Sregla ="POP ";

                    TabAnLL1.add(Spila);

                    TabAnLL1.add(sigma.substring(L.IniLexema, sigma.length())+"$");
                    TabAnLL1.add(Sregla);
                   // JOptionPane.showMessageDialog(null,Spila+"  "+ sigma.substring(L.IniLexema, sigma.length())+"$  " + Sregla );
                    Pila.pop();
                    auxNA= PilaArbol.pop();
                    auxNA.hoja=true;
                    auxNA.cadena=L.Lexema;


                    token = L.yylex();
                } else {
                    //JOptionPane.showMessageDialog(null," no terminal son diferentes " );
                    return false;
                }

            }
    
        }

        if (token==0 && Pila.size()==0) {
            JOptionPane.showMessageDialog(null,"niveles "+newArbol.niveles+ "  id totales "+ newArbol.indexid );
            for (int j = 0; j < newArbol.nodos.size(); j++) {
                JOptionPane.showMessageDialog(null,"nodo "+newArbol.nodos.get(j).id+ "  cadena "+ newArbol.nodos.get(j).cadena+"   nivel: "+newArbol.nodos.get(j).nivel +" hoja: "+ newArbol.nodos.get(j).hoja+ "   num hijos: "+ newArbol.nodos.get(j).numHijos);
                System.out.println("nodo "+newArbol.nodos.get(j).id+ "  cadena "+ newArbol.nodos.get(j).cadena+"   nivel: "+newArbol.nodos.get(j).nivel +" hoja: "+ newArbol.nodos.get(j).hoja+ "   num hijos: "+ newArbol.nodos.get(j).numHijos);
            }
            
            return true; 

        }

        return false;
    }

    /*/

    public Boolean AsociarToken(){

    } */
    
}
