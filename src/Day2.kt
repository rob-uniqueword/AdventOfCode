import input.readInputFile
import input.toSimpleIntcode
import utilities.UnknownOpcodeException
import utilities.processSimpleIntcode

fun day2()
{
    val inputIntcode = readInputFile( "day2_input.txt" ).toSimpleIntcode()
    doPart1( inputIntcode )
    doPart2( inputIntcode )
}

private fun doPart1( inputIntcode:List<Int> )
{
    val intCode = inputIntcode.toMutableList()
    intCode[1] = 12
    intCode[2] = 2
    println( processSimpleIntcode( intCode ) )
}

private fun doPart2( inputIntcode: List<Int> )
{
    val intcode = inputIntcode.toMutableList()

    for ( noun in inputIntcode.indices )
    {
        for ( verb in inputIntcode.indices )
        {
            try
            {
                intcode[1] = noun
                intcode[2] = verb
                val processed = processSimpleIntcode( intcode )
                if ( processed[0] == 19690720 )
                {
                    println( noun )
                    println( verb )
                    println( 100 * noun + verb )
                    return
                }
            }
            catch ( e:UnknownOpcodeException )
            {
                // do nothing
            }
        }
    }
}