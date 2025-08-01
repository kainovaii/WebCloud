package fr.kainovaii.shopspring.service;

import fr.kainovaii.shopspring.model.ClientService;
import fr.kainovaii.shopspring.repository.ServiceInstanceRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ClientServiceService
{
    private final ServiceInstanceRepository serviceInstanceRepository;

    public ClientServiceService(ServiceInstanceRepository serviceInstanceRepository) {
        this.serviceInstanceRepository = serviceInstanceRepository;
    }

    public List<ClientService> findAll() {
        return serviceInstanceRepository.findAll();
    }

    public Optional<ClientService> findById(Long id) {
        return serviceInstanceRepository.findById(id);
    }

    public ClientService create(ClientService service) {
        return serviceInstanceRepository.save(service);
    }

    public ClientService update(Long id, ClientService updatedService) {
        return serviceInstanceRepository.findById(id)
        .map(service -> {
            service.setServiceName(updatedService.getServiceName());
            service.setPrice(updatedService.getPrice());
            service.setDuration(updatedService.getDuration());
            return serviceInstanceRepository.save(service);
        }).orElseThrow(() -> new RuntimeException("Service not found with id " + id));
    }

    public void delete(Long id) {
        serviceInstanceRepository.deleteById(id);
    }

    public List<ClientService> getServicesByUser(long userId) {
        return serviceInstanceRepository.findAllByUserId(userId);
    }

    public long countServicesByUser(Long userId) {
        return serviceInstanceRepository.countByUserId(userId);
    }

    public long count() {
        return serviceInstanceRepository.count();
    }
}