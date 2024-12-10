package minesweeper.infrastructure

import minesweeper.adapter.MinesweeperInputAdapter
import minesweeper.domain.FieldHeight
import minesweeper.domain.FieldWidth
import minesweeper.domain.MineCount
import minesweeper.view.InputVIew

class ConsoleMinesweeperInputAdapter(private val inputVIew: InputVIew) : MinesweeperInputAdapter {
    override fun fetchFieldWidth(): FieldWidth {
        inputVIew.inputFieldWidth().let {
            return FieldWidth(it.toInt())
        }
    }

    override fun fetchFieldHeight(): FieldHeight {
        inputVIew.inputFieldHeight().let {
            return FieldHeight(it.toInt())
        }
    }

    override fun fetchMineCount(): MineCount {
        inputVIew.inputMineCount().let {
            return MineCount(it.toInt())
        }
    }
}
