package io.github.aimsio.meteo.data.entity.model

import io.github.aimsio.meteo.data.model.weather.*

data class FutureEntity(
    val id: Long,
    val cnt: Int,
    val cod: String,
    val list: List<Info>,
    val message: Int
)