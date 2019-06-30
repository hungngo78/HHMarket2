package come.hhmarket.mobile.model;

public class Category {
    private int categoryId;
    private String name;
    private String picture;
    private String description;

    public Category() {

    }

    // gia lap khi chua lay hinh tu Web
    private String imageUrl;

    public String getImageUrl() {
        //return imageUrl;
        return "https://upload.wikimedia.org/wikipedia/commons/5/55/Apple_orchard_in_Tasmania.jpg";
    }
    public void setImageUrl(final String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(int id) {
        this.categoryId = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(final String picture) {
        this.picture = picture;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

}
