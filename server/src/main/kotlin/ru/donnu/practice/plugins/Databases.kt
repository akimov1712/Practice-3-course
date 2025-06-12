package ru.donnu.practice.plugins

import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.application.*
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.transaction
import ru.donnu.practice.tables.country.CountryTable
import ru.donnu.practice.tables.manufacture.ManufactureTable
import ru.donnu.practice.tables.region.RegionTable
import ru.donnu.practice.utills.Env

fun Application.configureDatabases() {
    dotenv()
    Database.connect(
        url = Env["DATABASE_URL"],
        user = Env["DATABASE_USERNAME"],
        driver = Env["DATABASE_DRIVER"],
        password = Env["DATABASE_PASSWORD"],
    )
    transaction {
        SchemaUtils.create(RegionTable, CountryTable, ManufactureTable)
    }

}