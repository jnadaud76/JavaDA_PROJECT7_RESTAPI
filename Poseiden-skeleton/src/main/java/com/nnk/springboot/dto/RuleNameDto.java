package com.nnk.springboot.dto;

import javax.validation.constraints.Size;

public class RuleNameDto {

    private Integer id;
    @Size(max = 125)
    private String name;
    @Size(max = 125)
    private String description;
    @Size(max = 125)
    private String json;
    @Size(max = 512)
    private String template;
    @Size(max = 125)
    private String sqlStr;
    @Size(max = 125)
    private String sqlPart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getTemplate() {
        return template;
    }

    public void setTemplate(String template) {
        this.template = template;
    }

    public String getSqlStr() {
        return sqlStr;
    }

    public void setSqlStr(String sqlStr) {
        this.sqlStr = sqlStr;
    }

    public String getSqlPart() {
        return sqlPart;
    }

    public void setSqlPart(String sqlPart) {
        this.sqlPart = sqlPart;
    }
}
