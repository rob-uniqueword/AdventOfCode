import input.readInputFile
import input.toSpaceImage
import utilities.SpaceImage

fun day8()
{
    val input = readInputFile( "day8_input.txt" ).toSpaceImage()
    val image = SpaceImage( 25, 6, input )
    println( image.checksum() )

    image.print()
}