package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ValidateCodeKtTest {

    @Test
    fun isValidCodeTest() {
        assertFalse( isValidCode( "111111" ) )
        assertFalse( isValidCode( "223450" ) )
        assertFalse( isValidCode( "123789" ) )
        assertFalse( isValidCode( "123444" ) )

        assertTrue( isValidCode( "112233" ) )
        assertTrue( isValidCode( "111122" ) )
    }
}