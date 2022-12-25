package minesweeper.domain.button

import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions

class Buttons private constructor(
    private val buttonGraph: List<List<Button>>
) : List<List<Button>> by buttonGraph {

    companion object {
        fun of(height: Int, width: Int, totalMineCount: Int): Buttons {
            val minePositions =
                randomGenerateMinePositions(height, width, totalMineCount)

            val buttonGraph = List(height) { row ->
                List(width) { col ->
                    if (minePositions.havePosition(row, col))
                        Mine.of(row, col)
                    else
                        NoMine.of(row, col)
                }
            }

            return Buttons(buttonGraph)
        }
    }
}

fun randomGenerateMinePositions(height: Int, width: Int, totalMineCount: Int): Positions {
    return Positions(
        listOf((0 until height).toList(), (0 until width).toList()).cartesianProduct()
            .shuffled()
            .take(totalMineCount)
            .map { Position(it[0], it[1]) }
    )
}

private fun <T> Collection<Iterable<T>>.cartesianProduct(): List<List<T>> =
    if (isEmpty()) emptyList()
    else drop(1)
        .fold(first().map(::listOf)) { acc, iterable ->
            acc.flatMap { list ->
                iterable.map(list::plus)
            }
        }
