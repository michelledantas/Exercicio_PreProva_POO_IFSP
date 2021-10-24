import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class Principal {

    private static FuncionarioDAO dao;

    public static void main(String[] args) {
        Principal principal = new Principal();
        dao = new FuncionarioDAO();

        principal.populateDataBase();

        /*principal.cadastrarNovoFuncionario();
        principal.removerFuncionario();
        */principal.editarFuncionario();
        

        listFuncionarios(dao);
    }

    private void editarFuncionario() {
        Scanner scanner = new Scanner(System.in);
        String cpf = scanner.nextLine();
        String nome = scanner.nextLine();
        int idade = Integer.parseInt(scanner.nextLine());
        boolean sexo = Boolean.parseBoolean(scanner.nextLine());
        double valorVendido = Double.parseDouble(scanner.nextLine());
        String cpfResponsavel = scanner.nextLine();

        Funcionario funcionario = dao.findOne(cpf);
        if (funcionario == null) return;

        Funcionario funcionarioResponsavel = dao.findOne(cpfResponsavel);
        if (funcionarioResponsavel == null) return;

        if(!funcionario.getResponsavel().equals(funcionarioResponsavel)){
            if (funcionarioResponsavel instanceof Revendedor)
                funcionarioResponsavel = promoveAConsultor((Revendedor) funcionarioResponsavel);

            Consultor responsavel = (Consultor) funcionarioResponsavel;

            Revendedor novoRevendedor = new Revendedor(cpf, nome, idade, sexo, valorVendido, responsavel);
            responsavel.addSubordinado(novoRevendedor);
            funcionario.setResponsavel(responsavel);
            dao.update(responsavel);

        }
        funcionario.setNome(nome);
        funcionario.setIdade(idade);
        funcionario.setSexo(sexo);
        funcionario.setValorVendido(valorVendido);
        dao.update(funcionario);



    }

    private static void listFuncionarios(FuncionarioDAO dao) {
        final List<Funcionario> funcionarios = dao.findAll();
        for (Funcionario funcionario : funcionarios) {
            System.out.println(funcionario);
        }

        /* outra forma de fazer o item anterior, mas o mais usual é a forma anterior
        for (int i = 0; i < funcionarios.size(); i++) {
            System.out.println(funcionarios.get(i));

        }*/
    }

    private void removerFuncionario() {
        Scanner scanner = new Scanner(System.in);
        String cpf = scanner.nextLine();
        final Funcionario funcionario = dao.findOne(cpf);
        if (funcionario instanceof Consultor)
            promoveSubordinados((Consultor) funcionario);
        dao.delete(cpf);
    }

    private void promoveSubordinados(Consultor consultor) {
        final Iterator<Funcionario> it = consultor.iteratorSubordinados();
        while (it.hasNext()){
            Funcionario subordinado = it.next();
            subordinado.setResponsavel(consultor.getResponsavel());
            dao.update(subordinado);

        }
    }

    private void cadastrarNovoFuncionario() {
        Scanner scanner = new Scanner(System.in);
        String cpf = scanner.nextLine();
        String nome = scanner.nextLine();
        int idade = Integer.parseInt(scanner.nextLine());
        boolean sexo = Boolean.parseBoolean(scanner.nextLine());
        double valorVendido = Double.parseDouble(scanner.nextLine());
        String cpfResponsavel = scanner.nextLine();

        Funcionario result = dao.findOne(cpfResponsavel);
        if (result instanceof Revendedor)
            result = promoveAConsultor((Revendedor) result);

        Consultor responsavel = (Consultor) result;

        Revendedor novoRevendedor = new Revendedor(cpf, nome, idade, sexo, valorVendido, responsavel);
        responsavel.addSubordinado(novoRevendedor);

        dao.update(responsavel);
        dao.save(novoRevendedor);
    }

    private Funcionario promoveAConsultor(Revendedor revendedor) {
        return new Consultor(revendedor);
    }

    private void populateDataBase(){
        Consultor huffman = new Consultor("12312312312", "David A. Huffman", 74, false, 7000.00, null);
        Consultor ada = new Consultor("32132132131", "Augusta Ada Byron", 36, true, 3000.00,huffman);
        Consultor dijkstra = new Consultor("21321321313", "Edsger Dijkstra", 72, true, 1520.00,huffman);
        Consultor turing = new Consultor("45645645646", "Alan Mathison Turing", 41, false, 780.00,ada);
        Revendedor neumann = new Revendedor("65465465464", "John von Neumann", 53, false, 300.00, turing);
        Revendedor knuth = new Revendedor("90219021902", "Donald Ervin Knuth", 80, false, 432.00, turing);
        Revendedor hopper = new Revendedor("54654654654", "Grace Murray Hopper", 85, true, 432.00, dijkstra);

        dijkstra.addSubordinado(hopper);
        turing.addSubordinado(knuth);
        turing.addSubordinado(neumann);
        ada.addSubordinado(turing);
        huffman.addSubordinado(ada);
        huffman.addSubordinado(dijkstra);

        dao.save(huffman);
        dao.save(ada);
        dao.save(dijkstra);
        dao.save(turing);
        dao.save(neumann);
        dao.save(knuth);
        dao.save(hopper);
    }
}
