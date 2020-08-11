package com.wh.es.entity;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.DateFormat;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.io.Serializable;
import java.util.Date;


/**
 * @author Wenhao Wang
 * @version 1.0.0
 * @date 2020/08/10
 */
@Data
@Document(indexName = "test-20200807")
// @JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
public class OrderInfo implements Serializable {

    private static final long serialVersionUID = 2606990193821073203L;

    @Id
    @Field(name = "order_no", type = FieldType.Keyword)
    private String orderNo;

    @Field(name = "type", type = FieldType.Integer)
    private Integer type;

    @Field(name = "source", type = FieldType.Keyword)
    private String source;

    @Field(name = "fk_cust_info", type = FieldType.Keyword)
    private String fkCustInfo;

    @Field(name = "fk_cust_info_third", type = FieldType.Keyword)
    private String fkCustInfoThird;

    @Field(name = "fk_cust_bank_card", type = FieldType.Long)
    private Long fkCustBankCard;

    @Field(name = "model_id", type = FieldType.Long)
    private Long modelId;

    @Field(name = "fk_body_color", type = FieldType.Long)
    private Long fkBodyColor;

    @Field(name = "body_color", type = FieldType.Keyword)
    private String bodyColor;

    @Field(name = "fk_inner_color", type = FieldType.Long)
    private Long fkInnerColor;

    @Field(name = "inner_color", type = FieldType.Keyword)
    private String innerColor;

    @Field(name = "area_code", type = FieldType.Keyword)
    private String areaCode;

    @Field(name = "agent_id", type = FieldType.Keyword)
    private String agentId;

    @Field(name = "sub_agent_id", type = FieldType.Keyword)
    private String subAgentId;

    @Field(name = "saleman", type = FieldType.Text, analyzer = "ik_max_word")
    private String saleman;

    @Field(name = "saleman_tel", type = FieldType.Text, analyzer = "ik_max_word")
    private String salemanTel;

    @Field(name = "saleman_area", type = FieldType.Text, analyzer = "ik_max_word")
    private String salemanArea;

    @Field(name = "vender_price", type = FieldType.Double)
    private Double venderPrice;

    @Field(name = "period", type = FieldType.Integer)
    private Integer period;

    @Field(name = "contract", type = FieldType.Integer)
    private Integer contract;

    @Field(name = "loan_rate", type = FieldType.Keyword)
    private String loanRate;

    @Field(name = "state", type = FieldType.Integer)
    private Integer state;

    @Field(name = "remark", type = FieldType.Text, analyzer = "ik_max_word")
    private String remark;

    @Field(name = "creator_id", type = FieldType.Keyword)
    private String creatorId;

    @Field(name = "creator", type = FieldType.Text, analyzer = "ik_max_word")
    private String creator;

    @Field(name = "gmt_create", format = DateFormat.date_optional_time, type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCreate;

    @Field(name = "modifier_id", type = FieldType.Keyword)
    private String modifierId;

    @Field(name = "modifier", type = FieldType.Text, analyzer = "ik_max_word")
    private String modifier;

    @Field(name = "gmt_modify", format = DateFormat.date_optional_time, type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtModify;

    @Field(name = "gmt_commit", format = DateFormat.date_optional_time, type = FieldType.Date)
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date gmtCommit;

    @Field(name = "is_supply", type = FieldType.Keyword)
    private String isSupply;

    @Field(name = "sort_no", type = FieldType.Long)
    private Long sortNo;

    @Field(name = "finance_order_state", type = FieldType.Integer)
    private Integer financeOrderState;
}
