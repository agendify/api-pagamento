package br.com.agendify.dto.output;

import br.com.agendify.dto.input.HolderDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CardResponseDTO {
    private String brand;
    private String first_digits;
    private String last_digits;
    private String exp_month;
    private String exp_year;
    private HolderDTO holder;


}
