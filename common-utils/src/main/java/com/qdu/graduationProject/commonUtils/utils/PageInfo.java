package com.qdu.graduationProject.commonUtils.utils;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

/**
 * @author xdl
 * @date 2023/3/17 10:22
 */
@Getter
@Setter
@NoArgsConstructor
public class PageInfo<T> {
    List<T> list;
    Integer totalPage;
    Integer pageNo;
    Integer pageSize;

    @Override
    public String toString() {
        return "PageInfo{" +
                "list = " + list +
                "totalPage" + totalPage +
                ", pageNo=" + pageNo +
                ", pageSize=" + pageSize +
                "}";
    }
}
