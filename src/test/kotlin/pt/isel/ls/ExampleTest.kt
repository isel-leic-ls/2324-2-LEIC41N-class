package pt.isel.ls

import pt.isel.ls.utils.indexOfBinary
import java.io.FileInputStream
import java.io.FileNotFoundException
import kotlin.test.Test
import kotlin.test.assertEquals

class ExampleTest {


    @Test
    fun indexOfBinaryT() {
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 0, 1, 1),0);
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 0, 0, 4),-1);
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 1, 1, 4),-1);
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 1, 1, 2),-1);
        assertEquals(indexOfBinary(intArrayOf(1, 2, 3, 4), 1, 3, 2),1);
    }
    
}