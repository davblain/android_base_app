package com.gemini.base.ui.samples.films.adapters

import android.annotation.SuppressLint
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import com.gemini.base.R
import com.gemini.base.ui.samples.films.Film
import com.gemini.base.ui.samples.films.FilmItem
import com.gemini.base.ui.samples.films.RealFilm
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import com.hannesdorfmann.adapterdelegates4.AsyncListDifferDelegationAdapter
import com.hannesdorfmann.adapterdelegates4.dsl.adapterDelegate

object FilmsAdapters {

    fun filmAdapterDelegate() = adapterDelegate<RealFilm, FilmItem>(
        layout = R.layout.item_film,
        on = { filmItem: FilmItem, list: List<FilmItem>, i: Int ->
            filmItem is RealFilm
        }
    ) {
        val name: TextView = findViewById(R.id.filmNameTextView)
        bind {
            name.text = item.film.name
        }
    }

    fun createAdapter() = AsyncListDifferDelegationAdapter(
        DiffFilmsItem,
        AdapterDelegatesManager<List<FilmItem>>()
            .addDelegate(filmAdapterDelegate())
    )

    private object DiffFilmsItem : DiffUtil.ItemCallback<FilmItem>() {
        override fun areItemsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FilmItem, newItem: FilmItem): Boolean =
            oldItem == newItem
    }
}