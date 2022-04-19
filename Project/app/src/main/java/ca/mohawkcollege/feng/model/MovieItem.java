package ca.mohawkcollege.feng.model;

/**
 * represents movie
 */
public class MovieItem {
    private String Title;
    private String Year;
    private String imdbID;
    private String Type;
    private String Poster;


    /**
     * movie constructor
     * @param title tile
     * @param year  year
     * @param imdbID  id
     * @param type  type
     * @param poster  poster
     */
    public MovieItem(String title, String year, String imdbID, String type, String poster) {
        Title = title;
        Year = year;
        this.imdbID = imdbID;
        Type = type;
        Poster = poster;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }
}