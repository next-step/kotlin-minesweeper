package minesweeper.domain.coordinate

import minesweeper.common.value.CoordinateValue
import minesweeper.domain.MineCountNumber
import minesweeper.domain.MineCountNumbers
import minesweeper.domain.Mines
import minesweeper.domain.NearMineCount
import minesweeper.domain.Position

sealed interface MineCountingCoordinateSystem : CoordinateSystem {
    val mineCountNumbers: MineCountNumbers
}

class MineCountingCoordinateSystemDecorator(
    private val mineCoordinateSystem: MineCoordinateSystem
) : MineCountingCoordinateSystem, MineCoordinateSystem by mineCoordinateSystem {
    override val mineCountNumbers: MineCountNumbers

    init {
        val mineCountNumberList = buildList {
            coordinate.forEach {
                val mineCountNumber = countNearMine(it, mines)
                this.add(mineCountNumber)
            }
        }

        mineCountNumbers = MineCountNumbers(mineCountNumbers = mineCountNumberList)
    }

    private fun countNearMine(target: Position, mines: Mines): MineCountNumber {
        val nearPosition = getNearPosition(target)

        var count = 0
        nearPosition.forEach {
            if (mines.hasPosition(it)) count++
        }

        return MineCountNumber(position = target, count = NearMineCount(count))
    }

    fun getNearPosition(target: Position): Set<Position> {
        return buildSet {
            val nearCoordinateX = getNearCoordinate(target.x)
            val nearCoordinateY = getNearCoordinate(target.y)

            this.addPosition(nearCoordinateX, nearCoordinateY.prev)
            this.addPosition(nearCoordinateX, nearCoordinateY.current)
            this.addPosition(nearCoordinateX, nearCoordinateY.next)
            this.remove(target)
        }
    }

    private fun getNearCoordinate(target: CoordinateValue): NearCoordinate {
        return NearCoordinate(CoordinateValue(target.value - 1), CoordinateValue(target.value), CoordinateValue(target.value + 1))
    }

    private fun MutableSet<Position>.addPosition(nearCoordinateX: NearCoordinate, targetCoordinate: CoordinateValue) {
        coordinate.find { it == Position(nearCoordinateX.prev, targetCoordinate) }?.let { this.add(it) }
        coordinate.find { it == Position(nearCoordinateX.current, targetCoordinate) }?.let { this.add(it) }
        coordinate.find { it == Position(nearCoordinateX.next, targetCoordinate) }?.let { this.add(it) }
    }

    data class NearCoordinate(
        val prev: CoordinateValue,
        val current: CoordinateValue,
        val next: CoordinateValue
    )
}