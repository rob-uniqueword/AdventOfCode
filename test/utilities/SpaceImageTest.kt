package utilities

import input.toSpaceImage
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class SpaceImageTest
{
    @Test
    fun createSpaceImageTest() {
        val spaceImage = SpaceImage( 3, 2, "123456789012".toSpaceImage() )
        assertEquals( 1, spaceImage.checksum() )

        val printImage = SpaceImage( 2, 2, "0222112222120000".toSpaceImage() )
        printImage.print()
    }
}