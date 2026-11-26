package com.itq.noticiasapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.splashscreen.SplashScreen;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.itq.noticiasapp.adapter.NewsAdapter;
import com.itq.noticiasapp.viewmodel.NewsViewModel;

/**
 * Actividad Principal que muestra la lista de noticias globales.
 */
public class MainActivity extends AppCompatActivity {

    private NewsViewModel newsViewModel;
    private NewsAdapter newsAdapter;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SplashScreen splashScreen = SplashScreen.installSplashScreen(this);
        long startTime = System.currentTimeMillis();
        long minSplashDurationMs = 1500; // Mantiene el Splash Screen al menos 1.5 segundos
        long maxSplashTimeoutMs = 3000;  // Tiempo límite de seguridad

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewNoticias);
        swipeRefreshLayout = findViewById(R.id.swipeRefreshLayout);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        newsAdapter = new NewsAdapter(articulo -> {
            if (articulo.getUrl() != null && !articulo.getUrl().isEmpty()) {
                Intent intent = new Intent(MainActivity.this, DetalleNoticiaActivity.class);
                intent.putExtra("ARTICLE_URL", articulo.getUrl());
                startActivity(intent);
            } else {
                Toast.makeText(MainActivity.this, R.string.error_invalid_article_url, Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.setAdapter(newsAdapter);

        newsViewModel = new ViewModelProvider(this).get(NewsViewModel.class);

        // Mantiene visible el Splash Screen durante la duración deseada o mientras cargan las noticias
        splashScreen.setKeepOnScreenCondition(() -> {
            long elapsedTime = System.currentTimeMillis() - startTime;
            if (elapsedTime >= maxSplashTimeoutMs) {
                return false;
            }
            boolean isMinTimePassed = elapsedTime >= minSplashDurationMs;
            boolean isDataLoaded = newsViewModel.getNoticias().getValue() != null;
            return !(isMinTimePassed && isDataLoaded);
        });

        swipeRefreshLayout.setRefreshing(true);
        newsViewModel.getNoticias().observe(this, listaArticulos -> {
            swipeRefreshLayout.setRefreshing(false);
            if (listaArticulos != null && !listaArticulos.isEmpty()) {
                newsAdapter.actualizarNoticias(listaArticulos);
            } else {
                Toast.makeText(MainActivity.this, R.string.error_load_news_network, Toast.LENGTH_LONG).show();
            }
        });

        swipeRefreshLayout.setOnRefreshListener(() -> {
            swipeRefreshLayout.setRefreshing(true);
            newsViewModel.recargarNoticias();
        });
    }
}