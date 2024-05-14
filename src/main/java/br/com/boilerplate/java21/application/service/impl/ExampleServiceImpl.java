package br.com.boilerplate.java21.application.service.impl;

import br.com.boilerplate.java21.application.service.ExampleService;
import br.com.boilerplate.java21.domain.model.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExampleServiceImpl implements ExampleService {

    /**
     * Search the examples
     *
     * @param page pagination
     * @return a pagination of examples
     */
    public Page<Example> search(Pageable page) {

        List<Example> examples = new ArrayList<>();
        examples.add(Example.builder().id(1L).firstName("Hello").lastName("Java 21").build());
        examples.add(Example.builder().id(2L).firstName("Hello").lastName("Spring Boot 3.2.5").build());

        return new PageImpl<>(examples, page, examples.size());
    }

}
