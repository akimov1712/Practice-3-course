package ru.donnu.practice.tables.country

import entity.CountryEntity
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ReferenceOption
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction
import ru.donnu.practice.tables.region.RegionTable

object CountryTable: IntIdTable("country") {

    private val name = text("name")
    private val regionId = reference("region_id", RegionTable, ReferenceOption.SET_NULL)
    private val image = text("image")

    fun selectWithId(id: Int) = transaction {
        selectAll().where{ CountryTable.id eq id }.first().toCountry()
    }

    fun selectCountryList() = transaction {
        selectAll().map { it.toCountry() }
    }

    fun insertCountry(country: CountryEntity) = transaction {
        val regionId = RegionTable.selectIdWithName(country.region.name)
        insert {
            it[CountryTable.name] = country.name
            it[CountryTable.regionId] = regionId
            it[CountryTable.image] = country.image
        }
    }

    private fun ResultRow.toCountry() = CountryEntity(
        id = this[CountryTable.id].value,
        name = this[CountryTable.name],
        region = RegionTable.selectWithId(this[CountryTable.regionId].value),
        image = this[CountryTable.image],
    )

}