package com.springfield.website.common;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class PaginationMeta {
    private long pageNumber = 1;   // The page size used in the query. A replica of what the client passed.
    private long pageSize = 10; // The page number used in the query. A replica of what the client passed.
    private long pageCount = 0; // The number of records currently in the page served.
    private long totalCount = 0; // The total item retrieved based on the query even before pagination process
    private long numberOfPages = 0; // The total number of the pages

    public static PaginationMeta fromRecordsAndPageParametersAndCounts(List<?> records, PageParameters pageParameters, long totalCount, long pageCount){
        PaginationMeta meta = new PaginationMeta();
        meta.setPageNumber(pageParameters.getPageNumber());
        meta.setPageSize(pageParameters.getPageSize());
        meta.setPageCount(records.size());
        meta.setTotalCount(totalCount);
        meta.setNumberOfPages(pageCount);
        return meta;
    }
}
