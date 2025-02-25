package pt.isel.ls

import org.postgresql.ds.PGSimpleDataSource
import java.sql.Connection
import java.sql.SQLException


class AppDb {
    private val dataSource = PGSimpleDataSource()
    private var jdbcDatabaseURL = System.getenv("JDBC_DATABASE_URL")

    //dataSource.setURL(jdbcDatabaseURL)
    private lateinit var connection: Connection


    // INIT
    init {
        // hard code just to test only
        jdbcDatabaseURL = "jdbc:postgresql://localhost/postgres?user=postgres&password=postgres"
        dataSource.setURL(jdbcDatabaseURL)
    }

    // OPEN
    private fun openConnection(): Boolean {
        try {
            connection = dataSource.connection
        } catch (e: SQLException) {
            println("Connection error: ${e.message}")
        }

        return connection.isValid(0)
    }

    //CLOSE
    private fun closeConnection() {
        if (connection.isValid(0) && !connection.isClosed) {
            try {
                connection.close()
            } catch (e: SQLException) {
                e.printStackTrace()
            }
        }
    }

    // DELETE
    fun doDelete(record: Int): Boolean {
        var error = false

        openConnection()

        try {
            val stm = connection.prepareStatement("delete from students where number =?;")
            stm.setInt(1, record)
            val rs = stm.execute()
        } catch (e: Exception) {
            println("Connection error: ${e.message}")
            error = true
        } finally {
            closeConnection()
        }

        return !error
    }

    // INSERT
    fun doInsert(course:Int, number:Int, name:String): Boolean {
        var error = false

        openConnection()

        try {
            val stm = connection.prepareStatement("insert into students(course, number, name) values (?, ?, ?);")
            stm.setInt(1, course)
            stm.setInt(2, number)
            stm.setString(3, name)
            val rs = stm.execute()
        } catch (e: Exception) {
            println("Connection error: ${e.message}")
            error = true
        } finally {
            closeConnection()
        }

        return !error
    }


}


fun main() {


    val dataSource = PGSimpleDataSource()
    val jdbcDatabaseURL = System.getenv("JDBC_DATABASE_URL")
    //dataSource.setURL(jdbcDatabaseURL)
    dataSource.setURL("jdbc:postgresql://localhost/postgres?user=postgres&password=postgres")

    dataSource.getConnection().use {
        val stm = it.prepareStatement("select * from students")
        val rs = stm.executeQuery()
        while (rs.next()) {
            println(rs.getString("name"))
        }
    }


}
