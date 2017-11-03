
package Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A classe Vertice, é responsável, por ter um nome de cidade, e sua localização
 * no eixo em um mapa Grafo.
 *
 * @author Allan
 */
public class Vertice implements Serializable{

    private int id;
    private String vertice;
    private List listadeArestas;
    private int x;
    private int y;

    /**
     * O construtor, cria um novo vertice ao ser Instanciada, com um nome, e
     * sua posição x e y.
     *
     * @param vertice nome do vertice
     * @param x posição x
     * @param y posição y
     */
    public Vertice(String vertice, int x, int y) {
        this.vertice = vertice;
        this.listadeArestas = new ArrayList();
        this.id = -5;
        this.x = x;
        this.y = y;
    }

    /**
     * Esse método lista os Vertice vizinhos ao vertice escolhido no grafo.
     *
     * @return o Iterator desse vertices.
     */

    public Iterator ListarVerticeVizinho() {
        Iterator it = listadeArestas.iterator();
        List aux = new ArrayList();
        while (it.hasNext()) {
            Aresta a = (Aresta) it.next();
            if (id != a.getV1().id) {
                aux.add(a.getV1());
            } else {
                aux.add(a.getV2());
            }
        }
        return aux.iterator();
    }

    /**
     * altera o ID.
     *
     * @param id novo Id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * recupera um Id de um vertice
     *
     * @return id vertice
     */
    public int getId() {
        return id;
    }

    /**
     * retorna o nome de um vértice
     *
     * @return nome vertice
     */
    public String getNome() {
        return vertice;
    }

    /**
     * Lista todas as arestas da lista de arestas deste vértice.
     *
     * @return iterator
     */
    public Iterator ListarArestas() {
        return listadeArestas.iterator();
    }

    /**
     * Insere um elemento na lista de Arestas.
     *
     * @param a nova aresta
     */
    public void inserirAresta(Aresta a) {
        listadeArestas.add(a);
    }

    /**
     * Esse equals é simples, ele só verifica se o nome passado é igual ao
     * vertice.
     *
     * @param ver nome a ser passado "String"
     * @return
     */
    @Override
    public boolean equals(Object ver) {
        return vertice.equals(ver);
    }

    /**
     * retorna a posiçao x
     *
     * @return
     */
    public int getX() {
        return x;
    }

    /**
     * retorna a posiçao y
     *
     * @return
     */

    public int getY() {
        return y;
    }

    /**
     * Refeito o método toString, para exibir rapidamente o nome.
     *
     * @return
     */
    @Override
    public String toString() {
        return vertice;
    }
}
