package doctor_api.com.example.api_doctor.controller;


import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import doctor_api.com.example.api_doctor.helper.ApiResponse;
import doctor_api.com.example.api_doctor.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @PostMapping("create-snap-transaction")
    public ResponseEntity<ApiResponse<Map<String,Object>>> createSnapTransaction(@RequestBody Map<String, Object> requestBody) {
        String orderId = (String) requestBody.get("orderId");
        double grossAmount = ((Number) requestBody.get("grossAmount")).doubleValue();
        String customerEmail = (String) requestBody.get("customerEmail");
        return this.paymentService.createSnapTransaction(orderId, grossAmount, customerEmail);
    }
    
    
}
