package com.kursatkumsuz.search.data.mapper

import com.kursatkumsuz.search.data.model.SearchResult
import com.kursatkumsuz.search.domain.model.SearchUI

fun SearchResult.toSearchUI(): SearchUI {
    return SearchUI(
        id = id.toString(),
        title = title,
        image = posterPath,
        releaseDate = releaseDate,
        overview = overview
    )
}