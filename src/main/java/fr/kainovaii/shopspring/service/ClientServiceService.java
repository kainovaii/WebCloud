package fr.kainovaii.shopspring.service;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.repository.ClientServiceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceService
{
    private final ClientServiceRepository serviceRepository;

    public ClientServiceService(ClientServiceRepository serviceRepository) {
        this.serviceRepository = serviceRepository;
    }

    public List<ClientService> getAll() {
        return serviceRepository.findAll();
    }

    public Optional<ClientService> getById(Long id) {
        return serviceRepository.findById(id);
    }

    public ClientService create(ClientService service) {
        return serviceRepository.save(service);
    }

    public ClientService update(Long id, ClientService updatedService) {
        return serviceRepository.findById(id)
        .map(service -> {
            service.setServiceName(updatedService.getServiceName());
            service.setPrice(updatedService.getPrice());
            service.setDuration(updatedService.getDuration());
            return serviceRepository.save(service);
        }).orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }

    public void delete(Long id) {
        serviceRepository.deleteById(id);
    }
}