package utilities

fun processSimpleIntcode(intcode:List<Int> ) : List<Int>
{
    val code = intcode.toMutableList()
    var curr = 0

    while ( true )
    {
        when ( code[ curr ] ) {
            1 -> code[ code[ curr + 3 ] ] = code[ code[ curr + 1 ] ] + code[ code [ curr + 2 ] ]
            2 -> code[ code[ curr + 3 ] ] = code[ code[ curr + 1 ] ] * code[ code [ curr + 2 ] ]
            99 -> return code
            else -> { throw UnknownOpcodeException( "Encountered unexpected opcode ${code[ curr ]}" ) }
        }

        curr += 4
    }
}