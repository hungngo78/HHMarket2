package come.hhmarket.mobile.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SpeciesList {
    @SerializedName("results")
    @Expose
    private List<Species> speciesList;

    public  SpeciesList() {

    }

    public void setSpeciesList (List<Species> _speciesList) {
        this.speciesList = _speciesList;
    }

    public List<Species> getSpeciesList () {
        return this.speciesList;
    }
}
