package com.hehen.heweater.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * @author chenping
 * @date 2019/2/24 7:58 PM
 * @Description:
 */
@DatabaseTable(tableName = "tb_yesterday")
public class Yesterday {
    private int id;
    @DatabaseField(columnName = "weather_id",foreign = true,foreignAutoCreate = true)
    private Weather weather;

}
