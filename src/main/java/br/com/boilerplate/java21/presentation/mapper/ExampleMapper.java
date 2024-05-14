package br.com.boilerplate.java21.presentation.mapper;

import br.com.boilerplate.java21.domain.model.Example;
import br.com.boilerplate.java21.presentation.dto.record.ExampleRecord;
import br.com.boilerplate.java21.util.StringUtil;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ExampleMapper {

    @Mapping(target = "fullName", expression = "java(setFullName(example))")
    ExampleRecord toDTO(Example example);

    List<ExampleRecord> toListDTO(List<Example> example);

    default String setFullName(Example example) {
        return StringUtil.getNonNullString(example.getFirstName()) + " " + StringUtil.getNonNullString(example.getLastName());
    }
}
