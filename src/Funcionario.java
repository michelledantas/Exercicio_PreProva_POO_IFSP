import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

@Getter
public abstract class Funcionario {

    private String cpf;
    @Setter
    private String nome;
    @Setter
    private int idade;
    @Setter
    private boolean sexo;
    @Setter
    private double valorVendido;
    @Setter
    private Consultor responsavel;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Funcionario that = (Funcionario) o;
        return cpf.equals(that.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }

    @Override
    public String toString() {
        return  "Cpf='" + cpf + '\'' +
                ", Nome='" + nome + '\'' +
                ", Idade=" + idade +
                ", Sexo=" + sexo +
                ", Valor Vendido=" + valorVendido +
                ", Responsavel=" + (responsavel!=null ? responsavel.getNome() : "--" ) +
                ", Comissão=" + calculaComissao();
    }

    public Funcionario(String cpf) {
        this.cpf = cpf;
    }

    public Funcionario(String cpf, String nome, int idade, boolean sexo, double valorVendido, Consultor responsavel) {
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.valorVendido = valorVendido;
        this.responsavel = responsavel;
    }

    public abstract double calculaComissao();


}
