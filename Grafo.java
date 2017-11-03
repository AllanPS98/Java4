
package Util;

import Model.Aresta;
import Model.Vertice;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * A classe Grafo é responsável por fazer a criação do mapa contendo tanto
 * vértices, quanto suas arestas.
 *
 * @author Allan
 */
public class Grafo implements Serializable{

    private final List listaAdjacente;
    private int id;
    private final double infinito;
    private double pesovertices;
    /**
     * inicializa uma lista de Adjacencia que conterá todos os vertices e cria
     * um valor infinito, com o maximo valor de um tipo Double.
     */
    public Grafo() {
        this.listaAdjacente = new ArrayList();
        this.infinito = Double.MAX_VALUE;
    }

    /**
     * O método recebe um vertice, a ser adicionado ma lista Adjacente,
     * recebendo um id para sua identificação.
     *
     * @param vertice nova vértice.
     */
    public void inserirVertice(Vertice vertice) {
        listaAdjacente.add(vertice);
        vertice.setId(id);
        id++;
    }

    /**
     * retorna o Iterador da listaAdjacente.
     *
     * @return Iterator.
     */
    public Iterator ListarVertices() {
        return listaAdjacente.iterator();
    }

    /**
     * Retorna a listaAdjacente.
     *
     * @return a lista.
     */
    public List ListaGrafo() {
        return listaAdjacente;
    }

    /**
     * Procura uma vertice na lista Adjacente pelo seu ID, se o id por igual ao
     * passado por parâmetro, essa vertice é retornada.
     *
     * @param id identificado
     * @return vertice ou nulo
     */
    public Vertice procurarVertice(int id) {
        Iterator it = listaAdjacente.iterator();
        Vertice encontrado;
        while (it.hasNext()) {
            encontrado = (Vertice) it.next();
            if (encontrado.getId() == id) {
                return encontrado;
            }
        }
        return null;
    }

    /**
     * Procura um id na lista Adjacente pelo seu nome, se o nome por igual ao
     * passado por parâmetro, retorna seu id.
     *
     * @param nome nome igual ao de uma vertice existente.
     * @return -1 porque tem id = 0.
     */
    public int procurarVerticeNome(String nome) {
        Iterator it = listaAdjacente.iterator();
        Vertice encontrado;
        while (it.hasNext()) {
            encontrado = (Vertice) it.next();
            if (encontrado.getNome().equals(nome)) {
                return encontrado.getId();
            }
        }
        return -1;
    }

    /**
     * Calcula a distância do menor caminho e retorna sua kilometragem.
     *
     * @param origem ponto de partida.
     * @param destino ponto de chegada.
     * @return a kilometragem ou zero.
     */
    public double CacularMenorTempo(int origem, int destino) {
        double menorTempo = infinito;
        AuxDjikstra[] info = Djikstra(origem, false);
        for (int i = 0; i < info.length; i++) {
            if (info[i].getPertenceId() == destino) {
                return menorTempo = info[i].getPesos();
            }
        }
        return 0;
    }

    /**
     * MOstra o menor caminho entre suas vértice. Verificando todos os caminhos,
     * escolhe o de menor distancia e salva em uma lista.
     *
     * @param origem ponto de partida.
     * @param destino ponto de chegada.
     * @param sensorMenorDistancia inicia com false
     * @return a lista com o menor caminho.
     */
    public List listarMenorCaminho(int origem, int destino, boolean sensorMenorDistancia) {

        List caminho = new ArrayList();
        AuxDjikstra[] info = Djikstra(origem, sensorMenorDistancia);
        boolean achou;

        while ((destino != origem) && (destino != -5)) {

            achou = false;
            caminho.add(procurarVertice(destino));
            for (int i = 0; i < info.length && achou == false; i++) {
                if (info[i].getPertenceId() == destino) {
                    destino = info[i].getAntecessor();
                    achou = true;
                }
            }
        }
        if (destino == origem) {
            caminho.add(procurarVertice(destino));
        }
        return caminho;
    }

