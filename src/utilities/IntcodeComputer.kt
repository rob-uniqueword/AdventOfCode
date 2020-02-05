package utilities

import java.math.BigInteger

class IntcodeComputer( private val intcode:List<BigInteger>, input:() -> BigInteger, output:(BigInteger) -> Unit )
{
    private val memory = Memory( intcode )
    private var relativeBase = BigInteger.ZERO

    private val operations:Map<BigInteger,Operation> = mapOf(
        Pair( 1.toBigInteger(), Operation( 2, 1 ) { code:Memory, params:List<BigInteger> ->
            run { code[params[2]] = params[0] + params[1]; return@Operation null }
        }),
        Pair( 2.toBigInteger(), Operation( 2, 1 ) { code:Memory, params:List<BigInteger> ->
            run { code[ params[2] ] = params[0] * params[1]; return@Operation null }
        }),
        Pair( 3.toBigInteger(), Operation( 0, 1 ) { code:Memory, params:List<BigInteger> ->
            run { code[ params[0] ] = input(); return@Operation null }
        }),
        Pair( 4.toBigInteger(), Operation( 1, 0 ) { _:Memory, params:List<BigInteger> ->
            run { output( params[0] ); return@Operation null }
        }),
        Pair( 5.toBigInteger(), Operation( 2, 0 ) { _:Memory, params:List<BigInteger> ->
            run { return@Operation if ( params[0] != BigInteger.ZERO ) params[1] else null }
        }),
        Pair( 6.toBigInteger(), Operation( 2, 0 ) { _:Memory, params:List<BigInteger> ->
            run { return@Operation if ( params[0] == BigInteger.ZERO ) params[1] else null }
        }),
        Pair( 7.toBigInteger(), Operation( 2, 1 ) { code:Memory, params:List<BigInteger> ->
            run { code[ params[2] ] = if ( params[0] < params[1] ) BigInteger.ONE else BigInteger.ZERO; return@Operation null }
        }),
        Pair( 8.toBigInteger(), Operation( 2, 1 ) { code:Memory, params:List<BigInteger> ->
            run { code[ params[2] ] = if ( params[0] == params[1] ) BigInteger.ONE else BigInteger.ZERO; return@Operation null }
        }),
        Pair( 9.toBigInteger(), Operation( 1, 0 ) { _:Memory, params:List<BigInteger> ->
            run { relativeBase += params[0]; return@Operation null }
        }),
        Pair( 99.toBigInteger(), Operation( 0, 0 ) { _:Memory, _:List<BigInteger> ->
            run { return@Operation (-1).toBigInteger() }
        })
    )

    private val readParamModes:Map<BigInteger,ReadParamMode> = mapOf(
        Pair( 0.toBigInteger(), ReadParamMode { code:Memory, pointer:BigInteger -> code[ code[ pointer ] ] } ),
        Pair( 1.toBigInteger(), ReadParamMode { code:Memory, pointer:BigInteger -> code[ pointer ] } ),
        Pair( 2.toBigInteger(), ReadParamMode { code:Memory, pointer:BigInteger -> code[ relativeBase + code[ pointer ] ] } )
    )

    private val writeParamModes:Map<BigInteger,WriteParamMode> = mapOf(
        Pair( 0.toBigInteger(), WriteParamMode { code:Memory, pointer:BigInteger -> code[ pointer ] } ),
        Pair( 2.toBigInteger(), WriteParamMode { code:Memory, pointer:BigInteger -> relativeBase + code[ pointer ] } )
    )

    fun start() : BigInteger
    {
        val code = Memory( intcode )
        var position = BigInteger.ZERO

        while( true )
        {
            var instruction = code[ position++ ]
            val opcode = instruction.mod( 100.toBigInteger() )
            instruction /= 100.toBigInteger()
            
            if ( opcode == 99.toBigInteger() )
                return code[ BigInteger.ZERO ]
            
            val operation = operations[ opcode ] ?: throw UnknownOpcodeException( "Encountered unknown opcode $opcode" )

            val params = mutableListOf<BigInteger>()
            for ( i in 1..operation.readParamCount )
            {
                val readParamMode = readParamModes[ instruction.mod( 10.toBigInteger() ) ] ?: throw UnknownParamcodeException( "Encountered unknown paramcode ${instruction.mod( 10.toBigInteger() )}" )
                instruction /= 10.toBigInteger()

                params.add( readParamMode.method( code, position++ ) )
            }
            for ( i in 1..operation.writeParamCount )
            {
                val writeParamMode = writeParamModes[ instruction.mod( 10.toBigInteger() ) ] ?: throw UnknownParamcodeException( "Encountered unknown paramcode ${instruction.mod( 10.toBigInteger() )}" )
                instruction /= 10.toBigInteger()

                params.add( writeParamMode.method( code, position++ ) )
            }

            val next = operation.method( code, params )
            if ( next == (-1).toBigInteger()) return code[ 0.toBigInteger()]
            if ( next != null ) position = next
        }
    }
}

class UnknownOpcodeException( message:String ) : Exception( message )
class UnknownParamcodeException( message: String ) : Exception( message )

private class Operation( val readParamCount:Int, val writeParamCount:Int, val method:( Memory, List<BigInteger> ) -> BigInteger? )
private class ReadParamMode(val method: ( Memory, BigInteger ) -> BigInteger )
private class WriteParamMode( val method:( Memory, BigInteger ) -> BigInteger )

private class Memory( code:List<BigInteger> )
{
    private val memory = code.withIndex().associateBy( { it.index.toBigInteger() }, { it.value } ).toMutableMap()
    operator fun get(index:BigInteger ) = memory.getOrDefault( index, BigInteger.ZERO )
    operator fun set(index:BigInteger, value:BigInteger )
    {
        memory[ index ] = value
    }
}