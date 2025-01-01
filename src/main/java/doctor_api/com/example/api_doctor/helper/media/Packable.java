package doctor_api.com.example.api_doctor.helper.media;
/**
 * Created by Li on 10/1/2016.
 */
public interface Packable {
    ByteBuf marshal(ByteBuf out);
}
