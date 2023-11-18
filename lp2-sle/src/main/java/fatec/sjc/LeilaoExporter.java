package fatec.sjc;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import fatec.sjc.entity.Leilao;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LeilaoExporter {

    private static final Logger LOGGER = Logger.getLogger(LeilaoExporter.class.getName());

    public static void exportarParaJSON(List<Leilao> leiloes, String nomeArquivo) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(nomeArquivo), leiloes);
            LOGGER.info("Exportação para JSON concluída com sucesso.");
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao exportar para JSON", e);
        }
    }

    public static void main(String[] args) {
        String endpointUrl = "http://localhost:8080/leilao/listarOrdenadosPorData";

        try {
            String responseData = fetchDataFromEndpoint(endpointUrl);

            if (responseData != null) {
                List<Leilao> meusLeiloes = convertDataToLeilao(responseData);
                exportarParaJSON(meusLeiloes, "leiloes.DET");
            } else {
                LOGGER.warning("Falha ao obter dados do endpoint.");
            }
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Erro durante a execução do programa", e);
        }
    }

    private static String fetchDataFromEndpoint(String endpointUrl) {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(endpointUrl))
                .build();

        try {
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
            return response.body();
        } catch (IOException | InterruptedException e) {
            LOGGER.log(Level.SEVERE, "Erro ao fazer a solicitação HTTP", e);
            return null;
        }
    }

    private static List<Leilao> convertDataToLeilao(String responseData) {
        ObjectMapper objectMapper = new ObjectMapper();

        // Custom date format that handles square brackets
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssX");
        objectMapper.setDateFormat(dateFormat);

        try {
            return objectMapper.readValue(responseData, new TypeReference<List<Leilao>>() {});
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Erro ao converter dados para objetos Leilao", e);
            return Collections.emptyList();
        }
    }
}
