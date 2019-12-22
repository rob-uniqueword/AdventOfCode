package utilities

class OrbitMap( input:List<Pair<String,String>> )
{
    private val orbitMap = parseInputToOrbits( input )

    fun getOrbitCount() = orbitMap.values.firstOrNull()?.getSystemOrbitCount() ?: 0

    fun getDistanceBetweenOrbitees( firstObjectName:String, secondObjectName:String ) : Int
    {
        val firstObject = orbitMap[ firstObjectName ] ?: error( "Orbit $firstObjectName does not exist" )
        val secondObject = orbitMap[ secondObjectName ] ?: error( "Orbit $secondObjectName does not exist" )

        val firstOrbitee = firstObject.orbitee ?: error( "Orbit $firstObjectName has no orbitee" )
        val secondOrbitee = secondObject.orbitee ?: error( "Orbit $firstObjectName has no orbitee" )

        return getDistanceBetween( firstOrbitee, secondOrbitee )
    }

    private fun getDistanceBetween( firstSpaceObject:SpaceObject, secondSpaceObject:SpaceObject ) : Int
    {
        val firstAddress = firstSpaceObject.getAddress()
        val secondAddress = secondSpaceObject.getAddress()

        return firstAddress.size + secondAddress.size - 2 * ( firstAddress.intersect( secondAddress ).size )
    }

    private fun parseInputToOrbits( input:List<Pair<String,String>> ) : Map<String, SpaceObject>
    {
        val orbits = mutableMapOf<String, SpaceObject>()

        for ( entry:Pair<String,String> in input )
        {
            val orbitee = orbits.getOrPut( entry.first, { SpaceObject( entry.first ) } )
            val orbiter = orbits.getOrPut( entry.second, { SpaceObject( entry.second ) } )

            orbitee.addOrbiter( orbiter )
        }

        return orbits
    }
}

private class SpaceObject( val name:String )
{
    private val orbiters:MutableList<SpaceObject> = mutableListOf()

    var orbitee:SpaceObject? = null
        private set

    fun addOrbiter( orbiter:SpaceObject ) {
        orbiter.orbitee = this
        orbiters.add( orbiter )
    }

    fun getAddress() : List<SpaceObject> = orbitee?.getAddress()?.plus( this ) ?: listOf( this )

    fun getUniversalCOM() : SpaceObject = orbitee?.getUniversalCOM() ?: this

    fun getSystemOrbitCount() : Int
    {
        return getUniversalCOM().getChildOrbitCount( 0 )
    }

    private fun getChildOrbitCount( parentCount:Int ) : Int = parentCount + orbiters.sumBy { o -> o.getChildOrbitCount( parentCount + 1 ) }
}