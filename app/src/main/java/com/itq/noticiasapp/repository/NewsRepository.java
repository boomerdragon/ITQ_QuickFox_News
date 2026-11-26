package com.itq.noticiasapp.repository;

import android.content.Context;
import androidx.lifecycle.MutableLiveData;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.itq.noticiasapp.dto.ArticleDto;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Repositorio orquestador encargado de consultar el API REST de noticias mediante Volley.
 */
public class NewsRepository {

    private static NewsRepository instance;
    private final RequestQueue requestQueue;

    // Endpoint de noticias estructuradas
    private static final String NEWS_API_URL = "https://api.rss2json.com/v1/api.json?rss_url=https://news.google.com/rss/headlines/section/topic/TECHNOLOGY";

    private NewsRepository(Context context) {
        this.requestQueue = Volley.newRequestQueue(context.getApplicationContext());
    }

    public static synchronized NewsRepository getInstance(Context context) {
        if (instance == null) {
            instance = new NewsRepository(context);
        }
        return instance;
    }

    /**
     * Realiza la solicitud HTTP GET asíncrona para obtener la lista de noticias.
     */
    public void obtenerNoticiasDestacadas(MutableLiveData<List<ArticleDto>> liveDataNoticias) {
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                NEWS_API_URL,
                null,
                response -> {
                    List<ArticleDto> listaArticulos = new ArrayList<>();
                    try {
                        if (response.has("articles")) {
                            JSONArray articlesArray = response.getJSONArray("articles");
                            for (int i = 0; i < articlesArray.length(); i++) {
                                JSONObject articleObj = articlesArray.getJSONObject(i);

                                String title = articleObj.optString("title", "Noticia sin título");
                                String description = articleObj.optString("description", "Sin resumen disponible.");
                                String url = articleObj.optString("url", "");
                                String urlToImage = articleObj.optString("urlToImage", "");
                                String publishedAt = articleObj.optString("publishedAt", "");

                                String sourceName = "Fuente Noticiosa";
                                if (articleObj.has("source") && !articleObj.isNull("source")) {
                                    JSONObject sourceObj = articleObj.getJSONObject("source");
                                    sourceName = sourceObj.optString("name", "Fuente Desconocida");
                                }

                                ArticleDto article = new ArticleDto(title, description, url, urlToImage, publishedAt, sourceName);
                                listaArticulos.add(article);
                            }
                        } else if (response.has("items")) {
                            JSONArray itemsArray = response.getJSONArray("items");
                            for (int i = 0; i < itemsArray.length(); i++) {
                                JSONObject itemObj = itemsArray.getJSONObject(i);

                                String rawTitle = itemObj.optString("title", "Noticia sin título");
                                String title = rawTitle;
                                String sourceName = "Google News";

                                int lastDashIndex = rawTitle.lastIndexOf(" - ");
                                if (lastDashIndex != -1 && lastDashIndex < rawTitle.length() - 3) {
                                    title = rawTitle.substring(0, lastDashIndex).trim();
                                    sourceName = rawTitle.substring(lastDashIndex + 3).trim();
                                }

                                String rawDescription = itemObj.optString("description", "");
                                String description = stripHtml(rawDescription);
                                if (description.isEmpty()) {
                                    description = "Sin resumen disponible.";
                                }

                                String url = itemObj.optString("link", "");
                                String publishedAt = itemObj.optString("pubDate", "");

                                String urlToImage = itemObj.optString("thumbnail", "");
                                if (urlToImage.isEmpty() && itemObj.has("enclosure") && !itemObj.isNull("enclosure")) {
                                    JSONObject enclosure = itemObj.getJSONObject("enclosure");
                                    urlToImage = enclosure.optString("link", "");
                                }

                                ArticleDto article = new ArticleDto(title, description, url, urlToImage, publishedAt, sourceName);
                                listaArticulos.add(article);
                            }
                        }
                        liveDataNoticias.setValue(listaArticulos);

                    } catch (JSONException e) {
                        e.printStackTrace();
                        liveDataNoticias.setValue(new ArrayList<>());
                    }
                },
                error -> {
                    error.printStackTrace();
                    liveDataNoticias.setValue(new ArrayList<>());
                }
        );

        requestQueue.add(jsonObjectRequest);
    }

    private String stripHtml(String html) {
        if (html == null) return "";
        return html.replaceAll("<[^>]*>", "").trim();
    }
}