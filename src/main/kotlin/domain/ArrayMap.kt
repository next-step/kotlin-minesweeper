package domain

/**
 * 지뢰찾기 맵은 이중 배열의 형태
 * 첫 번째 (outer) List는 y축
 * 두 번째 (inner) List는 x축
 */
class ArrayMap(private val map: Row<Row<Spot>>) {

    val height: Int = map.size()
    val width: Int = map.first().size()

    constructor(map: List<List<Spot>>) : this(Row(map.map { Row(it) }))

    fun getPoint(y: Int, x: Int): Spot {
        require(y in map.indices()) { "잘못된 y값입니다." }
        require(x in map[y].indices()) { "잘못된 x값입니다." }
        return map[y][x]
    }

    fun getPointOrNull(y: Int, x: Int): Spot? =
        map.getOrNull(y)?.getOrNull(x)
}
