package utilities

fun findBestAmplifierPhasePermutation( intCode:List<Int>, phases:List<Int> ) : Int
{
    return phases.permutations().map { p -> amplifySignal( 0, intCode, p ) }.max() ?: 0
}

private fun amplifySignal(input:Int, intCode:List<Int>, amplifierPhases:List<Int> ) : Int
{
    var signal = input
    amplifierPhases.forEach { p -> signal = Amplifier( intCode, p ).amplify( signal ) }
    return signal
}

private class Amplifier( intCode:List<Int>, private var phaseSetting:Int )
{
    private val inputs:MutableList<Int> = mutableListOf()
    private val outputs:MutableList<Int> = mutableListOf()

    private val computer = IntcodeComputer( intCode,
        { if ( inputs.isNotEmpty() ) inputs.removeAt( 0 ) else error( "Insufficient inputs?" ) },
        { out:Int -> outputs.add( out ) } )

    fun amplify( input:Int ) : Int
    {
        inputs.add( phaseSetting )
        inputs.add( input )
        computer.process()
        return outputs[ 0 ]
    }
}