package com.wh.controller;

import com.wh.dto.proto.PersonProto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/12
 */
@RestController
@RequestMapping("protobuf")
public class ProtobufController {

    // @GetMapping(value = "person", produces = "application/x-protobuf")
    @GetMapping(value = "person")
    public PersonProto.Person getPerson() {
        return PersonProto.Person.newBuilder()
                .setId(666)
                .setName("风清扬")
                .setEmail("wwh1874@163.com")
                .build();
    }
}
