package domain.game

import domain.map.MapCapture

sealed class OpenResult {

    abstract val isGameFinished: Boolean

    object MineOpened : OpenResult() {

        override val isGameFinished = true
    }

    data class GroundOpened(val mapCapture: MapCapture) : OpenResult() {

        override val isGameFinished = false
    }

    object AllMineFound : OpenResult() {

        override val isGameFinished = true
    }
}
