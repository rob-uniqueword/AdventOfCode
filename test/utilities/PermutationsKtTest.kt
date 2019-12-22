package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class PermutationsKtTest {

    @Test
    fun permutations() {
        val testList = listOf( 1, 2, 3 )
        assertEquals(
            listOf(
                listOf( 1, 2, 3 ),
                listOf( 1, 3, 2 ),
                listOf( 2, 1, 3 ),
                listOf( 2, 3, 1 ),
                listOf( 3, 1, 2 ),
                listOf( 3, 2, 1 )
            ),
            testList.permutations()
        )
    }
}