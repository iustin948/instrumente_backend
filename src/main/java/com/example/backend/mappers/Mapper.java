package com.example.backend.mappers;
import org.springframework.context.annotation.Bean;


public interface Mapper<A ,B> {
    B mapTo(A a);
    A mapFrom(B b);
}
