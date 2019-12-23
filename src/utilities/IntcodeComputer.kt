package utilities

class IntcodeComputer( private val intcode:List<Int>, input:() -> Int, output:(Int) -> Unit )
{
    private val operations:Map<Int,Operation> = mapOf(
        Pair( 1, Operation( 2, 1 ) { code:MutableList<Int>, params:List<Int> ->
            run { code[params[2]] = params[0] + params[1]; return@Operation null }
        }),
        Pair( 2, Operation( 2, 1 ) { code:MutableList<Int>, params:List<Int> ->
            run { code[ params[2] ] = params[0] * params[1]; return@Operation null }
        }),
        Pair( 3, Operation( 0, 1 ) { code:MutableList<Int>, params:List<Int> ->
            run { code[ params[0] ] = input(); return@Operation null }
        }),
        Pair( 4, Operation( 1, 0 ) { _:MutableList<Int>, params:List<Int> ->
            run { output( params[0] ); return@Operation null }
        }),
        Pair( 5, Operation( 2, 0 ) { _:MutableList<Int>, params:List<Int> ->
            run { return@Operation if ( params[0] != 0 ) params[1] else null }
        }),
        Pair( 6, Operation( 2, 0 ) { _:MutableList<Int>, params:List<Int> ->
            run { return@Operation if ( params[0] == 0 ) params[1] else null }
        }),
        Pair( 7, Operation( 2, 1 ) { code:MutableList<Int>, params:List<Int> ->
            run { code[ params[2] ] = if ( params[0] < params[1] ) 1 else 0; return@Operation null }
        }),
        Pair( 8, Operation( 2, 1 ) { code:MutableList<Int>, params:List<Int> ->
            run { code[ params[2] ] = if ( params[0] == params[1] ) 1 else 0; return@Operation null }
        }),
        Pair( 99, Operation( 0, 0 ) { _: MutableList<Int>, _: List<Int> ->
            run { return@Operation -1 }
        })
    )

    private val paramModes:Map<Int,ParamMode> = mapOf(
        Pair( 0, ParamMode { code:MutableList<Int>, pointer:Int -> code[ code[ pointer ] ] } ),
        Pair( 1, ParamMode { code:MutableList<Int>, pointer:Int -> code[ pointer ] } )
    )

    fun start() : Int
    {
        val code = intcode.toMutableList()
        var position = 0

        while( true )
        {
            var instruction = code[ position++ ]
            val opcode = instruction % 100
            instruction /= 100
            
            if ( opcode == 99 )
                return code[ 0 ]
            
            val operation = operations[ opcode ] ?: throw UnknownOpcodeException( "Encountered unknown opcode $opcode" )

            val params = mutableListOf<Int>()
            for ( i in 1..operation.readParamCount )
            {
                val paramMode = paramModes[ instruction % 10 ] ?: throw UnknownParamcodeException( "Encountered unknown paramcode ${instruction % 10}" )
                instruction /= 10

                params.add( paramMode.method( code, position++ ) )
            }
            for ( i in 1..operation.writeParamCount )
            {
                params.add( code[ position++ ] )
            }

            val next = operation.method( code, params )
            if ( next == -1 ) return code[0]
            if ( next != null ) position = next
        }
    }
}

class UnknownOpcodeException( message:String ) : Exception( message )
class UnknownParamcodeException( message: String ) : Exception( message )

private class Operation(val readParamCount:Int, val writeParamCount:Int, val method:(MutableList<Int>, List<Int> ) -> Int? )
private class ParamMode(val method:(MutableList<Int>, Int ) -> Int )