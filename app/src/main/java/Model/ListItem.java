package Model;

public class ListItem {
    private String name;
    private String imageUrl;

    public ListItem(String name, String imageUrl)
    {
        this.name = name;
        this.imageUrl = imageUrl;
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() { return imageUrl; }
}
