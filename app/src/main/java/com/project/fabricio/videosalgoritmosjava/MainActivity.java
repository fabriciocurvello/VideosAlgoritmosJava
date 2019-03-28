package com.project.fabricio.videosalgoritmosjava;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class MainActivity extends YouTubeBaseActivity implements YouTubePlayer.OnInitializedListener {

    private YouTubePlayerView youTubePlayerView;

    private static final String GOOGLE_API_KEY = "AIzaSyDS4cJoieUrK9yLUo39hV9EAMSkwR1fvQw";

    private YouTubePlayer.PlaybackEventListener playbackEventListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        playbackEventListener = new YouTubePlayer.PlaybackEventListener() {
            @Override
            public void onPlaying() {
                Toast.makeText( MainActivity.this, "Vídeo executando", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onPaused() {
                Toast.makeText( MainActivity.this, "Vídeo pausado", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onStopped() {
                Toast.makeText( MainActivity.this, "Vídeo parado (stop)", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onBuffering(boolean b) {
                Toast.makeText( MainActivity.this, "Carregando o vídeo", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSeekTo(int i) {
                Toast.makeText( MainActivity.this, "Movimentando o vídeo até o ponto " + i, Toast.LENGTH_SHORT).show();
            }
        };

        youTubePlayerView = findViewById(R.id.viewYouTubePlayer);
        youTubePlayerView.initialize(GOOGLE_API_KEY, this);
    }

    //Define o que fazer quando o player for iniciado corretamente
    /*
        provider - é o provedor usado para iniciar o YouTubePlayer
        youTubePlayer - é obj de YouTubePlayer que pode ser usado para controlar a reprodução do vídeo no provedor
        foiRestaurado - true para continuar de onde o vídeo parou anteriormente, false para recarregar o vídeo do início
     */
    @Override
    public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean foiRestaurado) {
        Toast.makeText(this, "Player inciado com sucesso!", Toast.LENGTH_SHORT).show();

        //Associando o youTubePlayer ao Listener que captura as ações do usuário ao executar o player
        //Todas as ações deste listener estão no onCreate()
        youTubePlayer.setPlaybackEventListener( playbackEventListener );

        //TODO Observar que existe a opção loadPlaylist

        //Código do vídeo que aparece na URL youtube após v=
        //https://www.youtube.com/watch?v=xVq5QcrJNA4
        //youTubePlayer.loadVideo("xVq5QcrJNA4");
        //youTubePlayer.cuePlaylist("PLIn2kYkmuf42swbLyKLmtCE5SPBJv7Yw4");

        if ( !foiRestaurado ) { //este if garante que o vídeo não seja reiniciado caso a tela seja rotacionada
            youTubePlayer.loadVideo("xVq5QcrJNA4");
        }

        Log.i("estado_player", "estado: " + foiRestaurado);
    }

    //Define o que fazer quando o player NAO for iniciado corretamente
    @Override
    public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) {
        Toast.makeText(this, "Erro ao iniciar o player: " + youTubeInitializationResult.toString(), Toast.LENGTH_SHORT).show();
    }
}