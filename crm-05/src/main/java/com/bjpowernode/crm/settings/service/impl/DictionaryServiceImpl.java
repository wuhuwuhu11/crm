package com.bjpowernode.crm.settings.service.impl;

import com.bjpowernode.crm.exception.AjaxRequestException;
import com.bjpowernode.crm.settings.dao.DictionaryTypeDao;
import com.bjpowernode.crm.settings.dao.DictionaryValueDao;
import com.bjpowernode.crm.settings.domain.DictionaryType;
import com.bjpowernode.crm.settings.domain.DictionaryValue;
import com.bjpowernode.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DictionaryServiceImpl implements DictionaryService {

    @Autowired
    private DictionaryTypeDao dictionaryTypeDao;

    @Autowired
    private DictionaryValueDao dictionaryValueDao;

    @Override
    public List<DictionaryType> findDictionaryTypeList() {
        return dictionaryTypeDao.findAll();
    }

    @Override
    public DictionaryType findDictionaryTypeByCode(String code) {
        return dictionaryTypeDao.findByCode(code);
    }

    @Override
    public boolean saveDictionaryType(DictionaryType dictionaryType) {
        return dictionaryTypeDao.insert(dictionaryType);
    }

    @Override
    public boolean updateDictionaryType(DictionaryType dictionaryType) {
        return dictionaryTypeDao.update(dictionaryType);
    }

    @Override
    public boolean deleteDictionaryList(String[] codes) throws AjaxRequestException {

        //批量删除
        //1. 可以根据遍历的方式进行逐个删除
        //for (String code : codes) {
        //    boolean flag = dictionaryTypeDao.deleteByCode(code);
        //    if(!flag)
        //        throw new AjaxRequestException("删除失败...");
        //}

        //2. 直接批量删除,通过sql语句
        boolean flag = dictionaryTypeDao.deleteListByCodes(codes);

        if(!flag)
            throw new AjaxRequestException("批量删除失败...");

        return true;
    }

    @Override
    public List<String> deleteDictionaryListCondition(String[] codes) throws AjaxRequestException {

        List<String> codeList = new ArrayList<>();

        //遍历接收到的参数集合
        for (String code : codes) {

            //根据编码查询是否有一方关联的多方数据,字典值的列表
            int valueCount = dictionaryValueDao.findCountByTypeCode(code);

            if(valueCount == 0){
                //如果没有关联的列表数据,则可以删除
                boolean flag = dictionaryTypeDao.deleteByCode(code);

                if(!flag)
                    throw new AjaxRequestException("删除失败...");

            }else{
                //如果有关联的列表数据,则无法删除
                //将无法删除的数据(code),存入到集合中返回到页面,进行提示给用户
                codeList.add(code);
            }

        }

        //将无法删除的编码集合返回
        return codeList;

    }

    @Override
    public List<DictionaryValue> findDictionaryValueList() {
        return dictionaryValueDao.findAll();
    }
}
