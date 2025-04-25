package com.example.JournalService.service;

import com.example.JournalService.model.Event;
import com.example.JournalService.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class KafkaConsumerService {

    @Autowired
    private EventRepository eventRepository;

    @KafkaListener(topics = "user-events", groupId = "journal-group")
    public void consume(String message) {
        System.out.println("Received message: " + message);
        Event event = new Event(message, LocalDateTime.now());
        eventRepository.save(event);
    }
}
