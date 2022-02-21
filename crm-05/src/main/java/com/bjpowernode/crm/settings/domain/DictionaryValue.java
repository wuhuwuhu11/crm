package com.bjpowernode.crm.settings.domain;

import lombok.Data;

import java.io.Serializable;

@Data
public class DictionaryValue implements Serializable {

    private String id;
    private String text;
    private String value;
    private String orderNo;
    private String typeCode;

}
