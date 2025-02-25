package pt.isel.ls

import pt.isel.ls.utils.indexOfBinary
import java.io.FileInputStream
import java.io.FileNotFoundException
import kotlin.test.Test
import kotlin.test.assertEquals
import pt.isel.ls.AppDb
import kotlin.test.assertTrue

class ExampleTest {


    @Test
    fun testDatabase() {
         val db: AppDb = AppDb();

        assertTrue { db.doDelete(2201) }
        assertTrue { db.doDelete(2202) }
        assertTrue { db.doInsert(1, 2201, "Faustino 2201") }
        assertTrue { db.doInsert(1, 2202, "Faustino 2202") }

    }


    //@Test
    fun indexOfBinaryT() {


        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 0, 1, 1),0);
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 0, 0, 4),-1);
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 1, 1, 4),-1);
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 1, 1, 2),-1);
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 1, 3, 2),1);
    }
    
}