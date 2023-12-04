package domain

class ArrayMap(
    private val list: Row<Spot>
) {

    constructor(listMap: List<List<Spot>>) : this(Row(listMap.map { Column(it) }))

    fun get(point: Point): Spot = list[point.y][point.x]

    fun flatMap(): List<Spot> = list.flatMap { it }

    fun getClosedCount(): Int = flatMap().count { it.isOpen().not() }
}
