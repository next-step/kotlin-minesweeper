package domain.game

import domain.map.Coordinate
import domain.map.MineMap

class MineSweeperGame(
    private val mineMap: MineMap
) {

    fun open(coordinate: Coordinate): OpenResult {
        if (mineMap[coordinate].isMine()) {
            return OpenResult.MineOpened
        }

        throw NotImplementedError("ground open not implemented")
    }
}
