package minesweeper.domain

import minesweeper.domain.mine.Mine
import minesweeper.dto.HeightResult
import minesweeper.dto.NumberOfMinesResult
import minesweeper.dto.WidthResult

data class Minefield(val width: Int, val height: Int, val numberOfMines: Int = 0) {
    private val _mines: MutableList<Mine> = mutableListOf()
    val mines: List<Mine>
        get() = _mines.toList()

    val chunkedBoard: List<List<Mine>>
        get() = _mines.chunked(width)

    init {
        val area = height * width
        val numberOfLands = area - numberOfMines
        val list: MutableList<Mine> = mutableListOf()
        val generatedLands = List(numberOfLands) { Mine.generateLand() }
        list.addAll(generatedLands)
        val generatedMines = List(numberOfMines) { Mine.generateMine() }
        list.addAll(generatedMines)
        _mines.addAll(list.shuffled())
    }

    fun enrollMines(numberOfMinesResult: NumberOfMinesResult): Minefield {
        return this.copy(numberOfMines = numberOfMinesResult.number)
    }

    companion object {
        fun of(widthResult: WidthResult, heightResult: HeightResult): Minefield {
            return Minefield(widthResult.width, heightResult.height)
        }
    }
}
