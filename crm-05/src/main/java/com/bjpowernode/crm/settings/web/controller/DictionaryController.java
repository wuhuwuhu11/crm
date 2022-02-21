package com.bjpowernode.crm.settings.web.controller;

import com.bjpowernode.crm.exception.AjaxRequestException;
import com.bjpowernode.crm.exception.TranditionRequestException;
import com.bjpowernode.crm.settings.domain.DictionaryType;
import com.bjpowernode.crm.settings.domain.DictionaryValue;
import com.bjpowernode.crm.settings.service.DictionaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/settings/dictionary")
public class DictionaryController {

    @Autowired
    private DictionaryService dictionaryService;

    /**
     * 跳转到字典模块首页面
     * @return
     */
    @RequestMapping("/toIndex.do")
    public String toIndex(){
        return "/settings/dictionary/index";
    }

    /**
     * 跳转到字典模块的类型模块的首页面
     *      Model 和 ModelAndVie 是一样的东西
     *          ModelAndVie,封装了视图和数据
     *          Model中封装了数据,视图通过返回值进行跳转
     * @return
     */
    @RequestMapping("/type/toIndex.do")
    public String toTypeIndex(Model model){

        //将数据字典列表查询出来
        List<DictionaryType> dictionaryTypeList = dictionaryService.findDictionaryTypeList();

        //通过Model对象,携带到页面中
        //页面通过EL表达式进行加载和遍历
        model.addAttribute("dictionaryTypeList",dictionaryTypeList);

        return "/settings/dictionary/type/index";
    }


    /**
     * 跳转到字典类型的新增页面
     * @return
     */
    @RequestMapping("/toTypeSave.do")
    public String toTypeSave(){
        return "/settings/dictionary/type/save";
    }


    /**
     * 根据编码查询数据库中是否已重复
     * @param code
     * @return
     */
    @RequestMapping("/type/checkCode.do")
    @ResponseBody
    public Map<String,Object> checkTypeCode(String code) throws AjaxRequestException {

        //根据编码查询字典类型对象
        DictionaryType dictionaryType = dictionaryService.findDictionaryTypeByCode(code);

        //定义返回值结果集
        Map<String,Object> resultMap = new HashMap<>();

        if(ObjectUtils.isEmpty(dictionaryType)){
            //编码未重复,可以插入
            resultMap.put("code",0);
            resultMap.put("msg","编码未重复,可以新增...");
            return resultMap;
        }else {
            //编码已重复,不能插入
            throw new AjaxRequestException("编码已重复,无法新增...");
        }

    }


    /**
     * 新增字典类型
     * @param dictionaryType
     * @return
     */
    @RequestMapping("/type/saveDictionaryType.do")
    @ResponseBody
    public Map<String,Object> saveDictionaryType(DictionaryType dictionaryType) throws AjaxRequestException {

        //新增操作,新增成功,1,新增失败,0
        boolean count = dictionaryService.saveDictionaryType(dictionaryType);

        Map<String,Object> resultMap = new HashMap<>();

        if(count){
            resultMap.put("code",0);
            resultMap.put("msg","新增成功...");
            return resultMap;
        }else{
            throw new AjaxRequestException("新增失败...");
        }

    }


    /**
     * 跳转到字典类型编辑页面
     *      根据编码查询字典类型数据
     * @param code
     * @return
     * @throws TranditionRequestException
     */
    @RequestMapping("/type/toEdit.do")
    public String toTypeEdit(String code,Model model) throws TranditionRequestException {

        //根据编码查询字典类型数据
        DictionaryType dictionaryType = dictionaryService.findDictionaryTypeByCode(code);

        if(dictionaryType == null)
            throw new TranditionRequestException("当前数据查询异常...");

        //封装到Model对象中
        model.addAttribute("dictionaryType",dictionaryType);

        //跳转到修改页面
        return "/settings/dictionary/type/edit";
    }


    /**
     * 更新字典类型操作
     *      根据编码更新类型名称和描述
     * @param dictionaryType
     * @return
     */
    @RequestMapping("/type/updateDictionaryType.do")
    @ResponseBody
    public Map<String,Object> updateDictionaryType(DictionaryType dictionaryType) throws AjaxRequestException {

        //更新操作,以update方法名称开头,开启事务
        boolean flag = dictionaryService.updateDictionaryType(dictionaryType);

        if(!flag){
            //修改失败,抛出异常
            throw new AjaxRequestException("修改字典类型失败...");
        }

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("msg","修改成功...");

        return resultMap;

    }


    /**
     * 批量删除字典类型
     * @param codes
     * @return
     */
    @RequestMapping("/type/batchDeleteDictionaryType.do")
    @ResponseBody
    public Map<String,Object> batchDeleteDictionaryType(String[] codes) throws AjaxRequestException {

        //批量删除
        boolean flag = dictionaryService.deleteDictionaryList(codes);

        if(!flag)
            throw new AjaxRequestException("删除失败...");

        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",0);
        resultMap.put("msg","删除成功...");

        return resultMap;
    }

    /**
     * 批量删除字典类型
     *      一方 : tbl_dic_type
     *      多方 : tbl_dic_value
     * @param codes
     * @return
     */
    @RequestMapping("/type/batchDeleteDictionaryTypeCondition.do")
    @ResponseBody
    public Map<String,Object> batchDeleteDictionaryTypeCondition(String[] codes) throws AjaxRequestException {

        //批量删除
        List<String> codeList = dictionaryService.deleteDictionaryListCondition(codes);

        if(ObjectUtils.isEmpty(codeList)){
            //批量删除,全部执行成功,删除的数据,没有关联关系
            Map<String,Object> resultMap = new HashMap<>();
            resultMap.put("code",0);
            resultMap.put("msg","删除成功...");

            return resultMap;
        }

        //有部分内容无法进行删除,说明包含了关联关系
        Map<String,Object> resultMap = new HashMap<>();
        resultMap.put("code",1);
        resultMap.put("msg","删除成功...");
        resultMap.put("data",codeList);

        return resultMap;

    }

    /**
     * 跳转到字典模块的值模块的首页面
     * @return
     */
    @RequestMapping("/value/toIndex.do")
    public String toValueIndex(){
        return "/settings/dictionary/value/index";
    }


    /**
     * 查询字典值列表
     * @return
     */
    @RequestMapping("/value/getDictionaryValueList.do")
    @ResponseBody
    public Map<String,Object> getDictionaryValueList(){

        //查询字典值列表
        List<DictionaryValue> dictionaryValueList = dictionaryService.findDictionaryValueList();

        //封装返回值结果集
        Map<String,Object> resultMap = new HashMap<>();

        if(ObjectUtils.isEmpty(dictionaryValueList)){
            //没有字典值列表数据
            resultMap.put("code",1);
            resultMap.put("msg","当前列表无数据...");
            return resultMap;
        }

        resultMap.put("code",0);
        resultMap.put("msg","查询成功...");
        resultMap.put("data",dictionaryValueList);
        return resultMap;

    }
}
