package br.com.agendify.resource;


import br.com.agendify.dto.input.DadosPagamentoDTO;
import br.com.agendify.dto.output.RespPagSeguroDTO;
import br.com.agendify.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("pagamento")
public class PagamentoResource {

    @Autowired
    PagamentoService service;

    @PostMapping(value ="/pagSeguro", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public RespPagSeguroDTO pagSeguro(@RequestBody DadosPagamentoDTO dados) throws Exception {

     return  service.methodPaymentCredit(dados);

    }










}
