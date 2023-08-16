import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import javax.swing.JFileChooser;

import javax.swing.JOptionPane;

public class AFD {
    public static HashSet<AFD> ConjDeAFDs = new HashSet<AFD>();
    public int [][] TransicionesAFD;
    public int idAFD;
    public int row1;
    public int column1;

    public AFD(int carEdosIj,  HashSet<ConjIJ> EdosAFD){
        TransicionesAFD= new int[carEdosIj][256];
        row1=carEdosIj;
        column1=256;
        for(int i=0; i<carEdosIj;i++){
            for (ConjIJ cIJ : EdosAFD) {
                if(cIJ.Idj==i){
                    TransicionesAFD[i]=cIJ.TransicionesAFD;
                } 
            }
        }
    }
    
    public AFD(){
        idAFD=0;
        row1=0;
        
    }
    public void getInf(){
        for(int j=0; j<row1;j++){
            for(int k=0; k<column1;k++){
                System.out.print(TransicionesAFD[j][k]);
            }
            System.out.println("\n");
        }
    }

    public void creartxt() throws IOException{
        String FileName1 = new String();
        FileWriter archivo=null;
        PrintWriter escritor=null;
        JFileChooser fileChooser1 = new JFileChooser();
        fileChooser1.showOpenDialog(fileChooser1);

        try{
            FileName1 = fileChooser1.getSelectedFile().getAbsolutePath();
            archivo=new FileWriter(FileName1);
            escritor= new PrintWriter(archivo);
            escritor.println(this.row1);  
            for(int n=0; n < this.row1;n++){
                for(int p=0;p<256;p++){
               
                    escritor.print(String.valueOf(this.TransicionesAFD[n][p])+" ");
                     
                }
                escritor.println("");  
                
            }

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            archivo.close();
        }
        

    }
  
    public void LeerAFDdeArchivo (int id){
        
        //String aux = new String();
        //int col=0, row=0;
        this.idAFD=id;
        String FileName = new String();
        //Scanner entrada = null;
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.showOpenDialog(fileChooser);
        try {
            FileName = fileChooser.getSelectedFile().getAbsolutePath();                                        
            //////////
            //int row1;
            //File ruta = new File("C:/Users/garci/Desktop/Compilador/Analizador_Lexico/"+archivo);
            FileReader fr = new FileReader(FileName);
            int x=0,y=0;
            int i;
            String num = new String();
            while ((i=fr.read())!=13 || fr.read()!=10) {
                num= num + String. valueOf((char)i);            
            }
            row1 = Integer.parseInt(num);
            //System.out.println("1:"+ row1);
            num= "";
            TransicionesAFD= new int[row1][256];
            while((i=fr.read())!= -1){
                if (i==13 || i==10){
                }else{
                    if (i==32) {
                        TransicionesAFD[y][x]=Integer.parseInt(num);
                        //System.out.println(":"+TransicionesAFD[y][x]);
                        x++;
                        if (x==256) {
                            y++;
                            x=0;
                        }
                        num= ""; 
                    }else{
                        num = num+String. valueOf((char)i);
                    }   
                }  
            }
            fr.close();
            JOptionPane.showMessageDialog(null,"SE HA SELECCIONADO CORRECTAMENTE EL ARCHIVO" );
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            
        }

        
    }

    public void LeerRuta (String rut, int id){
        
        //String aux = new String();
        //int col=0, row=0;
        this.idAFD=id;
        //String FileName = new String();
        //Scanner entrada = null;
        
        
        try {
            //FileName = fileChooser.getSelectedFile().getAbsolutePath();                                        
            //////////
            //int row1;
            //File ruta = new File("C:/Users/garci/Desktop/Compilador/Analizador_Lexico/"+archivo);
            FileReader fr = new FileReader(rut);
            int x=0,y=0;
            int i;
            String num = new String();
            while ((i=fr.read())!=13 || fr.read()!=10) {
                num= num + String. valueOf((char)i);            
            }
            row1 = Integer.parseInt(num);
            //System.out.println("1:"+ row1);
            num= "";
            TransicionesAFD= new int[row1][256];
            while((i=fr.read())!= -1){
                if (i==13 || i==10){
                }else{
                    if (i==32) {
                        TransicionesAFD[y][x]=Integer.parseInt(num);
                        //System.out.println(":"+TransicionesAFD[y][x]);
                        x++;
                        if (x==256) {
                            y++;
                            x=0;
                        }
                        num= ""; 
                    }else{
                        num = num+String. valueOf((char)i);
                    }   
                }  
            }
            fr.close();
            JOptionPane.showMessageDialog(null,"SE HA SELECCIONADO CORRECTAMENTE EL ARCHIVO" );
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (NullPointerException e) {
            System.out.println("No se ha seleccionado ningún fichero");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            
        }

        
    }




}
   

