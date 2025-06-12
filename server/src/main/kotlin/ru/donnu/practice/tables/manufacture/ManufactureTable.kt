package ru.donnu.practice.tables.manufacture

import entity.ManufactureEntity
import entity.ManufactureType
import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq
import org.jetbrains.exposed.sql.transactions.transaction
import ru.donnu.practice.tables.country.CountryTable

object ManufactureTable: IntIdTable("manufacture") {

    private val countryId = reference("country_id", CountryTable)
    private val type = text("type")
    private val value = double("value").default(0.0)

    fun insertManufacture(countryId: Int, type: ManufactureType, value: Double) = transaction {
        val manufactureExists = selectWithType(countryId, type).firstOrNull()?.toManufacture()?.id ?: 0
        val id = upsert {
            it[ManufactureTable.id] = manufactureExists
            it[ManufactureTable.countryId] = countryId
            it[ManufactureTable.type] = type.name
            it[ManufactureTable.value] = value
        }[ManufactureTable.id]
        selectWithId(id.value)
    }

    fun selectWithId(id: Int) = transaction {
        selectAll().where(ManufactureTable.id eq id).first().toManufacture()
    }

    fun selectWithType(countryId: Int, type: ManufactureType) = transaction {
        selectAll().where((ManufactureTable.countryId eq countryId) and (ManufactureTable.type eq type.name))
    }

    fun selectAllManufacture() = transaction {
        selectAll().map { it.toManufacture() }
    }

    fun deleteWithId(countryId: Int) = transaction {
        deleteWhere { ManufactureTable.countryId eq countryId }
    }

    private fun ResultRow.toManufacture() = ManufactureEntity(
        id = this[id].value,
        countryId = this[countryId].value,
        type = ManufactureType.valueOf(this[type]),
        value = this[value]
    )

}