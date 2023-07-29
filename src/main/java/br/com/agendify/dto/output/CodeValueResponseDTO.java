package br.com.agendify.dto.output;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CodeValueResponseDTO {

    private Integer value;
    private String currency;
    private SummaryDTO summary;
}
