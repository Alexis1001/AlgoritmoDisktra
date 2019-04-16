/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintografos;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author DELL HOME
 */
public class FXMLDocumentController implements Initializable {           
    @FXML private AnchorPane inicio;    
    @FXML private StackPane tablero_mostrar;
    @FXML private TextField text_tamanio,text_porcentaje;
    ArrayList<Integer> nodos_visitados = new ArrayList<>();
    ArrayList<Integer> nodos_visitados2 = new ArrayList<>();
            
    @FXML private void handleButtonAction(ActionEvent event) throws IOException {
        System.out.println("You clicked me!");      
        int filas=0;
        int porcentaje=0;
        int total_datos=0;
        int total_caminos=0;
        int total_paredes=0;              
        filas = Integer.parseInt(this.text_tamanio.getText());
        porcentaje = Integer.parseInt(this.text_porcentaje.getText());                
        
        total_datos=filas*filas;
        total_paredes=total_datos*porcentaje/100;
        total_caminos=total_datos-total_paredes;       
        System.out.println("Total de datos "+total_datos);
        System.out.println("El "+porcentaje+" % de "+total_datos+" es "+total_paredes);
        System.out.println("Total paredes "+total_paredes);
        System.out.println("Total caminos "+total_caminos);
        System.out.println(" ");
               
        GenerardorMatrizes matriz = new GenerardorMatrizes(filas,total_paredes,total_caminos);
        int laberinto[][] =matriz.Matriz();
        generarTablero(filas);
        int matrizAbyacente[][] = generarMatrizAbyacente(laberinto);
        algoritmoDijstra(matrizAbyacente);   
        BuscarCamino(matrizAbyacente);
       // caminominimos(matrizAbyacente);
        System.out.println("nodos: " + matrizAbyacente.length);
        pintarGrafo(matrizAbyacente);
       
    }
    
