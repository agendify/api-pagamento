package br.com.agendify.service;
import br.com.agendify.dto.input.DadosPagamentoDTO;
import br.com.agendify.dto.output.RespPagSeguroDTO;
import br.com.agendify.mensages.CustomException;
import br.com.agendify.mensages.MessageCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;

@Slf4j
@Service
public class PagamentoService {


    @Autowired
    EndpointsDePagamentoService endpointsDePagamentoService;


    public RespPagSeguroDTO methodPaymentCredit(DadosPagamentoDTO dados) throws CustomException {

        log.info("Dados de entrada"+ dados);

        if (dados == null || dados.getPayment_method() == null || dados.getPayment_method().getCard() == null){
            throw new CustomException(MessageCode.INVALID_REQUEST_INPUT.getMessage(), MessageCode.INVALID_REQUEST_INPUT.getExceptionCode());
        }

        if (dados.getPayment_method().getType().isEmpty() || dados.getPayment_method().getType().isBlank() || !dados.getPayment_method().getType().equals("CREDIT_CARD")){
            throw new CustomException(MessageCode.INVALID_PAYMENT_TYPE.getMessage(), MessageCode.INVALID_PAYMENT_TYPE.getExceptionCode());
        }

        if(!dados.getPayment_method().getCard().getHolder().getName().isEmpty() || !dados.getPayment_method().getCard().getHolder().getName().isBlank()){
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            LocalDate dataAtual = LocalDate.now();

            String mesCartao = dados.getPayment_method().getCard().getExp_month();
            String anoCartao = dados.getPayment_method().getCard().getExp_year();
            String dataRealCartao = anoCartao + mesCartao + "01";
            LocalDate dataFormatada = LocalDate.parse(dataRealCartao, formatter);

            if (dataFormatada.isBefore(dataAtual)){
                throw new CustomException(MessageCode.EXPIRED_CARD.getMessage(), MessageCode.EXPIRED_CARD.getExceptionCode());
            }

            if(dados.getPayment_method().getCard().getSecurity_code().length() != 3){
                throw new CustomException(MessageCode.SECURITY_NUMBER_INVALID.getMessage(), MessageCode.SECURITY_NUMBER_INVALID.getExceptionCode());
            }

        }else{
            throw new CustomException(MessageCode.BUYERS_NAME_REQUIRED.getMessage(), MessageCode.BUYERS_NAME_REQUIRED.getExceptionCode());
        }
        log.info("Passou das validações do cartão");

        RespPagSeguroDTO resp = endpointsDePagamentoService.requestPayment(dados);

        return resp;
    }

}
