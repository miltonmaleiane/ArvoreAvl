package ArvoreAVL;



public interface Arvore<T extends Comparable<T>> {

    Arvore<T> inserir(T data);

    void apagar(T data);

    void travessia();

    T getMaximo();

    T getMinimo();

    boolean estavazio();

}