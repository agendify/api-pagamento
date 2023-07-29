package br.com.agendify.dto.output;

import br.com.agendify.dto.input.MetadataDTO;
import br.com.agendify.dto.input.PaymentMethodDTO;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class RespPagSeguroDTO {


    private String id;
    private String reference_id;
    private String status;
    private String created_at;
    private String description;
    private CodeValueResponseDTO amount;
    private PaymentResponseDTO payment_response;
    private PaymentMethodResponseDTO payment_method;
    private List<String> notification_urls;
    private MetadataDTO metadata;
    private List<LinksDTO> links;


}
