package com.cesgroup.auth.genertor.entity;

import com.cesgroup.core.entity.BaseEntity;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * 自动生成器实体类
 */
@Entity
@Table(name = "T_AUTH_GENERATOR")
public class GeneratorEntity implements BaseEntity<String>{

    /** id */
    @Id
    @Column(name = "id")
    @GeneratedValue(generator = "uuid")   //指定生成器名称
    @GenericGenerator(name = "uuid", strategy = "uuid")  //生成器名称，uuid生成类
    private String id;

    /** 名称 */
    @Column(name = "name")
    private String name;

    /** 前缀 */
    @Column(name = "prefix")
    private String prefix;

    /** 数字 */
    @Column(name = "sys_number")
    private Integer sysNumber;

    /** 后缀 */
    @Column(name = "suffix")
    private String suffix;


    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public Integer getSysNumber() {
        return sysNumber;
    }

    public void setSysNumber(Integer sysNumber) {
        this.sysNumber = sysNumber;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
