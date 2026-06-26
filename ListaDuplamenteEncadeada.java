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

    public boolean soHaUmNo() {
        return tamanho == 1;
    }

    public void primeiraInsercao(String dadoDoNovoPrimeiroNo) {
        if(!estaVazia()) {
            throw new RuntimeException("A lista já teve uma primeira inserção.");
        }

        No novoPrimeiroNo = new No(dadoDoNovoPrimeiroNo);
        primeiroNo = novoPrimeiroNo;
        ultimoNo = novoPrimeiroNo;
        incrementarTamanho();
    }

    public void inserirEmSequencia(String dadoDoNovoNo) {
        No novoUltimoNo = new No(dadoDoNovoNo);

        if(estaVazia()) {
            primeiraInsercao(novoUltimoNo);
        } else {
            novoUltimoNo.setNoAnterior(ultimoNo);
            ultimoNo.setProximoNo(novoUltimoNo);
            ultimoNo = novoUltimoNo;
            incrementarTamanho();
        }
    }

    public void removerEmSequencia() {
        if(estaVazia()) {
            throw new RuntimeException("A lista está vazia.");
        }
        if(soHaUmNo()) {
            primeiroNo = null;
            ultimoNo = null;
            tamanho = 0;
        } else {
            No novoUltimo = ultimoNo.getNoAnterior();
            ultimoNo.setNoAnterior(null);
            novoUltimo.setProximoNo(null);
            ultimoNo = novoUltimo;
            decrementarTamanho();
        }
    }

    public int getTamanho() {
        return tamanho;
    }

    public void incrementarTamanho() {
        tamanho++;
    }

    public void decrementarTamanho() {
        tamanho--;
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

    public No buscarNoDaFrentePraTras(String busca) {
        if(estaVazia()) {
            throw new RuntimeException("A lista está vazia.");
        }
        if(soHaUmNo()) {
            return primeiroNo.getDado().equals(busca) ? primeiroNo : null;
        }

        No noAtual = primeiroNo;
        boolean encontrou = false;
        while(encontrou == false && noAtual != null) {
            if(noAtual.getDado().equals(busca)) {
                encontrou = true;
                break;
            }
            noAtual = noAtual.getProximoNo();
        }

        return encontrou ? noAtual : null;
    }

    public int buscarIndiceDaFrentePraTras(String busca) {
        if(estaVazia()) {
            throw new RuntimeException("A lista está vazia.");
        }
        int indice = 0;
        if(soHaUmNo()) {
            return primeiroNo.getDado().equals(busca) ? indice : -1;
        }

        No noAtual = primeiroNo;
        boolean encontrou = false;
        while(encontrou == false && noAtual != null) {
            if(noAtual.getDado().equals(busca)) {
                encontrou = true;
                break;
            }
            noAtual = noAtual.getProximoNo();
            indice++;
        }

        return encontrou ? indice : -1;
    }

    public static void main(String[] args) {
        ListaDuplamenteEncadeada lista = new ListaDuplamenteEncadeada();
        System.out.println(lista.toString());
        lista.inserirEmSequencia("Aromatizador");
        System.out.println(lista.toString());
        lista.inserirEmSequencia("Detergente amarelo");
        System.out.println(lista.toString());
        lista.inserirEmSequencia("Pinho sol");
        System.out.println(lista.toString());
        lista.inserirEmSequencia("Detergente transparente");
        No noBuscado = lista.buscarNoDaFrentePraTras("Detergente amarelo");
        int indiceNoBuscado = lista.buscarIndiceDaFrentePraTras("Detergente transparente");
        System.out.println(lista.toString());
    }
}