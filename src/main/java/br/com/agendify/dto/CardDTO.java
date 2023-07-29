package br.com.agendify.dto;

import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CardDTO {

    private String number;
    private String exp_month;
    private String exp_year;
    private String security_code;
    private HolderDTO holder;

}
