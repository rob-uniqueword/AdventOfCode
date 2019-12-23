package utilities

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.util.concurrent.LinkedBlockingQueue

suspend fun findBestAmplifierPhasePermutation( intCode:List<Int>, phases:List<Int> ) : Int
{
    return phases.permutations().map { p -> amplifySignal( 0, intCode, p ) }.max() ?: 0
}

private suspend fun amplifySignal(input:Int, intCode:List<Int>, amplifierPhases:List<Int> ) : Int
{
    val amplifiers = mutableListOf<Amplifier>()

    for ( phase:Int in amplifierPhases )
    {
        val amplifier:Amplifier

        if ( amplifiers.isEmpty() )
            amplifier = Amplifier( intCode, phase, listOf( input ) )
        else
        {
            amplifier = Amplifier( intCode, phase )
            amplifiers.last().outputTo( amplifier )
        }

        amplifiers.add( amplifier )
    }

    amplifiers.last().outputTo( amplifiers[0] )

    val outputs = amplifiers.map { a -> GlobalScope.async { a.run() } }
    return outputs.last().await()
}

private class Amplifier( intCode:List<Int>, phaseSetting:Int, initialInputs:List<Int> = listOf() )
{
    private var inputQueue = LinkedBlockingQueue<Int>( listOf( phaseSetting ) + initialInputs )
    private var outputQueue = LinkedBlockingQueue<Int>()
    private val outputs = mutableListOf<Int>()

    private val computer = IntcodeComputer( intCode,
        { inputQueue.take(); },
        { out:Int -> outputQueue.add( out ); outputs.add( out ) } )

    fun outputTo( nextAmplifier:Amplifier )
    {
        this.outputQueue = nextAmplifier.inputQueue
    }

    fun run() : Int
    {
        computer.start()
        return outputs.last()
    }
}