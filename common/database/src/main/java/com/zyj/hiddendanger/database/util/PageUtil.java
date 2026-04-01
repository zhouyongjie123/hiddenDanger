package com.zyj.hiddendanger.database.util;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import java.util.List;

public class PageUtil {
    private PageUtil() {
    }

    @SuppressWarnings("unchecked")
    public static <E, T> Page<T> pageConvert(Page<E> sourcePage, List<T> newRecords) {
        Page<T> targetPage = (Page<T>) sourcePage;
        targetPage.setRecords(newRecords);
        return targetPage;
    }

}
