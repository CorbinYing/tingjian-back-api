package org.corbin.common.base.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.lang.NonNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Getter
@Setter
@Slf4j
public class BaseVo extends EssentialVo {

    protected int pageNum;
    protected int pageSize = 10;

    /**
     * 获得分页参数
     *
     * @return
     */
    public Pageable of() {
        return PageRequest.of(this.pageNum, this.pageSize);
    }

    public static String date2String(@NonNull Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        //将Date类型转换成String类型
        String format = sdf.format(date);
        return format;
    }

    public static Date string2Date(String date) {
        if (date == null || "".equals(date)) {
            return null;
        }
        Date date1 = null;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            date1 = sdf.parse(date);
        } catch (ParseException e) {
            log.info(null, e);
        }

        return date1;
    }
}
