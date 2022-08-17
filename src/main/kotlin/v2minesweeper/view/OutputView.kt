package v2minesweeper.view

import v2minesweeper.domain.MineSweeperBoard
import v2minesweeper.domain.MineZone
import v2minesweeper.domain.Position
import v2minesweeper.domain.SafeZone
import v2minesweeper.domain.Zone

object OutputView {
    private const val MINE_ZONE_SYMBOL = "*"
    private const val HIDDEN_SYMBOL = "C"

    fun printInitMineSweeperBoard(mineSweeperBoard: MineSweeperBoard) {
        println("지뢰찾기 게임 시작")
        printCurrentMineSweeperBoard(mineSweeperBoard)
    }

    private fun printCurrentMineSweeperBoard(mineSweeperBoard: MineSweeperBoard) {
        val zones = mineSweeperBoard.zones.values
        val rowLength = zones.keys.asSequence()
            .map { it.value.first }
            .distinctBy { it }
            .sortedBy { it }

        val columnLength = zones.keys.asSequence()
            .map { it.value.second }
            .distinctBy { it }
            .sortedBy { it }

        for (row in rowLength) {
            printMineSweeperBoardRow(columnLength, zones, row)
        }
    }

    private fun printMineSweeperBoardRow(
        columnLength: Sequence<Int>,
        zones: Map<Position, Zone>,
        row: Int
    ) {
        for (column in columnLength) {
            print("${mapToMineZoneOrSafeZone(zones[Position(row to column)]!!)} ")
        }
        println()
    }

    private fun mapToMineZoneOrSafeZone(zone: Zone): String {
        if (zone.isHidden) {
            return HIDDEN_SYMBOL
        }

        return when (zone) {
            is MineZone -> MINE_ZONE_SYMBOL
            is SafeZone -> HIDDEN_SYMBOL
        }
    }

    // fun printResult(result: MineSweeperResult) {
    //     println(result.message)
    // }
}
