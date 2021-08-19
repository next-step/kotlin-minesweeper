package minesweeper.domain

class MineGenerator(private val marker: Marker) {

    fun generateMinePositions(generator: PositionGenerator, countOfMine: Int): HashSet<Position> {
        val sizeOfMarker = marker.size()
        require(countOfMine < sizeOfMarker) { "지뢰는 모든 땅의 개수보다 클 수 없습니다. : $sizeOfMarker"}

        val positions = HashSet<Position>()

        while (positions.size < countOfMine) {
            positions.add(marker.generateMinePosition(generator))
        }

        return positions
    }
}
