package br.com.agendify.dto;


import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DadosPagamentoDTO {

    private String reference_id;
    private String desciption;
    private CodeValeuDTO amount;
    private PaymentMethodDTO payment_method;
    private List<String> notification_urls;
    private metadataDTO metadata;

}
