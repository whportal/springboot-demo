package com.wh.protobuf.test;


import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.util.JsonFormat;
import com.wh.dto.proto.PersonProto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/12
 */
@SpringBootTest
public class ProtobufTest {

    @Autowired
    private RestTemplate restTemplate;

    @Test
    void testProtobuf() throws InvalidProtocolBufferException {
        // 通过 PersonEntity 的内部类 Builder 构建
        PersonProto.Person.Builder personBuilder = PersonProto.Person.newBuilder();
        personBuilder.setId(1);
        personBuilder.setName("张三");
        personBuilder.setEmail("wwh1874@163.com");

        // 序列化
        PersonProto.Person personEntity = personBuilder.build();
        System.out.println("================protobuf 数据================");
        System.out.println(personEntity.toString());
        System.out.println("================protobuf 数据================");
        System.out.println("protobuf 数据 bytes[]：" + Arrays.toString(personEntity.toByteArray()));
        System.out.println("protobuf 数据大小：" + personEntity.toByteString().size());

        // 将封装有数据的对象实例转换为字节数组用于数据传输、存储等
        byte[] bytes = personEntity.toByteArray();
        /*
            ======================================================
            1.获取到 byte 字节数组后，可以将该数据进行数据传输或存储
            2.通常进行 RPC 通信传输数据类型会用 protobuf
            3.模拟 byte 传输到另一个项目，然后进行反序列化
            ======================================================
         */

        // 将字节数组反序列化为对应的对象实例
        PersonProto.Person person = null;
        try {
            person = PersonProto.Person.parseFrom(bytes);
            // 获取到 person 实例后，就可以根据需求进行操作里面的数据
            System.out.println("学生ID：" + person.getId());
            System.out.println("姓名：" + person.getName());
            System.out.println("邮箱：" + person.getEmail());
        } catch (InvalidProtocolBufferException e) {
            e.printStackTrace();
        }

        // 进行 JSON 格式化
        String jsonObject = JsonFormat.printer().print(person);
        System.out.println("格式化结果：\n" + jsonObject);
        System.out.println("Json 格式化数据大小：" + jsonObject.getBytes().length);
    }

    @Test
    void testController() throws InvalidProtocolBufferException {
        PersonProto.Person person = restTemplate.getForObject("http://localhost:10000/protobuf/person", PersonProto.Person.class);
        String jsonString = JsonFormat.printer().print(person);
        System.out.println(jsonString);
    }
}
