import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        SistemaEventos sistema = new SistemaEventos();
        sistema.carregarEventos();

        System.out.println("Cadastro de usuário ");
        System.out.print("Nome: " +
                " ");
        String nome = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Cidade: ");
        String cidade = scanner.nextLine();

        Usuario usuario = new Usuario(nome, email, cidade);

        int opcao;
        do {
            System.out.println("\n1. Cadastrar evento");
            System.out.println("2. Listar eventos");
            System.out.println("3. Confirmar participação");
            System.out.println("4. Cancelar participação");
            System.out.println("5. Ver meus eventos");
            System.out.println("6. Sair");
            System.out.print("Escolha: ");
            opcao = Integer.parseInt(scanner.nextLine());

            switch (opcao) {
                case 1:
                    System.out.print("Nome do evento: ");
                    String enome = scanner.nextLine();
                    System.out.print("Endereço: ");
                    String endereco = scanner.nextLine();
                    System.out.print("Categoria (FESTA, ESPORTE, SHOW, PALESTRA, OUTRO): ");
                    CategoriaEvento categoria = CategoriaEvento.valueOf(scanner.nextLine().toUpperCase());
                    System.out.print("Data e hora (yyyy-MM-ddTHH:mm): ");
                    LocalDateTime dataHora = LocalDateTime.parse(scanner.nextLine());
                    System.out.print("Descrição: ");
                    String descricao = scanner.nextLine();

                    Evento novoEvento = new Evento(enome, endereco, categoria, dataHora, descricao);
                    sistema.cadastrarEvento(novoEvento);
                    break;
                case 2:
                    List<Evento> eventos = sistema.getEventosOrdenados();
                    for (int i = 0; i < eventos.size(); i++) {
                        Evento e = eventos.get(i);
                        System.out.printf("[%d] %s - %s - %s\n", i, e.getNome(), e.getHorario(),
                                e.estaOcorrendoAgora() ? "(Ocorrendo agora)"
                                        : e.jaOcorreu() ? "(Já ocorreu)" : "(Futuro)");
                    }
                    break;
                case 3:
                    System.out.print("ID do evento para participar: ");
                    int id = Integer.parseInt(scanner.nextLine());
                    sistema.getEventos().get(id).adicionarParticipante(usuario);
                    sistema.salvarEventos();
                    break;
                case 4:
                    System.out.print("ID do evento para sair: ");
                    int id2 = Integer.parseInt(scanner.nextLine());
                    sistema.getEventos().get(id2).removerParticipante(usuario);
                    sistema.salvarEventos();
                    break;
                case 5:
                    List<Evento> meus = sistema.getEventosDoUsuario(usuario);
                    if (meus.isEmpty()) {
                        System.out.println("Você não está participando de nenhum evento.");
                    } else {
                        for (Evento e : meus) {
                            System.out.println(e.getNome() + " - " + e.getHorario());
                        }
                    }
                    break;
            }
        } while (opcao != 6);

        System.out.println("Encerrando aplicação.");
    }
}
