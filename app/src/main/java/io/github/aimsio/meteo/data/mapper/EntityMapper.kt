package io.github.aimsio.meteo.data.mapper

interface EntityMapper<DataModel, DomainModel> {

    fun mapFromEntity(entity: DataModel): DomainModel

    fun mapToEntity(domain: DomainModel): DataModel

}