package br.com.starlog.controller;

import br.com.starlog.model.CriptoMensagem;
import java.util.ArrayList;
import java.util.List;

public class TransmissaoController {

    private final List<String> bufferTransmissoes = new ArrayList<>();

    public String processarTransmissao(String remetenteRaw, String msgRaw, String chaveStr) {
        // TODO: Etapa 3 - Orquestração no Controller
        int deslocamento = converterChave(chaveStr);

        CriptoMensagem cripto = new CriptoMensagem(remetenteRaw, msgRaw, deslocamento);

        String textoCifrado = cripto.cifrar();
        int checksum = cripto.calcularChecksum();

        String pacote = String.format("[%s] #%d -> %s", cripto.getRemetente(), checksum, textoCifrado);

        bufferTransmissoes.add(pacote);
        return pacote;
    }

    public int getTotalTransmissoes() {
        return bufferTransmissoes.size();
    }

    private int converterChave(String chaveStr) {
        try {
            return Integer.parseInt(chaveStr.trim());
        } catch (NumberFormatException | NullPointerException e) {
            throw new IllegalArgumentException("Chave deve ser um número");
        }
    }
}
