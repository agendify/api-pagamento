package br.com.agendify.dto;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodDTO {

    private String type;
    private Integer installments;
    private Boolean capture;
    private String soft_descriptor;
    private CardDTO card;



}
