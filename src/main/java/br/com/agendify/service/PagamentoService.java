package br.com.agendify.service;
import br.com.agendify.dto.input.DadosPagamentoDTO;
import br.com.agendify.dto.output.RespPagSeguroDTO;
import br.com.agendify.mensages.MessageCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Service
public class PagamentoService {


    @Autowired
    EndpointsDePagamentoService endpointsDePagamentoService;


    public RespPagSeguroDTO methodPaymentCredit(DadosPagamentoDTO dados) throws Exception {

        System.out.println(dados);

        if (dados == null || dados.getPayment_method() == null || dados.getPayment_method().getCard() == null){
            throw new Exception(MessageCode.INVALID_REQUEST_INPUT.getMessage());
        }

        if (dados.getPayment_method().getType().isEmpty() || dados.getPayment_method().getType().isBlank() || !dados.getPayment_method().getType().equals("CREDIT_CARD")){
            throw new Exception(MessageCode.INVALID_PAYMENT_TYPE.getMessage());
        }

        if(!dados.getPayment_method().getCard().getHolder().getName().isEmpty() || !dados.getPayment_method().getCard().getHolder().getName().isBlank()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate dataAtual = LocalDate.now();

            String mesCartao = dados.getPayment_method().getCard().getExp_month();
            String anoCartao = dados.getPayment_method().getCard().getExp_year();
            String dataRealCartao = anoCartao + mesCartao + "01";
            LocalDate dataFormatada = LocalDate.parse(dataRealCartao, formatter);

            if (dataFormatada.isBefore(dataAtual)){
                throw new Exception(MessageCode.EXPIRED_CARD.getMessage());
            }

            if(dados.getPayment_method().getCard().getSecurity_code().length() != 3){
                throw new Exception(MessageCode.SECURITY_NUMBER_INVALID.getMessage());
            }

        }else{
            throw new Exception(MessageCode.BUYERS_NAME_REQUIRED.getMessage());
        }

        RespPagSeguroDTO resp = endpointsDePagamentoService.requestPayment(dados);

        return resp;
    }




}
