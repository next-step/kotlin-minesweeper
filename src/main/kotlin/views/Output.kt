package views

import domains.GameMap
import domains.Position

object Output {
    fun startGame() {
        println("지뢰찾기 게임 시작")
    }

    fun showMap(gameMap: GameMap) {
        (0 until gameMap.gameSize.width).forEach { width ->
            (0 until gameMap.gameSize.height).forEach { height ->
                val block = gameMap.getBlockByPosition(Position.fromApplication(width, height))
                print("${block.marker} ")
            }
            println()
        }
    }

    fun clickMineBlock() {
        println("Clicked Mine Block.")
    }

    fun printResult(message: String) {
        println(message)
    }

    fun alreadyOpenPosition() {
        println("already opened position")
    }
}
