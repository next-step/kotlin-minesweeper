package domain

class MineGenerator(
    override val locations: Locations,
    override val boardInfo: BoardInfo
) : CellGenerator {

    override fun generate(): List<Mine> {
        return locations.values.map {
            Mine.from(it / boardInfo.getRow() + 1, it % boardInfo.getRow() + 1)
        }
    }
}
