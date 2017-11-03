
package Util;

import java.io.Serializable;

/**
 * A classe AuxDjikstra, auxilia o método de Djikstra na classe grafo, cada
 * auxDjikstra representa um vértice.
 *
 * @author Allan
 */
public class AuxDjikstra implements Serializable{

    private int pertenceId;
    private boolean foiVisitado;
    private double pesos;
    private int antecessor = -5;

    /**
     * Retorna se o vertice ja foi visitado.
     *
     * @return True or False
     */
    public boolean isfoiVisitado() {
        return foiVisitado;
    }

    /**
     * Altera para verdadeiro quando for selecionada pelo djikstra.
     *
     * @param fixo true or false
     */
    public void setFoiVisitado(boolean fixo) {
        this.foiVisitado = fixo;
    }

    /**
     * retorna o acumulo dos pesos feito pelo djikstra.
     *
     * @return distancia.
     */
    public double getPesos() {
        return pesos;
    }

    /**
     * Altera o peso atual, pelo novo pelo acumulado.
     *
     * @param pesos distância.
     */
    public void setPesos(double pesos) {
        this.pesos = pesos;
    }

    /**
     * retorna o Id da vertice
     *
     * @return
     */
    public int getPertenceId() {
        return pertenceId;
    }

    /**
     * atribue um Id a um vértice.
     *
     * @param pertenceId id da vértice
     */
    public void setPertenceId(int pertenceId) {
        this.pertenceId = pertenceId;
    }

    /**
     * para saber o caminho anterior.
     *
     * @return
     */
    public int getAntecessor() {
        return antecessor;
    }

    /**
     * O djikstra avança e muda seu caminho anterior.
     *
     * @param antecessor Id dos anteriores
     */
    public void setAntecessor(int antecessor) {
        this.antecessor = antecessor;
    }

}
