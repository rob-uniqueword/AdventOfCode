import input.readInputFileAsIntcode
import utilities.IntcodeComputer
import java.math.BigInteger

fun day9()
{
    val input = readInputFileAsIntcode( "day9_input.txt" )
    val computer = IntcodeComputer( input, ::input, ::output )
    computer.start()
}

private fun input() = 2.toBigInteger()
private fun output( out: BigInteger ) = print( "$out, ")