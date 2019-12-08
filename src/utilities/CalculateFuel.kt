package utilities

fun calculateFuel( mass:Int ) : Int
{
    val fuel = mass / 3 - 2
    return if ( fuel < 9 ) fuel else fuel + calculateFuel( fuel )
}