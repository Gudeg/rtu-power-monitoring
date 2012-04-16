package my.utm.cairo.prototype.client.mvc.model;

import java.io.Serializable;

import com.extjs.gxt.ui.client.data.BaseModelData;
import com.extjs.gxt.ui.client.data.ModelType;

@SuppressWarnings("serial")
public class PowerData extends BaseModelData implements Serializable {
    // fields: logid, average_consumption, uptime, date 
    
    private static final ModelType type = new ModelType();

    public PowerData() {}

    public PowerData(int logid, double average_consumption, 
        double uptime, String date) {

        set("logid", logid);
        set("average_consumption", average_consumption);
        set("uptime", uptime);
        set("date", date);
    }

    public int getLogId() {
        return (Integer) get("logid");
    }

    public double getAverageConsumption() {
        return (Double) get("average_consumption");
    }

    public double getUptime() {
        return (Double) get("uptime");
    }

    public String getDate() {
        return (String) get("date");
    }

    public static ModelType getModelType() {

        // TODO: Shorten the json key
        // JSON Format: 
        // {"totalCount": "123", 
        //  "Power Data": [
        //      {"logid": "12", "average_consumption":"123.55", 
        //       "uptime": "3.0", "date":"1999-12-31"}

        type.setRoot("Power Data");
        type.setTotalName("totalCount");
        type.addField("logid", "logid");
        type.addField("average_consumption", "average_consumption");
        type.addField("uptime", "uptime");
        type.addField("date", "date");

        return type;

    }
}
