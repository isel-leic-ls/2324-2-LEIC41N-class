package pt.isel.ls

import org.postgresql.ds.PGSimpleDataSource

fun main(){

    val dataSource = PGSimpleDataSource()
    val jdbcDatabaseURL = System.getenv("JDBC_DATABASE_URL")
    //dataSource.setURL(jdbcDatabaseURL)
    dataSource.setURL("jdbc:postgresql://localhost/postgres?user=postgres&password=postgres")

    // DELETE SNIPPET
    dataSource.getConnection().use {
        val stm = it.prepareStatement("delete from students where number =?;")
        stm.setInt(1, 2201)

        val rs = stm.execute();
    }

    // INSERT SNIPPET
    dataSource.getConnection().use {
        val stm = it.prepareStatement("insert into students(course, number, name) values (?, ?, ?);")
        stm.setInt(1, 1)
        stm.setInt(2, 2200)
        stm.setString(3, "Faustino")

        val rs = stm.execute();
    }
    // UPDATE SNIPPET
    dataSource.getConnection().use {
        val stm = it.prepareStatement("update students set number=? where name =?")
        stm.setInt(1, 2201)
        stm.setString(2, "Faustino")

        val rs = stm.execute();
    }

    dataSource.getConnection().use {
        val stm = it.prepareStatement("select * from students")
        val rs = stm.executeQuery()
        while (rs.next()) {
            println(rs.getString("name"))
        }
    }
}