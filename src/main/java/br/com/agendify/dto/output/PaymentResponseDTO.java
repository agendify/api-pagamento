package br.com.agendify.dto.output;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentResponseDTO {

    private String code;
    private String message;
    private String reference;

}
