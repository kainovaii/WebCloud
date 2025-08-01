package fr.kainovaii.shopspring.controller.shop;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import fr.kainovaii.shopspring.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Controller("paypalController")
@RequestMapping("/api/paypal")
public class PayPalController
{
    @Autowired
    private PayPalService payPalService;

    private static final String SUCCESS_URL = "http://localhost:8080/api/paypal/success";
    private static final String CANCEL_URL = "http://localhost:8080/api/paypal/cancel";

    @PostMapping("/pay")
    public String payment(@RequestParam double amount)
    {
        try {
            Payment payment = payPalService.createPayment(
                    amount,                   // total
                    "EUR",                   // currency
                    "paypal",                // method
                    "sale",                  // intent
                    "Test paiement PayPal",  // description
                    CANCEL_URL,
                    SUCCESS_URL
            );
            for (Links link : payment.getLinks()) {
                if ("approval_url".equals(link.getRel())) {
                    return "redirect:" + link.getHref();
                }
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/success")
    public String success(@RequestParam Map<String,String> allParams, Model model) {
        allParams.forEach((key, value) -> System.out.println(key + " : " + value));

        String paymentId = allParams.get("paymentId");
        String payerId = allParams.get("PayerID");
        if (payerId == null) {
            payerId = allParams.get("PayerId");
        }
        if (paymentId == null || payerId == null) {
            System.out.println("Paramètres manquants !");
            return "redirect:/";
        }

        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if ("approved".equals(payment.getState())) {
                model.addAttribute("paymentId", paymentId);
                //return "success";
                return "redirect:/client/orders";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }
}
