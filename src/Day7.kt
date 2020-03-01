import input.readInputFile
import input.toIntcode
import kotlinx.coroutines.runBlocking

import utilities.findBestAmplifierPhasePermutation

fun day7()
{
    val input = readInputFile( "day7_input.txt" ).toIntcode()

    runBlocking {
        println( findBestAmplifierPhasePermutation( input, listOf( 0, 1, 2, 3, 4 ).map { i -> i.toBigInteger() } ) )
        println( findBestAmplifierPhasePermutation( input, listOf( 5, 6, 7, 8, 9 ).map { i -> i.toBigInteger() } ) )
    }
}