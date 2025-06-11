package ru.donnu.practice.model.region

import org.jetbrains.exposed.dao.id.IntIdTable
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.selectAll
import org.jetbrains.exposed.sql.transactions.transaction

object RegionTable: IntIdTable("region") {

    private val name = text("name")

    fun insertName(name: String) = transaction { insert { it[RegionTable.name] = name } }

    fun selectIdWithName(name: String) = transaction {
        selectAll().where { RegionTable.name eq name }.first()[RegionTable.id].value
    }

    fun selectWithId(id: Int) = transaction {
        selectAll().where { RegionTable.id eq id }.first().toRegion()
    }

    private fun ResultRow.toRegion() = RegionEntity(
        id = this[RegionTable.id].value,
        name = this[RegionTable.name],
    )

}