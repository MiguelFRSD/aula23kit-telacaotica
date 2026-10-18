package br.com.starlog.model;

/**
 * CAMADA MODEL (Modelo de Domínio)
 * Representa uma mensagem criptográfica a ser transmitida.
 * 
 * ATENÇÃO ESTUDANTE:
 * Implemente esta classe a partir do código extraído da TelaCaoticaLegada
 * para satisfazer os testes TDD de 01 a 06!
 * 
 * REGRA SAGRADA DE ARQUITETURA:
 * O Model NUNCA deve importar componentes visuais (zero imports de javax.swing.*)!
 */
public class CriptoMensagem {

    private static final int CHAVE_MIN = 1;
    private static final int CHAVE_MAX = 25;
    private static final int TAMANHO_ALFABETO = 26;

    private final String remetente;
    private final String mensagem;
    private final int chaveDeslocamento;

    public CriptoMensagem(String remetente, String mensagem, int chaveDeslocamento) {
        // TODO: Etapa 2 - Implementar barreira Fail-Fast
        // 1. Validar se remetente é nulo ou vazio -> lançar IllegalArgumentException("Remetente não pode ser nulo ou vazio.");
        if (remetente == null || remetente.trim().isEmpty()) {
            throw new IllegalArgumentException("Remetente não pode ser nulo ou vazio.");
        }

        // 2. Validar se mensagem é nula ou vazia -> lançar IllegalArgumentException("Mensagem não pode ser nula ou vazia.");
        if (mensagem == null || mensagem.trim().isEmpty()) {
            throw new IllegalArgumentException("Mensagem não pode ser nula ou vazio.");
        }
        // 3. Validar se chaveDeslocamento está fora de 1..25 -> lançar IllegalArgumentException("Chave deve estar entre 1 e 25.");
        if (chaveDeslocamento < CHAVE_MIN || chaveDeslocamento > CHAVE_MAX) {
            throw new IllegalArgumentException("Chave de deslocamento deve estar entre 1 e 25.");
        }
        // 4. Normalizar remetente e mensagem (.trim().toUpperCase())
        
        this.remetente = remetente.trim().toUpperCase();
        this.mensagem = mensagem.trim().toUpperCase();
        this.chaveDeslocamento = chaveDeslocamento;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getMensagem() {
        return mensagem;
    }

    public int getChaveDeslocamento() {
        return chaveDeslocamento;
    }

    public String cifrar() {
        // TODO: Etapa 2 - Extrair algoritmo da Cifra de César da TelaCaoticaLegada
        StringBuilder cifrado = new StringBuilder();
        for (int i = 0; i < mensagem.length(); i++) {
            char c = mensagem.charAt(i);
            if (c >= 'A' && c <= 'Z') {
                cifrado.append((char) ('A' + (c - 'A' + chaveDeslocamento) % TAMANHO_ALFABETO));
            } else {
                cifrado.append(c);
            }
        }
        return cifrado.toString();
    }

    /**
     * Calcula o checksum somando o valor ordinal ASCII de cada caractere da mensagem em maiúsculo.
     */
    public int calcularChecksum() {
        // TODO: Etapa 2 - Extrair cálculo de Checksum da TelaCaoticaLegada
        int checksum = 0;
        for (int i = 0; i < mensagem.length(); i++) {
            checksum += mensagem.charAt(i);
        }
        return checksum;
    }
}
