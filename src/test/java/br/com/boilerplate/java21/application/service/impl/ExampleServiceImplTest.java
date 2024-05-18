package br.com.boilerplate.java21.application.service.impl;

import br.com.boilerplate.java21.domain.model.Example;
import br.com.boilerplate.java21.infrastructure.client.http.ExampleClient;
import br.com.boilerplate.java21.infrastructure.client.http.ExampleClientFactory;
import br.com.boilerplate.java21.presentation.dto.record.MyIpRecord;
import br.com.boilerplate.java21.presentation.exception.ApiErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ExampleServiceTest {

    @InjectMocks
    private ExampleServiceImpl service;

    @Mock
    private ExampleClientFactory exampleClientFactory;

    @Mock
    private ExampleClient exampleClient;

    @Test
    void testSearch() {
        List<Example> expected = new ArrayList<>();
        expected.add(Example.builder().id(1L).firstName("Hello").lastName("Java 21").build());
        expected.add(Example.builder().id(2L).firstName("Hello").lastName("Spring Boot 3.2.5").build());

        Page<Example> actual = service.search(PageRequest.of(0, 30));

        assertEquals(expected, actual.getContent());
    }

    @Test
    void testGetError400() {
        ApiErrorException exception = assertThrows(ApiErrorException.class, () -> {
            service.getError400();
        });

        assertEquals(HttpStatus.BAD_REQUEST, exception.getStatus());
    }

    @Test
    void testGetError404() {
        ApiErrorException exception = assertThrows(ApiErrorException.class, () -> {
            service.getError404();
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    void testGetError500() {
        ApiErrorException exception = assertThrows(ApiErrorException.class, () -> {
            service.getError500();
        });

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, exception.getStatus());
    }

    @Test
    void testSearchMyIp() {
        MyIpRecord myIp = MyIpRecord.builder().myIp("192.168.0.1").build();

        when(exampleClientFactory.createClient()).thenReturn(exampleClient);
        when(exampleClient.searchMyIp()).thenReturn(myIp);

        String actual = service.searchMyIp();

        Assertions.assertEquals("192.168.0.1", actual);
    }
}
