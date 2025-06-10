package ru.donnu.practice.model.country

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.transactions.transaction
import ru.donnu.practice.model.region.RegionTable

object CountryTable: IntIdTable("country") {

    val name = text("name")
    val regionId = reference("region_id", RegionTable, ReferenceOption.SET_NULL).nullable()
    val image = text("image")

    fun insertCountry(country: CountryEntity) = transaction {
        val regionId = RegionTable.selectIdWithName(country.region.name)
        insert {
            it[CountryTable.name] = country.name
            it[CountryTable.regionId] = regionId
            it[CountryTable.image] = country.image
        }
    }

}