package br.com.boilerplate.java21.application.service.impl;

import br.com.boilerplate.java21.domain.model.Example;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
class ExampleServiceTest {

    @InjectMocks
    private ExampleServiceImpl exampleServiceImpl;

    @Test
    void given_find_when_foundData_then_shouldReturnData() {
        List<Example> expected = new ArrayList<>();
        expected.add(Example.builder().id(1L).firstName("Hello").lastName("Java 21").build());
        expected.add(Example.builder().id(2L).firstName("Hello").lastName("Spring Boot 3.2.5").build());

        Page<Example> actual = exampleServiceImpl.search(PageRequest.of(0, 30));

        assertEquals(expected, actual.getContent());
    }
}
