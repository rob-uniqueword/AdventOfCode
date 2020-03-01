import input.readInputFile
import input.toIntcode
import utilities.IntcodeComputer
import java.math.BigInteger

fun day5() {
    val intcode = readInputFile( "day5_input.txt" ).toIntcode()
    val computer = IntcodeComputer( intcode, ::input, ::output )
    computer.start()
}

private fun input() = 5.toBigInteger()
private fun output( out:BigInteger ) = print( "$out, ")