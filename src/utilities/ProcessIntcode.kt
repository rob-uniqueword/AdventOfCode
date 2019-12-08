package utilities

fun processIntcode( intcode:List<Int> ) : List<Int>
{
    val code = intcode.toMutableList()
    var curr = 0

    while ( true )
    {
        val opcode = code[ curr ]

        when ( opcode ) {
            1 -> code[ code[ curr + 3 ] ] = code[ code[ curr + 1 ] ] + code[ code [ curr + 2 ] ]
            2 -> code[ code[ curr + 3 ] ] = code[ code[ curr + 1 ] ] * code[ code [ curr + 2 ] ]
            99 -> return code
            else -> { throw Exception( "Encountered unexpected opcode $opcode" ) }
        }

        curr += 4
    }
}