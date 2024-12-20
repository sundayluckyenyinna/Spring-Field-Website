package com.springfield.website.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageParameters {
    private Integer pageNumber;
    private Integer pageSize;

    public static PageParameters with(int pageNumber, int pageSize){
        PageParameters pageParameters = new PageParameters();
        pageNumber = Math.max(pageNumber, 1);
        pageSize = Math.max(pageSize, 1);
        pageParameters.setPageNumber(pageNumber - 1);
        pageParameters.setPageSize(pageSize);
        return pageParameters;
    }
}
