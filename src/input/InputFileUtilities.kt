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
fun String.toPoints() = Regex( "<x=([-0-9]*), y=([-0-9]*), z=([-0-9]*)>" )
    .findAll( this ).toList()
    .map { Point3D( it.groupValues[1].toInt(), it.groupValues[2].toInt(), it.groupValues[3].toInt() ) }

class Point3D( var x:Int, var y:Int, var z:Int )