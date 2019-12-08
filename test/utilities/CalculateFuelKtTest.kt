package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class CalculateFuelKtTest {

    @Test
    fun calculateFuelTest() {
        assertEquals( calculateFuel( 14 ), 2 )
        assertEquals( calculateFuel( 1969 ), 966 )
        assertEquals( calculateFuel( 100756 ), 50346 )
    }

}