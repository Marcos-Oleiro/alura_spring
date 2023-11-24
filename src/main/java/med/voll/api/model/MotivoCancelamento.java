package med.voll.api.model;

import com.fasterxml.jackson.annotation.JsonValue;


public enum MotivoCancelamento {

    PACIENTE_CANCELOU("Paciente cancelou"),
    MEDICO_CANCELOU("Médico cancelou"),
    OUTROS("Outros");

    private String motivo;

    MotivoCancelamento(String motivo) {
        this.motivo = motivo;
    }

    @JsonValue
    public String getMotivo() {
        return this.motivo;
    }

    // public static class Deserializer extends FromStringDeserializer<MotivoCancelamento> {
        
    //     public Deserializer() {
    //         super(MotivoCancelamento.class);
    //     }

    //     @Override
    //     protected MotivoCancelamento _deserialize(String value, DeserializationContext ctxt) {
    //         return MotivoCancelamento.valueOf(value.toUpperCase());
    //     }
    // }
}