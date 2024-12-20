package com.springfield.website.common;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Getter
@Setter
public class OmnixPage<T> {
    private PaginationMeta meta;
    private List<T> records;

    private OmnixPage(){
        this.meta = new PaginationMeta();
        this.records = new ArrayList<>();
    }

    public static <T> OmnixPage<T> from(Page<T> page){
        List<T> content = page.getContent();
        PaginationMeta paginationMeta = new PaginationMeta();
        paginationMeta.setPageNumber(page.getPageable().getPageNumber() + 1);
        paginationMeta.setPageSize(page.getPageable().getPageSize());
        paginationMeta.setPageCount(content.size());
        paginationMeta.setTotalCount(page.getTotalElements());
        paginationMeta.setNumberOfPages(page.getTotalPages());

        OmnixPage<T> omnixPage = new OmnixPage<>();
        return omnixPage.withPaginationMeta(paginationMeta).withRecords(content);
    }

    public OmnixPage<T> withPaginationMeta(PaginationMeta meta){
        setMeta(meta);
        return this;
    }

    public OmnixPage<T> withRecords(List<T> records){
        setRecords(records);
        return this;
    }

    public static <T> OmnixPage<T> withRecordsAndPageParameter(List<T> records, PageParameters pageParameters, long totalCount, long pageCount){
        OmnixPage<T> page = new OmnixPage<>();
        page.setRecords(records);
        PaginationMeta paginationMeta = PaginationMeta.fromRecordsAndPageParametersAndCounts(records, pageParameters, totalCount, pageCount);
        page.setMeta(paginationMeta);
        return page;
    }

    public static <T> OmnixPage<T> emptyPage(){
        return new OmnixPage<>();
    }

    public static <T> Page<T> getPageFromList(List<T> documentUploads, int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        int startItem = pageNumber * pageSize;
        List<T> list;
        if (documentUploads.size() < startItem) {
            list = Collections.emptyList();
        } else {
            int toIndex = Math.min(startItem + pageSize, documentUploads.size());
            list = documentUploads.subList(startItem, toIndex);
        }
        return new PageImpl<>(list, pageable, documentUploads.size());
    }
}
