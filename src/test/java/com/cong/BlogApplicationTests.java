package com.cong;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cong.web.mapper.SysUserMapper;
import com.cong.web.model.SysUser;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @description 测试类
 * @author guicong
 * @since 2019-04-18
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class BlogApplicationTests {

    /**
     * 新增用户
     */
    @Test
    public void addUser(){
        SysUser sysUser = new SysUser();
        String salt = UUID.randomUUID().toString().replace("-", "");
        Md5Hash md5Hash = new Md5Hash("123", salt,2);
        System.out.println(md5Hash.toString());
        sysUser.setUserName("zhangsan");
        sysUser.setPassword(md5Hash.toString());
        sysUser.setRealName("张三");
        sysUser.setSalt(salt);
        sysUser.setCreateOn(LocalDateTime.now());
        sysUser.setCreateBy(0L);
        if (sysUser.insert()) {
            System.out.println("插入成功");
        } else {
            System.out.println("插入失败");
        }
    }

    /**
     * 查询所有用户
     */
    @Test
    public void queryUser(){
        List<SysUser> list = new SysUser().selectAll();
        System.out.println(list.toString());
    }

    /**
     * 分页查询用户
     */
    @Test
    public void queryUserByPage(){
        Page<SysUser> page = new Page<>(1, 10);
        IPage<SysUser> iPage = new SysUser().selectPage(page,null);
        System.out.println(iPage.getRecords().toString());
    }


}
