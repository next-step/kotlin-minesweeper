package domain

class ArrayMap(
    private val list: Row<Spot>
) {

    constructor(listMap: List<List<Spot>>) : this(Row(listMap.map { Column(it) }))

    fun get(point: Point): Spot = list[point.y][point.x]

    fun getHeight(): Int = list.size

    fun getWidth(): Int = list.first().size
}
