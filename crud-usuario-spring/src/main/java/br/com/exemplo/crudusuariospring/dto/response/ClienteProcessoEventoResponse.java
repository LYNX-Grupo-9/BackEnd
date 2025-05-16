package br.com.exemplo.crudusuariospring.dto.response;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;
import java.util.List;

public class ClienteProcessoEventoResponse {

    private Integer idCliente;
    private String nome;
    private String documento;
    private String tipoDocumento;
    private String email;
    private String telefone;
    private String endereco;
    private String genero;
    private Date dataNascimento;
    private String estadoCivil;
    private String profissao;
    private String passaporte;
    private String cnh;
    private String naturalidade;

    private List<ProcessoResponse> processos;
    private List<EventoResponse> eventos;

    public static class ProcessoResponse{
        private String numeroProcesso;

        public String getNumeroProcesso() {
            return numeroProcesso;
        }

        public void setNumeroProcesso(String numeroProcesso) {
            this.numeroProcesso = numeroProcesso;
        }
    }

    public static class EventoResponse {
        private Long idEvento;
        private Date dataEvento;
        private LocalTime horaInicio;
        private LocalTime horaFim;
        private String titulo;
        private String tipo;

        public Long getIdEvento() {
            return idEvento;
        }

        public void setIdEvento(Long idEvento) {
            this.idEvento = idEvento;
        }

        public Date getDataEvento() {
            return dataEvento;
        }

        public void setDataEvento(Date dataEvento) {
            this.dataEvento = dataEvento;
        }

        public String getTitulo() {
            return titulo;
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getTipo() {
            return tipo;
        }

        public void setTipo(String tipo) {
            this.tipo = tipo;
        }

        public LocalTime getHoraInicio() {
            return horaInicio;
        }

        public void setHoraInicio(LocalTime horaInicio) {
            this.horaInicio = horaInicio;
        }

        public LocalTime getHoraFim() {
            return horaFim;
        }

        public void setHoraFim(LocalTime horaFim) {
            this.horaFim = horaFim;
        }
    }

    public Integer getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(Integer idCliente) {
        this.idCliente = idCliente;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public String getTipoDocumento() {
        return tipoDocumento;
    }

    public void setTipoDocumento(String tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(Date dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public String getPassaporte() {
        return passaporte;
    }

    public void setPassaporte(String passaporte) {
        this.passaporte = passaporte;
    }

    public String getCnh() {
        return cnh;
    }

    public void setCnh(String cnh) {
        this.cnh = cnh;
    }

    public String getNaturalidade() {
        return naturalidade;
    }

    public void setNaturalidade(String naturalidade) {
        this.naturalidade = naturalidade;
    }

    public List<ProcessoResponse> getProcessos() {
        return processos;
    }

    public void setProcessos(List<ProcessoResponse> processos) {
        this.processos = processos;
    }

    public List<EventoResponse> getEventos() {
        return eventos;
    }

    public void setEventos(List<EventoResponse> eventos) {
        this.eventos = eventos;
    }
}
