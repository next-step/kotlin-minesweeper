package domain.game

import domain.map.MapCapture

sealed class OpenResult {

    object MineOpened : OpenResult()

    data class GroundOpened(val mapCapture: MapCapture) : OpenResult()

    object AllMineFound : OpenResult()
}
