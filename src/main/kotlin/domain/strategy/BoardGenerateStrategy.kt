package domain.strategy

import domain.Fields
import domain.Height
import domain.MineCnt
import domain.Width

interface BoardGenerateStrategy {
    fun generate(height: Height, width: Width, mineCnt: MineCnt): Fields
}
