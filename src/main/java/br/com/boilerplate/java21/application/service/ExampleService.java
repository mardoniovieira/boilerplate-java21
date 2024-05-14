package br.com.boilerplate.java21.application.service;

import br.com.boilerplate.java21.domain.model.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ExampleService {

    Page<Example> search(Pageable page);

}
