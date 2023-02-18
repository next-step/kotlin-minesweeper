package views

import domains.GameMap
import domains.GameSize
import domains.Position

object Output {
    fun showMap(gameSize: GameSize, gameMap: GameMap) {
        println("지뢰찾기 게임 시작")
        (0 until gameSize.width).forEach { width ->
            (0 until gameSize.height).forEach { height ->
                val block = gameMap.getBlockByPosition(Position(width, height))
                print("${block.marker} ")
            }
            println()
        }
    }
}
