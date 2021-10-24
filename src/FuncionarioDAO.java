import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FuncionarioDAO implements DAO<String, Funcionario>{

    private static Map<String, Funcionario> bd = new LinkedHashMap<>();

    public Funcionario findOne(String cpf){
        return bd.get(cpf);
    }

    public List<Funcionario> findAll(){
        return new ArrayList<>(bd.values());
    }

    public boolean save(Funcionario funcionario){
        if(!bd.containsKey(funcionario.getCpf())) {
            bd.put(funcionario.getCpf(), funcionario);
            return true;
        }
        return false;
    }

    public boolean update(Funcionario funcionario){
        return bd.replace(funcionario.getCpf(), funcionario) != null;
    }

    public boolean delete (String cpf){
        return bd.remove(cpf) != null;
    }
}
