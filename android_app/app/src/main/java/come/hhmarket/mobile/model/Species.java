package come.hhmarket.mobile.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class Species implements Serializable {
    private String name;
    private String classification;
    private String designation;

    public Species() {

    }

    public String getName() {
        return this.name;
    }
    public void setName(String _name) {
        this.name = _name;
    }

    public String getClassification() {
        return this.classification;
    }
    public void setClassification(String _classification) {
        this.classification = _classification;
    }

    public String getDesignation() {
        return this.designation;
    }
    public void setDesignation(String _designation) {
        this.designation = _designation;
    }
}
