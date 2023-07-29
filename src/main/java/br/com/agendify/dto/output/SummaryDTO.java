package br.com.agendify.dto.output;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SummaryDTO {

    private Integer total;
    private Integer paid;
    private Integer refunded;


}
