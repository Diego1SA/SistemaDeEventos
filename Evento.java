import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Evento {
    private String nome;
    private String endereco;
    private CategoriaEvento categoria;
    private LocalDateTime horario;
    private String descricao;
    private List<Usuario> participantes = new ArrayList<>();

    public Evento(String nome, String endereco, CategoriaEvento categoria, LocalDateTime horario, String descricao) {
        this.nome = nome;
        this.endereco = endereco;
        this.categoria = categoria;
        this.horario = horario;
        this.descricao = descricao;
    }

    public String getNome() { return nome; }
    public String getEndereco() { return endereco; }
    public CategoriaEvento getCategoria() { return categoria; }
    public LocalDateTime getHorario() { return horario; }
    public String getDescricao() { return descricao; }
    public List<Usuario> getParticipantes() { return participantes; }

    public boolean estaOcorrendoAgora() {
        LocalDateTime agora = LocalDateTime.now();
        return horario.isBefore(agora.plusHours(1)) && horario.isAfter(agora.minusHours(1));
    }

    public boolean jaOcorreu() {
        return LocalDateTime.now().isAfter(horario);
    }

    public void adicionarParticipante(Usuario u) {
        if (!participantes.contains(u)) participantes.add(u);
    }

    public void removerParticipante(Usuario u) {
        participantes.remove(u);
    }
}
