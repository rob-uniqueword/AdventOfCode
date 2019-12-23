package utilities

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SpaceImageTest
{
    @Test
    fun createSpaceImageTest() {
        val spaceImage = SpaceImage( 3, 2, "123456789012".toSingleDigitIntList() )
        assertEquals( 1, spaceImage.checksum() )

        val printImage = SpaceImage( 2, 2, "0222112222120000".toSingleDigitIntList() )
        printImage.print()
    }

    private fun String.toSingleDigitIntList() = this.toCharArray().map { c -> c.toString().toInt() }
}