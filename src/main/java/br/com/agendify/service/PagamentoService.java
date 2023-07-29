package br.com.agendify.service;
import br.com.agendify.dto.DadosPagamentoDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class PagamentoService {


    @Autowired
    EndpointsDePagamentoService endpointsDePagamentoService;


    public Map<String, Object> methodPaymentCredit(DadosPagamentoDTO dados) throws Exception {

        if (dados == null || dados.getPayment_method() == null || dados.getPayment_method().getCard() == null){
            throw new Exception("INPUT de requisação inválido.");
        }

        if (dados.getPayment_method().getType().isEmpty() || dados.getPayment_method().getType().isBlank() || !dados.getPayment_method().getType().equals("CREDIT_CARD")){
            throw new Exception("Tipo de Pagamento inválido");
        }

        if(!dados.getPayment_method().getCard().getHolder().getName().isEmpty() || !dados.getPayment_method().getCard().getHolder().getName().isBlank()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate dataAtual = LocalDate.now();

            String mesCartao = dados.getPayment_method().getCard().getExp_month();
            String anoCartao = dados.getPayment_method().getCard().getExp_year();
            String dataRealCartao = anoCartao + mesCartao + "01";
            LocalDate dataFormatada = LocalDate.parse(dataRealCartao, formatter);

            if (dataFormatada.isBefore(dataAtual)){
                throw new Exception("Cartão Vencido");
            }

            if(dados.getPayment_method().getCard().getSecurity_code().length() != 3){
                throw new Exception("Numero de segurança é inválido");
            }

        }else{
            throw new Exception("Nome do Comprador é obrigatório");
        }

        Map<String, Object> resp = endpointsDePagamentoService.requestPayment(dados);

        System.out.println("respPagamentoService"+ resp);

        if(!resp.isEmpty()){
            return resp;
        }else{
            return (Map<String, Object>) resp.put("ERROR", "ERRO");
        }


    }




}
