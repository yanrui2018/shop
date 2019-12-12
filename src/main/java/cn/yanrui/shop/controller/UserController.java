package cn.yanrui.shop.controller;

import cn.yanrui.shop.config.DefaultResponse;
import cn.yanrui.shop.rabbitmq.impl.Producer;
import cn.yanrui.shop.rabbitmq.po.Mail;
import cn.yanrui.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(value = "/user",method = RequestMethod.GET)
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private Producer producer;


    @RequestMapping("/login")
    public DefaultResponse login(@RequestParam String phone,
                                 @RequestParam String  password){
        Mail mail = new Mail();
        mail.setMailId(phone);
        mail.setCountry("中国");
        producer.sendMail("myqueue",mail);


        boolean ret = userService.login(phone,password);
        DefaultResponse defaultResponse = new DefaultResponse();
        defaultResponse.setData(ret);
        defaultResponse.setSuccess(true);
        return defaultResponse;
    }


    @RequestMapping("/upload")
    public DefaultResponse upload(@RequestParam MultipartFile multipartFile){
        DefaultResponse defaultResponse = new DefaultResponse();
        boolean ret = userService.upload(multipartFile);
        defaultResponse.setSuccess(true);
        return defaultResponse;
    }


}
