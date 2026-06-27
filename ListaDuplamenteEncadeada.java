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

    public No buscarDaFrentePraTras(String busca) {
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

    public boolean osNosSaoVizinhos(No um, No dois) {
        if(um == null) {
            throw new RuntimeException("Primeiro argumento null.");
        }
        if(dois == null) {
            throw new RuntimeException("Segundo argumento null.");
        }

        return (um.getProximoNo() == dois && dois.getNoAnterior() == um) || (um.getNoAnterior() == dois && dois.getProximoNo() == um);
    }

    public void trocarDeLugar(No um, No dois) {
        if(um == null) {
            throw new RuntimeException("Primeiro argumento null.");
        }
        if(dois == null) {
            throw new RuntimeException("Segundo argumento null.");
        }
        if(um == dois) {
            throw new RuntimeException("Ambos os argumentos são iguais.");
        }
        if(soHaUmNo()) {
            throw new RuntimeException("Só há um nó na lista.");
        }

        if(osNosSaoVizinhos(um, dois)) {
            trocarVizinhosDeLugar(um, dois);
        } else {
            trocarDistantesDeLugar(um, dois);
        }
    }

    private void trocarDistantesDeLugar(No um, No dois) {
        if(um == null) {
            throw new RuntimeException("Primeiro argumento null.");
        }
        if(dois == null) {
            throw new RuntimeException("Segundo argumento null.");
        }
        if(um == dois) {
            throw new RuntimeException("Ambos os argumentos são iguais.");
        }
        No vizinhoEsquerdoDoUm = um.getNoAnterior();
        No vizinhoDireitoDoUm = um.getProximoNo();
        No vizinhoEsquerdoDoDois = dois.getNoAnterior();
        No vizinhoDireitoDoDois = dois.getProximoNo();
        if(vizinhoEsquerdoDoUm != null) {
            vizinhoEsquerdoDoUm.setProximoNo(null);
            vizinhoEsquerdoDoUm.setProximoNo(dois);
        }
        if(vizinhoDireitoDoUm != null) {
            vizinhoDireitoDoUm.setNoAnterior(null);
            vizinhoDireitoDoUm.setNoAnterior(dois);
        }
        um.setNoAnterior(null);
        um.setProximoNo(null);

    }

    private void trocarVizinhosDeLugar(No um, No dois) {
        if(soHaDoisNos()) {
            um.setNoAnterior(dois);
            dois.setProximoNo(um);
            um.setProximoNo(null);
            dois.setNoAnterior(null);
            primeiroNo = dois;
            ultimoNo = um;
        } else {
            No vizinhoEsquerdoDoUm = um.getNoAnterior();
            No vizinhoDireitoDoUm = um.getProximoNo();
            No vizinhoEsquerdoDoDois = dois.getNoAnterior();
            No vizinhoDireitoDoDois = dois.getProximoNo();

            // ..

        }

    }

    public boolean soHaDoisNos() {
        return tamanho == 2;
    }

    public boolean ehOPrimeiroNo(No no) {
        return no == primeiroNo;
    }

    public boolean ehUltimoNo(No no) {
        return no == ultimoNo;
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
        No noBuscado = lista.buscarDaFrentePraTras("Detergente amarelo");
        System.out.println(lista.toString());
    }