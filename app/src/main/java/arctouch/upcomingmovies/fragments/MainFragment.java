package arctouch.upcomingmovies.fragments;

import android.app.FragmentTransaction;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

import arctouch.upcomingmovies.R;
import arctouch.upcomingmovies.adapters.MovieAdapter;
import arctouch.upcomingmovies.domain.Movie;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Main Fragment Class.
 *
 * @author Cassio Espindola
 */
public class MainFragment extends Fragment {

    private ArrayList<Movie> movies;

    private ProgressDialog pDialog;

    private MovieAdapter adapter;

    private int page = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        //Initializing the main list.
        ListView listOfMovies = rootView.findViewById(R.id.movies_list_view);

        // Creating the list view footer button "Load More".
        Button btnLoadMore = new Button(getActivity());
        btnLoadMore.setText(getString(R.string.load_more));
        listOfMovies.addFooterView(btnLoadMore);
        btnLoadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //When "Load More" button clicked, request the next page of results.
                page++;
                new GetRequest().execute();
            }
        });

        //List of Movies used by adapter.
        movies = new ArrayList<Movie>();
        adapter = new MovieAdapter(getActivity(), R.layout.row, movies);
        listOfMovies.setAdapter(adapter);
        listOfMovies.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                android.support.v4.app.FragmentTransaction ft = getActivity().getSupportFragmentManager().beginTransaction();
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
                MovieFragment movieFragment = new MovieFragment();

                Bundle bundle = new Bundle();
                Movie movie = adapter.getItem(i);
                bundle.putSerializable("movie", movie);
                movieFragment.setArguments(bundle);
                ft.replace(android.R.id.content, movieFragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });

        // Calls ASyncTask to make a GET request to the server.
        new GetRequest().execute();

        // Inflate the layout for this fragment.
        return rootView;
    }

    /**
     * This private class is responsible to make a request to the server.
     * It uses OkHttp3 library
     */
    private class GetRequest extends AsyncTask<Void, Void, Void> {

        /* TheMovieDB API KEY.*/
        private final String API_KEY = "1f54bd990f1cdfb230adb312546d765d";
        /* TMDB API KEY.*/
        private final String URL = "https://api.themoviedb.org/3/movie/upcoming?api_key=" +
                API_KEY + "&language=" + getString(R.string.english_us) + "&page=";

        private final OkHttpClient client;

        private GetRequest() {
            this.client = new OkHttpClient();
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // Creates a progress dialog asking the user to wait.
            pDialog = new ProgressDialog(getActivity());
            pDialog.setMessage(getString(R.string.please_wait));
            pDialog.setCancelable(false);
            pDialog.show();
        }

        @Override
        protected Void doInBackground(Void... params) {
            try {
                Request request = new Request.Builder().url(URL + page).build();
                //Response from the server.
                Response response = client.newCall(request).execute();
                //Transform the response from the server in a JSON format.
                JSONObject jsonObj = new JSONObject(response.body().string());
                // Gets a JSONArray from "results" attribute.
                JSONArray upcomingMovies = jsonObj.getJSONArray(getString(R.string.results));
                //Add in our list all the results fetched.
                for (int i = 0; i < upcomingMovies.length(); i++) {
                    JSONObject movieJson = upcomingMovies.getJSONObject(i);

                    Movie movie = new Movie();
                    movie.setTitle(movieJson.getString(getString(R.string.title)));
                    movie.setPosterPath(movieJson.getString(getString(R.string
                            .poster_path)));
                    movie.setReleaseDate(movieJson.getString(getString(R.string.release_date)));
                    movie.setGenre(movieJson.getString(getString(R.string.genre))
                            .replace("[", "").replace("]", "")
                            .split(","));
                    movie.setPreview(movieJson.getString(getString(R.string.overview)));

                    movies.add(movie);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            if (pDialog.isShowing())
                pDialog.dismiss();
            // refresh our list view with new results.
            adapter.notifyDataSetChanged();
            super.onPostExecute(aVoid);
        }
    }
}
