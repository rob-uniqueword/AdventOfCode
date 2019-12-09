package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ValidateCodeKtTest {

    @Test
    fun isValidCodeTest() {
        assertTrue( isValidCode( "111111" ) )
        assertFalse( isValidCode( "223450" ) )
        assertFalse( isValidCode( "123789" ) )
    }
}