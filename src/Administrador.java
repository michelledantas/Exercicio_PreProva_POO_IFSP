import java.util.List;

public class Administrador implements DAO<Double, Administrador>{

    private Double chavePrimaria;

    @Override
    public Administrador findOne(Double key) {
        return null;
    }

    @Override
    public List<Administrador> findAll() {
        return null;
    }

    @Override
    public boolean delete(Double key) {
        return false;
    }

    @Override
    public boolean save(Administrador value) {
        return false;
    }

    @Override
    public boolean update(Administrador value) {
        return false;
    }
}
