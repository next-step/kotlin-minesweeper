package minesweeper.view

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.Zone

object OutputView {
    private const val MINE_ZONE_SYMBOL = "*"
    private const val SAFE_ZONE_SYMBOL = "C"

    fun printMineSweeperBoard(mineSweeperBoard: MineSweeperBoard) {
        println("지뢰찾기 게임 시작")
        mineSweeperBoard.zones.forEach { println(mapToMineZoneOrSafeZoneSymbols(it)) }
    }

    private fun mapToMineZoneOrSafeZoneSymbols(zones: List<Zone>): String {
        return zones.joinToString(" ") { mapToMineZoneOrSafeZoneSymbol(it) }
    }

    private fun mapToMineZoneOrSafeZoneSymbol(zone: Zone) = when (zone.hasMine) {
        true -> MINE_ZONE_SYMBOL
        false -> SAFE_ZONE_SYMBOL
    }
}
