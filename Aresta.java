/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.io.Serializable;

/**
 * A Classe Aresta é responsável por ligar duas Vértices. Ela recebe como
 * parâmetro duas vértices para criar uma nova aresta que contém uma distância
 * no caso seu peso.
 *
 * @author Allan
 */
public class Aresta implements Serializable{

    private final Vertice v1;
    private final Vertice v2;
    private final double peso;

    /**
     * Construtor da classe Aresta. Recebe parâmetros para interligar duas
     * vértices.
     *
     * @param v1 primeira vértice.
     * @param v2 segunda vértice.
     * @param peso distância entre elas.
     */
    public Aresta(Vertice v1, Vertice v2, double peso) {
        this.peso = peso;
        this.v1 = v1;
        this.v2 = v2;
    }

    /**
     * Retorna a distância entre duas vértices.
     *
     * @return distância.
     */
    public double getPeso() {
        return peso;
    }

    /**
     * Retorna a primeira vértice da aresta.
     *
     * @return vértice um
     */
    public Vertice getV1() {
        return v1;
    }

    /**
     * Retorna a segunda vértice da aresta.
     *
     * @return vértice dois
     */
    public Vertice getV2() {
        return v2;
    }

    /**
     * Retorna se duas arestas são iguais. Elas são iguais, se suas vértices
     * estão ligadas independente do sentido.
     *
     * @param ob1
     * @return
     */
    @Override
    public boolean equals(Object ob1) {
        if (ob1 instanceof Aresta) {
            Aresta a = (Aresta) ob1;
            if (a.getV1().getId() == v1.getId() && a.getV2().getId() == v2.getId()) {
                return true;
            }
            if (a.getV1().getId() == v2.getId() && a.getV2().getId() == v1.getId()) {
                return true;
            }
        }
        return false;
    }
}
