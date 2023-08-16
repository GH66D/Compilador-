import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.lang.Class;


public class Ventana extends JFrame {
    List<AFN> AFNaux = new ArrayList<>();
    List<AFN> AFNaux1 = new ArrayList<>();
    public JPanel panel = new JPanel(); //principal
    public JPanel panel2 = new JPanel(); //opciones
    public JPanel panel3 = new JPanel(); //area
    public JPanel panelAFN = new JPanel(); //opciones AFN
    public JPanel panelAS = new JPanel(); //OPCOON SINTACTICOs
    public JPanel panelBasico = new JPanel(); //vista Basico
    public JPanel panelUnion = new JPanel();
    public JPanel panelConc = new JPanel();
    public JPanel panelCerrP = new JPanel();
    public JPanel panelCerrK = new JPanel();
    public JPanel panelOpci = new JPanel();
    public JPanel panelUAL = new JPanel();
    public JPanel panelERAFN = new JPanel();
    public JPanel panelCAFNAFD = new JPanel();
    public JPanel panelACadena = new JPanel();
    public JPanel panelProbAL = new JPanel();
    public JPanel panelSelAchi = new JPanel();

    public JPanel panelMatriz = new JPanel();
    public JPanel panelASER = new JPanel();
    public JPanel panelASGram = new JPanel();
    public JPanel panelASLL1 = new JPanel();


    //Menu principal
    public JButton boton1 = new JButton("AFN's");
    public JButton boton2 = new JButton("Sintactico");

    //Menu AFN's
    public    JButton bBsico = new JButton("Basico");
    public    JButton bUnir = new JButton("Unir");
    public    JButton bConca = new JButton("Concatenar");
    public    JButton bCerrP = new JButton("Cerradura +");
    public    JButton bCerrK = new JButton("Cerradura *");
    public    JButton bOpci = new JButton("Opcional");
    public    JButton bERAFN = new JButton("ER->AFN");
    public    JButton bUALex = new JButton("Union para Analizador Lexico");
    public    JButton bAFNaAFD = new JButton("Convertir AFN a AFD");
    public    JButton bAnCad = new JButton("Analizar una cadena");
    public    JButton bProbAL = new JButton("Probar analizador Lexico");

    //MENU sintactico
    public    JButton bMatriz = new JButton("Matriz");
    public    JButton bER = new JButton("Exprecion Regular");
    public    JButton bGram = new JButton("Gramaticas");
    public    JButton bLL1 = new JButton("LL(1)");


    //Text area para ingresar datos de AFN basico
    public    JTextField cajaTIDBas = new JTextField();//id AFN
    public    JTextField cajaTCIBas = new JTextField();//caracter inferior
    public    JTextField cajaTCSBas = new JTextField();//Caracter superior
    public    JTextArea cadenA = new JTextArea();//Cadena a analizar
    public    JTextArea cadenMatriz = new JTextArea();  //Cadena matriz a analizar
    public    JTextField idAFNER = new JTextField();//id AFN DE ER

    //botones para realizar las operacuines de "AFN's" respectivamente 
    public    JButton bCreAFNB = new JButton("Crear AFN");
    public    JButton bUnirAFN = new JButton("Unir AFN's");
    public    JButton bConcAFN = new JButton("Concatenar AFN's");
    public    JButton bCerrPAFN = new JButton("APLICAR CERRADURA POSITIVA");
    public    JButton bCerrKAFN = new JButton("APLICAR CERRADURA KLEEN");
    public    JButton bOpciAFN = new JButton("APLICAR CERRADURA OPCIONAL");
    public    JButton bCrearUAL = new JButton("UNIR AFN'S");
    public    JButton bCrearAFNaAFD = new JButton("Convertir AFN a AFD");
    public    JButton bCargarArchivo = new JButton("Seleccionar archivo");
    public    JButton bGuardarArchivo = new JButton("Guardar AFD");
    public    JButton bAnalizar = new JButton("Analizar Lexicamente");

    //botones para realizar operaciones sintactico
    public    JButton bAnalizarMat = new JButton("Analizar Matriz");
    public    JButton bAnalizarSER = new JButton("Analizar Sintacticament ER");
    public    JButton bAnalizarSemER = new JButton("Generar AFN de la ER");
    public    JButton bAnalizarGram = new JButton("Analizar Gramatica");
    public    JButton bGenTabLL1 = new JButton("Generar tabla");
    public    JButton bAsTok = new JButton("Asociar Tokens");
    public    JButton bAnalizarSLL1 = new JButton("Analizar Sintacticamente");
    


    

   // private JTextField cajaTexto = new JTextField();
    // JLabel etiqueta = new JLabel();

    //listas desplegables que muestran los id de los AFNs
    public static JComboBox<Integer> listaDesplegable = new JComboBox<Integer>();
    public static JComboBox<Integer> listaDesplegable2 = new JComboBox<Integer>();

    //tabla para union especial
    public static JTable tabUnionE;
    public JScrollPane s1;
    public String cab[]= {"SEL","ID AFN","TOKEN"};
    public Object datos[] = new Object[cab.length];
    public DefaultTableModel mt;

    //tabla AFD
    public static JTable tAFD;
    public JScrollPane s2;
    public String cab2[]= new String [256];
    public Object datos2[] = new Object[cab2.length];
    public DefaultTableModel mt2;
    
    //tabla analisis Lexico
    public static JTable tAL;
    public JScrollPane s3 = new JScrollPane();
    
    public String cab3[]= {"Lexema","Token"};
    public Object datos3[] = new Object[cab3.length];
    public DefaultTableModel mt3;
   

    //Tabla NO terninaales

    public static JTable tVn;
    public JScrollPane s4;
    public String cab4[]= {"No terminales"};
    public Object datos4[] = new Object[cab4.length];
    public DefaultTableModel mt4;

    //Tabla Terminales
    public static JTable tVt;
    public JScrollPane s5;
    public String cab5[]= {"Terminales","TOKEN"};
    public Object datos5[] = new Object[cab5.length];
    public DefaultTableModel mt5;

    //Tabla LL1
    public static JTable tLL1;
    public JScrollPane s6;
    public DefaultTableModel mt6;


    //Tabla Analisis LL1
    public static JTable tAnalisisLL1;
    public JScrollPane s7;
    public String cab7[]= {"Pila","Cadena","Accion"};
    public Object datos7[] = new Object[cab7.length];
    public DefaultTableModel mt7;

    ///////
    public JLabel tituloProbAL = new JLabel("",SwingConstants.CENTER);

    //
    JCheckBox ascii = new JCheckBox("Usar codigo ascic", null, false);
  
/************************CONSTRUCTOR**************************/
    public Ventana(){
        this.setSize(1000,535);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Analizador lexico");
        setLocationRelativeTo(null);

        iniciarComponentes();
    }
/************************METODOS*************************** */
    private void iniciarComponentes(){
        
        colocarPanel();
        boton1.setBounds(0,0,110,40);
        boton1.setBackground(new Color(34, 175, 255 ));
        boton2.setBounds(110,0,110,40);
        boton2.setBackground(Color.ORANGE);
        panel.add(boton1);
        panel.add(boton2);

        panel2.setBounds(0,40,220,460);
        panel2.setBackground(Color.black); //color panel
        panel2.setLayout(null);
        panel.add(panel2);

        panel3.setBounds(220,0,780,500);
        panel3.setBackground(Color.gray); //color panel
        panel3.setLayout(null);
        panel.add(panel3);
        PanAFN();
        eventoBottAFN();

        PanAS();
        eventoBottAS();

        PanBasico();
        eventoBottBasico();
        PanUnion();
        eventoBottUnion();
        PanConc();
        eventoBottConc();
        PanCerrP();
        eventoBottCerrP();
        PanCerrK();
        eventoBottCerrK();
        PanOpci();
        eventoBottOpci();
        PanUal();
        eventoBottUALex();
        PanCAFNAFD();
        eventoBottAFNaAFD();
        PanProbAL();
        eventoBottProbAL();
        eventoBottAnCad();

        PanSelArchi();

        PanMatriz();
        eventoBottMatriz();
        PanASER();
        eventoBottESER();
        PanASGran();
        eventoBottGram();
        PanASLL1();
        eventoBottLL1();

        
        


        eventoBottLex();
        eventobCreAFNB();
        EventobUnir();
        EventoConc();
        EventoCerrP(); 
        EventoCerrK();
        EventoOpci();
        EventoUEAL();
        EventoAFNaAFD();
        EventoGuardarAFD();
        EventoSelArchi();
        EventoAnalizar();

        EventoAnalizarMatS();
        EventoAnalizarSER();
        EventoGenER();
        EventoAnalizarGram();
        EventoGenTabLL1();
        EventoAsociarToken();
        EventoAnLexLL1();

        TabUAFNs();
        TabAFD();
        TabAL();
        TabVn();
        TabVt();
        TabLL1();
        TabAnLL1();

        int idafd = 1010;
        AFD afd = new AFD();
        afd.LeerRuta("C:/Users/garci/Desktop/Compilador/Analizador_Lexico/afdGram.txt",idafd);  
        AFD.ConjDeAFDs.add(afd);
        //cajaTIDBas.setText(null);
        /*listaDesplegable.removeAllItems();
        //listaDesplegable2.removeAllItems();
        for(AFD a : AFD.ConjDeAFDs){
            //int c =
        /// System.out.println(a.IdAFN);
            listaDesplegable.addItem(a.idAFD);
            //listaDesplegable2.addItem(a.IdAFN);
        
        }*/
    }

