package doctor_api.com.example.api_doctor.helper.utils.Agora;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;
@Component
public class AgorUtil {
    @Value("${agora.appId}")
    private String APP_ID;
    @Value("${agora.appCertificate}")
    private String APP_CERTIFICATE;


    public String GenereteTokenRtc(String channelName, String uid, String role, int expireTime) {
        
        
    }
}
