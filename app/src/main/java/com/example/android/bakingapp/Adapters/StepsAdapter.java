package com.example.android.bakingapp.Adapters;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.android.bakingapp.R;
import com.example.android.bakingapp.model.Steps;
import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.extractor.DefaultExtractorsFactory;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;
import com.squareup.picasso.Picasso;

import java.net.MalformedURLException;
import java.util.List;

/**
 * Created by dell on 6/10/2017.
 */

public class StepsAdapter extends RecyclerView.Adapter<StepsAdapter.StepsViewHolder> {


    private Context mContext;
    private List<Steps> mSteps;


    public StepsAdapter(List<Steps> steps, Context context) {
        mSteps = steps;
        mContext = context;
    }

    @Override
    public int getItemCount() {

        if (mSteps != null)
            return mSteps.size();
        else
            return 0;
    }

    @Override
    public StepsViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.recipe_step, viewGroup, false);
        StepsViewHolder pvh = null;
        try {
            pvh = new StepsViewHolder(v);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return pvh;
    }


    @Override
    public void onBindViewHolder(final StepsViewHolder holder, int position) {
        if (mSteps != null) {
            holder.stepName.setText(mSteps.get(position).getShortDescription());
            holder.stepDes.setText(mSteps.get(position).getDescription());
            String imageURL = mSteps.get(position).getThumbnailURL();
            if (!TextUtils.isEmpty(imageURL))
                Picasso.with(mContext).load(imageURL).into(holder.thumbStep);
            else holder.thumbStep.setVisibility(View.GONE);
            final Uri mp4VideoUri = Uri.parse(mSteps.get(position).getVideoURL());
            holder.simpleExoPlayerView.requestFocus();
            holder.simpleExoPlayerView.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
                SimpleExoPlayer player;

                @Override
                public void onViewAttachedToWindow(View v) {
                    if (!TextUtils.isEmpty(mp4VideoUri.toString())) {
                        TrackSelector trackSelector = new DefaultTrackSelector();
                        player = ExoPlayerFactory.newSimpleInstance(mContext, trackSelector);
                        String userAgent = Util.getUserAgent(mContext, mContext.getString(R.string.app_name));
                        MediaSource mediaSource = new ExtractorMediaSource(mp4VideoUri, new DefaultDataSourceFactory(mContext, userAgent), new DefaultExtractorsFactory(), null, null);
                        player.prepare(mediaSource);
                        player.setPlayWhenReady(false);
                        holder.simpleExoPlayerView.setPlayer(player);
                    } else {
                        holder.simpleExoPlayerView.setVisibility(View.GONE);
                    }

                }

                @Override
                public void onViewDetachedFromWindow(View v) {
                    if (player != null) {
                        player.stop();
                        player.release();
                    }
                }
            });
        }
    }


    public class StepsViewHolder extends RecyclerView.ViewHolder {

        private TextView stepName;
        private TextView stepDes;
        private SimpleExoPlayerView simpleExoPlayerView;
        private ImageView thumbStep;

        public StepsViewHolder(View itemView) throws MalformedURLException {
            super(itemView);
            simpleExoPlayerView = (SimpleExoPlayerView) itemView.findViewById(R.id.player_view);
            stepName = (TextView) itemView.findViewById(R.id.tv_step_name);
            stepDes = (TextView) itemView.findViewById(R.id.tv_step_description);
            thumbStep = (ImageView) itemView.findViewById(R.id.iv_step_image);
        }


    }
}