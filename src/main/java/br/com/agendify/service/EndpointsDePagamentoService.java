package br.com.agendify.service;

import br.com.agendify.dto.input.DadosPagamentoDTO;
import br.com.agendify.dto.output.RespPagSeguroDTO;
import br.com.agendify.mensages.MessageCode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class EndpointsDePagamentoService {
    private static final String URL_BASE = "https://sandbox.api.pagseguro.com/charges";
    private static final String TOKEN_ACESS = "446A6E9174254348827625BDCCFCB777";

    public RespPagSeguroDTO requestPayment(DadosPagamentoDTO dados) throws Exception {

        ResponseEntity<String>  resp;

        RespPagSeguroDTO meuObjeto;

        try{

            RestTemplate restTemplate = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            headers.set("Authorization", "Bearer " + TOKEN_ACESS);
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<DadosPagamentoDTO> requestEntity = new HttpEntity<>(dados, headers);

            resp = restTemplate.exchange(URL_BASE, HttpMethod.POST, requestEntity, String.class);

            String respBody = resp.getBody();
            ObjectMapper objectMapper = new ObjectMapper();

            meuObjeto = objectMapper.readValue(respBody, RespPagSeguroDTO.class);

        }catch (Exception e){
            throw new Exception(MessageCode.ERRO_REQUEST_PAYMENT.getMessage());
        }

        return meuObjeto;
    }


}
