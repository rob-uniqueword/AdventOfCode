package utilities

import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import java.math.BigInteger
import java.util.concurrent.LinkedBlockingQueue

suspend fun findBestAmplifierPhasePermutation( intCode:List<BigInteger>, phases:List<BigInteger> ) : BigInteger
{
    return phases.permutations().map { p -> amplifySignal( 0.toBigInteger(), intCode, p ) }.max() ?: 0.toBigInteger()
}

private suspend fun amplifySignal(input:BigInteger, intCode:List<BigInteger>, amplifierPhases:List<BigInteger> ) : BigInteger
{
    val amplifiers = mutableListOf<Amplifier>()

    for ( phase:BigInteger in amplifierPhases )
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

private class Amplifier( intCode:List<BigInteger>, phaseSetting:BigInteger, initialInputs:List<BigInteger> = listOf() )
{
    private var inputQueue = LinkedBlockingQueue<BigInteger>( listOf( phaseSetting ) + initialInputs )
    private var outputQueue = LinkedBlockingQueue<BigInteger>()
    private val outputs = mutableListOf<BigInteger>()

    private val computer = IntcodeComputer( intCode,
        { inputQueue.take(); },
        { out:BigInteger -> outputQueue.add( out ); outputs.add( out ) } )

    fun outputTo( nextAmplifier:Amplifier )
    {
        this.outputQueue = nextAmplifier.inputQueue
    }

    fun run() : BigInteger
    {
        computer.start()
        return outputs.last()
    }
}