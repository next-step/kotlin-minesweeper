package minesweeper.domain.tile

@JvmInline
value class Tiles(private val value: List<Tile>) {
    init {
        require(value.isNotEmpty()) { "타일은 적어도 1개 이상이어야 합니다." }
        require(value.size == value.toSet().size) { "타일은 중복될 수 없습니다." }
    }

    fun getListAsString(): List<String> {
        return value.map { tile -> tile.marking.symbol }
    }
}
