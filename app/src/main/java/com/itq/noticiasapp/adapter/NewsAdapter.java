package com.itq.noticiasapp.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.itq.noticiasapp.R;
import com.itq.noticiasapp.dto.ArticleDto;

import java.util.ArrayList;
import java.util.List;

/**
 * Adaptador del RecyclerView para renderizar las tarjetas de noticias.
 */
public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.NewsViewHolder> {

    private List<ArticleDto> listaArticulos = new ArrayList<>();
    private final OnItemClickListener clickListener;

    public interface OnItemClickListener {
        void onItemClick(ArticleDto articulo);
    }

    public NewsAdapter(OnItemClickListener clickListener) {
        this.clickListener = clickListener;
    }

    public void actualizarNoticias(List<ArticleDto> nuevasNoticias) {
        this.listaArticulos = nuevasNoticias;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public NewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_noticia, parent, false);
        return new NewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsViewHolder holder, int position) {
        ArticleDto articulo = listaArticulos.get(position);
        holder.bind(articulo, clickListener);
    }

    @Override
    public int getItemCount() {
        return listaArticulos.size();
    }

    static class NewsViewHolder extends RecyclerView.ViewHolder {

        private final TextView tvFuente;
        private final TextView tvTitulo;
        private final TextView tvDescripcion;
        private final TextView tvFecha;

        public NewsViewHolder(@NonNull View itemView) {
            super(itemView);
            tvFuente = itemView.findViewById(R.id.tvFuenteNoticia);
            tvTitulo = itemView.findViewById(R.id.tvTituloNoticia);
            tvDescripcion = itemView.findViewById(R.id.tvDescripcionNoticia);
            tvFecha = itemView.findViewById(R.id.tvFechaNoticia);
        }

        public void bind(final ArticleDto articulo, final OnItemClickListener listener) {
            tvFuente.setText(articulo.getSourceName());
            tvTitulo.setText(articulo.getTitle());
            tvDescripcion.setText(articulo.getDescription());
            tvFecha.setText(articulo.getPublishedAt());

            itemView.setOnClickListener(v -> listener.onItemClick(articulo));
        }
    }
}