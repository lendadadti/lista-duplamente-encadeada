import java.util.Objects;

public class No {
    private No noAnterior;
    private String dado;
    private No proximoNo;

    public No() { /* Um nó vazio */
        this.noAnterior = null;
        this.dado = null;
        this.proximoNo = null;
    }

    public No(String dado) {
        this.noAnterior = null;
        this.dado = dado;
        this.proximoNo = null;
    }

    public boolean ambosOsObjetosPossuemAMesmaIdentidade(Object that) {
        return (this == that);
    }

    public boolean ambosOsObjetosSaoCompativeis(Object that) {
        boolean thatEhNull = (that == null);
        boolean ambosOsObjetosSaoDeClassesDiferentes = this.getClass() != that.getClass();

        if(thatEhNull || ambosOsObjetosSaoDeClassesDiferentes) return false;

        return true;
    }

    public boolean osDoisNosSaoAMesmaIntanciaNaMemoria(Object oNoQueSeQuerComparar) {
        return (this == oNoQueSeQuerComparar);
    }

    public boolean oNoQueEstaEmOmparacaoEhNuloOuDeOutraClasse(Object oNoEmComparacao) {
        if(oNoEmComparacao == null || this.getClass() != oNoEmComparacao.getClass()) return false;
        return true;
    }

    @Override
    public boolean equals(Object that) {
        if(this.ambosOsObjetosPossuemAMesmaIdentidade(that)) return true;
        if(!this.ambosOsObjetosSaoCompativeis(that)) return false;

        // Aqui ficou o cast porque ainda quero comparar o dado armazenado dentro do objeto
        No thatNo = (No) that;
        return (this.getNoAnterior() == thatNo.getNoAnterior()) && (this.getProximoNo() == thatNo.getProximoNo()) && Objects.equals(this.getDado(), thatNo.getDado());
    }

    public No(No noAnterior, String dado, No proximoNo) {
        this.noAnterior = noAnterior;
        this.dado = dado;
        this.proximoNo = proximoNo;
    }

    public boolean temNoAnterior() {
        return this.noAnterior != null;
    }

    public boolean temProximoNo() {
        return this.proximoNo != null;
    }

    public No getNoAnterior() {
        return this.noAnterior;
    }

    public void setNoAnterior(No noAnterior) {
        this.noAnterior = noAnterior;
    }

    public String getDado() {
        return this.dado;
    }

    public void setDado(String dado) {
        this.dado = dado;
    }

    public No getProximoNo() {
        return proximoNo;
    }

    public void setProximoNo(No proximoNo) {
        this.proximoNo = proximoNo;
    }

    public String toString() {
        return "[ " + getDado() + " ]";
    }

}