package cn.yanrui.shop.config;

import lombok.Data;

@Data
public class DefaultResponse<T> {
    T data;
    String code;
    boolean success;

}
