package br.com.boilerplate.java21.application.service.impl;

import br.com.boilerplate.java21.application.service.ExampleService;
import br.com.boilerplate.java21.domain.model.Example;
import br.com.boilerplate.java21.infrastructure.client.http.ExampleClient;
import br.com.boilerplate.java21.infrastructure.client.http.ExampleClientFactory;
import br.com.boilerplate.java21.presentation.dto.record.MyIpRecord;
import br.com.boilerplate.java21.presentation.exception.ApiErrorException;
import br.com.boilerplate.java21.presentation.exception.ApiErrorMessage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExampleServiceImpl implements ExampleService {

    private final ExampleClientFactory exampleClientFactory;

    public ExampleServiceImpl(ExampleClientFactory exampleClientFactory) {
        this.exampleClientFactory = exampleClientFactory;
    }

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

    @Override
    public void getError400() {
        throw new ApiErrorException(HttpStatus.BAD_REQUEST, ApiErrorMessage.CUSTOM_BAD_REQUEST.getProperty());
    }

    @Override
    public void getError404() {
        throw new ApiErrorException(HttpStatus.NOT_FOUND, ApiErrorMessage.CUSTOM_NOT_FOUND.getProperty());
    }

    @Override
    public void getError500() {
        throw new ApiErrorException(HttpStatus.INTERNAL_SERVER_ERROR, ApiErrorMessage.UNKNOWN_ERROR.getProperty());
    }

    @Override
    public void getError500WithNullPointer() {
        Example example = null;
        example.getFirstName();
    }

    @Override
    public String searchMyIp() {
        ExampleClient client = exampleClientFactory.createClient();
        MyIpRecord result = client.searchMyIp();
        return result.myIp();
    }

}