    /*INICIALIZACION DE PANELES */
    private void colocarPanel(){
        panel.setBackground(Color.DARK_GRAY); //color panel
        panel.setLayout(null);
        this.getContentPane().add(panel);
    }

    private void PanAFN(){
        panelAFN.setBounds(0,0,220,460);
        panelAFN.setBackground(new Color(34, 175, 255 )); //color panel
        panelAFN.setLayout(null);
        
        bBsico.setBounds(0,10,220,40);
        bUnir.setBounds(0,50,220,40);
        bConca.setBounds(0,90,220,40);
        bCerrP.setBounds(0,130,220,40);
        bCerrK.setBounds(0,170,220,40);
        bOpci.setBounds(0,210,220,40);
        bERAFN.setBounds(0,250,220,40);
        bUALex.setBounds(0,290,220,40);
        bAFNaAFD.setBounds(0,330,220,40);
        bAnCad.setBounds(0,370,220,40);
        bProbAL.setBounds(0,410,220,40);

        bBsico.setBackground(new Color(162, 221, 255));
        bUnir.setBackground(new Color(162, 221, 255));
        bConca.setBackground(new Color(162, 221, 255));
        bCerrP.setBackground(new Color(162, 221, 255));
        bCerrK.setBackground(new Color(162, 221, 255));
        bOpci.setBackground(new Color(162, 221, 255));
        bERAFN.setBackground(new Color(162, 221, 255));
        bUALex.setBackground(new Color(162, 221, 255));
        bAFNaAFD.setBackground(new Color(162, 221, 255));
        bAnCad.setBackground(new Color(162, 221, 255));
        bProbAL.setBackground(new Color(162, 221, 255));

        panelAFN.add(bBsico);
        panelAFN.add(bUnir);
        panelAFN.add(bConca);
        panelAFN.add(bCerrP);
        panelAFN.add(bCerrK);
        panelAFN.add(bOpci);
        panelAFN.add(bERAFN);
        panelAFN.add(bUALex);
        panelAFN.add(bAFNaAFD);
        panelAFN.add(bAnCad);
        panelAFN.add(bProbAL);
        //this.getContentPane().add(panel);
    }

    private void PanAS(){
        panelAS.setBounds(0,0,220,460);
        panelAS.setBackground(Color.ORANGE); //color panel
        panelAS.setLayout(null);

        bMatriz.setBounds(0,10,220,40);
        bER.setBounds(0,50,220,40);
        bGram.setBounds(0,90,220,40);
        bLL1.setBounds(0,130,220,40);

        bMatriz.setBackground(new Color(255, 219, 124 ));
        bER.setBackground(new Color(255, 219, 124 ));
        bGram.setBackground(new Color(255, 219, 124 ));
        bLL1.setBackground(new Color(255, 219, 124 ));

        

        panelAS.add(bMatriz); 
        panelAS.add(bER);
        panelAS.add(bGram); 
        panelAS.add(bLL1);        
        
    }
    private void PanBasico(){
        panelBasico.setBounds(0,0,780,500);
        panelBasico.setBackground(Color.gray); //color panel
        panelBasico.setLayout(null);
        
        JLabel etiqueta1 = new JLabel("CREACION DE AFN BASICO",SwingConstants.CENTER);
        etiqueta1.setBounds(140,10,500,30);
        etiqueta1.setOpaque(false);//fondode etiqueta visible
        etiqueta1.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        etiqueta1.setFont(new Font("arial",Font.BOLD,20));
        panelBasico.add(etiqueta1);

        JLabel etiqueta2 = new JLabel("ID del AFN",SwingConstants.CENTER);
        etiqueta2.setBounds(140,100,100,30);
        panelBasico.add(etiqueta2);


        JLabel etiqueta3 = new JLabel("Caracter Inf",SwingConstants.CENTER);
        etiqueta3.setBounds(340,100,100,30);
        panelBasico.add(etiqueta3);

        cajaTCIBas.setBounds(340,140,100,30);
        panelBasico.add(cajaTCIBas);

        JLabel etiqueta4 = new JLabel("Caracter Sup",SwingConstants.CENTER);
        etiqueta4.setBounds(540,100,100,30);
        panelBasico.add(etiqueta4);

        cajaTCSBas .setBounds(540,140,100,30);
        panelBasico.add(cajaTCSBas );

        JLabel imagBasic = new JLabel(new ImageIcon("images/basico.jpg"));
        imagBasic.setBounds(140,140,500,300);
        panelBasico.add(imagBasic);

        bCreAFNB.setBounds(340,420,100,35);
        panelBasico.add(bCreAFNB);

        ascii.setBounds(140, 60, 200, 20);
        ascii.setBackground(Color.gray);
        panelBasico.add(ascii);
        //this.getContentPane().add(panel);
    }
    private void PanUnion(){
        panelUnion.setBounds(0,0,780,500);
        panelUnion.setBackground(Color.gray); //color panel
        panelUnion.setLayout(null);
        

        JLabel tituloUnion = new JLabel("UNIR AFN's",SwingConstants.CENTER);
        tituloUnion.setBounds(140,10,500,30);
        tituloUnion.setOpaque(false);//fondode etiqueta visible
        tituloUnion.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloUnion.setFont(new Font("arial",Font.BOLD,20));
        panelUnion.add(tituloUnion);
        
        JLabel imagUnir = new JLabel(new ImageIcon("images/unir.jpg"));
        imagUnir.setBounds(150,140,500,300);
        panelUnion.add(imagUnir);

        bUnirAFN.setBounds(340,450,100,35);
        panelUnion.add(bUnirAFN);;
        

        //panelUnion.add(listaDesplegable);
    }
    private void PanConc(){
        panelConc.setBounds(0,0,780,500);
        panelConc.setBackground(Color.gray); //color panel
        panelConc.setLayout(null);
        

        JLabel tituloConc = new JLabel("Concatenar AFN's",SwingConstants.CENTER);
        tituloConc.setBounds(140,10,500,30);
        tituloConc.setOpaque(false);//fondode etiqueta visible
        tituloConc.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloConc.setFont(new Font("arial",Font.BOLD,20));
        panelConc.add(tituloConc);
        
        JLabel imagUnir = new JLabel(new ImageIcon("images/con.jpg"));
        imagUnir.setBounds(30,140,720,300);
        panelConc.add(imagUnir);
        
        bConcAFN.setBounds(305,450,170,30);
        panelConc.add(bConcAFN);;
    }
    private void PanCerrP(){
        panelCerrP.setBounds(0,0,780,500);
        panelCerrP.setBackground(Color.gray); //color panel
        panelCerrP.setLayout(null);
        

        JLabel tituloConc = new JLabel("CERRADURA POSITIVA",SwingConstants.CENTER);
        tituloConc.setBounds(140,10,500,30);
        tituloConc.setOpaque(false);//fondode etiqueta visible
        tituloConc.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloConc.setFont(new Font("arial",Font.BOLD,20));
        panelCerrP.add(tituloConc);
        
        JLabel imagCerrp = new JLabel(new ImageIcon("images/cerPos.jpg"));
        imagCerrp.setBounds(30,140,720,300);
        panelCerrP.add(imagCerrp);
        
        bCerrPAFN.setBounds(280,450,270,30);
        panelCerrP.add(bCerrPAFN);

       
    }
    private void PanCerrK(){
        panelCerrK.setBounds(0,0,780,500);
        panelCerrK.setBackground(Color.gray); //color panel
        panelCerrK.setLayout(null);
        

        JLabel tituloConc = new JLabel("CERRADURA KLEEN",SwingConstants.CENTER);
        tituloConc.setBounds(140,10,500,30);
        tituloConc.setOpaque(false);//fondode etiqueta visible
        tituloConc.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloConc.setFont(new Font("arial",Font.BOLD,20));
        panelCerrK.add(tituloConc);
        
        JLabel imagCerrp = new JLabel(new ImageIcon("images/cerK.jpg"));
        imagCerrp.setBounds(30,140,720,300);
        panelCerrK.add(imagCerrp);
        
        bCerrKAFN.setBounds(280,450,270,30);
        panelCerrK.add(bCerrKAFN);
    }
    private void PanOpci(){
        panelOpci.setBounds(0,0,780,500);
        panelOpci.setBackground(Color.gray); //color panel
        panelOpci.setLayout(null);
        
        JLabel tituloConc = new JLabel("CERRADURA OPCIONAL",SwingConstants.CENTER);
        tituloConc.setBounds(140,10,500,30);
        tituloConc.setOpaque(false);//fondode etiqueta visible
        tituloConc.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloConc.setFont(new Font("arial",Font.BOLD,20));
        panelOpci.add(tituloConc);
        
        JLabel imagCerrp = new JLabel(new ImageIcon("images/opc.jpg"));
        imagCerrp.setBounds(30,140,720,300);
        panelOpci.add(imagCerrp);
        
        bOpciAFN.setBounds(280,450,270,30);
        panelOpci.add(bOpciAFN);
    }
    private void PanUal(){
        panelUAL.setBounds(0,0,780,500);
        panelUAL.setBackground(Color.gray); //color panel
        panelUAL.setLayout(null);
        
        JLabel tituloUAL = new JLabel("UNION ESPECIAL PARA ANALIZADOR LEXICO",SwingConstants.CENTER);
        tituloUAL.setBounds(140,10,500,30);
        tituloUAL.setOpaque(false);//fondode etiqueta visible
        tituloUAL.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloUAL.setFont(new Font("arial",Font.BOLD,20));
        panelUAL.add(tituloUAL);

        JLabel etiqueta5 = new JLabel("ID del AFN",SwingConstants.CENTER);
        etiqueta5.setBounds(450,375,100,30);
        panelUAL.add(etiqueta5);

        JLabel imagUEs = new JLabel(new ImageIcon("images/UEs.jpg"));
        imagUEs.setBounds(5,100,420,250);
        panelUAL.add(imagUEs);
        
        
        bCrearUAL.setBounds(280,450,270,30);
        panelUAL.add(bCrearUAL);
    }
    private void PanCAFNAFD(){
        panelCAFNAFD.setBounds(0,0,780,500);
        panelCAFNAFD.setBackground(Color.gray); //color panel
        panelCAFNAFD.setLayout(null);
        
        JLabel tituloCAFNAFD = new JLabel("CONVERTIR AFN A AFD",SwingConstants.CENTER);
        tituloCAFNAFD.setBounds(140,10,500,30);
        tituloCAFNAFD.setOpaque(false);//fondode etiqueta visible
        tituloCAFNAFD.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloCAFNAFD.setFont(new Font("arial",Font.BOLD,20));
        panelCAFNAFD.add(tituloCAFNAFD);

        JLabel etiqueta10 = new JLabel("AFN a convertir a AFD",SwingConstants.CENTER);
        etiqueta10.setBounds(20,50,150,30);
        panelCAFNAFD.add(etiqueta10);

        JLabel etiqueta11 = new JLabel("ID del AFD",SwingConstants.CENTER);
        etiqueta11.setBounds(290,50,100,30);
        panelCAFNAFD.add(etiqueta11);
        
       /*  JLabel imagCerrp = new JLabel(new ImageIcon("C:/Users/garci/Desktop/Compilador/Analizador_Lexico/opc.jpg"));
        imagCerrp.setBounds(30,140,720,300);
        panelCAFNAFD.add(imagCerrp);*/
        
        bCrearAFNaAFD.setBounds(550,50,180,30);
        panelCAFNAFD.add(bCrearAFNaAFD);
    }

