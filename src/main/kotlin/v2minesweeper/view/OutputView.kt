package v2minesweeper.view

import v2minesweeper.domain.MineSweeperBoard
import v2minesweeper.domain.MineZone
import v2minesweeper.domain.SafeZone
import v2minesweeper.domain.Zone

object OutputView {
    private const val MINE_ZONE_SYMBOL = "*"
    private const val HIDDEN_SYMBOL = "C"

    fun printInitMineSweeperBoard(mineSweeperBoard: MineSweeperBoard) {
        println("지뢰찾기 게임 시작")
        printCurrentMineSweeperBoard(mineSweeperBoard)
    }

    fun printCurrentMineSweeperBoard(mineSweeperBoard: MineSweeperBoard) {
        val zones = mineSweeperBoard.zones.values
        val rowLength = zones.keys.asSequence()
            .map { it.first }
            .distinctBy { it }
            .sortedBy { it }

        val columnLength = zones.keys.asSequence()
            .map { it.second }
            .distinctBy { it }
            .sortedBy { it }

        for (row in rowLength) {
            printMineSweeperBoardRow(columnLength, zones, row)
        }
    }

    private fun printMineSweeperBoardRow(
        columnLength: Sequence<Int>,
        zones: Map<Pair<Int, Int>, Zone>,
        row: Int
    ) {
        for (column in columnLength) {
            print("${mapToMineZoneOrSafeZone(zones[row to column]!!)} ")
        }
        println()
    }

    private fun mapToMineZoneOrSafeZone(zone: Zone): String {
        return when (zone) {
            is MineZone -> MINE_ZONE_SYMBOL
            is SafeZone -> HIDDEN_SYMBOL
        }
    }

    // fun printResult(result: MineSweeperResult) {
    //     println(result.message)
    // }
}