    public void generarTablero(int tam){
        this.inicio.setVisible(false);
        this.tablero_mostrar.setVisible(true);
        GridPane tablero = new GridPane();   
        tablero.setAlignment(Pos.CENTER);        
        for(int i = 0; i<tam; i++){
            for(int j = 0; j<tam; j++){
            tablero.add(new Button("."), i, j);
            }
        }        
        //tablero.add(new Button("Posicion 0, 0"), 0, 0);        
        tablero.setGridLinesVisible(true);
        this.tablero_mostrar.getChildren().add(tablero);
    }   
    public int[][] generarMatrizAbyacente(int laberinto[][]){
        int matrizAbyacente[][] =  new int[laberinto.length*laberinto.length][laberinto.length*laberinto.length];
        ArrayList<Integer> pesos = new ArrayList<>();
        ArrayList<String> posiciones = new ArrayList<>();          
        int cont = 0;
        for(int i = 0; i<laberinto.length; i++){                     
            for(int j = 0; j<laberinto.length; j++){                       
                if(laberinto[i][j] != 0 ){                    
                    if(i>=0 && (j-1)>=0){                        
                        pesos.add(laberinto[i][j-1]);
                        posiciones.add(i+","+(j-1));
                    }
                    if((i-1) >= 0 && (j-1) >=0){
                        pesos.add(laberinto[i-1][j-1]);
                        posiciones.add((i-1)+","+(j-1));
                    }
                    if((i-1) >= 0 && j>=0 ){
                        pesos.add(laberinto[i-1][j]);
                        posiciones.add((i-1)+","+j);
                    }
                    if((i-1) >= 0 && j+1<laberinto.length){
                        pesos.add(laberinto[i-1][j+1]);
                        posiciones.add((i-1)+","+(j+1));
                    }
                    if(i>=0 && (j+1) <laberinto.length){
                        pesos.add(laberinto[i][j+1]);
                        posiciones.add(i+","+(j+1));
                    }
                    if((i+1)<laberinto.length && (j+1)<laberinto.length){
                        pesos.add(laberinto[i+1][j+1]);
                        posiciones.add((i+1)+","+(j+1));
                    }
                    if((i+1)<laberinto.length && j>=0){
                        pesos.add(laberinto[i+1][j]);
                        posiciones.add((i+1)+","+j);
                    }
                    if((i+1)<laberinto.length && (j-1) >= 0){
                        pesos.add(laberinto[i+1][j-1]);
                        posiciones.add((i+1)+","+(j-1));
                    }    
                    System.out.println(pesos);
                    System.out.println(posiciones);                                                           
                }
                ArrayList<String> fila = pos_nodos(laberinto);
                llenarFila(pesos,posiciones,cont,matrizAbyacente,fila);
                pesos = new ArrayList<>();                    
                posiciones = new ArrayList<>(); 
                cont++;
            }            
        }   
        imprimirMatrizAbyacente(matrizAbyacente);
        return matrizAbyacente;
    }
    public ArrayList<String> pos_nodos(int laberinto[][]){
        ArrayList<String> fila = new ArrayList<>();
        for(int k = 0; k<laberinto.length; k++){
            for(int l = 0; l<laberinto.length; l++){
                fila.add(k+","+l);
            }           
        }
        return fila;
    }
    public void llenarFila(ArrayList<Integer> pesos,ArrayList<String> posiciones,int i,int matrizAbyacente[][],ArrayList<String> fila){
        boolean bandera = false;
        System.out.println("fila: "+fila);
        System.out.println("posiciones: "+posiciones);
        for(int j = 0; j<fila.size(); j++){
            bandera = false;
            int pos_temp = 0;
            for(int k = 0; k<posiciones.size(); k++){
                if(fila.get(j).equals(posiciones.get(k)) == true){
                    bandera = true;
                    pos_temp = k;
                }
            }
            if(bandera == true){
                matrizAbyacente[i][j] = pesos.get(pos_temp);
            }else{                
                matrizAbyacente[i][j] = 0;
            }           
        }        
    }
    public void imprimirMatrizAbyacente(int matrizAbyacente[][]){
        System.out.println("prro");
        for(int i = 0; i<matrizAbyacente.length; i++){
            System.out.println(Arrays.toString(matrizAbyacente[i]));
        }
        System.out.println("prro2\n\n");
    }
    /*public int[] calcularPesoMenor(ArrayList<Integer> pesos,ArrayList<String> posiciones,ArrayList<String> nodos_visitados){
        int menor_valor = pesos.get(0);
        String pos_tempx[] = posiciones.get(0).split(",");
        coordenadas[0] = Integer.parseInt(pos_tempx[0]);
        coordenadas[1] = Integer.parseInt(pos_tempx[1]);
        boolean bandera = false;
        
        for(int i = 0; i<pesos.size(); i++){
            String pos_temp2[] = posiciones.get(i).split(",");
            if(pesos.get(i) != 0 && nodo_visitado(nodos_visitados,pos_temp2[0]+","+pos_temp2[1]) == false){
                if(menor_valor > pesos.get(i)){                    
                    menor_valor = pesos.get(i);
                    String pos_temp[] = posiciones.get(i).split(",");
                    coordenadas[0] = Integer.parseInt(pos_temp[0]);
                    coordenadas[1] = Integer.parseInt(pos_temp[1]);                    
                    bandera = true;
                }
            }
        }                
        if(bandera == false){
            if(nodo_visitado(nodos_visitados,coordenadas[0]+","+coordenadas[1]) == true){
                
            }
        }
        return coordenadas;
    }
    public boolean nodo_visitado(ArrayList<String> nodos_visitados, String nuevo_nodo){
        for(int i = 0; i<nodos_visitados.size(); i++){
            if(nodos_visitados.get(i).equals(nuevo_nodo) == true){
                return true;
            }
        }
        return false;
    }*/
    public void algoritmoDijstra(int matrizAbyacente[][]){        
        int pos_final = matrizAbyacente.length-1;
        int peso_menor = 0;
        int posX = 0,posY = 0;        
        int i = 0;
        while(i<matrizAbyacente.length){            
            peso_menor = 1000;
            posX = i; posY = 0;
            for(int j = 0; j<matrizAbyacente.length; j++){
                if(matrizAbyacente[i][j] != 0 && nodo_visitado(j) == false && matrizAbyacente[i][j] != 1){
                    if(peso_menor > matrizAbyacente[i][j]){
                        peso_menor = matrizAbyacente[i][j];
                        posX = i;
                        posY = j;
                    }
                }                              
            }
            this.nodos_visitados.add(posY);
            System.out.println("valor: "+matrizAbyacente[posX][posY]+" posX: "+posX+" posY: "+posY);
            System.out.println("Visitados: "+this.nodos_visitados);
            i = posY;  
            if(posY == pos_final){
                System.out.println("Laberinto resuelto");
                break;
            }else if(matrizAbyacente[posX][posY] == 0){
                System.out.println("Sin solucion");
                break;
            }
        }
    }    
    
