package arctouch.upcomingmovies.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import arctouch.upcomingmovies.R;
import arctouch.upcomingmovies.domain.Movie;

/**
 * Fragment to show details of the movie selected.
 *
 * @author Cassio Espindola
 */
public class MovieFragment extends Fragment {

    /**
     * URL Path of the cover movie.
     */
    private final String POSTER_LARGE_PATH_URL = "http://image.tmdb.org/t/p/w185/";

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_movie, container, false);
        // Getting the selected movie by bundle.
        Bundle bundle = getArguments();
        Movie movie = (Movie) bundle.getSerializable("movie");

        // Instantiate UI elements.
        ImageView movieCover = rootView.findViewById(R.id.movieCover);
        TextView movieTitle = rootView.findViewById(R.id.movieTitle);
        TextView movieReleaseDate = rootView.findViewById(R.id.movieReleaseDate);
        TextView moviePreview = rootView.findViewById(R.id.moviePreview);

        //Setting UI elements with movie attributes.
        Picasso.with(getActivity()).load(POSTER_LARGE_PATH_URL + movie.getPosterPath()).into(movieCover);
        movieTitle.setText(movie.getTitle());
        movieReleaseDate.setText(movie.getReleaseDate());
        moviePreview.setText(movie.getPreview());

        return rootView;
    }

}
