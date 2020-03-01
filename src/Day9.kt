import input.readInputFile
import input.toIntcode
import utilities.IntcodeComputer
import java.math.BigInteger

fun day9()
{
    val input = readInputFile( "day9_input.txt" ).toIntcode()
    val computer = IntcodeComputer( input, ::input, ::output )
    computer.start()
}

private fun input() = 2.toBigInteger()
private fun output( out: BigInteger ) = print( "$out, ")