    private void PanProbAL(){
        panelProbAL.setBounds(0,0,780,500);
        panelProbAL.setBackground(Color.gray); //color panel
        panelProbAL.setLayout(null);
        
        
        tituloProbAL.setBounds(140,10,500,30);
        tituloProbAL.setOpaque(false);//fondode etiqueta visible
        tituloProbAL.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloProbAL.setFont(new Font("arial",Font.BOLD,20));
        panelProbAL.add(tituloProbAL);
        panelProbAL.add(panelSelAchi);

        JLabel etiqueta15 = new JLabel("AFD a utilizar:",SwingConstants.LEFT);
        etiqueta15.setBounds(30,138,150,30);
        panelProbAL.add(etiqueta15);
        JLabel etiqueta16 = new JLabel("Ingrese la cadena a analizar:",SwingConstants.LEFT);
        etiqueta16.setBounds(30,170,200,30);
        panelProbAL.add(etiqueta16);

        bGuardarArchivo.setBounds(300,450,180,30);
        panelProbAL.add(bGuardarArchivo);

        
        bAnalizar.setBounds(30,380,400,30);
        panelProbAL.add(bAnalizar);
        

    }
    private void PanSelArchi(){
        panelSelAchi.setBounds(0,50,780,70);
        panelSelAchi.setBackground(Color.DARK_GRAY); //color panel
        panelSelAchi.setLayout(null);

        bCargarArchivo.setBounds(400,20,180,30);
        panelSelAchi.add(bCargarArchivo);

        //cajaTIDBas.setText(null);
        
        JLabel etiqueta17 = new JLabel("ID AFD:",SwingConstants.LEFT);
        etiqueta17.setBounds(30,20,150,30);
        etiqueta17.setForeground(Color.white);
        panelSelAchi.add(etiqueta17);
        

    }

