import input.readInputFileAsIntcode
import utilities.IntcodeComputer

fun day5() {
    val intcode = readInputFileAsIntcode( "day5_input.txt" )
    val computer = IntcodeComputer( intcode, ::input, ::output )
    computer.start()
}

private fun input() = 5
private fun output( out:Int ) = print( "$out, ")