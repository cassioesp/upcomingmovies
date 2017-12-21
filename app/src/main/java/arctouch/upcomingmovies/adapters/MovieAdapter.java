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
import arctouch.upcomingmovies.utils.Utils;

/**
 * My custom adapter created for Movie ListView.
 *
 * @author Cassio Espindola
 */

public class MovieAdapter extends ArrayAdapter<Movie> {
    /**
     * List of movies.
     */
    private ArrayList<Movie> movieList;
    /**
     * View variable.
     */
    private LayoutInflater view;
    /**
     * Resource variable.
     */
    private int Resource;
    /**
     * View Holder variable.
     */
    private ViewHolder holder;
    /**
     * Context.
     */
    private Context context;

    public MovieAdapter(final Context context, final int resource, final ArrayList<Movie> objects) {
        super(context, resource, objects);
        view = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        Resource = resource;
        this.context = context;
        movieList = objects;
    }

    @Override
    public View getView(final int position, final View convertView, final ViewGroup parent) {
        View v = convertView;
        if (v == null) {
            holder = new ViewHolder();
            v = view.inflate(Resource, null);
            holder.imageview = v.findViewById(R.id.ivImage);
            holder.tvTitle = v.findViewById(R.id.tvTitle);
            holder.tvReleaseDate = v.findViewById(R.id.tvReleaseDate);
            holder.tvGenre = v.findViewById(R.id.tvHeight);
            v.setTag(holder);
        } else {
            holder = (ViewHolder) v.getTag();
        }

        // Here I download poster image from URL using Picasso library.
        // This library allows us to download images in background avoiding
        // blocking UI Thread and also implements caching mechanism avoiding
        // the request while the ListView is being scrolled.
        Picasso.with(context).load(Utils.POSTER_SMALL_PATH_URL + movieList.get(position).getPosterPath()).into(holder.imageview);
        holder.tvTitle.setText(movieList.get(position).getTitle());
        holder.tvReleaseDate.setText("Release Date: " + movieList.get(position).getReleaseDate());
        holder.tvGenre.setText("Genre: " + movieList.get(position).getGenre());
        return v;

    }

    /**
     * View Holder Pattern for our ListView.
     */
    static class ViewHolder {
        private ImageView imageview;
        private TextView tvTitle;
        private TextView tvReleaseDate;
        private TextView tvGenre;

    }
}