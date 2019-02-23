package com.hehen.heweater.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import java.io.Serializable;
/**
 * @author chenping
 * @date 2019/2/23 11:35 PM
 * @Description: 城市实体类
 */
@DatabaseTable(tableName = "tb_city")
public class City  implements Serializable {
    @DatabaseField(generatedId = true)
    private int _id;
    @DatabaseField
    private int id;
    @DatabaseField
    private int pid;
    @DatabaseField
    private String city_code;
    @DatabaseField
    private String city_name;

    public City(int _id, int id, int pid, String city_code, String city_name) {
        this._id = _id;
        this.id = id;
        this.pid = pid;
        this.city_code = city_code;
        this.city_name = city_name;
    }

    public City() {
    }

    public int get_id() {
        return _id;
    }
    public void set_id(int _id) {
        this._id = _id;
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getPid() {
        return pid;
    }
    public void setPid(int pid) {
        this.pid = pid;
    }
    public String getCity_code() {
        return city_code;
    }
    public void setCity_code(String city_code) {
        this.city_code = city_code;
    }
    public String getCity_name() {
        return city_name;
    }
    public void setCity_name(String city_name) {
        this.city_name = city_name;
    }

    @Override
    public String toString() {
        return "City{" +
                "_id=" + _id +
                ", id=" + id +
                ", pid=" + pid +
                ", city_code='" + city_code + '\'' +
                ", city_name='" + city_name + '\'' +
                '}';
    }
}
