package view

import GameSettingInfo
import Square
import domain.Point

private const val FIRST_INDEX = 0

object Response {

    fun startView(board: Map<Point, Square>, info: GameSettingInfo) {
        println("지뢰찾기 게임 시작")

        for (i in FIRST_INDEX until info.height) {
            for (j in FIRST_INDEX until info.width) {
                val point = Point(i, j)
                print("%s ".format(board.getValue(point).display()))
            }
            println()
        }
    }
}
