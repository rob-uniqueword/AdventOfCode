package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class IntcodeComputerTest {

    val testOutput = mutableListOf<Int>()
    fun makeTestInputFunction( input:Int ) : () -> Int = { input }
    fun makeTestOutputFunction( output:MutableList<Int> ) : ( Int ) -> Unit = { out:Int -> output.add( out ) }

    @Test
    fun processTest() {
        doProcessTest( "1002,4,3,4,33", 1, 1002 )
        doProcessTest( "3,0,4,0,99", 1, 1, listOf( 1 ) )
        doProcessTest( "3,0,4,0,99", 7, 7, listOf( 7 ) )
    }

    private fun doProcessTest( inputCode:String, inputVal:Int, expectedReturn:Int, expectedOutput:List<Int> = listOf() )
    {
        val output = mutableListOf<Int>()
        val computer = IntcodeComputer( inputCode.toIntcode(), makeTestInputFunction( inputVal ), makeTestOutputFunction( output ) )
        assertEquals( expectedReturn, computer.process() )
        assertEquals( expectedOutput, output )
    }

    private fun String.toIntcode() = this.split( "," ).map { i -> i.toInt() }

}