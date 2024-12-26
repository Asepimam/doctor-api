package doctor_api.com.example.api_doctor.config;

import com.midtrans.Config;
import com.midtrans.ConfigFactory;
import com.midtrans.service.MidtransCoreApi;
import com.midtrans.service.MidtransSnapApi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class PaymentMidtrans {
    @Value("${Midtrans.serverKey}")
    private String serverKey;

    @Value("${Midtrans.clientKey}")
    private String clientKey;

    @Value("${Midtrans.isProduction}")
   private boolean isProduction;

   
    @Bean
    public MidtransCoreApi midtransCoreApi() {
        // Konfigurasi Midtrans menggunakan builder
        Config coreApiConfigOptions = Config.builder()
                .setServerKey(serverKey)
                .setClientKey(clientKey)
                .setIsProduction(isProduction) // Tentukan mode production atau sandbox
                .build();

        // Menggunakan Config untuk mendapatkan instance CoreApi
        return new ConfigFactory(coreApiConfigOptions).getCoreApi();
    }

    @Bean
    public MidtransSnapApi midtransSnapApi() {
        // Konfigurasi Snap API menggunakan builder
        Config coreApiConfigOptions = Config.builder()
                .setServerKey(serverKey)
                .setClientKey(clientKey)
                .setIsProduction(isProduction) // Tentukan mode production atau sandbox
                .build();

        // Menggunakan Config untuk mendapatkan instance SnapApi
        return new ConfigFactory(coreApiConfigOptions).getSnapApi();
    }
}
