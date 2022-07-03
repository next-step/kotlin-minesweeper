package minesweeper.view

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.Position
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

    private fun mapToMineZoneOrSafeZoneSymbol(zone: Zone) = when (zone.hasMine) {
        true -> MINE_ZONE_SYMBOL
        false -> SAFE_ZONE_SYMBOL
    }
}
