
package laberintografos;

/**
 *
 * @author DELL HOME
 */
public class Nodo {
    int peso;
    int x,y;
    boolean visitado;
    public Nodo(int peso,int x,int y, boolean visitado){        
        this.peso = peso;
        this.x = x;
        this.y = y;
        this.visitado = visitado;
    } 

    public int getPeso() {
        return peso;
    }

    public void setPeso(int peso) {
        this.peso = peso;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isVisitado() {
        return visitado;
    }

    public void setVisitado(boolean visitado) {
        this.visitado = visitado;
    }       
}
