package com.bjpowernode.crm.settings.dao;

import com.bjpowernode.crm.settings.domain.DictionaryType;

import java.util.List;

/*
    通过Free Mybatis Plugin自动生成Sql标签
        以find或select开头,生成的就是<select>标签
        以insert开头,生成的就是<insert>标签
        以update开头,生成的就是<update>标签
        以delete开头,生成的就是<delete>标签
    Sql语句是需要自己编写
        如果以其他的前缀名称命名的方法,是需要自己选择标签进行生成对应映射的Sql标签
 */
public interface DictionaryTypeDao {

    List<DictionaryType> findAll();

    DictionaryType findByCode(String code);

    boolean insert(DictionaryType dictionaryType);

    boolean update(DictionaryType dictionaryType);

    boolean deleteByCode(String code);

    boolean deleteListByCodes(String[] codes);
}
