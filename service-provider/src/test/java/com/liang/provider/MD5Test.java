package com.liang.provider;

import com.liang.api.entity.User;
import org.springframework.util.DigestUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MD5Test {

    public static void main(String[] args) {

        List list = new ArrayList<>();

        for(int i = 0 ;i<10000;i++){
            User  user = new User();
            user.setUsername(i+"");
            user.setPassword(i+"");

            user.setAge(i+"");
            //user.setId(UUID.randomUUID().toString());

            list.add(user);

        }


        long start = System.currentTimeMillis();

        String md5 = DigestUtils.md5DigestAsHex(toByteArray(list));

        long end = System.currentTimeMillis();

        System.out.println((end-start)+"ms");

        System.out.println(md5);


    }


    public static byte[] toByteArray (Object obj) {
        byte[] bytes = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(obj);
            oos.flush();
            bytes = bos.toByteArray ();
            oos.close();
            bos.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return bytes;
    }
}
