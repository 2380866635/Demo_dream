package com.dream.search.mapper;

import com.dream.common.pojo.SearchItem;

import java.util.List;

public interface SearchItemMapper {

    List<SearchItem> getSerarchItemList();

    SearchItem getItemById(Long itemId);
}
