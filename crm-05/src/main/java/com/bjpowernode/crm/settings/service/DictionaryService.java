package com.bjpowernode.crm.settings.service;

import com.bjpowernode.crm.exception.AjaxRequestException;
import com.bjpowernode.crm.settings.domain.DictionaryType;
import com.bjpowernode.crm.settings.domain.DictionaryValue;

import java.util.List;

public interface DictionaryService {
    List<DictionaryType> findDictionaryTypeList();

    DictionaryType findDictionaryTypeByCode(String code);

    boolean saveDictionaryType(DictionaryType dictionaryType);

    boolean updateDictionaryType(DictionaryType dictionaryType);

    boolean deleteDictionaryList(String[] codes) throws AjaxRequestException;

    List<String> deleteDictionaryListCondition(String[] codes) throws AjaxRequestException;

    List<DictionaryValue> findDictionaryValueList();

}
