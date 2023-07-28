package view

import domain.EmptyElement
import domain.MapElement
import domain.MineMap

object DisplayView {
    private const val SEPARATOR = " "

    fun displayMap(mineMap: MineMap) {
        println("지뢰찾기 게임 시작")
        mineMap.elements.forEach { row ->
            val line = row.joinToString(SEPARATOR) { displayElement(it) }
            println(line)
        }
    }

    private fun displayElement(element: MapElement): String = if (element is EmptyElement) element.mineCnt.toString() else element.displayCharacter
}
