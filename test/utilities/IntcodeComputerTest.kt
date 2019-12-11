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

        doProcessTest( "3,9,8,9,10,9,4,9,99,-1,8", 8, expectedOutput = listOf( 1 ) )
        doProcessTest( "3,9,8,9,10,9,4,9,99,-1,8", 7, expectedOutput = listOf( 0 ) )
        doProcessTest( "3,9,7,9,10,9,4,9,99,-1,8", 7, expectedOutput = listOf( 1 ) )
        doProcessTest( "3,9,7,9,10,9,4,9,99,-1,8", 8, expectedOutput = listOf( 0 ) )
        doProcessTest( "3,3,1108,-1,8,3,4,3,99", 8, expectedOutput = listOf( 1 ) )
        doProcessTest( "3,3,1108,-1,8,3,4,3,99", 7, expectedOutput = listOf( 0 ) )
        doProcessTest( "3,3,1107,-1,8,3,4,3,99", 7, expectedOutput = listOf( 1 ) )
        doProcessTest( "3,3,1107,-1,8,3,4,3,99", 8, expectedOutput = listOf( 0 ) )

        doProcessTest( "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", 0, expectedOutput = listOf( 0 ) )
        doProcessTest( "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", 15, expectedOutput = listOf( 1 ) )
        doProcessTest( "3,3,1105,-1,9,1101,0,0,12,4,12,99,1", 0, expectedOutput = listOf( 0 ) )
        doProcessTest( "3,3,1105,-1,9,1101,0,0,12,4,12,99,1", 15, expectedOutput = listOf( 1 ) )

        doProcessTest( "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
            7, expectedOutput = listOf( 999 ) )
        doProcessTest( "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
            8, expectedOutput = listOf( 1000 ) )
        doProcessTest( "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
            9, expectedOutput = listOf( 1001 ) )
    }

    private fun doProcessTest( inputCode:String, inputVal:Int, expectedReturn:Int? = null, expectedOutput:List<Int>? = null )
    {
        val output = mutableListOf<Int>()
        val computer = IntcodeComputer( inputCode.toIntcode(), makeTestInputFunction( inputVal ), makeTestOutputFunction( output ) )
        val returnCode = computer.process()
        if ( expectedReturn != null ) assertEquals( expectedReturn, returnCode )
        if ( expectedOutput != null ) assertEquals( expectedOutput, output )
    }

    private fun String.toIntcode() = this.split( "," ).map { i -> i.toInt() }

}