import input.readInputFileAsIntcode
import utilities.IntcodeComputer
import java.math.BigInteger

fun day5() {
    val intcode = readInputFileAsIntcode( "day5_input.txt" )
    val computer = IntcodeComputer( intcode, ::input, ::output )
    computer.start()
}

private fun input() = 5.toBigInteger()
private fun output( out:BigInteger ) = print( "$out, ")