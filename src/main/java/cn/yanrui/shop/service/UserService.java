package cn.yanrui.shop.service;

import org.springframework.web.multipart.MultipartFile;

public interface UserService {
    boolean login(String phone, String password);

    boolean upload(MultipartFile multipartFile);
}
