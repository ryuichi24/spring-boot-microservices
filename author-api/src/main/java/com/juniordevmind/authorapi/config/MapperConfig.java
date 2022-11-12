package com.juniordevmind.authorapi.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.juniordevmind.authorapi.dtos.AuthorDto;
import com.juniordevmind.authorapi.models.Author;

@Configuration
public class MapperConfig {
    @Bean
    ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();

        modelMapper
                .typeMap(Author.class, AuthorDto.class)
                .addMappings(mapper -> mapper.map(src -> src.getBooks(), AuthorDto::setBookIds));

        modelMapper.addMappings(new PropertyMap<Author, AuthorDto>() {
            @Override
            protected void configure() {
                skip(destination.getBooks());
            }
        });
        return modelMapper;
    }
}