    private void TabUAFNs(){
        //mt.setColumnIdentifiers(cab);
        //DefaultTableModel mt ;
        //mt = new DefaultTableModel();
        //mt.setColumnIdentifiers(cab);
        tabUnionE = new JTable();
        tabUnionE.setBounds(450,100, 300, 250);
        tabUnionE.setDefaultRenderer(Object.class, new Render());
        boolean [] editable ={true,false,true};
        var types = new Class[]{java.lang.Boolean.class,java.lang.Object.class,java.lang.Object.class};

        mt = new DefaultTableModel(cab,0){
            public Class getColumnClass(int i){
                return types[i];
            }
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        LimpiarTabla(tabUnionE,mt);
        tabUnionE.setModel(mt);
        s1 = new JScrollPane();
        s1.setBounds(450,100, 300, 250);
        s1.setViewportView(tabUnionE);
        panelUAL.add(s1);
    }

    

    public void LimpiarTabla(JTable tabla, DefaultTableModel modeloTabla){
        if(modeloTabla.getRowCount()>0){
            for(int i=0; i< tabla.getRowCount();i++){
                modeloTabla.removeRow(i);
                i-=1;
            }
        }
    }

    private void TabAFD(){
        //mt.setColumnIdentifiers(cab);
        //DefaultTableModel mt ;
        //mt = new DefaultTableModel();
        //mt.setColumnIdentifiers(cab);
        tAFD = new JTable();
        tAFD.setBounds(450,100, 1000, 250);
        //tAFD.setDefaultRenderer(Object.class, new Render());
        boolean [] editable = new boolean [256];
        for(int k=0;k<256;k++){
            editable[k]= false;
        }

        var types = new Class[256];
        for(int l=0;l<256;l++){
            types[l]= java.lang.Object.class;
        }
        
        for(int h=0; h<255;h++){
            cab2[h]=String.valueOf((char)h);
        }
        cab2[255]="TOKEN";
        

        mt2 = new DefaultTableModel(cab2,0){
            public Class getColumnClass(int i){
                return types[i];
            }
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        LimpiarTabla(tAFD,mt2);
        tAFD.setModel(mt2);
        tAFD.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tAFD.doLayout();
        s2 = new JScrollPane();
        s2.setBounds(3,100, 760, 250);
        s2.setViewportView(tAFD);
        panelCAFNAFD.add(s2);
    }
    private void TabAL(){
        tAL = new JTable();
        tAL.setBounds(450,200, 300, 210);
       // tAL.setDefaultRenderer(Object.class, new Render());
        boolean [] editable3 ={false,false};
        var types = new Class[]{java.lang.Object.class,java.lang.Object.class};

        mt3 = new DefaultTableModel(cab3,0){
            public Class getColumnClass(int i){
                return types[i];
            }
            public boolean isCellEditable(int row, int column){
                return editable3[column];
            }
        };
        LimpiarTabla(tAL,mt3);
        tAL.setModel(mt3);
        //tAL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tAL.doLayout();
        
        

    }

    private void TabVn(){
        tVn = new JTable();
        tVn.setBounds(450,200, 300, 210);
       // tAL.setDefaultRenderer(Object.class, new Render());
        boolean [] editable4 ={false};
        var types = new Class[]{java.lang.Object.class};

        mt4 = new DefaultTableModel(cab4,0){
            public Class getColumnClass(int i){
                return types[i];
            }
            public boolean isCellEditable(int row, int column){
                return editable4[column];
            }
        };
        LimpiarTabla(tVn,mt4);
        tVn.setModel(mt4);
        //tAL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        tVn.doLayout();
        s4 = new JScrollPane();
        s4.setBounds(350,50, 90, 180);
        s4.setViewportView(tVn);
        panelASLL1.add(s4);

    }

    private void TabVt(){
        //mt.setColumnIdentifiers(cab);
        //DefaultTableModel mt ;
        //mt = new DefaultTableModel();
        //mt.setColumnIdentifiers(cab);
        tVt = new JTable();
        tVt.setBounds(450,100, 300, 250);
        tVt.setDefaultRenderer(Object.class, new Render());
        boolean [] editable ={false,true};
        var types = new Class[]{java.lang.Object.class,java.lang.Object.class};

        mt5 = new DefaultTableModel(cab5,0){
            public Class getColumnClass(int i){
                return types[i];
            }
            public boolean isCellEditable(int row, int column){
                return editable[column];
            }
        };
        LimpiarTabla(tVt,mt5);
        tVt.setModel(mt5);
        s5 = new JScrollPane();
        s5.setBounds(450,50, 140, 180);
        s5.setViewportView(tVt);
        panelASLL1.add(s5);
    }


    private void TabLL1(){
        //mt.setColumnIdentifiers(cab);
        //DefaultTableModel mt ;
        //mt = new DefaultTableModel();
        //mt.setColumnIdentifiers(cab);
        tLL1 = new JTable();
        tLL1.setBounds(450,100, 1000, 250);
        //tAFD.setDefaultRenderer(Object.class, new Render());
        
        
        //LimpiarTabla(tLL1,mt6);
        
        s6 = new JScrollPane();
        s6.setBounds(3,270, 400, 200);
        s6.setViewportView(tLL1);
        panelASLL1.add(s6);
    }


    private void TabAnLL1(){
        tAnalisisLL1 = new JTable();
        tAnalisisLL1.setBounds(450,200, 300, 210);
       // tAL.setDefaultRenderer(Object.class, new Render());
        boolean [] editable7 ={false,false,false};
        var types = new Class[]{java.lang.Object.class,java.lang.Object.class,java.lang.Object.class};

        mt7 = new DefaultTableModel(cab7,0){
            public Class getColumnClass(int i){
                return types[i];
            }
            public boolean isCellEditable(int row, int column){
                return editable7[column];
            }
        };
        LimpiarTabla(tAnalisisLL1,mt7);
        tAnalisisLL1.setModel(mt7);
        //tAL.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
        //tAnalisisLL1.doLayout();
        
        s7 = new JScrollPane();
        s7.setBounds(410,350, 330, 120);
        s7.setViewportView(tAnalisisLL1);
        panelASLL1.add(s7);
    }


    private void PanMatriz(){
        panelMatriz.setBounds(0,0,780,500);
        panelMatriz.setBackground(Color.gray); //color panel
        panelMatriz.setLayout(null);
        
        
        JLabel tituloMatriz = new JLabel("Matriz",SwingConstants.CENTER);
        tituloMatriz.setBounds(140,10,500,30);
        tituloMatriz.setOpaque(false);//fondode etiqueta visible
        tituloMatriz.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloMatriz.setFont(new Font("arial",Font.BOLD,20));
        panelMatriz.add(tituloMatriz);

        //panelMatriz.add(panelSelAchi);
        JLabel etiqueta20 = new JLabel("AFD a utilizar:",SwingConstants.LEFT);
        etiqueta20.setBounds(30,138,150,30);
        panelMatriz.add(etiqueta20);
        JLabel etiqueta19 = new JLabel("Ingrese la cadena a analizar:",SwingConstants.LEFT);
        etiqueta19.setBounds(30,170,200,30);
        panelMatriz.add(etiqueta19);

        
        
        bAnalizarMat.setBounds(30,380,400,30);
        panelMatriz.add(bAnalizarMat);

    }

    private void PanASER(){
        panelASER.setBounds(0,0,780,500);
        panelASER.setBackground(Color.gray); //color panel
        panelASER.setLayout(null);
        
        
        JLabel tituloASER = new JLabel("EXPRECION REGULAR",SwingConstants.CENTER);
        tituloASER.setBounds(140,10,500,30);
        tituloASER.setOpaque(false);//fondode etiqueta visible
        tituloASER.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloASER.setFont(new Font("arial",Font.BOLD,20));
        panelASER.add(tituloASER);

        //panelMatriz.add(panelSelAchi);
        JLabel etiqueta21 = new JLabel("AFD a utilizar:",SwingConstants.LEFT);
        etiqueta21.setBounds(30,138,150,30);
        panelASER.add(etiqueta21);
        JLabel etiqueta22 = new JLabel("Ingrese la cadena a analizar:",SwingConstants.LEFT);
        etiqueta22.setBounds(30,170,200,30);
        panelASER.add(etiqueta22);

        cadenMatriz.setBounds(30,200,400,180);
        //panelMatriz.add(cadenMatriz);
        bAnalizarSER.setBounds(30,380,400,30);
        panelASER.add(bAnalizarSER);
        bAnalizarSemER.setBounds(30,411,400,30);
        panelASER.add(bAnalizarSemER);

        JLabel etiqueta30 = new JLabel("ID del AFN a generar:",SwingConstants.LEFT);
        etiqueta30.setBounds(500,380,200,30);
        panelASER.add(etiqueta30);
        idAFNER.setBounds(500, 411, 150, 30);
        panelASER.add(idAFNER);

    }

    private void PanASGran(){
        panelASGram.setBounds(0,0,780,500);
        panelASGram.setBackground(Color.gray); //color panel
        panelASGram.setLayout(null);
        
        
        JLabel tituloGrsam = new JLabel("Gramatica",SwingConstants.CENTER);
        tituloGrsam.setBounds(140,10,500,30);
        tituloGrsam.setOpaque(false);//fondode etiqueta visible
        tituloGrsam.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloGrsam.setFont(new Font("arial",Font.BOLD,20));
        panelASGram.add(tituloGrsam);

        //panelMatriz.add(panelSelAchi);
        JLabel etiqueta25 = new JLabel("AFD a utilizar:",SwingConstants.LEFT);
        etiqueta25.setBounds(30,138,150,30);
        panelASGram.add(etiqueta25);
        JLabel etiqueta26 = new JLabel("Ingrese la cadena a analizar:",SwingConstants.LEFT);
        etiqueta26.setBounds(30,170,200,30);
        panelASGram.add(etiqueta26);

        
        
        bAnalizarGram.setBounds(30,380,400,30);
        panelASGram.add(bAnalizarGram);

    }

    private void PanASLL1(){
        panelASLL1.setBounds(0,0,780,500);
        panelASLL1.setBackground(Color.gray); //color panel
        panelASLL1.setLayout(null);
        
        
        JLabel tituloLL1 = new JLabel("LL1",SwingConstants.CENTER);
        tituloLL1.setBounds(140,10,500,30);
        tituloLL1.setOpaque(false);//fondode etiqueta visible
        tituloLL1.setForeground(Color.BLACK); //color letra
        //etiqueta1.setBackground(Color.WHITE);//color fondo
        tituloLL1.setFont(new Font("arial",Font.BOLD,20));
        panelASLL1.add(tituloLL1);

        //panelMatriz.add(panelSelAchi);
        JLabel etiqueta27 = new JLabel("AFD a utilizar:",SwingConstants.LEFT);
        etiqueta27.setBounds(30,138,150,30);
        panelASGram.add(etiqueta27);
        JLabel etiqueta28 = new JLabel("Ingrese la cadena a analizar:",SwingConstants.LEFT);
        etiqueta28.setBounds(30,20,200,30);
        panelASLL1.add(etiqueta28);

        
        bGenTabLL1.setBounds(20,230,320,30);
        panelASLL1.add(bGenTabLL1);

        bAsTok.setBounds(450,230,140,30);
        panelASLL1.add(bAsTok);

        bAnalizarSLL1.setBounds(410,320,165,30);
        panelASLL1.add(bAnalizarSLL1);

    }
    /***************************EVENTOS***************/


    private void eventoBottAFN(){
        ActionListener oyente = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel2.removeAll();
                panel2.add(panelAFN,BorderLayout.CENTER);
                panel2.revalidate();
                panel2.repaint();

            }

        };
        boton1.addActionListener(oyente);
    }

