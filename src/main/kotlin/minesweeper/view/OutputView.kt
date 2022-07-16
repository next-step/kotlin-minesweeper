package minesweeper.view

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.MineSweeperResult
import minesweeper.domain.MineZone
import minesweeper.domain.Position
import minesweeper.domain.SafeZone
import minesweeper.domain.Zone

object OutputView {
    private const val MINE_ZONE_SYMBOL = "*"
    private const val HIDDEN_SYMBOL = "C"

    fun printInitMineSweeperBoard(mineSweeperBoard: MineSweeperBoard) {
        println("지뢰찾기 게임 시작")
        printCurrentMineSweeperBoard(mineSweeperBoard)
    }

    fun printCurrentMineSweeperBoard(mineSweeperBoard: MineSweeperBoard) {
        val originalZones = mineSweeperBoard.zones.zones
        val zones = mineSweeperBoard.openAllZone()
        val rowLength = zones.keys.asSequence()
            .map { it.x }
            .distinctBy { it }
            .sortedBy { it }
        val columnLength = zones.keys.asSequence()
            .map { it.y }
            .distinctBy { it }
            .sortedBy { it }

        for (row in rowLength) {
            printMineSweeperBoardRow(columnLength, zones, originalZones, row)
        }
    }

    private fun printMineSweeperBoardRow(
        columnLength: Sequence<Int>,
        originalZones: Map<Position, Int>,
        zones: Map<Position, Zone>,
        row: Int
    ) {
        for (column in columnLength) {
            print("${mapToMineZoneOrSafeZone(zones[Position(row, column)]!!, originalZones[Position(row, column)]!!)} ")
        }
        println()
    }

    private fun mapToMineZoneOrSafeZone(zone: Zone, countOfNearMine: Int): String {
        if (zone.isHidden) {
            return HIDDEN_SYMBOL
        }

        return when (zone) {
            is MineZone -> MINE_ZONE_SYMBOL
            is SafeZone -> countOfNearMine.toString()
        }
    }

    fun printResult(result: MineSweeperResult) {
        println(result.message)
    }
}
