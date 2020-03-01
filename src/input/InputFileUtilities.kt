package input

class ResourceFile( fileName:String )
{
    val fileText = ResourceFile::class.java.getResource( fileName ).readText()
}

fun readInputFile(inputFileName: String ) : String = ResourceFile( inputFileName ).fileText

fun String.toIntegerList() = this.lines().map { s -> s.toInt() }
fun String.toSimpleIntcode() = this.split( "," ).map { s -> s.toInt() }
fun String.toIntcode() = this.split( "," ).map { s -> s.toBigInteger() }
fun String.toWirePaths() = this.lines().map { l -> l.split( "," ).map { s -> Pair( s[0], s.substring( 1 ).toInt() ) } }
fun String.toIntegerRange() = run {
    val pair = readInputFile( this ).split( "-" )
    pair[0].toInt()..pair[1].toInt()
}
fun String.toOrbitMap() = this.lines().map { l -> Pair( l.substringBefore( ")" ), l.substringAfter( ")" ) ) }
fun String.toSpaceImage() = this.toCharArray().map { c -> c.toString().toInt() }
fun String.toCharacterGrid() = this.lines().map { l -> l.toList() }