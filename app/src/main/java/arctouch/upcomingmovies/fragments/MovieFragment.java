package arctouch.upcomingmovies.fragments;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import arctouch.upcomingmovies.R;
import arctouch.upcomingmovies.domain.Movie;
import arctouch.upcomingmovies.utils.Utils;

/**
 * Fragment to show details of the movie selected.
 *
 * @author Cassio Espindola
 */
public class MovieFragment extends Fragment {

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container,
                             final Bundle savedInstanceState) {

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
        Picasso.with(getActivity()).load(Utils.POSTER_LARGE_PATH_URL + movie.getPosterPath())
                .into(movieCover);
        movieTitle.setText(movie.getTitle());
        movieReleaseDate.setText(getString(R.string.release_date_label) + movie.getReleaseDate());
        moviePreview.setText(movie.getPreview());

        return rootView;
    }
}
