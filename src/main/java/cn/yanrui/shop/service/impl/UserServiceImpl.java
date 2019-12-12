package cn.yanrui.shop.service.impl;

import cn.yanrui.shop.entity.User;
import cn.yanrui.shop.redis.RedisClient;
import cn.yanrui.shop.repository.UserRepository;
import cn.yanrui.shop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;


@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;
    @Autowired
    RedisClient redisClient;

    @Override
    public boolean login(String phone, String password) {
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(phone)) {
            return false;
        }
        Jedis jedis = redisClient.getJedis();
        String pwd = jedis.get(phone);
        if (password.equals(pwd)) {
            System.out.println("redis校验通过");
            return true;
        }


        User user = userRepository.getUser(phone);
        if (null != user && null != user.getPassword() && user.getPassword().equals(password)) {
            System.out.println("数据库校验通过");
            jedis.set(phone, password);
            return true;
        }

        return false;
    }

    @Override
    public boolean upload(MultipartFile multipartFile) {
        return false;
    }


}
