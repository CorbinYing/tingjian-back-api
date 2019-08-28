package org.corbin.common.base.convert;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: Corbin
 * @Date: 2018/12/4
 * @ClassName: BaseConvert
 * @Descripton:
 */

public class BaseConvert<VO,DO> {
    private static final Logger logger = LoggerFactory.getLogger(BaseConvert.class);

    /**
     * 单个对象转换
     * @param from  需要转换的对象
     * @param clazz  转成的目标对象类
     * @return
     */
    public VO convert(DO from, Class<VO> clazz) {
        return convert(from, clazz, null);
    }


    public DO counvert2Entity(VO from,Class<DO> clazz){
        return convert2Entity(from,clazz,null);
    }


    /**
     * 单个对象转换
     *
     * @param from 需要转换的对象
     * @param clazz 转成的目标对象类
     * @param ignoreProperties 转换对象中，不需要copy的属性
     */
    public VO convert(DO from, Class<VO> clazz, String... ignoreProperties) {
        if (from == null) {
            return null;
        }
        VO to = null;
        try {
            to = clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            logger.error("初始化{}对象失败。", clazz, e);
        }
        convert(from, to, ignoreProperties);
        return to;
    }

    /**
     * 单个对象转换
     *
     * @param from 需要转换的对象
     * @param clazz 转成的目标对象类
     * @param ignoreProperties 转换对象中，不需要copy的属性
     */
    public DO convert2Entity(VO from, Class<DO> clazz, String... ignoreProperties) {
        if (from == null) {
            return null;
        }
        DO to = null;
        try {
            to = clazz.newInstance();
        } catch (ReflectiveOperationException e) {
            e.printStackTrace();
            logger.error("初始化{}对象失败。", clazz, e);
        }
        convert2Entity(from, to, ignoreProperties);
        return to;
    }

    /**
     * 批量对象转换
     * @param fromList  需要转换的对象列表
     * @param clazz  转成的目标对象类
     */
    public List<VO> convert(List<DO> fromList, Class<VO> clazz) {
        return convert(fromList, clazz, null);
    }

    /**
     * 批量对象转换
     *
     * @param fromList 需要转换的对象列表
     * @param clazz 转成的目标对象类
     * @param ignoreProperties 转换对象中，不需要copy的属性
     */
    public List<VO> convert(List<DO> fromList, Class<VO> clazz, String... ignoreProperties) {
        List<VO> toList = new ArrayList<VO>();
        if (fromList != null) {
            for (DO from : fromList) {
                toList.add(convert(from, clazz, ignoreProperties));
            }
        }

        return toList;
    }

    /**
     * 分页对象转换
     * @param fromPage  需要转换的分页对象
     * @param clazz  转成的目标对象类
     */
    public Page<VO> convert(Page<DO> fromPage, Class<VO> clazz) {
        return convert(fromPage, clazz, null);
    }

    /**
     * 分页对象转换
     *
     * @param fromPage 需要转换的分页对象
     * @param clazz 转成的目标对象类
     * @param ignoreProperties 转换对象中，不需要copy的属性
     */
    public Page<VO> convert(Page<DO> fromPage, Class<VO> clazz, String... ignoreProperties) {
        List<VO> voContent = new ArrayList();
        PageRequest pageRequest = null;
        if (fromPage != null && fromPage.getContent() != null) {
            voContent = convert(fromPage.getContent(), clazz, ignoreProperties);
            pageRequest = new PageRequest(fromPage.getNumber(), fromPage.getSize(), fromPage.getSort());
        }
        Page<VO> voPage = new PageImpl<>(voContent, pageRequest, fromPage.getTotalElements());

        return voPage;
    }

    /**
     * 属性拷贝方法，有特殊需求时子类覆写此方法
     */
    protected void convert(DO from, VO to) {
        convert(from, to, null);
    }

    protected void convert2Entity(VO from,DO to){
        convert2Entity(from,to,null);
    }

    /**
     * 对象装换
     *
     * @param from 需要转换的对象
     * @param to 转换成的目标对象
     * @param ignoreProperties 目标对象中不需要转换的属性
     */
    protected void convert(DO from, VO to, String... ignoreProperties) {
        BeanUtils.copyProperties(from, to, ignoreProperties);
    }

    /**
     * 对象装换
     *
     * @param from 需要转换的对象
     * @param to 转换成的目标对象
     * @param ignoreProperties 目标对象中不需要转换的属性
     */
    protected void convert2Entity(VO from, DO to, String... ignoreProperties) {
        BeanUtils.copyProperties(from, to, ignoreProperties);
    }


}
