package com.deepanshu.model;

/*
 * @author Deepanshu Bhatti
 */
public class ReferenceData {
    private Integer id;
    private String name;
    private String abbrev;

    public ReferenceData(Integer id, String name, String abbrev) {
        this.id = id;
        this.name = name;
        this.abbrev = abbrev;
    }

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

    public String getAbbrev() {
        return abbrev;
    }

    public void setAbbrev(String abbrev) {
        this.abbrev = abbrev;
    }
}
