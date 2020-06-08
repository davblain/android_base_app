package com.gemini.base.ui.samples.films

sealed class FilmItem(val id: String)
data class RealFilm(val film: Film) : FilmItem(film.id)
object LoadingItem : FilmItem(id = "-1")