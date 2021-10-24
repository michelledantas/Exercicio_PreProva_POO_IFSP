import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Consultor extends Funcionario{

    private List<Funcionario> subordinados = new ArrayList<>();

    public Consultor(String cpf) {
        super(cpf);
    }

    public Consultor (Revendedor r){
        this(r.getCpf(), r.getNome(), r.getIdade(), r.isSexo(), r.getValorVendido(), r.getResponsavel());
    }

    public Consultor(String cpf, String nome, int idade, boolean sexo, double valorVendido, Consultor responsavel) {
        super(cpf, nome, idade, sexo, valorVendido, responsavel);
    }

    public Iterator<Funcionario> iteratorSubordinados(){
        return subordinados.iterator();
    }



    @Override
    public double calculaComissao() {
        double comissao = getValorVendido() * 0.15;
        for (Funcionario subordinado : subordinados) {
            comissao += subordinado.calculaComissao()*0.3;
        }
        return comissao ;
    }

    public int numSubordinados(){
        int numSubordinados=0;
        for (Funcionario subordinado : subordinados) {
            numSubordinados++;
            if (subordinado instanceof Consultor)
                numSubordinados += ((Consultor)subordinado).numSubordinados();
        }
        return numSubordinados;
    }

    @Override
    public String toString() {
        return super.toString() + ", Subordinados=" + numSubordinados();
    }

    public void addSubordinado (Funcionario funcionario){
        subordinados.add(funcionario);
    }



}