    /**
     * Método Djistra, verifica o menor caminho e torna o seu vetor.
     *
     * @param origem origem.
     * @param sensorMenorDistancia espaço a ser marcado como verdadeiro ou falso
     * @return
     */

    private AuxDjikstra[] Djikstra(int origem, boolean sensorMenorDistancia) {

        AuxDjikstra[] menorCaminho = new AuxDjikstra[listaAdjacente.size()];
        Vertice vert = procurarVertice(origem);
        Iterator it = listaAdjacente.iterator();

        for (int i = 0; i < menorCaminho.length; i++) {

            AuxDjikstra novo = new AuxDjikstra();
            Vertice vertice = (Vertice) it.next();
            novo.setPertenceId(vertice.getId());
            novo.setPesos(infinito);
            menorCaminho[i] = novo;
        }

        menorCaminho[vert.getId()].setPesos(0);
        menorCaminho[vert.getId()].setFoiVisitado(true);
        menorCaminho[vert.getId()].setAntecessor(0);
        menorCaminho[vert.getId()].setPertenceId(0);

        double peso = 0.0;
        while (podeContinuar(menorCaminho)) {

            for (int i = 0; i < menorCaminho.length; i++) {

                AuxDjikstra usar = menorCaminho[i];

                peso = procurarArestaPeso(vert, usar.getPertenceId());
                if (peso != -333.333) {
                    if (sensorMenorDistancia) {
                        peso = 1;

                    }
                    if (menorCaminho[vert.getId()].getPesos() + peso < menorCaminho[i].getPesos()) {
                        menorCaminho[i].setPesos(menorCaminho[vert.getId()].getPesos() + peso + pesovertices);
                        menorCaminho[i].setAntecessor(vert.getId());
                    }
                }

            }
            vert = menorPeso(menorCaminho);
        }

        return menorCaminho;
    }

    /**
     * Marca em cada volta uma vértice como selecionada pelo Djikstra, até todas
     * estarem com o status true, quando não haver mais nenhuma vertice a
     * visitar, o método não poserá continuar
     *
     * @param menorCaminho vetor no auxDjikstra.
     * @return
     */
    private boolean podeContinuar(AuxDjikstra[] menorCaminho) {

        boolean continuar = false;
        for (int i = 0; i < menorCaminho.length; i++) {
            if (menorCaminho[i].isfoiVisitado() == false) {

                continuar = true;
            }
        }
        return continuar;

    }

    /**
     * Esse método retorna uma aresta, através de um vertice, e o Id e retorna seu peso
     *
     * @param vert vertice.
     * @param id é para procurar o vertice.
     * @return o peso
     */
    private double procurarArestaPeso(Vertice vert, int id) {

        Iterator it = vert.ListarArestas();
        while (it.hasNext()) {
            Aresta a = (Aresta) it.next();
            if (a.equals(new Aresta(vert, procurarVertice(id), 0))) {
                return a.getPeso();
            }
        }
        return -333.333;
    }

    /**
     * Método utilizado para verificar um menor peso e depois retornar sua
     * vértice.
     *
     * @param menorCaminho vetor no auxDjikstra.
     * @return a vértice desse peso.
     */
    private Vertice menorPeso(AuxDjikstra[] menorCaminho) {

        int index = -1;
        double menor = infinito;
        for (int i = 0; i < menorCaminho.length; i++) {
            if (menorCaminho[i].isfoiVisitado() == false && menor >= menorCaminho[i].getPesos() && menorCaminho[i].getPesos() != 0) {
                menor = menorCaminho[i].getPesos();
                index = i;
            }
        }
        if (index != -1) {
            menorCaminho[index].setFoiVisitado(true);
        } else {
            return null;
        }
        return procurarVertice(index);
    }
    /**
     * Retorna o peso dos vértices do grafo
     * @return 
     */
    public double getPesovertices() {
        return pesovertices;
    }
    /**
     * Altera o peso dos vértices do grafo
     * @param pesovertices 
     */
    public void setPesovertices(double pesovertices) {
        this.pesovertices = pesovertices;
    }
    
    
}
