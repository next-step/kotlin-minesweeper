package view

import model.Map
import model.Size

object Output {
    fun drawMap(map: Map, width: Size) {
        map.items.toSortedMap(compareBy({ it.y }, { it.x })).entries.forEachIndexed { index, entry ->
            print(entry.value.value)
            print(if (index % width.value == width.value - 1) "\n" else " ")
        }
    }

    fun lose() {
        println("you lose")
    }

    fun win() {
        println("you win")
    }
}
