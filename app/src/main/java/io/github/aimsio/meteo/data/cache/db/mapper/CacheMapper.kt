package io.github.aimsio.meteo.data.cache.db.mapper


interface CacheMapper<CachedModel, DataModel> {

    fun mapFromCached(cached: CachedModel): DataModel

    fun mapToCached(entity: DataModel): CachedModel

}