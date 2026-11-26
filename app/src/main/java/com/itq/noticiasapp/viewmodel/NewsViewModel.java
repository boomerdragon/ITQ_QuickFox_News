package com.itq.noticiasapp.viewmodel;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.itq.noticiasapp.dto.ArticleDto;
import com.itq.noticiasapp.repository.NewsRepository;

import java.util.List;

/**
 * ViewModel que preserva el estado de las noticias ante giros de pantalla.
 */
public class NewsViewModel extends AndroidViewModel {

    private final NewsRepository repository;
    private final MutableLiveData<List<ArticleDto>> noticiasLiveData = new MutableLiveData<>();
    private boolean cargadoInicial = false;

    public NewsViewModel(@NonNull Application application) {
        super(application);
        this.repository = NewsRepository.getInstance(application);
    }

    public LiveData<List<ArticleDto>> getNoticias() {
        if (!cargadoInicial) {
            cargadoInicial = true;
            recargarNoticias();
        }
        return noticiasLiveData;
    }

    public void recargarNoticias() {
        repository.obtenerNoticiasDestacadas(noticiasLiveData);
    }
}