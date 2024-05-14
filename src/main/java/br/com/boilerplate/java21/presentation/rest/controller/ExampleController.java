package br.com.boilerplate.java21.presentation.rest.controller;

import br.com.boilerplate.java21.application.service.ExampleService;
import br.com.boilerplate.java21.domain.model.Example;
import br.com.boilerplate.java21.presentation.dto.record.ExampleRecord;
import br.com.boilerplate.java21.presentation.mapper.ExampleMapper;
import br.com.boilerplate.java21.presentation.rest.HttpConstantsUtils;
import br.com.boilerplate.java21.util.Constants;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "examples")
public class ExampleController {

    private final ExampleService exampleService;
    private final ExampleMapper exampleMapper;

    public ExampleController(ExampleService exampleService, ExampleMapper exampleMapper) {
        this.exampleService = exampleService;
        this.exampleMapper = exampleMapper;
    }

    @Operation(summary = "Search examples with pagination")
    @ApiResponses(value = {@ApiResponse(responseCode = HttpConstantsUtils.SUCCESS_REQUEST_CODE, description = HttpConstantsUtils.UNKNOWN_SERVER_ERROR_DESCRIPTION),
            @ApiResponse(responseCode = HttpConstantsUtils.UNKNOWN_SERVER_ERROR_CODE, description = HttpConstantsUtils.UNKNOWN_SERVER_ERROR_DESCRIPTION)})
    @GetMapping("/")
    public ResponseEntity<Page<ExampleRecord>> getExamples(
            @PageableDefault(sort = "id", direction = Direction.DESC, page = Constants.DEFAULT_PAGE_FIRST, size = Constants.DEFAULT_PAGE_SIZE) Pageable page) {
        Page<Example> examples = this.exampleService.search(page);
        List<ExampleRecord> dtoList = this.exampleMapper.toListDTO(examples.getContent());
        return ResponseEntity.ok(new PageImpl<>(dtoList, page, examples.getTotalElements()));
    }

}