    public boolean nodo_visitado(int nodo){        
        for(int i = 0; i<this.nodos_visitados.size(); i++){
            if(this.nodos_visitados.get(i) == nodo){
                return true;
            }
        }
        return false;
    }
    public void pintarGrafo(int matrizAbyacente[][]) throws IOException{
        FileWriter fichero = null;
        PrintWriter pw = null;
        try
        {            
            fichero = new FileWriter("grafo.dot");
            pw = new PrintWriter(fichero);
            pw.println("digraph G{");
            pw.println("node [shape=circle];");
            pw.println("node [style=filled];");
            pw.println("node [fillcolor=\"#EEEEEE\"];");
            pw.println("node [color=\"#EEEEEE\"];");
            pw.println("edge [color=\"#31CEF0\"];");
            for(int i = 0; i<matrizAbyacente.length; i++){
                this.nodos_visitados2.add(i);
                for(int j = 0; j<matrizAbyacente.length; j++ ){
                    if(matrizAbyacente[i][j] != 0 && matrizAbyacente[i][j] != 1 && nodo_visitado2(j) == false){
                        pw.println(i+" -> "+j+" [label=\""+matrizAbyacente[i][j]+"\"]");
                    }
                }
            }            
            pw.println("rankdir=LR;");
            pw.println("}");            
        } catch (Exception e) {
            e.printStackTrace();
        } finally {         
              fichero.close();           
        }        
    }
    public boolean nodo_visitado2(int nodo){        
        for(int i = 0; i<this.nodos_visitados2.size(); i++){
            if(this.nodos_visitados2.get(i) == nodo){
                return true;
            }
        }
        return false;
    }
    
/*public void caminominimos(int [][] matrizAdy){
 
ArrayList<Integer> nodos=new ArrayList<Integer>(); 
int tamanio=matrizAdy.length*matrizAdy.length;

for(int i=0;i<tamanio;i++){
     for(int j=0;j<tamanio;j++){
        nodos.add(matrizAdy[i][j]);
         }
    }

while(!nodos.isEmpty()){


    System.out.println("hola");

     }

    
    
}
  */  
    
 

public void BuscarCamino(int [][]matrizAdy){

        System.out.println("mi matriz alexis");
        int visited  []=new int[100];
        int preD  []=new int[100];
        int distance[]=new int[100];


            int min = 999, nextNode = 0; // min holds the minimum value, nextNode holds the value for the next node.
            for(int i=0; i<matrizAdy.length; i++){
                visited[i] = 0; //initialize visited array to zeros
                preD[i] = 0;
                for(int j=0; j<matrizAdy.length; j++){
                    if(matrizAdy[i][j]==0){
                        matrizAdy[i][j]=999;
                    }
                }
            }
            distance = matrizAdy[1]; //initialize the distance array
            visited[0] = 1; //set the source node as visited
            distance[0] = 1; //set the distance from source to source to zero which is the starting point

            for(int counter =0; counter < matrizAdy.length; counter++){
                    min = 999;
                    for(int i = 1; i < matrizAdy.length; i++){
                            if(min > distance[i] && visited[i]!=1){
                                    min = distance[i];
                                    nextNode = i;
                            }
                    }

                    visited[nextNode] = 1;
                    for(int i =0; i < matrizAdy.length; i++){
                            if(visited[i]!=1){
                                    if(min+matrizAdy[nextNode][i] < distance[i]){
                                            distance[i] = min+matrizAdy[nextNode][i];
                                            preD[i] = nextNode;

                                    }
                            }
                    }
            }

            for(int i =0; i <matrizAdy.length; i++){
                System.out.print("|" + distance[i]);
                }
            System.out.println("|");
            int j;
            for(int i = 0; i <=matrizAdy.length; i++){
                    if(i!=0){
                            System.out.print("Path = " + i);
                            j = i;
                            do{
                                    j=preD[j];
                                    if(j==0){
                                    System.out.print(" <- " + 1);
                                    }else{
                                    System.out.print(" <- " + j);
                                }
                            }while(j!=0);
                    }
                    System.out.println();

            }
      }
    

    
   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
