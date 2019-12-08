package utilities

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ProcessIntcodeKtTest {

    @Test
    fun processIntcodeTest() {
        doProcessIntcodeTest( "1,0,0,0,99", "2,0,0,0,99" )
        doProcessIntcodeTest( "2,3,0,3,99", "2,3,0,6,99" )
        doProcessIntcodeTest( "2,4,4,5,99,0", "2,4,4,5,99,9801" )
        doProcessIntcodeTest( "1,1,1,4,99,5,6,0,99", "30,1,1,4,2,5,6,0,99" )
    }

    private fun doProcessIntcodeTest( input:String, expected:String ) = assertEquals( processIntcode( input.toIntcode() ), expected.toIntcode() )

    private fun String.toIntcode() = this.split( "," ).map { i -> i.toInt() }

}