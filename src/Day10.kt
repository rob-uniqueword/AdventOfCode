import input.readInputFileAsCharacterGrid
import utilities.AsteroidMap

fun day10()
{
    val input = readInputFileAsCharacterGrid( "day10_input.txt" )

    val map = AsteroidMap( input )

    val base = map.getAsteroidWithHighestViewCount()!!.first
    println( base )
    println( map.getDestroyedOrder( base )[ 199 ] )
}