import input.readInputFileAsCharacterGrid
import utilities.AsteroidMap

fun day10()
{
    val input = readInputFileAsCharacterGrid( "day10_input.txt" )
    println( AsteroidMap( input ).getAsteroidWithHighestViewCount()!!.second )
}