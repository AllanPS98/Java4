
package Util;
import Model.Aresta;
import Model.Vertice;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.ObjectOutputStream;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author Allan
 * 
 * 
 */


public class ArquivoSerializado {
    private final List<Vertice> vertices = new LinkedList<>();
    /**
     * 
     * @param lista
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void serializar(Grafo lista) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileOutputStream inp = new FileOutputStream("MapaSerial.allan");
        ObjectOutputStream ob = new ObjectOutputStream(inp);
        ob.writeObject(lista);
        
    }
    /**
     * 
     * @param lista
     * @throws FileNotFoundException
     * @throws IOException
     * @throws ClassNotFoundException 
     */
    public void lerArquivoSerializado(Grafo lista) throws FileNotFoundException, IOException, ClassNotFoundException{
        FileInputStream inFile = new FileInputStream("MapaSerial.allan");
        ObjectInputStream d = new ObjectInputStream(inFile);
        lista = (Grafo) d.readObject();
    }
    /**
     * 
     * @param grafo
     * @return String
     * @throws FileNotFoundException 
     */
    public String lerArquivoTexto(Grafo grafo) throws FileNotFoundException{
        FileReader arq = new FileReader("MapaMetro.txt");
        BufferedReader lerArq = new BufferedReader(arq);
        String linha;
        try {
            do {
                linha = lerArq.readLine();  
                System.out.println(linha);
                if(linha != null && linha.charAt(0)!='#'){
                    String nome[] = linha.split(",");
                    double peso = Double.parseDouble(nome[2]);
                    Vertice v1 = new Vertice(nome[0], 0, 0);
                    Vertice v2 = new Vertice(nome[1], 0, 0);
                    boolean a1 = false;
                    boolean a2 = false;
                    for(Vertice v : vertices){
                        if(v.toString().equals(v1.toString())){
                            v1 = v;
                            a1 = true;
                        }
                    }
                    if(a1 == false){
                        vertices.add(v1);
                    }
                    for(Vertice v : vertices){
                        if(v.toString().equals(v2.toString())){

                            v2 = v;
                            a2 = true;
                        }
                    }
                    if(a2 == false){
                        vertices.add(v2);
                    }
                    Aresta est = new Aresta(v1,v2,peso);
                    v1.inserirAresta(est);
                    v2.inserirAresta(est);

                }
            } while (linha != null);
            arq.close();
            
            for(Vertice a : vertices){
                grafo.inserirVertice(a);
            }
            List a = grafo.listarMenorCaminho(2, 5, false);
            Iterator it = a.iterator();
            String nome = "";
            while (it.hasNext()) {
                nome = " -> "+it.next().toString() + " " + nome;
            }
            System.out.println(nome);
           
            return "Arquivo lido com sucesso.";
        } catch (IOException ex) {
            return "Erro: Não foi possível ler o arquivo!" + ex.getMessage();
        }
    }
    public static void main(String[] args) throws FileNotFoundException, IOException, ClassNotFoundException {
        Grafo grafo = new Grafo();
        ArquivoSerializado serie = new ArquivoSerializado();
        serie.lerArquivoSerializado(grafo);
        List lista2 = grafo.ListaGrafo();
        System.out.println(lista2.isEmpty());
        Iterator it = lista2.iterator();
        while(it.hasNext()){
            Vertice v = (Vertice) it.next();
            System.out.println(v.getNome());
        }
    }
}
