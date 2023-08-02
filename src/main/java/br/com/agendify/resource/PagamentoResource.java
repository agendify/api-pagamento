package br.com.agendify.resource;


import br.com.agendify.dto.input.DadosPagamentoDTO;
import br.com.agendify.dto.output.RespPagSeguroDTO;
import br.com.agendify.mensages.CustomException;
import br.com.agendify.service.PagamentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("pagamento")
public class PagamentoResource {

    @Autowired
    PagamentoService service;

    @PostMapping(value ="/pagSeguro", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> pagSeguro(@RequestBody DadosPagamentoDTO dados) throws CustomException {

        try {
            RespPagSeguroDTO response = service.methodPaymentCredit(dados);
            return ResponseEntity.ok(response);
        } catch (CustomException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new CustomException(e.getMessage(), e.getExceptionCode()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Erro interno no servidor.");
        }

    }










}