    private void eventoBottAS(){
        ActionListener oyente51 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel2.removeAll();
                panel2.add(panelAS,BorderLayout.CENTER);
                panel2.revalidate();
                panel2.repaint();

            }

        };
        boton2.addActionListener(oyente51);
    }


    

    private void eventoBottBasico(){
        ActionListener oyente3 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelBasico,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();
                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(140,140,100,30);
                panelBasico.add(cajaTIDBas);

            }

        };
        bBsico.addActionListener(oyente3);
    }

    private void eventoBottLex(){
        ActionListener oyente2 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel2.removeAll();
               // panel2.add(panelAFN,BorderLayout.CENTER);
                panel2.revalidate();
                panel2.repaint();
                panel3.removeAll();
                panel3.revalidate();
                panel3.repaint();
            }

        };
        boton2.addActionListener(oyente2);
    }

    private void eventoBottConc(){
        ActionListener oyente7 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelConc,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(160,100,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelConc.add(listaDesplegable);
                listaDesplegable2.setBounds(530,100,100,30);
                listaDesplegable2.revalidate();
                listaDesplegable2.repaint();
                panelConc.add(listaDesplegable2);
            }

        };
        bConca.addActionListener(oyente7);
    }

    private void eventoBottCerrP(){
        ActionListener oyente10 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelCerrP,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(350,100,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelCerrP.add(listaDesplegable);
               
            }

        };
        bCerrP.addActionListener(oyente10);
    }

    private void eventoBottCerrK(){
        ActionListener oyente10 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelCerrK,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(350,100,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelCerrK.add(listaDesplegable);
               
            }

        };
        bCerrK.addActionListener(oyente10);
    }

    private void eventoBottOpci(){
        ActionListener oyente14 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelOpci,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(350,100,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelOpci.add(listaDesplegable);
               
            }

        };
        bOpci.addActionListener(oyente14);
    }
    private void eventoBottUnion(){
        ActionListener oyente6 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelUnion,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(160,100,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelUnion.add(listaDesplegable);
                listaDesplegable2.setBounds(530,100,100,30);
                listaDesplegable2.revalidate();
                listaDesplegable2.repaint();
                panelUnion.add(listaDesplegable2);   
            }
        };
        bUnir.addActionListener(oyente6);
    }

    private void eventoBottUALex(){
        ActionListener oyente16 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelUAL,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                LimpiarTabla(tabUnionE,mt);
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                /// System.out.println(a.IdAFN);
                    datos[0]=false;
                    datos[1]=String.valueOf(a.IdAFN);
                    datos[2]="";
                    mt.addRow(datos); 
                }
                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(550,375,100,30);
                panelUAL.add(cajaTIDBas);
              
            }
        };
        bUALex.addActionListener(oyente16);
    }

    private void eventoBottAFNaAFD(){
        ActionListener oyente20 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelCAFNAFD,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(175,50,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelCAFNAFD.add(listaDesplegable);
                
                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(380,50,100,30);
                panelCAFNAFD.add(cajaTIDBas);
                

            }

        };
        bAFNaAFD.addActionListener(oyente20);
    }

    private void eventoBottProbAL(){
        ActionListener oyente22 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelProbAL,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFD a : AFD.ConjDeAFDs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.idAFD);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(120,130,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelProbAL.add(listaDesplegable);

                panelSelAchi.setBounds(0,50,780,70);
                bCargarArchivo.setBounds(400,20,180,30);
                panelSelAchi.add(bCargarArchivo);
                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(120,20,100,30);
                panelSelAchi.add(cajaTIDBas);
                panelProbAL.add(panelSelAchi);


                

                cadenA.setBounds(30,200,400,180);
                JScrollPane sp3 = new JScrollPane(cadenA);
                cadenA.setText(null);
                sp3.setBounds(30,200,400,180);
                panelProbAL.add(sp3);
               

                LimpiarTabla(tAL, mt3);
                tituloProbAL.setText("PROBAR ANALIZADOR LEXICO");
                
                s3.setBounds(450,200, 300, 210);
                s3.setViewportView(tAL);
                panelProbAL.add(s3);

                bAnalizar.setBounds(30,380,400,30);
                panelProbAL.add(bAnalizar);

            }

        };
        bProbAL.addActionListener(oyente22);
    }

    public void eventoBottAnCad(){
        ActionListener oyente29 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelProbAL,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFD a : AFD.ConjDeAFDs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.idAFD);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(120,130,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelProbAL.add(listaDesplegable);

                panelSelAchi.setBounds(0,50,780,70);
                bCargarArchivo.setBounds(400,20,180,30);
                panelSelAchi.add(bCargarArchivo);
                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(120,20,100,30);
                panelSelAchi.add(cajaTIDBas);
                
                panelProbAL.add(panelSelAchi);
                //cadenA.setText(null);
                LimpiarTabla(tAL, mt3);

                cadenA.setBounds(30,200,400,180);
                JScrollPane sp4 = new JScrollPane(cadenA);
                cadenA.setText(null);
                sp4.setBounds(30,200,400,180);
                panelProbAL.add(sp4);

                tituloProbAL.setText("ANALIZAR CADENA");

                s3.setBounds(450,200, 300, 210);
                s3.setViewportView(tAL);
                panelProbAL.add(s3);

                bAnalizar.setBounds(30,380,400,30);
                panelProbAL.add(bAnalizar);
                

            }

        };
        bAnCad.addActionListener(oyente29);

    }

    public void eventoBottMatriz(){
        ActionListener oyente30 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelMatriz,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFD a : AFD.ConjDeAFDs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.idAFD);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(120,130,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelMatriz.add(listaDesplegable);

                panelSelAchi.setBounds(0,50,780,70);
                bCargarArchivo.setBounds(400,20,180,30);
                panelSelAchi.add(bCargarArchivo);
                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(120,20,100,30);
                panelSelAchi.add(cajaTIDBas);
                panelMatriz.add(panelSelAchi);
                cadenMatriz.setBounds(30,200,400,180);
                cadenMatriz.setText(null);
                panelMatriz.add(cadenMatriz);
                //LimpiarTabla(tAL, mt3);

                

            }

        };
        bMatriz.addActionListener(oyente30);

    }

    

    public void eventoBottESER(){
        ActionListener oyente31 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelASER,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFD a : AFD.ConjDeAFDs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.idAFD);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(120,130,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelASER.add(listaDesplegable);

                panelSelAchi.setBounds(0,50,780,70);
                bCargarArchivo.setBounds(400,20,180,30);
                panelSelAchi.add(bCargarArchivo);
                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(120,20,100,30);
                panelSelAchi.add(cajaTIDBas);
                panelASER.add(panelSelAchi);
                cadenMatriz.setBounds(30,200,400,180);
                cadenMatriz.setText(null);
                panelASER.add(cadenMatriz);
               // panelASER.add(cadenMatriz);
                //LimpiarTabla(tAL, mt3);

                

            }

        };
        bER.addActionListener(oyente31);

    }



    public void eventoBottGram(){
        ActionListener oyente66 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelASGram,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFD a : AFD.ConjDeAFDs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.idAFD);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(120,130,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelASGram.add(listaDesplegable);

                panelSelAchi.setBounds(0,50,780,70);
                bCargarArchivo.setBounds(400,20,180,30);
                panelSelAchi.add(bCargarArchivo);
                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(120,20,100,30);
                panelSelAchi.add(cajaTIDBas);


                panelASGram.add(panelSelAchi);
                cadenMatriz.setBounds(30,200,400,180);
                cadenMatriz.setText(null);
                panelASGram.add(cadenMatriz);
                //LimpiarTabla(tAL, mt3);

                

            }

        };
        bGram.addActionListener(oyente66);

    }



    public void eventoBottLL1(){
        ActionListener oyente80 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                panel3.removeAll();
                panel3.add(panelASLL1,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

               

                listaDesplegable.removeAllItems();
                //listaDesplegable2.removeAllItems();
                for(AFD a : AFD.ConjDeAFDs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.idAFD);
                    //listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(650,230,80,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelASLL1.add(listaDesplegable);


                cadenMatriz.setBounds(20,50,320,180);
                JScrollPane sp = new JScrollPane(cadenMatriz);
                cadenMatriz.setText(null);
                sp.setBounds(20,50,320,180);
                panelASLL1.add(sp);

                cadenA.setBounds(410,270,330,50);
                JScrollPane sp2 = new JScrollPane(cadenA);
                cadenA.setText(null);
                sp2.setBounds(410,270,330,50);
                panelASLL1.add(sp2);


                LimpiarTabla(tAL, mt3);
                s3.setBounds(600,50, 140, 180);
                s3.setViewportView(tAL);
                panelASLL1.add(s3);

                bCargarArchivo.setBounds(145,10,150,30);
                panelSelAchi.setBounds(450,0,320,70);
                panelASLL1.add(panelSelAchi);

                cajaTIDBas.setText(null);
                cajaTIDBas.setBounds(100,10,30,30);
                panelSelAchi.add(cajaTIDBas);

                bAnalizar.setBounds(575,320,165,30);
                panelASLL1.add(bAnalizar);
                

            }

        };
        bLL1.addActionListener(oyente80);
    }

    /////////////////////////////////////////////////

    private void eventobCreAFNB(){
        ActionListener oyente4 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                int id = Integer.parseInt(cajaTIDBas.getText());
                char carI,carS;
                if(ascii.isSelected() == true){
                    int c1 = Integer.parseInt(cajaTCIBas.getText());
                    int c2 = Integer.parseInt(cajaTCSBas.getText());

                    carI = (char) c1;
                    carS = (char) c2;
                    
                }else{
                    
                    carI = cajaTCIBas.getText().charAt(0);
                    carS = cajaTCSBas.getText().charAt(0);

                }
                
                AFN a1 = new AFN();
            
                AFN.ConjDeAFNs.add(a1.CrearAFNBasico(carI, carS));
                a1.IdAFN=id;
                JOptionPane.showMessageDialog(null,"Se ha creado el AFN con id "+ id);
                cajaTCIBas.setText(null);
                cajaTCSBas.setText(null);
                cajaTIDBas.setText(null);

               
            }

        };
        bCreAFNB.addActionListener(oyente4);
    }
    
    private void EventobUnir(){
        ActionListener oyente5 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                int id1 = Integer.parseInt(listaDesplegable2.getSelectedItem().toString());
                ArrayList<AFN> afnP1 = new ArrayList<AFN>();
              //  System.out.println(id);
            //    System.out.println(id1);
                for(AFN a: AFN.ConjDeAFNs){
                    if(id == a.IdAFN){
                        AFNaux.add(a);
                        for(AFN b: AFN.ConjDeAFNs){
                            if(id1 == b.IdAFN){
                              a.UnirAFN(b); 
                              afnP1.add(b);
                              JOptionPane.showMessageDialog(null,"Se ha Unido el AFN con id:  "+ a.IdAFN + "\ncon el AFN con ID: "+ b.IdAFN); 
                            }
                        }       
                    }
                    
                }
                AFN.ConjDeAFNs.remove(afnP1.get(0));
                listaDesplegable.removeAllItems();
                listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    listaDesplegable2.addItem(a.IdAFN);
                   
                }
                /* 
                for(AFN a: AFN.ConjDeAFNs){
                    if(id == a.IdAFN){
                        for(Character car: a.Alfabeto)
                        System.out.println(car);
                                
                    }
                    
                }*/
            } 
        };
        bUnirAFN.addActionListener(oyente5);
    }
    private void EventoConc(){
        ActionListener oyente9 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                boolean non;
                ArrayList<AFN> afnP = new ArrayList<AFN>();
                ArrayList<AFN> afnN = new ArrayList<AFN>();
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                int id1 = Integer.parseInt(listaDesplegable2.getSelectedItem().toString());
            //  System.out.println(id);
            //    System.out.println(id1);
                for(AFN a: AFN.ConjDeAFNs){
                    if(id == a.IdAFN){
                        AFNaux.add(a);
                        for(AFN b: AFN.ConjDeAFNs){
                            if(id1 == b.IdAFN){
                                a.ConcAFN(b); 
                                JOptionPane.showMessageDialog(null,"se ha concatenado el AFN "+ a.IdAFN + "\nCon el AFN  "+ b.IdAFN);
                                afnP.add(a);
                                afnN.add(b);
                                
                            }
                        }            
                    }
                }
                non=AFN.ConjDeAFNs.remove(afnN.get(0));

                listaDesplegable.removeAllItems();
                listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(160,100,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelConc.add(listaDesplegable);
                listaDesplegable2.setBounds(530,100,100,30);
                listaDesplegable2.revalidate();
                listaDesplegable2.repaint();
                panelConc.add(listaDesplegable2);

                /* 
                panel3.removeAll();
                panel3.add(panelConc,BorderLayout.CENTER);
                panel3.revalidate();
                panel3.repaint();

                listaDesplegable.removeAllItems();
                listaDesplegable2.removeAllItems();
                for(AFN a : AFN.ConjDeAFNs){
                    //int c =
                   /// System.out.println(a.IdAFN);
                    listaDesplegable.addItem(a.IdAFN);
                    listaDesplegable2.addItem(a.IdAFN);
                   
                }
                listaDesplegable.setBounds(160,100,100,30);
                listaDesplegable.revalidate();
                listaDesplegable.repaint();
                panelConc.add(listaDesplegable);
                listaDesplegable2.setBounds(530,100,100,30);
                listaDesplegable2.revalidate();
                listaDesplegable2.repaint();
                panelConc.add(listaDesplegable2);
                /* 
                for(AFN a: AFN.ConjDeAFNs){
                    if(id == a.IdAFN){
                        for(Character car: a.Alfabeto)
                        System.out.println(car);
                                
                    }
                    
                }*/
            } 
        };
        bConcAFN.addActionListener(oyente9);
    }
    private void EventoCerrP(){
        ActionListener oyente11 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){

                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                
            //  System.out.println(id);
          
                for(AFN a: AFN.ConjDeAFNs){
                    if(id == a.IdAFN){
                     a.CerrPos();
                     JOptionPane.showMessageDialog(null,"Se le ha aplicado la Cerradura Positiva\na el AFN con id:  "+ a.IdAFN );     
                    }
                    
                }
            } 
        };
        bCerrPAFN.addActionListener(oyente11);
    }
    
    private void EventoCerrK(){
        ActionListener oyente12 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
            //  System.out.println(id);
                for(AFN a: AFN.ConjDeAFNs){
                    if(id == a.IdAFN){
                     a.CerrKleen();
                     JOptionPane.showMessageDialog(null,"Se le ha aplicado la Cerradura  de Kleen\na el AFN con id:  "+ a.IdAFN );      
                    }   
                }
            } 
        };
        bCerrKAFN.addActionListener(oyente12);
    }
    private void EventoOpci(){
        ActionListener oyente15 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
            //  System.out.println(id);
                for(AFN a: AFN.ConjDeAFNs){
                    if(id == a.IdAFN){
                     a.Opcional(); 
                     JOptionPane.showMessageDialog(null,"Se le ha aplicado la Cerradura Opcional\na el AFN con id:  "+ a.IdAFN );     
                    } 
                }
            } 
        };
        bOpciAFN.addActionListener(oyente15);
    }

    private void EventoUEAL(){

        ActionListener oyente19 = new ActionListener(){
            @Override

            public void actionPerformed(ActionEvent ae){
                int ide = Integer.parseInt(cajaTIDBas.getText());
                AFN e1= new AFN();
                e1.IdAFN= ide;
                
                ArrayList<AFN> afnP = new ArrayList<AFN>();
                
                Boolean b1=true,b2=true;
                int cont=0;
                String resultado ="";
                for(int i=0; i<tabUnionE.getRowCount(); i++){
                    String sel= String.valueOf(tabUnionE.getValueAt(i, 0));
                    int id= Integer.parseInt(String.valueOf(tabUnionE.getValueAt(i, 1)));
                    String tok= String.valueOf(tabUnionE.getValueAt(i, 2));
                    if (sel == "true" && tok==""){
                        b1=false; 
                    } 

                    if (sel == "true"){
                        cont ++; 
                    }
                    /*if (sel == "true" && b2==true){
                        for(AFN a: AFN.ConjDeAFNs){
                            if(id == a.IdAFN){
                                afnP.add(a);
                                //tabUnionE.setValueAt(, i, );
                            } 
                        }
                        b2=false; 
                    }*/
                    resultado += "sel "+ sel + " id "+ id + " tok "+ tok +"\n";
                    

                }
                if(cont<1){
                    JOptionPane.showMessageDialog(null,"Debe elegir por lo menos 1 AFN's");
                }else if (b1==false){
                    JOptionPane.showMessageDialog(null,"hay algun AFN sin token");
                }else{
                    for(int i=0; i<tabUnionE.getRowCount(); i++){
                        String sel= String.valueOf(tabUnionE.getValueAt(i, 0));
                        int id= Integer.parseInt(String.valueOf(tabUnionE.getValueAt(i, 1)));
                        int tok= Integer.parseInt(String.valueOf(tabUnionE.getValueAt(i, 2)));
                        for(AFN a: AFN.ConjDeAFNs){
                            if(id == a.IdAFN){
                                e1.UnionEspecialAFNs(a, tok);
                                JOptionPane.showMessageDialog(null,"afn p" + e1.IdAFN +"  se unio con " + a.IdAFN  );
                                afnP.add(a);
                                
                            } 
                        }
                    }
                    JOptionPane.showMessageDialog(null,resultado);
                    for(int g=0;g<afnP.size();g++){
                        AFN.ConjDeAFNs.remove(afnP.get(g));
                    }
                    AFN.ConjDeAFNs.add(e1);
                    LimpiarTabla(tabUnionE,mt);
                    for(AFN a : AFN.ConjDeAFNs){
                        //int c =
                    /// System.out.println(a.IdAFN);
                        datos[0]=false;
                        datos[1]=String.valueOf(a.IdAFN);
                        datos[2]="";
                        mt.addRow(datos); 
                    }
                    cajaTIDBas.setText(null);

                }
                
            } 
        };
        bCrearUAL.addActionListener(oyente19);

    }

    private void EventoAFNaAFD(){
        ActionListener oyente22 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                int afn = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                int ide = Integer.parseInt(cajaTIDBas.getText());
                ArrayList<AFD> afd1 = new ArrayList<AFD>();
            //  System.out.println(id);
                for(AFN a: AFN.ConjDeAFNs){
                    if(afn == a.IdAFN){
                     afd1.add( a.ConvAFNaAFD());
                     
                     JOptionPane.showMessageDialog(null,"Se ha convertido a AFD" );     
                    } 
                }
                afd1.get(0).idAFD=ide;
                AFD.ConjDeAFDs.add(afd1.get(0));
                afd1.get(0).getInf();

                LimpiarTabla(tAFD,mt2);
                for(int n=0; n < afd1.get(0).row1;n++){
                    for(int p=0;p<256;p++){
                   
                        datos2[p]=String.valueOf(afd1.get(0).TransicionesAFD[n][p]);
                         
                    }
                    mt2.addRow(datos2);
                }
            } 
        };
        bCrearAFNaAFD.addActionListener(oyente22);
    }

    private void EventoGuardarAFD(){
        ActionListener oyente23 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                int afd = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                //ArrayList<AFD> afn1 = new ArrayList<AFD>();
            //  System.out.println(id);
                for(AFD a: AFD.ConjDeAFDs){
                    if(afd == a.idAFD){
                        try {
                            a.creartxt();
                        } catch (IOException e) {
                            //TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                          
                    } 
                }   
            } 
        };
        bGuardarArchivo.addActionListener(oyente23);
    }

    private void EventoSelArchi(){
        ActionListener oyente23 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                
                //int afd = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                //ArrayList<AFD> afn1 = new ArrayList<AFD>();
            //  System.out.println(id);
           
                if ((cajaTIDBas.getText().length()) == 0){
                    JOptionPane.showMessageDialog(null,"INGRESE PRIMERO UN ID" );
                }else{
                    int idafd = Integer.parseInt(cajaTIDBas.getText());
                    AFD afd = new AFD();
                    afd.LeerAFDdeArchivo(idafd);  
                    AFD.ConjDeAFDs.add(afd);
                    cajaTIDBas.setText(null);
                    listaDesplegable.removeAllItems();
                    //listaDesplegable2.removeAllItems();
                    for(AFD a : AFD.ConjDeAFDs){
                        //int c =
                    /// System.out.println(a.IdAFN);
                        listaDesplegable.addItem(a.idAFD);
                        //listaDesplegable2.addItem(a.IdAFN);
                    
                    }
                }

            } 
        };
        bCargarArchivo.addActionListener(oyente23);
    }



    private void EventoAnalizar(){
        ActionListener oyente25 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                String cade = cadenA.getText();
                int y;
                
                ArrayList<AFD> afd1 = new ArrayList<AFD>();
            
                for(AFD a: AFD.ConjDeAFDs){
                    if(id == a.idAFD){
                     afd1.add( a);
                 
                    } 
                }
                LimpiarTabla(tAL, mt3);

                AnalizLexico an = new AnalizLexico(cade,  afd1.get(0));
                while((an.IndiceCaracterActual ) <= (cade.length() )){
                    
                    y=an.yylex();
                    //System.out.println(y);
                    //JOptionPane.showMessageDialog(null,y + "  " + an.Lexema);
                    if(y!=SimbolosEspeciales.ERROR){
                        datos3[1]= String.valueOf(y);
                        datos3[0]= an.Lexema;
                        mt3.addRow(datos3);
                    }  
                    if (y==SimbolosEspeciales.FIN) {
                        break;                        
                    }
                    
                }
            } 
        };
        bAnalizar.addActionListener(oyente25);
    }


    private void EventoAnalizarMatS(){
        ActionListener oyente52 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                String cade = cadenMatriz.getText();
                int y;
                boolean res;
                
                ArrayList<AFD> afd1 = new ArrayList<AFD>();
            
                for(AFD a: AFD.ConjDeAFDs){
                    if(id == a.idAFD){
                     afd1.add( a);
                 
                    } 
                }
                
                ClaseEvalMatrices ESM = new ClaseEvalMatrices(cade,afd1.get(0) );

                res = ESM.IniEvalMatrices();
                if (res == true) {
                    JOptionPane.showMessageDialog(null,"la Cadena es correcta");
                }else{
                    JOptionPane.showMessageDialog(null,"la Cadena es NO correcta");
                }
                //AnalizLexico an = new AnalizLexico(cade,  afd1.get(0));
               
            } 
        };
        bAnalizarMat.addActionListener(oyente52);
    }

    private void EventoAnalizarSER(){
        ActionListener oyente53 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                String cade = cadenMatriz.getText();
                int y;
                boolean res;
                

                ArrayList<AFD> afd2 = new ArrayList<AFD>();
            
                for(AFD a: AFD.ConjDeAFDs){
                    if(id == a.idAFD){
                     afd2.add( a);
                 
                    } 
                }
                
                ASExpR ASER = new ASExpR(cade,afd2.get(0) );
                res = ASER.IniEvalER();

                if (res == true) {
                    JOptionPane.showMessageDialog(null,"la Cadena es correcta");
                    //AFN.ConjDeAFNs.add( ASER.afn1.get(0));
                }else{
                    JOptionPane.showMessageDialog(null,"la Cadena es NO correcta");
                }
                //AnalizLexico an = new AnalizLexico(cade,  afd1.get(0));
               
            } 
        };
        bAnalizarSER.addActionListener(oyente53);
    }

   
    private void EventoGenER(){
        ActionListener oyente54 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                String cade = cadenMatriz.getText();
                boolean res;
                
                if ((idAFNER.getText().length()) == 0){
                    JOptionPane.showMessageDialog(null,"INGRESE PRIMERO UN ID" );
                }else{

                    ArrayList<AFD> afd2 = new ArrayList<AFD>();
                    int idER = Integer.parseInt(idAFNER.getText());
                
                    for(AFD a: AFD.ConjDeAFDs){
                        if(id == a.idAFD){
                            afd2.add( a);
                        
                        } 
                    }
                    
                    ASemExpR ASemER = new ASemExpR(cade,afd2.get(0) ,idER);
                    res = ASemER.IniEvalER();

                    if (res == true) {
                        JOptionPane.showMessageDialog(null,"Se ha creado el AFN a apartir de la ER");
                        AFN.ConjDeAFNs.add( ASemER.afn1.get(0));
                    }else{
                        JOptionPane.showMessageDialog(null,"Hubo un problema al generar el AFD");
                    }
                    //AnalizLexico an = new AnalizLexico(cade,  afd1.get(0));
                }
               
            } 
        };
        bAnalizarSemER.addActionListener(oyente54);
    }


    private void EventoAnalizarGram(){
        ActionListener oyente77 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                
                int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                String cade = cadenMatriz.getText();
                int y;
                boolean res;
                
                ArrayList<AFD> afd1 = new ArrayList<AFD>();
            
                for(AFD a: AFD.ConjDeAFDs){
                    if(id == a.idAFD){
                     afd1.add( a);
                 
                    } 
                }
                
                ASGram ESM = new ASGram(cade,afd1.get(0) );

                res = ESM.IniEvalGram();
                if (res == true) {
                    JOptionPane.showMessageDialog(null,"la Cadena es correcta");
                }else{
                    JOptionPane.showMessageDialog(null,"la Cadena es NO correcta");
                }
                //AnalizLexico an = new AnalizLexico(cade,  afd1.get(0));
                for(String s: ESM.Vn){
                    JOptionPane.showMessageDialog(null,"NO" +s);
                }
                ESM.IdentificarTerminales();
                for(String s: ESM.Vt){
                    JOptionPane.showMessageDialog(null,"Ter" +s);
                }
               
            } 
        };
        bAnalizarGram.addActionListener(oyente77);
    }

    private void EventoGenTabLL1(){
        ActionListener oyente78 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                
                //int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                String cade = cadenMatriz.getText();
                int y;
                boolean res;
                
                ArrayList<AFD> afd1 = new ArrayList<AFD>();
            
                for(AFD a: AFD.ConjDeAFDs){
                    if(1010 == a.idAFD){
                     afd1.add( a);
                 
                    } 
                }
                
                ASGram ESM = new ASGram(cade,afd1.get(0) );

                res = ESM.IniEvalGram();
                if (res == true) {
                    JOptionPane.showMessageDialog(null,"la Cadena es correcta");
                }else{
                    JOptionPane.showMessageDialog(null,"la Cadena es NO correcta");
                }


                LimpiarTabla(tVn,mt4);
                for(String s: ESM.LVn){
                    //int c =
                /// System.out.println(a.IdAFN);
                    datos4[0]=s;
                    mt4.addRow(datos4); 
                }
                //ESM.IdentificarTerminales();
                LimpiarTabla(tVt,mt5);
                for(String s1: ESM.LVt){
                    //int c =
                /// System.out.println(a.IdAFN);
                    datos5[0]=s1;
                    datos5[1]="";
                    mt5.addRow(datos5); 
                }

                String cab6[]= new String [ESM.LVt.size()+1];
                Object datos6[] = new Object[cab6.length];

                boolean [] editable = new boolean [ESM.LVt.size()+1];
                for(int k=0;k<=ESM.LVt.size();k++){
                    editable[k]= false;
                }
                var types = new Class[ESM.LVt.size()+1];
                for(int l=0;l<=ESM.LVt.size();l++){
                    types[l]= java.lang.Object.class;
                }

                for(int h=1; h<=ESM.LVt.size();h++){
                    cab6[h]=ESM.LVt.get(h-1);
                }
                cab6[0]="no T";

                mt6 = new DefaultTableModel(cab6,0){
                    public Class getColumnClass(int i){
                        return types[i];
                    }
                    public boolean isCellEditable(int row, int column){
                        return editable[column];
                    }
                };
                
                LimpiarTabla(tLL1,mt6);
                tLL1.setModel(mt6);
                tLL1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                tLL1.doLayout();

                

                TLL1 ntll1= new TLL1(ESM);

                if (TLL1.ConjDeLL1.size()!=0) {
                    TLL1.ConjDeLL1.clear();
                    
                } 
                TLL1.ConjDeLL1.add(ntll1);

                ntll1.llenarTabla();
                for(int k=0; k< ESM.LVn.size(); k++){

                    for(int p=0;p<(ESM.LVt.size()+1);p++){
                   
                        datos6[p]=ntll1.TabLL1[k][p];
                         
                    }
                    //datos5[1]="";
                    mt6.addRow(datos6); 
                }

                /* 
                //AnalizLexico an = new AnalizLexico(cade,  afd1.get(0));
                for(String s: ESM.Vn){
                    JOptionPane.showMessageDialog(null,"NO" +s);
                }
                
                for(String s: ESM.Vt){
                    JOptionPane.showMessageDialog(null,"Ter" +s);
                }*/
               
            } 
        };
        bGenTabLL1.addActionListener(oyente78);
    }


    private void EventoAsociarToken(){
        ActionListener oyente78 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                boolean b1=true;

                if (TLL1.ConjDeLL1.size()!=0 && tVt.getRowCount()!=0) {
                
                    for(int i=0; i<tVt.getRowCount(); i++){
                        
                        String tok= String.valueOf(tVt.getValueAt(i, 1));
                        if (tok==""){
                            b1=false;
                            JOptionPane.showMessageDialog(null,"fffff"); 
                        } 
                        
                    }
                    if (b1==false){
                        JOptionPane.showMessageDialog(null,"hay algun AFN sin token");
                    }else{
                        TLL1.ConjDeLL1.get(0).indexlex.clear();
                        for(int i=0; i<tVt.getRowCount(); i++){
                            
                            Integer tok= Integer.parseInt(String.valueOf(tVt.getValueAt(i, 1)));
                            
                            TLL1.ConjDeLL1.get(0).indexlex.add(tok);
                            
                        }

                        if (TLL1.ConjDeLL1.get(0).indexT.size() == TLL1.ConjDeLL1.get(0).indexlex.size()) {
                            JOptionPane.showMessageDialog(null,"Se han asociado los TOKENs correctamente");
                        } else {
                            JOptionPane.showMessageDialog(null,"ERROR al asociar los tokens" + TLL1.ConjDeLL1.get(0).indexT.size() + TLL1.ConjDeLL1.get(0).indexlex.size());
                        }
                    } 
                }else{
                    JOptionPane.showMessageDialog(null,"No ha generado la tabla LL1 o no hay terminales" );
                }
               
            } 
        };
        bAsTok.addActionListener(oyente78);
    }


    private void EventoAnLexLL1(){
        ActionListener oyente79 = new ActionListener(){
            @Override
            public void actionPerformed(ActionEvent ae){
                

                if (TLL1.ConjDeLL1.size()!=0 ) {
                    String cade = cadenA.getText();
                    boolean b1=false;
                
                    int id = Integer.parseInt(listaDesplegable.getSelectedItem().toString());
                    ArrayList<AFD> afd1 = new ArrayList<AFD>();
            
                    for(AFD a: AFD.ConjDeAFDs){
                        if(id == a.idAFD){
                        afd1.add( a);
                    
                        } 
                    }
                    b1=TLL1.ConjDeLL1.get(0).analizarSin(afd1.get(0),cade);

                    if (b1==true) {
                        JOptionPane.showMessageDialog(null,"La cadena es sintacticamente correcta" );
                        //JOptionPane.showMessageDialog(null,"Tamaño de lista: " + TLL1.ConjDeLL1.get(0).TabAnLL1.size()/3);

                        LimpiarTabla(tAnalisisLL1,mt7);

                     
                        tAnalisisLL1.setModel(mt7);
                        tAnalisisLL1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        tAnalisisLL1.doLayout();
                        for(int k=0; k<(TLL1.ConjDeLL1.get(0).TabAnLL1.size()/3);k++){
                            //int c =
                        /// System.out.println(a.IdAFN);
                            datos7[0]= TLL1.ConjDeLL1.get(0).TabAnLL1.get(k*3);
                            datos7[1]= TLL1.ConjDeLL1.get(0).TabAnLL1.get((k*3)+1);
                            datos7[2]= TLL1.ConjDeLL1.get(0).TabAnLL1.get((k*3)+2);
                            mt7.addRow(datos7); 
                        }
                        datos7[0]= "$";
                        datos7[1]= "$";
                        datos7[2]= "";
                        mt7.addRow(datos7); 

                         
                    } else {
                        JOptionPane.showMessageDialog(null,"es incorrecto" );   
                        //JOptionPane.showMessageDialog(null,"Tamaño de lista: " + TLL1.ConjDeLL1.get(0).TabAnLL1.size()/3);
                        LimpiarTabla(tAnalisisLL1,mt7);

                     
                        tAnalisisLL1.setModel(mt7);
                        tAnalisisLL1.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
                        //tAnalisisLL1.doLayout();
                        for(int k=0; k<(TLL1.ConjDeLL1.get(0).TabAnLL1.size()/3);k++){
                            //int c =
                        /// System.out.println(a.IdAFN);
                            datos7[0]= TLL1.ConjDeLL1.get(0).TabAnLL1.get(k*3);
                            datos7[1]= TLL1.ConjDeLL1.get(0).TabAnLL1.get((k*3)+1);
                            datos7[2]= TLL1.ConjDeLL1.get(0).TabAnLL1.get((k*3)+2);
                            mt7.addRow(datos7); 
                        }
                    }
                    
                }else{
                    JOptionPane.showMessageDialog(null,"No ha generado la tabla LL1 o no hay terminales" );
                }
               
            } 
        };
        bAnalizarSLL1.addActionListener(oyente79);
    }
}

