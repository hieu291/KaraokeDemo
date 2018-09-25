package karaoke.demo.com.baitaprenluyenkaraoke.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import karaoke.demo.com.baitaprenluyenkaraoke.R;
import karaoke.demo.com.baitaprenluyenkaraoke.model.Song;

public class SongAdapter extends ArrayAdapter<Song> {
    Activity context;
    int resource;
    public SongAdapter(@NonNull Activity context, int resource) {
        super(context, resource);
        this.context=context;
        this.resource=resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {
        ViewHolder viewHolder;
        if (view==null) {
            viewHolder = new ViewHolder();
            view = this.context.getLayoutInflater().inflate(this.resource,null);
            viewHolder.tvID=view.findViewById(R.id.tvID);
            viewHolder.tvSong=view.findViewById(R.id.tvNameSong);
            viewHolder.tvArtist=view.findViewById(R.id.tvArtist);
            viewHolder.imgLike=view.findViewById(R.id.imgLike);
            viewHolder.imgDislike=view.findViewById(R.id.imgDislike);
            view.setTag(viewHolder);
        }
        else {
            viewHolder = (ViewHolder) view.getTag();
        }
        Song song = getItem(position);
        viewHolder.tvID.setText(song.getId());
        viewHolder.tvSong.setText(song.getSong());
        viewHolder.tvArtist.setText(song.getArtist());
        if (song.getLike() == 1) {
            viewHolder.imgLike.setVisibility(view.INVISIBLE);
            viewHolder.imgDislike.setVisibility(view.VISIBLE);
        }
        else
        {
            viewHolder.imgLike.setVisibility(view.VISIBLE);
            viewHolder.imgDislike.setVisibility(view.INVISIBLE);
        }
        return view;
    }
    class ViewHolder{
        TextView tvID;
        TextView tvSong;
        TextView tvArtist;
        ImageView imgLike;
        ImageView imgDislike;
    }
}
