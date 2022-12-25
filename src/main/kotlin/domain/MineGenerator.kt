package domain

class MineGenerator(
    override val locations: List<Int>,
    override val boardInfo: BoardInfo
) : CellGenerator {

    override fun generate(): List<Mine> {
        return locations.map {
            Mine.from(it / boardInfo.getRow() + 1, it % boardInfo.getRow() + 1)
        }
    }
}
