import model.model.Animal;
import model.model.Dono;
import model.model.Atendimento;
import model.model.Procedimento;
import model.model.Produto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenuClinicaVeterinaria {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Inicialize os dados da clínica veterinária aqui (listas de animais, donos, produtos, etc.)
        List<Animal> animais = new ArrayList<>();
        List<Dono> donos = new ArrayList<>();
        List<Atendimento> atendimentos = new ArrayList<>();
        List<Produto> produtos = new ArrayList<>();

        int escolha;

        do {
            System.out.println("\n--- Menu da Clínica Veterinária ---");
            System.out.println("1. Cadastrar Animal");
            System.out.println("2. Cadastrar Dono");
            System.out.println("3. Realizar Atendimento");
            System.out.println("4. Cadastrar Produto");
            System.out.println("5. Listar Animais");
            System.out.println("6. Listar Donos");
            System.out.println("7. Listar Atendimentos");
            System.out.println("8. Listar Produtos");
            System.out.println("9. Sair");
            System.out.print("Escolha uma opção: ");

            escolha = scanner.nextInt();
            scanner.nextLine(); // Limpar o buffer

            switch (escolha) {
                case 1:
                    cadastrarAnimal(animais, donos);
                    break;
                case 2:
                    cadastrarDono(donos);
                    break;
                case 3:
                    realizarAtendimento(animais, donos, atendimentos);
                    break;
                case 4:
                    cadastrarProduto(produtos);
                    break;
                case 5:
                    listarAnimais(animais);
                    break;
                case 6:
                    listarDonos(donos);
                    break;
                case 7:
                    listarAtendimentos(atendimentos);
                    break;
                case 8:
                    listarProdutos(produtos);
                    break;
                case 9:
                    System.out.println("Saindo do programa.");
                    break;
                default:
                    System.out.println("Opção inválida. Tente novamente.");
            }

        } while (escolha != 9);

        scanner.close();
    }

    private static void cadastrarAnimal(List<Animal> animais, List<Dono> donos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do animal: ");
        String nomeAnimal = scanner.nextLine();
        System.out.print("Especie: ");
        String especie = scanner.nextLine();
        System.out.print("Raça: ");
        String raca = scanner.nextLine();
        System.out.print("Data de nascimento (dd/MM/yyyy): ");
        String dataNascimentoStr = scanner.nextLine();
        LocalDate dataNascimento = LocalDate.parse(dataNascimentoStr, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        System.out.print("Histórico médico: ");
        String historicoMedico = scanner.nextLine();

        // Verifique se o dono já existe
        System.out.print("Nome do dono: ");
        String nomeDono = scanner.nextLine();
        Dono dono = encontrarDonoPorNome(donos, nomeDono);
        if (dono == null) {
            dono = new Dono(nomeDono);
            donos.add(dono);
        }

        Animal animal = new Animal(0, nomeAnimal, especie, raca, dataNascimentoStr, historicoMedico);
        animal.setEspecie(especie);
        animal.setRaca(raca);
        animal.setDataNascimento(dataNascimentoStr);
        animal.setHistoricoMedico(historicoMedico);

        animais.add(animal);
        System.out.println("Animal cadastrado com sucesso!");
    }

    private static void cadastrarDono(List<Dono> donos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do dono: ");
        String nome = scanner.nextLine();
        System.out.print("Endereço: ");
        String endereco = scanner.nextLine();
        System.out.print("Telefone: ");
        String telefone = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();

        Dono dono = new Dono(nome, endereco, telefone, email);
        donos.add(dono);
        System.out.println("Dono cadastrado com sucesso!");
    }

    private static void cadastrarProduto(List<Produto> produtos) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nome do produto: ");
        String nome = scanner.nextLine();
        System.out.print("Descrição: ");
        String descricao = scanner.nextLine();
        System.out.print("Preço: R$");
        double preco = scanner.nextDouble();
    
        Produto produto = new Produto(0, nome, descricao, preco);
        produtos.add(produto);
        System.out.println("Produto cadastrado com sucesso!");
    }
    

    private static void realizarAtendimento(List<Animal> animais, List<Dono> donos, List<Atendimento> atendimentos) {
        Scanner scanner = new Scanner(System.in);

        // Código para realizar um atendimento
        // Implemente de acordo com suas necessidades
    }

    private static void listarAnimais(List<Animal> animais) {
        System.out.println("\nLista de Animais:");
        for (Animal animal : animais) {
            System.out.println(animal);
        }
    }

    private static void listarDonos(List<Dono> donos) {
        System.out.println("\nLista de Donos:");
        for (Dono dono : donos) {
            System.out.println(dono);
        }
    }

    private static void listarAtendimentos(List<Atendimento> atendimentos) {
        System.out.println("\nLista de Atendimentos:");
        for (Atendimento atendimento : atendimentos) {
            System.out.println(atendimento);
        }
    }

    private static void listarProdutos(List<Produto> produtos) {
        System.out.println("\nLista de Produtos:");
        for (Produto produto : produtos) {
            System.out.println(produto);
        }
    }

    private static Dono encontrarDonoPorNome(List<Dono> donos, String nome) {
        for (Dono dono : donos) {
            if (dono.getNome().equalsIgnoreCase(nome)) {
                return dono;
            }
        }
        return null;
    }
}
