package ArvoreAVL;


import ArvoreAVL.Arvore;
public class ArvoreAVL<T extends Comparable<T>> implements Arvore<T> {

    private No<T> raiz;

    @Override
    public Arvore<T> inserir(T dados) {
        raiz = inserir(dados, raiz);
        return this;
    }

    private No<T> inserir(T dado, No<T> no) {
        if (no == null) {
            return new No<>(dado);
        }
        if (dado.compareTo(no.getDados()) < 0) {
            no.setFilhoEsquerda(inserir(dado, no.getFilhoEsquerda()));
        } else if (dado.compareTo(no.getDados()) > 0) {
            no.setFilhoDireita(inserir(dado, no.getFilhoDireita()));
        } else {
            return no;
        }
        actualizarAltura(no);
        return aplicarRotacao(no);
    }

    @Override
    public void apagar(T dado) {
        raiz = apagar(dado, raiz);
    }

    private No<T> apagar(T data, No<T> node) {
        if (node == null) {
            return null;
        }
        if (data.compareTo(node.getDados()) < 0) {
            node.setFilhoEsquerda(apagar(data, node.getFilhoEsquerda()));
        } else if (data.compareTo(node.getDados()) > 0) {
            node.setFilhoDireita(apagar(data, node.getFilhoDireita()));
        } else {
            // One Child or Leaf No (no children)
            if (node.getFilhoEsquerda() == null) {
                return node.getFilhoDireita();
            } else if (node.getFilhoDireita() == null) {
                return node.getFilhoEsquerda();
            }
            // Two Children
            node.setDados(getMaximo(node.getFilhoEsquerda()));
            node.setFilhoEsquerda(apagar(node.getDados(), node.getFilhoEsquerda()));
        }
        actualizarAltura(node);
        return aplicarRotacao(node);
    }

    @Override
    public void travessia() {
        travessiaEmOrdem(raiz);
    }

    private void travessiaEmOrdem(No<T> node) {
        if (node != null) {
            travessiaEmOrdem(node.getFilhoEsquerda());
            System.out.println(node);
            travessiaEmOrdem(node.getFilhoDireita());
        }
    }

    @Override
    public T getMaximo() {
        if (estavazio()) {
            return null;
        }
        return getMaximo(raiz);
    }

    private T getMaximo(No<T> node) {
        if (node.getFilhoDireita() != null) {
            return getMaximo(node.getFilhoDireita());
        }
        return node.getDados();
    }

    @Override
    public T getMinimo() {
        if (estavazio()) {
            return null;
        }
        return getMinimo(raiz);
    }

    private T getMinimo(No<T> node) {
        if (node.getFilhoEsquerda() != null) {
            return getMinimo(node.getFilhoEsquerda());
        }
        return node.getDados();
    }

    @Override
    public boolean estavazio() {
        return raiz == null;
    }

    private No<T> aplicarRotacao(No<T> node) {
        int balance = balancear(node);
        if (balance > 1) {
            if (balancear(node.getFilhoEsquerda()) < 0) {
                node.setFilhoEsquerda(rotacaoEsquerda(node.getFilhoEsquerda()));
            }
            return rotacaoDireita(node);
        }
        if (balance < -1) {
            if (balancear(node.getFilhoDireita()) > 0) {
                node.setFilhoDireita(rotacaoDireita(node.getFilhoDireita()));
            }
            return rotacaoEsquerda(node);
        }
        return node;
    }

    private No<T> rotacaoDireita(No<T> node) {
        No<T> leftNode = node.getFilhoEsquerda();
        No<T> centerNode = leftNode.getFilhoDireita();
        leftNode.setFilhoDireita(node);
        node.setFilhoEsquerda(centerNode);
        actualizarAltura(node);
        actualizarAltura(leftNode);
        return leftNode;
    }

    private No<T> rotacaoEsquerda(No<T> node) {
        No<T> rightNode = node.getFilhoDireita();
        No<T> centerNode = rightNode.getFilhoEsquerda();
        rightNode.setFilhoEsquerda(node);
        node.setFilhoDireita(centerNode);
        actualizarAltura(node);
        actualizarAltura(rightNode);
        return rightNode;
    }

    private void actualizarAltura(No<T> node) {
        int maxHeight = Math.max(
                height(node.getFilhoEsquerda()),
                height(node.getFilhoDireita())
        );
        node.setAltura(maxHeight + 1);
    }

    private int balancear(No<T> node) {
        return node != null ? height(node.getFilhoEsquerda()) - height(node.getFilhoDireita()) : 0;
    }

    private int height(No<T> node) {
        return node != null ? node.getAltura() : 0;
    }

}