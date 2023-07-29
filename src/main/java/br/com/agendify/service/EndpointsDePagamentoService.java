package br.com.agendify.service;

import br.com.agendify.dto.DadosPagamentoDTO;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

@Service
public class EndpointsDePagamentoService {
    private static final String URL_BASE = "https://sandbox.api.pagseguro.com/charges";
    private static final String TOKEN_ACESS = "446A6E9174254348827625BDCCFCB777";

    public Map<String, Object> requestPayment(DadosPagamentoDTO dados) throws Exception {

        ResponseEntity<String>  resp;

        Map<String, Object> map;

        try{

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            headers.set("Authorization", "Bearer " + TOKEN_ACESS);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<DadosPagamentoDTO> requestEntity = new HttpEntity<>(dados, headers);

            resp = restTemplate.exchange(URL_BASE, HttpMethod.POST, requestEntity, String.class);

            map = stringToMap(String.valueOf(resp));

        }catch (Exception e){
            throw new Exception("Erro ao fazer requisação de pagamento " + "Tipo de pagamento:" + dados.getPayment_method() + "Valor Cobrado:" +
                    dados.getAmount().getValue());
        }

        return map;
    }


    public static Map<String, Object> stringToMap(String input) {
        Map<String, Object> map = new HashMap<>();

        // Dividir a string pelos separadores e criar pares chave-valor no mapa
        String[] keyValuePairs = input.split(", ");
        for (String pair : keyValuePairs) {
            String[] keyValue = pair.split(": ");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                Object value = keyValue[1].trim();
                map.put(key, value);
            }
        }

        return map;
    }

}
