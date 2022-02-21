package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.DictionaryValue;

import java.util.List;

public interface DictionaryValueDao {
    int findCountByTypeCode(String code);

    List<DictionaryValue> findAll();
}
