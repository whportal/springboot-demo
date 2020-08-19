package com.wh.es.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/18
 */
@Data
@Document(indexName = "index_user")
public class User implements Serializable {

    private static final long serialVersionUID = -2013034623957459896L;

    @Id
    @Field(type = FieldType.Long)
    private Long id;

    @Field(type = FieldType.Text,analyzer = "ik_max_word")
    private String username;

    @Field(type = FieldType.Keyword)
    private String password;

    @Field(type = FieldType.Integer)
    private Integer age;

    @Field(type = FieldType.Date)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern ="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date birthday;

}
