package doctor_api.com.example.api_doctor.service;


import com.midtrans.service.MidtransSnapApi;

import doctor_api.com.example.api_doctor.helper.ApiResponse;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class PaymentService {

    @Autowired
    private MidtransSnapApi snapApi;

    public ResponseEntity<ApiResponse<Map<String,Object>>> createSnapTransaction(String orderId, double grossAmount, String customerEmail) {
        // Detail transaksi
        Map<String, Object> transactionDetails = new HashMap<>();
        transactionDetails.put("order_id", orderId);
        transactionDetails.put("gross_amount", grossAmount);

        // Detail pelanggan
        Map<String, Object> customerDetails = new HashMap<>();
        customerDetails.put("email", customerEmail);

        // Gabungkan semua parameter ke dalam request body
        Map<String, Object> requestBody = new HashMap<>();
        requestBody.put("transaction_details", transactionDetails);
        requestBody.put("customer_details", customerDetails);

        try {
            // Memanggil Snap API untuk mendapatkan token
            JSONObject response = snapApi.createTransaction(requestBody);
            Map<String, Object> responseData = response.toMap();
            // ambil token dan url dari response
            String token = (String) responseData.get("token");
            String redirectUrl = (String) responseData.get("redirect_url");
            Map<String, Object> data = new HashMap<>(
                    Map.of("token", token, "redirect_url", redirectUrl)
            );

            return ResponseEntity.ok(new ApiResponse<>(200, "Success create transaction", data));


        } catch (Exception e) {
            throw new RuntimeException("Error creating transaction: " + e.getMessage());
        }
    }
}
