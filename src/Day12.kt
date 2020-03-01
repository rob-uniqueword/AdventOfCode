import input.readInputFile
import input.toPoints
import utilities.MoonSimulator

fun day12()
{
    val input = readInputFile( "day12_input.txt" ).toPoints()

    val simulator = MoonSimulator( input )
    simulator.process( 1000 )
    println( simulator.totalPotentialEnergy() )
}