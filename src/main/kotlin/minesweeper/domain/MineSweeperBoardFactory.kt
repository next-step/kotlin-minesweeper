package minesweeper.domain

import kotlin.properties.Delegates

fun factory(block: MineSweeperBoardBuilder.() -> Unit): MineSweeperBoard {
    return MineSweeperBoardBuilder().apply(block).build()
}

class MineSweeperBoardBuilder {
    private var height: Height by Delegates.notNull()
    private var width: Width by Delegates.notNull()
    private var countOfMine: CountOfMine by Delegates.notNull()
    private lateinit var strategy: (width: Int, height: Int, countOfMine: Int) -> Map<Position, Zone>

    fun height(value: Int) {
        height = Height(value)
    }

    fun width(value: Int) {
        width = Width(value)
    }

    fun countOfMine(value: Int) {
        countOfMine = CountOfMine(value)
    }

    fun strategy(value: (width: Int, height: Int, countOfMine: Int) -> Map<Position, Zone>) {
        strategy = value
    }

    fun build(): MineSweeperBoard {
        val zoneArea = height.value * width.value
        require(zoneArea >= countOfMine.value) { "지뢰 갯수는 총 면적보다 클 수 없습니다. 총 면적 = $zoneArea, 지뢰 갯수 = $countOfMine" }

        return MineSweeperBoard(
            strategy.invoke(width.value, height.value, countOfMine.value)
        )
    }
}
