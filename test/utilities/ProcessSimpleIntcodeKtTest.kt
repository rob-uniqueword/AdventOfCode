package utilities

import input.toSimpleIntcode
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class ProcessSimpleIntcodeKtTest {

    @Test
    fun processSimpleIntcodeTest() {
        doProcessSimpleIntcodeTest( "1,0,0,0,99", "2,0,0,0,99" )
        doProcessSimpleIntcodeTest( "2,3,0,3,99", "2,3,0,6,99" )
        doProcessSimpleIntcodeTest( "2,4,4,5,99,0", "2,4,4,5,99,9801" )
        doProcessSimpleIntcodeTest( "1,1,1,4,99,5,6,0,99", "30,1,1,4,2,5,6,0,99" )
    }

    private fun doProcessSimpleIntcodeTest( input:String, expected:String ) = assertEquals( processSimpleIntcode( input.toSimpleIntcode() ), expected.toSimpleIntcode() )

}