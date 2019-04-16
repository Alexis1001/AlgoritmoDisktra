/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package laberintografos;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author DELL HOME
 */
public class GenerardorMatrizes {
    List<ArrayList<Integer>>arreglo=new ArrayList<ArrayList<Integer>>();
    List<ArrayList<String>>lista=new ArrayList<ArrayList<String>>();
    int filas;
    int columnas;
    int total_paredes;
    int fila_aleatoria;
    int columna_aleatoria;
    int numero_ramdom;
    int cont;
    int cont2;
    int total_caminos;
    public GenerardorMatrizes(int filas,int total_paredes,int total_caminos){
        this.filas=filas;
        this.columnas=filas;
        this.total_paredes=total_paredes;
        this.fila_aleatoria=0;
        this.columna_aleatoria=0;
        this.numero_ramdom=0;
        this.cont=0;
        this.cont2=-1;
        this.total_caminos=total_caminos;
    }
    public int[][] Matriz(){
        int matriz[][]=new int[filas][columnas];
        for(int i=0;i<matriz.length;i++){
            for(int j=0;j<matriz.length;j++){
                matriz[i][j]=numero_ramdom=(int)(Math.random()*10)+1;
                //System.out.print(matriz[i][j]);
                //System.out.printf(" ");
            }
        //System.out.println("  ");
        }
        matriz[0][0]=100;
        matriz[filas-1][columnas-1]=100;
        for(int k=0;k<total_paredes;k++){
            fila_aleatoria=(int) (Math.random() *filas-1);
            columna_aleatoria=(int)(Math.random()*columnas-1);
            if(matriz[fila_aleatoria][columna_aleatoria]==0){
                k=k-1;
            }
            if(matriz[fila_aleatoria][columna_aleatoria]>0){
                matriz[fila_aleatoria][columna_aleatoria]=0;
            }
            if(fila_aleatoria==0&&columna_aleatoria==0){
                matriz[fila_aleatoria][columna_aleatoria]=100;
                k=k-1;
            }
            if(fila_aleatoria==filas-1&&columna_aleatoria==columnas-1){
                matriz[filas-1][columnas-1]=100;
                k=k-1;
            }
        }
        Matrizabyacente(matriz);
        return matriz;
    }

