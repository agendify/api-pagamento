package br.com.agendify.dto.input;


import br.com.agendify.dto.output.CardResponseDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class PaymentMethodDTO {

    private String type;
    private Integer installments;
    private Boolean capture;
    private String soft_descriptor;
    private CardDTO card;



}
