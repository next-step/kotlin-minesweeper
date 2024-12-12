package minesweeper.fixture

import minesweeper.domain.FakeMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.Land
import minesweeper.domain.Lands
import minesweeper.domain.MineCount
import minesweeper.domain.Mines
import minesweeper.domain.Point
import minesweeper.domain.Width

/**
 * row = (0 ~ size -1) , col = (0 ~ size -1) 범위의  size * size 만큼 땅 생성
 * 지뢰는 0개임
 */
fun landsFixture(size: Int): Lands {
    val mines =
        Mines(
            FakeMineGenerator(emptyList()),
            Height(1),
            Width(1),
            MineCount(1),
        )
    return Lands(
        (0 until size * size).map {
            val point = Point(it / size, it % size)
            Land(mines, point)
        },
    )
}
