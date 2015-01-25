package pl.edu.uam.rest.marsey.model;

import com.wordnik.swagger.annotations.ApiModel;
import com.wordnik.swagger.annotations.ApiModelProperty;

import java.util.Date;

@ApiModel(value = "Activity")
public class Activity {
    private String id;
    private String type;
    private String description;
    private Date date;
    
    public Activity() {}

    public Activity(String id, String type, String description, Date date) {
        this.id = id;
        this.type = type;
        this.description = description;
        this.date = date;
    }

    @ApiModelProperty(value = "Activity Id", required = true)
    public String getId() {
        return id;
    }

    @ApiModelProperty(value = "Activity name", required = true)
    public String getType() {
        return type;
    }

    @ApiModelProperty(value = "Activity description", required = false)
    public String getDescription() {
        return description;
    }

    @ApiModelProperty(value = "Date of activity", required = false)
    public Date getDate() {
        return date;
    }
}
