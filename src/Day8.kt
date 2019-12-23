import input.readInputFileAsSpaceImage
import utilities.SpaceImage

fun day8()
{
    val input = readInputFileAsSpaceImage( "day8_input.txt" )
    val image = SpaceImage( 25, 6, input )
    println( image.checksum() )

    image.print()
}