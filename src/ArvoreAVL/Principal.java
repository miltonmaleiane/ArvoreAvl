package ArvoreAVL;


import ArvoreAVL.Arvore;





public class Principal {

    /*
     * Video Reference: https://youtu.be/Jj9Mit24CWk
     */
    public static void main(String[] args) {

        Arvore<Integer> avlTree = new ArvoreAVL<>();
        avlTree.inserir(10).inserir(2).inserir(6).inserir(8).inserir(25).inserir(18).inserir(35).inserir(15).inserir(22).inserir(42)
                .inserir(30).inserir(40).inserir(12).inserir(17).inserir(19).inserir(24).inserir(28).inserir(33).inserir(38);

        avlTree.travessia();

        System.out.println("Maximo e': " + avlTree.getMaximo());
        System.out.println("Minimo e': " + avlTree.getMinimo());

        System.out.println("apagando 42 da Arvore");
        avlTree.apagar(42);

        System.out.println("Novo maximo e': " + avlTree.getMaximo());

        avlTree.travessia();

    }

}