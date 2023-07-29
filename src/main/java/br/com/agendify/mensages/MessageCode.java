package br.com.agendify.mensages;

public enum MessageCode implements GeralMensagensCode{

    ERRO_REQUEST_PAYMENT("Erro ao fazer requisação de pagamento"),
    INVALID_REQUEST_INPUT("INPUT de requisação inválido"),
    INVALID_PAYMENT_TYPE("Tipo de Pagamento inválido"),
    EXPIRED_CARD("Cartão Vencido"),
    SECURITY_NUMBER_INVALID("Security number is invalid"),
    BUYERS_NAME_REQUIRED("Nome do Comprador é obrigatório");

    private String message;


    private MessageCode(String message){
        this.message = message;
    }


    public String getMessage() {
        return message;
    }


    public String getExceptionCode() {
        return this.getClass().getCanonicalName() + '.' + this.name();
    }
}
