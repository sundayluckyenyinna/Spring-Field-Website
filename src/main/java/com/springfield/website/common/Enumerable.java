package com.springfield.website.common;

import java.util.Arrays;
import java.util.Objects;

public interface Enumerable<E extends Enum<?>> {

    Integer getId();
    E[] getValues();

    default E getById(Integer id){
        return Arrays.stream(getValues())
                .filter(element -> Objects.equals(getId(), id))
                .findFirst()
                .orElse(null);
    }
}
