package br.com.agendify.dto.input;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class MetadataDTO {

    private String Exemplo;
    private String Notafiscal;
    private String idComprador;

}
