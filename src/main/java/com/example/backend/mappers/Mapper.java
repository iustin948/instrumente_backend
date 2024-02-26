package com.example.backend.mappers;
import com.example.backend.domain.dto.SignUpDto;
import org.springframework.context.annotation.Bean;


public interface Mapper<A ,B> {
    B mapTo(A a);
    A mapFrom(B b);

}
