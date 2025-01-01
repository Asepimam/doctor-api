package doctor_api.com.example.api_doctor.helper.media;

public interface PackableEx extends Packable {
    void unmarshal(ByteBuf in);
}
