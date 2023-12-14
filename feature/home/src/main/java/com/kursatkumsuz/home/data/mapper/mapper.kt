package com.kursatkumsuz.home.data.mapper

import com.kursatkumsuz.home.data.model.Result
import com.kursatkumsuz.home.domain.model.ResultHomeUI

fun Result.toResultHomeUI(): ResultHomeUI {
    return ResultHomeUI(
        id = id,
        posterPath = posterPath,
        title = title
    )
}