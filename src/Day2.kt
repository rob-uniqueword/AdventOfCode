import utilities.processIntcode

fun day2()
{
    println( processIntcode( "1,0,0,0,99".split(",").map { i -> i.toInt() } ) )
    println( processIntcode( "2,3,0,3,99".split(",").map { i -> i.toInt() } ) )
    println( processIntcode( "2,4,4,5,99,0".split(",").map { i -> i.toInt() } ) )
    println( processIntcode( "1,1,1,4,99,5,6,0,99".split(",").map { i -> i.toInt() } ) )
}