import input.readInputFileAsIntcode
import utilities.findBestAmplifierPhasePermutation

fun day7()
{
    val input = readInputFileAsIntcode( "day7_input.txt" )
    println( findBestAmplifierPhasePermutation( input, listOf( 0, 1, 2, 3, 4 ) ) )
}