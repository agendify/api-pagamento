package br.com.agendify.dto.input;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardDTO {

    private String number;
    private String exp_month;
    private String exp_year;
    private String security_code;
    private HolderDTO holder;

}
