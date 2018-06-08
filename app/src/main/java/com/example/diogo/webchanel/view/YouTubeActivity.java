package com.example.diogo.webchanel.view;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import java.util.Vector;

import com.example.diogo.webchanel.R;
import com.example.diogo.webchanel.model.YouTubeVideo;
import com.example.diogo.webchanel.view.adapters.VideoAdapter;

public class YouTubeActivity extends AppCompatActivity {
    RecyclerView recyclerView;
    Vector<YouTubeVideo> youtubeVideos = new Vector<YouTubeVideo>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager( new LinearLayoutManager(this));

        youtubeVideos.add( new YouTubeVideo("<iframe width=\"100%\" height=\"100%\" allowtransparency=\"true\" style=\"background-color:#000000;\" src=\"https://www.youtube.com/embed/O7stFfPfEts\" frameborder=\"0\" allowfullscreen=\"true\"></iframe>") );
        VideoAdapter videoAdapter = new VideoAdapter(youtubeVideos);
        recyclerView.setAdapter(videoAdapter);
    }
}