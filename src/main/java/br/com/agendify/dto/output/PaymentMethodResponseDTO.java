package br.com.agendify.dto.output;

import br.com.agendify.dto.input.CardDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PaymentMethodResponseDTO {

    private String type;
    private Integer installments;
    private Boolean capture;
    private String capture_before;
    private CardResponseDTO card;
    private String soft_descriptor;

}