    public void Matrizabyacente(int matriz[][]){
        String pocicion_i="";
        String pocicion_j="";
        String pocicion_i_j="";
        String pocicion1_i_j="";
        String pocicion_j1="";
        int cont=0;
        String postemporal_i="";
        String postemporal_j="";
        String postemporal="";
        System.out.println("Laberinto");
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                System.out.print(matriz[i][j]);
                System.out.print(" ");
            }
            System.out.println(" ");
        }
        for(int k=0;k<total_caminos;k++){
            lista.add(new ArrayList<String>());
            arreglo.add(new ArrayList<Integer>());
        }
        System.out.println(" ");
        for(int i=0;i<filas;i++){
            for(int j=0;j<columnas;j++){
                if(j<columnas-1&&matriz[i][j]>0&&matriz[i][j+1]>0){
                    cont2=cont2+1;
                    pocicion_i=String.valueOf(i);
                    pocicion_j=String.valueOf(j);
                    pocicion_i_j=pocicion_i+pocicion_j;
                    //System.out.println("pocicion i..j "+pocicion_i_j); 
                    lista.get(cont2).add(pocicion_i_j);
                    pocicion_j1=String.valueOf(j+1);
                    pocicion1_i_j=pocicion_i+pocicion_j1;
                    // System.out.println("pocicion next to "+pocicion1_i_j);
                    //arreglo.add(pocicion1_i_j);
                    lista.get(cont2).add(pocicion1_i_j);
                    arreglo.get(cont2).add(matriz[i][j+1]);
                    if(j>0&&i<filas-1){
                        if(matriz[i+1][j-1]>0){
                        //   System.out.println("pocicion anterior "+matriz[i+1][j-1]);
                            postemporal_i=String.valueOf(i+1);
                            postemporal_j=String.valueOf(j-1);
                            postemporal=postemporal_i+postemporal_j;
                         // System.out.println("cordenadas "+postemporal);
                         // arreglo.add(postemporal);
                            lista.get(cont2).add(postemporal);
                            arreglo.get(cont2).add(matriz[i+1][j-1]);
                        }
                    }
        if(i<filas-1){
           if(matriz[i+1][j]>0){
           //System.out.println("pocicion en medio "+matriz[i+1][j]);
             postemporal_i=String.valueOf(i+1);
             postemporal_j=String.valueOf(j);
             postemporal=postemporal_i+postemporal_j;  
          // System.out.println("cordenadas "+postemporal);
          // arreglo.add(postemporal);
             lista.get(cont2).add(postemporal);
             arreglo.get(cont2).add(matriz[i+1][j]);
           }
        }
        if(i<filas-1){
           if(matriz[i+1][j+1]>0){
          // System.out.println("pocicion siguiente "+matriz[i+1][j+1]);
             postemporal_i=String.valueOf(i+1);
             postemporal_j=String.valueOf(j+1);
             postemporal=postemporal_i+postemporal_j;
          // System.out.println("cordenadas "+postemporal);
          // arreglo.add(postemporal);
             lista.get(cont2).add(postemporal);
             arreglo.get(cont2).add(matriz[i+1][j+1]);
           }
        }   
    }
    
    if(j<columnas-1&&matriz[i][j]>0&&matriz[i][j+1]==0){
       cont2=cont2+1;
       pocicion_i=String.valueOf(i);
       pocicion_j=String.valueOf(j);
       pocicion_i_j=pocicion_i+pocicion_j;
   //  System.out.println("pocicion i..j "+pocicion_i_j);
       lista.get(cont2).add(pocicion_i_j);
       if(j>0&&i<filas-1){
          if(matriz[i+1][j-1]>0){
         //  System.out.println("pocicion anterior "+matriz[i+1][j-1]);
             postemporal_i=String.valueOf(i+1);
             postemporal_j=String.valueOf(j-1);
             postemporal=postemporal_i+postemporal_j;
          // System.out.println("cordenadas "+postemporal);
           //arreglo.add(postemporal);
             lista.get(cont2).add(postemporal);
             arreglo.get(cont2).add(matriz[i+1][j-1]);
             }
        }
        if(i<filas-1){
           if(matriz[i+1][j]>0){
           // System.out.println("pocicion en medio "+matriz[i+1][j]);
              postemporal_i=String.valueOf(i+1);
              postemporal_j=String.valueOf(j);
              postemporal=postemporal_i+postemporal_j;
            //System.out.println("cordenadas "+postemporal);
            //arreglo.add(postemporal);
              lista.get(cont2).add(postemporal);
              arreglo.get(cont2).add(matriz[i+1][j]);
              }
           if(matriz[i+1][j+1]>0){
            //System.out.println("pocicion siguiente "+matriz[i+1][j+1]);
              postemporal_i=String.valueOf(i+1);
              postemporal_j=String.valueOf(j+1);
              postemporal=postemporal_i+postemporal_j;
        //    System.out.println("cordenadas "+postemporal);
           // arreglo.add(postemporal);
              lista.get(cont2).add(postemporal);
              arreglo.get(cont2).add(matriz[i+1][j+1]);
              }
          }
        }
     
        if(j==columnas-1&&matriz[i][j]>0){
           cont2=cont2+1;
           pocicion_i=String.valueOf(i);
           pocicion_j=String.valueOf(j);
           pocicion_i_j=pocicion_i+pocicion_j;
         //System.out.println("pocicion i..j "+pocicion_i_j);  
           lista.get(cont2).add(pocicion_i_j);
         //  arreglo.get(cont2).add(matriz[i][j]);
           if(i<filas-1){
              if(matriz[i+1][j]>0){
              // System.out.println("pocicion en medio "+matriz[i+1][j]);
                 postemporal_i=String.valueOf(i+1);
                 postemporal_j=String.valueOf(j);
                 postemporal=postemporal_i+postemporal_j;
                //System.out.println("cordenadas "+postemporal);
               //arreglo.add(postemporal);
                 lista.get(cont2).add(postemporal);
                 arreglo.get(cont2).add(matriz[i+1][j]);
                 }
              if(matriz[i+1][j-1]>0){
             //  System.out.println("pocicion anterior "+matriz[i+1][j-1]);
                 postemporal_i=String.valueOf(i+1);
                 postemporal_j=String.valueOf(j-1);
                 postemporal=postemporal_i+postemporal_j;
             //  System.out.println("cordenadas "+postemporal);
               //arreglo.add(postemporal);
                 lista.get(cont2).add(postemporal);
                 arreglo.get(cont2).add(matriz[i+1][j-1]);
                 }  
            }
        } 
        
       
    } 
    
}
    arreglo.get(cont2).add(100);
    System.out.println(" ");
    System.out.println(lista);
    System.out.println(" ");
    System.out.println(arreglo);
    Caminos(lista,arreglo);
}

public void Caminos(List<ArrayList<String>> lista,List<ArrayList<Integer>>arreglo){
String numero=" ";
String Origen="";
String vector[]=new String [total_caminos];
List<String> origen=new ArrayList<String>();
List<String> destino=new ArrayList<String>();
int cont=0;

System.out.println("Imprimiendo conexiones");
for(int i=0;i<lista.size();i++){
    numero=String.valueOf(lista.get(i));
    vector[i]=numero; 
    String cadena[]=vector[i].split("\\[");
    String pos2=cadena[1];
    String cadena1[]=pos2.split("\\]");
    String cad=cadena1[0];
    vector[i]=cad;
    }

for(int i=0;i<vector.length;i++){
     Origen=vector[i];
     origen.add(Origen.substring(0,2));
     for(int k=0;k<Origen.length();k=k+1){
         if(Origen.charAt(k)==','){
            cont++;
            }
        }
     if(cont>0){
        destino.add(vector[i].substring(3,vector[i].length()));
        System.out.println("Origen "+origen.get(i)+" Peso---"+arreglo.get(i)+"---> Destino "+destino.get(i));
        }
     if(cont==0){
        destino.add(null);
        System.out.println("Origen "+origen.get(i)+" Peso---"+arreglo.get(i)+"---> Destino "+destino.get(i));
        }
     cont=0;
    }
   }
}
