import input.readInputFile
import input.toCharacterGrid
import utilities.AsteroidMap

fun day10()
{
    val input = readInputFile( "day10_input.txt" ).toCharacterGrid()

    val map = AsteroidMap( input )

    val base = map.getAsteroidWithHighestViewCount()!!.first
    println( base )
    println( map.getDestroyedOrder( base )[ 199 ] )
}