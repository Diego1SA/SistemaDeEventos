import java.io.*;
import java.time.LocalDateTime;
import java.util.*;

public class SistemaEventos {
    private List<Evento> eventos = new ArrayList<>();

    public void carregarEventos() {
        try (BufferedReader br = new BufferedReader(new FileReader("events.data"))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                String[] dados = linha.split(";");
                Evento e = new Evento(
                    dados[0], dados[1], CategoriaEvento.valueOf(dados[2]),
                    LocalDateTime.parse(dados[3]), dados[4]);
                eventos.add(e);
            }
        } catch (IOException e) {
            System.out.println("Arquivo de eventos não encontrado ou vazio.");
        }
    }

    public void salvarEventos() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("events.data"))) {
            for (Evento e : eventos) {
                bw.write(String.join(";", e.getNome(), e.getEndereco(), e.getCategoria().name(),
                    e.getHorario().toString(), e.getDescricao()));
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Erro ao salvar eventos.");
        }
    }

    public void cadastrarEvento(Evento evento) {
        eventos.add(evento);
        salvarEventos();
    }

    public List<Evento> getEventos() {
        return eventos;
    }

    public List<Evento> getEventosOrdenados() {
        eventos.sort(Comparator.comparing(Evento::getHorario));
        return eventos;
    }

    public List<Evento> getEventosDoUsuario(Usuario u) {
        List<Evento> lista = new ArrayList<>();
        for (Evento e : eventos) {
            if (e.getParticipantes().contains(u)) lista.add(e);
        }
        return lista;
    }
}
