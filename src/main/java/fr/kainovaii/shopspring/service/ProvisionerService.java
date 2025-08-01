package fr.kainovaii.shopspring.service;

import fr.kainovaii.shopspring.model.Order;
import fr.kainovaii.shopspring.provisioning.ProvisionResult;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class ProvisionerService {

    public ProvisionResult provision(Order order) {
        // Ici, on simule la création d'un VPS avec une IP et un nom d'hôte
        String ip = generateFakeIp();
        String hostname = "vps-" + UUID.randomUUID().toString().substring(0, 8);

        System.out.println("Provisionnement du VPS pour la commande " + order.getId() +
                " (Distribution: " + order.getDistribution() + ")");

        return new ProvisionResult(ip, hostname);
    }

    private String generateFakeIp() {
        int a = 192, b = 168;
        int c = (int) (Math.random() * 255);
        int d = (int) (Math.random() * 255);
        return a + "." + b + "." + c + "." + d;
    }
}
