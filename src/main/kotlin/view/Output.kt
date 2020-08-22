package view

import model.Map

object Output {
    fun drawMap(map: Map) {
        println("지뢰찾기 게임 시작")
        println(map)
    }
}
