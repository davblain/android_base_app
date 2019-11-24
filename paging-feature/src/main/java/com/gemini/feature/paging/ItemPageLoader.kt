package com.gemini.feature.paging

import io.reactivex.Observable

interface ItemPageLoader<T: Any> {
    fun getPage(page:Int): Observable<Page<T>>
}