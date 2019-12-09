package utilities

fun isValidCode( code:String ) : Boolean
{
    val digits = code.toCharArray()

    if ( digits.size != 6 )
        return false

    var containsDouble = false

    for ( i:Int in 0..4 )
    {
        if ( code[ i ] == code[ i + 1 ] )
            containsDouble = true

        if ( code[ i ] > code[ i + 1 ] )
            return false
    }

    return containsDouble
}