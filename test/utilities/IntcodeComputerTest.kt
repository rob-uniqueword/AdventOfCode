package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.math.BigInteger

internal class IntcodeComputerTest {

    private fun makeTestInputFunction( input:BigInteger ) : () -> BigInteger = { input }
    private fun makeTestOutputFunction(output:MutableList<BigInteger> ) : ( BigInteger ) -> Unit = { out:BigInteger -> output.add( out ) }

    @Test
    fun processTest() {
        doProcessTest( "1002,4,3,4,33", 1.toBigInteger(), 1002.toBigInteger() )
        doProcessTest( "3,0,4,0,99", 1.toBigInteger(), 1.toBigInteger(), listOf( 1.toBigInteger() ) )
        doProcessTest( "3,0,4,0,99", 7.toBigInteger(), 7.toBigInteger(), listOf( 7.toBigInteger() ) )

        doProcessTest( "3,9,8,9,10,9,4,9,99,-1,8", 8.toBigInteger(), expectedOutput = listOf( 1.toBigInteger() ) )
        doProcessTest( "3,9,8,9,10,9,4,9,99,-1,8", 7.toBigInteger(), expectedOutput = listOf( 0.toBigInteger() ) )
        doProcessTest( "3,9,7,9,10,9,4,9,99,-1,8", 7.toBigInteger(), expectedOutput = listOf( 1.toBigInteger() ) )
        doProcessTest( "3,9,7,9,10,9,4,9,99,-1,8", 8.toBigInteger(), expectedOutput = listOf( 0.toBigInteger() ) )
        doProcessTest( "3,3,1108,-1,8,3,4,3,99", 8.toBigInteger(), expectedOutput = listOf( 1.toBigInteger() ) )
        doProcessTest( "3,3,1108,-1,8,3,4,3,99", 7.toBigInteger(), expectedOutput = listOf( 0.toBigInteger() ) )
        doProcessTest( "3,3,1107,-1,8,3,4,3,99", 7.toBigInteger(), expectedOutput = listOf( 1.toBigInteger() ) )
        doProcessTest( "3,3,1107,-1,8,3,4,3,99", 8.toBigInteger(), expectedOutput = listOf( 0.toBigInteger() ) )

        doProcessTest( "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", 0.toBigInteger(), expectedOutput = listOf( 0.toBigInteger() ) )
        doProcessTest( "3,12,6,12,15,1,13,14,13,4,13,99,-1,0,1,9", 15.toBigInteger(), expectedOutput = listOf( 1.toBigInteger() ) )
        doProcessTest( "3,3,1105,-1,9,1101,0,0,12,4,12,99,1", 0.toBigInteger(), expectedOutput = listOf( 0.toBigInteger() ) )
        doProcessTest( "3,3,1105,-1,9,1101,0,0,12,4,12,99,1", 15.toBigInteger(), expectedOutput = listOf( 1.toBigInteger() ) )

        doProcessTest( "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
            7.toBigInteger(), expectedOutput = listOf( 999.toBigInteger() ) )
        doProcessTest( "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
            8.toBigInteger(), expectedOutput = listOf( 1000.toBigInteger() ) )
        doProcessTest( "3,21,1008,21,8,20,1005,20,22,107,8,21,20,1006,20,31,1106,0,36,98,0,0,1002,21,125,20,4,20,1105,1,46,104,999,1105,1,46,1101,1000,1,20,4,20,1105,1,46,98,99",
            9.toBigInteger(), expectedOutput = listOf( 1001.toBigInteger() ) )

        doProcessTest( "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99",
            0.toBigInteger(), expectedOutput = "109,1,204,-1,1001,100,1,100,1008,100,16,101,1006,101,0,99".toIntcode() )
        doProcessTest( "1102,34915192,34915192,7,4,7,99,0", 0.toBigInteger(), expectedOutput = listOf( "1219070632396864".toBigInteger() ) )
        doProcessTest( "104,1125899906842624,99", 0.toBigInteger(), expectedOutput = listOf( "1125899906842624".toBigInteger() ) )
    }

    private fun doProcessTest( inputCode:String, inputVal:BigInteger, expectedReturn:BigInteger? = null, expectedOutput:List<BigInteger>? = null )
    {
        val output = mutableListOf<BigInteger>()
        val computer = IntcodeComputer( inputCode.toIntcode(),
                                        makeTestInputFunction( inputVal ),
                                        makeTestOutputFunction( output ) )
        val returnCode = computer.start()

        if ( expectedReturn != null ) assertEquals( expectedReturn, returnCode )
        if ( expectedOutput != null ) assertEquals( expectedOutput, output )
    }

    private fun String.toIntcode() = this.split( "," ).map { i -> i.toBigInteger() }

}