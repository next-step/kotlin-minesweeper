package minesweeper

import java.util.Random

const val BLOCK_CHARACTER = "C"
const val MINE_CHARACTER = "*"

class MineSweeperBoard(width: Int, height: Int, mineCount: Int = 0) {

    private val _state: MutableList<MutableList<String>>

    val state: List<List<String>>
        get() = _state.toList()

    val mineCount
        get() = countMine()

    init {
        require(width * height >= mineCount) {
            "지뢰의 개수는 총 블록의 개수보다 많을 수 없습니다."
        }
        val boardState = buildBoard(width, height)
        _state = boardState
        plantMines(mineCount)
    }

    private fun buildBoard(width: Int, height: Int): MutableList<MutableList<String>> {
        return buildList {
            repeat(height) {
                add(
                    buildRows(width)
                )
            }
        }.toMutableList()
    }

    private fun buildRows(width: Int): MutableList<String> {
        return buildList {
            repeat(width) {
                add(BLOCK_CHARACTER)
            }
        }.toMutableList()
    }

    private fun plantMines(plantingMineCount: Int) {
        while (mineCount < plantingMineCount) {
            val height = Random().nextInt(_state.size)
            val width = Random().nextInt(_state[0].size)

            _state[height][width] = MINE_CHARACTER
        }
    }

    private fun countMine(): Int {
        return _state.fold(0) { total, row ->
            total + row.count { block -> block == MINE_CHARACTER }
        }
    }
}
