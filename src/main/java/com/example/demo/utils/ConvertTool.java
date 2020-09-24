package com.example.demo.utils;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;

public class ConvertTool {
    private static final Mapper MAPPER = DozerBeanMapperBuilder.buildDefault();

    public static <T> T convertObject(Object source, Class<T> destinationClass) {
        return MAPPER.map(source, destinationClass);
    }
}
