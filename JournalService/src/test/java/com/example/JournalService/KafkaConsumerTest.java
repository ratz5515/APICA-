package com.example.JournalService;

import com.example.JournalService.repository.EventRepository;
import com.example.JournalService.service.KafkaConsumerService;
import com.example.JournalService.model.Event;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;

public class KafkaConsumerServiceTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private KafkaConsumerService kafkaConsumerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testConsumeEvent() {
        String eventMessage = "User Registered";

        kafkaConsumerService.consumeEvent(eventMessage);

        verify(eventRepository, times(1)).save(any(Event.class));
    }
}
