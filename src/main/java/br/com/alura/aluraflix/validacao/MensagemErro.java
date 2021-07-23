package br.com.alura.aluraflix.validacao;

public class MensagemErro {
    private String campo;
    private String mensagem;

    public MensagemErro(String campo, String mensagem) {
        this.campo = campo;
        this.mensagem = mensagem;
    }

    public String getCampo() {
        return campo;
    }

    public String getMensagem() {
        return mensagem;
    }
}
