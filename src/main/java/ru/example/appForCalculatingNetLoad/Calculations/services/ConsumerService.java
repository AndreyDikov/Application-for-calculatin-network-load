package ru.example.appForCalculatingNetLoad.Calculations.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.example.appForCalculatingNetLoad.Calculations.entities.ConsumerEntity;
import ru.example.appForCalculatingNetLoad.Calculations.repositories.ConsumerRepository;

@Service
@RequiredArgsConstructor
public class ConsumerService {

    private final ConsumerRepository consumerRepository;

    public ConsumerEntity getById(Long id) {
        return consumerRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Потребитель с id " + id + " не найден"));
    }

    public ConsumerEntity save(ConsumerEntity consumer) {
        return consumerRepository.save(consumer);
    }
}
