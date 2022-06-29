package minesweeper.domain

fun factory(block: MineSweeperBoardBuilder.() -> Unit): MineSweeperBoard {
    return MineSweeperBoardBuilder().apply(block).build()
}

class MineSweeperBoardBuilder {
    private lateinit var height: Height
    private lateinit var width: Width
    private lateinit var countOfMine: CountOfMine

    fun height(value: Int) {
        height = Height(value)
    }

    fun width(value: Int) {
        width = Width(value)
    }

    fun countOfMine(value: Int) {
        countOfMine = CountOfMine(value)
    }

    fun build(): MineSweeperBoard {
        val zoneArea = height.value * width.value
        require(zoneArea >= countOfMine.value) { "지뢰 갯수는 총 면적보다 클 수 없습니다. 총 면적 = $zoneArea, 지뢰 갯수 = $countOfMine" }
        val safeZones = List(zoneArea - countOfMine.value) { Zone(false) }
        val mineZones = List(countOfMine.value) { Zone(true) }

        return MineSweeperBoard(
            (mineZones + safeZones).shuffled()
                .chunked(width.value)
        )
    }
}
