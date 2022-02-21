package com.bjpowernode.crm.settings.domain;

import lombok.Data;

import java.io.Serializable;


@Data
public class DictionaryType implements Serializable {

    private String code;
    private String name;
    private String description;
}
