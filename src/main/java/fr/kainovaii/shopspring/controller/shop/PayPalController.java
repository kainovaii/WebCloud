package fr.kainovaii.shopspring.controller.shop;

import com.paypal.api.payments.Links;
import com.paypal.api.payments.Payment;
import com.paypal.base.rest.PayPalRESTException;
import fr.kainovaii.shopspring.service.PayPalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller("paypalController")
public class PayPalController
{
    @Autowired
    private PayPalService payPalService;

    private static final String SUCCESS_URL = "http://localhost:8080/success";
    private static final String CANCEL_URL = "http://localhost:8080/cancel";

    @GetMapping("/pay")
    public String payment() {
        try {
            Payment payment = payPalService.createPayment(
                    20.00,                   // total
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
    public String success(
            @RequestParam("paymentId") String paymentId,
            @RequestParam("PayerID") String payerId,
            Model model
    ) {
        try {
            Payment payment = payPalService.executePayment(paymentId, payerId);
            if ("approved".equals(payment.getState())) {
                model.addAttribute("paymentId", paymentId);
                return "success";
            }
        } catch (PayPalRESTException e) {
            e.printStackTrace();
        }
        return "redirect:/";
    }

    @GetMapping("/cancel")
    public String cancel() {
        return "cancel";
    }
}
