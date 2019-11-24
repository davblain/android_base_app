package com.gemini.base.data.films

import com.gemini.base.ui.samples.films.Film
import com.gemini.feature.paging.ItemPageLoader
import com.gemini.feature.paging.Page
import io.reactivex.Observable
import javax.inject.Inject

class FilmLoader @Inject constructor(): ItemPageLoader<Film> {
    override fun getPage(page: Int): Observable<Page<Film>> =
        Observable.just(
            Page(
                items = listOf(Film("1", "2")),
                page = page + 1,
                hasNextPage = true
            )
        )

}