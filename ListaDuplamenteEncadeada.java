public class ListaDuplamenteEncadeada {
    No primeiroNo;
    No ultimoNo;
    int tamanho;

    public ListaDuplamenteEncadeada() {
        primeiroNo = null;
        ultimoNo = null;
        tamanho = 0;
    }

    public void primeiraInsercao(No novoPrimeiroNo) {
        primeiroNo = novoPrimeiroNo;
        ultimoNo = novoPrimeiroNo;
        incrementarTamanho();
    }

    public void primeiraInsercao(String dadoDoNovoPrimeiroNo) {
        if(!estaVazia()) {
            throw new RuntimeException("A lista já teve uma primeira inserção");
        }

        No novoPrimeiroNo = new No(dadoDoNovoPrimeiroNo);
        primeiroNo = novoPrimeiroNo;
        ultimoNo = novoPrimeiroNo;
        incrementarTamanho();
    }

    public void inserirEmSequencia(No novoNo) {
        if(estaVazia()) {
            primeiraInsercao(novoNo);
        } else {
            No ultimoNoAtual = ultimoNo;
            ultimoNo.setProximoNo(novoNo);
            ultimoNo = novoNo;
            ultimoNo.setNoAnterior(ultimoNo);
            ultimoNoAtual.setProximoNo(ultimoNo);
            incrementarTamanho();
        }
    }

    public void inserirEmSequencia(String dadoDoNovoNo) {
        No novoNo = new No(dadoDoNovoNo);
        if(estaVazia()) {
            primeiraInsercao(novoNo);
        } else {
            No ultimoNoAtual = ultimoNo;
            ultimoNo.setProximoNo(novoNo);
            ultimoNo = novoNo;
            ultimoNo.setNoAnterior(ultimoNo);
            ultimoNoAtual.setProximoNo(ultimoNo);
            incrementarTamanho();
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void incrementarTamanho() {
        tamanho++;
    }

    public boolean estaVazia() {
        return tamanho == 0;
    }

    @Override
    public String toString() {
        if (estaVazia()) {
            return "∅";
        }

        StringBuilder resultado = new StringBuilder();

        No atual = primeiroNo;

        if (atual.getNoAnterior() == null) {
            resultado.append("∅");
        }

        while (atual != null) {
            resultado.append(" ⇔ ").append(atual.toString());

            if (atual.getProximoNo() == null) {
                resultado.append(" ⇔ ∅");
            }

            atual = atual.getProximoNo();
        }

        return resultado.toString();
    }

    public static void main(String[] args) {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        System.out.println(lista.toString());
        lista.inserirEmSequencia("1o nó");
        System.out.println(lista.toString());
        lista.inserirEmSequencia("2o nó");
        System.out.println(lista.toString());
        lista.inserirEmSequencia("3o nó");
        System.out.println(lista.toString());
    }
}