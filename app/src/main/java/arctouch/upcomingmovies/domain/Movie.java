package arctouch.upcomingmovies.domain;

import java.io.Serializable;

/**
 * Movie Class.
 * This class represents a movie with all its attributes.
 *
 * @author Cassio Espindola
 */
public class Movie implements Serializable {

    private String title;

    private String genre;

    private String posterPath;

    private String releaseDate;

    private String preview;

    public String getTitle() {
        return title;
    }

    public void setTitle(final String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(final String[] genres) {
        String[] genresNames = new String[]{"Action ", "Adventure ", "Animation ", "Comedy ",
                "Crime ", "Documentary ", "Drama ", "Family ", "Fantasy ", "History ", "Horror ",
                "Music ", "Mistery ", "Romance ", "Science Fiction ", "TV Movie ", "Thriller ",
                "War ", "Western "};

        this.genre = "";
        for (String genre : genres) {
            if (genre.equals("28")) {
                this.genre += genresNames[0];
            } else if (genre.equals("12")) {
                this.genre += genresNames[1];
            } else if (genre.equals("16")) {
                this.genre += genresNames[2];
            } else if (genre.equals("35")) {
                this.genre += genresNames[3];
            } else if (genre.equals("80")) {
                this.genre += genresNames[4];
            } else if (genre.equals("99")) {
                this.genre += genresNames[5];
            } else if (genre.equals("18")) {
                this.genre += genresNames[6];
            } else if (genre.equals("10751")) {
                this.genre += genresNames[7];
            } else if (genre.equals("14")) {
                this.genre += genresNames[8];
            } else if (genre.equals("36")) {
                this.genre += genresNames[9];
            } else if (genre.equals("27")) {
                this.genre += genresNames[10];
            } else if (genre.equals("10402")) {
                this.genre += genresNames[11];
            } else if (genre.equals("9648")) {
                this.genre += genresNames[12];
            } else if (genre.equals("10749")) {
                this.genre += genresNames[13];
            } else if (genre.equals("878")) {
                this.genre += genresNames[14];
            } else if (genre.equals("53")) {
                this.genre += genresNames[15];
            } else if (genre.equals("10752")) {
                this.genre += genresNames[16];
            } else if (genre.equals("37")) {
                this.genre += genresNames[17];
            }
        }
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(final String posterPath) {
        this.posterPath = posterPath;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(final String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setPreview(String preview) {
        this.preview = preview;
    }

    public String getPreview() {
        return preview;
    }
}
