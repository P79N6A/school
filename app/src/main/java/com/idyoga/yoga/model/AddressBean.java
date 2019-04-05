package com.idyoga.yoga.model;

/**
 * @author weilgu
 * @time 2019/2/26 17:26
 * @des ${TODO}
 */

public class AddressBean {

    private int id;
    private String name;
    private boolean isSelect;

    public AddressBean(int id,String name,boolean isSelect){
        this.id = id;
        this.name = name;
        this.isSelect = isSelect;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelect() {
        return isSelect;
    }

    public void setSelect(boolean select) {
        isSelect = select;
    }
}
