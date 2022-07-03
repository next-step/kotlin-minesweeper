package minesweeper.view

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.MineZone
import minesweeper.domain.Position
import minesweeper.domain.SafeZone
import minesweeper.domain.Zone

object OutputView {
    private const val MINE_ZONE_SYMBOL = "*"
    private const val SAFE_ZONE_SYMBOL = "C"

    fun printMineSweeperBoard(mineSweeperBoard: MineSweeperBoard) {
        println("지뢰찾기 게임 시작")
        val zones = mineSweeperBoard.zones

        var x = 1
        var y = 1
        while (zones.containsKey(Position(x, y))) {
            while (zones.containsKey(Position(x, y))) {
                print("${mapToMineZoneOrSafeZoneSymbol(zones[Position(x, y)]!!)} ")
                y++
            }
            println()
            y = 1
            x++
        }
    }

    private fun mapToMineZoneOrSafeZoneSymbol(zone: Zone) = when (zone) {
        is MineZone -> MINE_ZONE_SYMBOL
        is SafeZone -> SAFE_ZONE_SYMBOL
    }
}
