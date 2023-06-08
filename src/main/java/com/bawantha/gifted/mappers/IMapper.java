package com.bawantha.gifted.mappers;

public interface IMapper <T,D>{
    T from(D d);
    D to(T t);
}
