package pt.isel.ls

import org.postgresql.ds.PGSimpleDataSource
import java.sql.Connection
import java.sql.SQLException


public class AppDb {
    val dataSource = PGSimpleDataSource()
    var jdbcDatabaseURL = System.getenv("JDBC_DATABASE_URL")

    //dataSource.setURL(jdbcDatabaseURL)
    private lateinit var connection: Connection

    // INIT
    init {
        // test only
        jdbcDatabaseURL = "jdbc:postgresql://localhost/postgres?user=postgres&password=postgres"
        dataSource.setURL(jdbcDatabaseURL)
    }

    // OPEN
    fun openConnection(): Boolean {
        println("Connection")
        try {
            connection = dataSource.connection
        } catch (e: SQLException) {
            println("Connection error: ${e.message}")
        }

        return connection.isValid(0)
    }

    //CLOSE
    fun closeConnection() {
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


        // INSERT SNIPPET
        /*
        dataSource.connection.use {
            val stm = connection.prepareStatement("insert into students(course, number, name) values (?, ?, ?);")
            stm.setInt(1, 1)
            stm.setInt(2, 2200)
            stm.setString(3, "Faustino")

            val rs = stm.execute()
        }
        // UPDATE SNIPPET
        dataSource.connection.use {
            val stm = connection.prepareStatement("update students set number=? where name =?")
            stm.setInt(1, 2201)
            stm.setString(2, "Faustino")

            val rs = stm.execute()
        }

        // SELECT SNIPPET
        dataSource.connection.use {
            val stm = connection.prepareStatement("select * from students")
            val rs = stm.executeQuery()
            while (rs.next()) {
                println(rs.getString("name"))
            }
        }
*/

    }
