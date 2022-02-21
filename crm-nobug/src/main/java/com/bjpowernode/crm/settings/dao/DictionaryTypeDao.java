package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.DictionaryType;

import java.util.List;

public interface DictionaryTypeDao {

    List<DictionaryType> findAll();

    boolean insert(DictionaryType dictionaryType);

    DictionaryType findByCode(String code);
}
