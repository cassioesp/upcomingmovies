package arctouch.upcomingmovies.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import arctouch.upcomingmovies.R;
import arctouch.upcomingmovies.domain.Movie;

/**
 * My custom adapter created for Movie ListView.
 *
 * @author Cassio Espindola
 */

public class MovieAdapter extends ArrayAdapter<Movie> {

    ArrayList<Movie> movieList;
    LayoutInflater view;
    int Resource;
    ViewHolder holder;
    Context context;

    private final String POSTER_PATH_URL = "http://image.tmdb.org/t/p/w92/";

    public MovieAdapter(Context context, int resource, ArrayList<Movie> objects) {
        super(context, resource, objects);
        view = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        this.context = context;
        movieList = objects;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // convert view = design
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = view.inflate(Resource, null);
            holder.imageview = (ImageView) v.findViewById(R.id.ivImage);
            holder.tvTitle = (TextView) v.findViewById(R.id.tvTitle);
            holder.tvReleaseDate = (TextView) v.findViewById(R.id.tvReleaseDate);
            holder.tvGenre = (TextView) v.findViewById(R.id.tvHeight);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        // Here I download poster image from URL using Picasso library.
        // This library allows us to download images in background avoiding
        // blocking UI Thread and also implements caching mechanism avoiding
        // the request while the ListView is being scrolled.
        Picasso.with(context).load(POSTER_PATH_URL + movieList.get(position).getPosterPath()).into(holder.imageview);
        holder.tvTitle.setText(movieList.get(position).getTitle());
        holder.tvReleaseDate.setText("Release Date: " + movieList.get(position).getReleaseDate());
        holder.tvGenre.setText("Genre: " + movieList.get(position).getGenre());
        return v;

    }

    static class ViewHolder {
        public ImageView imageview;
        public TextView tvTitle;
        public TextView tvReleaseDate;
        public TextView tvGenre;

    }
}