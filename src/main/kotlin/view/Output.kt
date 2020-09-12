package view

import model.Map

object Output {
    fun drawMap(map: Map) {
        println(map)
    }

    fun win() {
        println("you win")
    }
}
