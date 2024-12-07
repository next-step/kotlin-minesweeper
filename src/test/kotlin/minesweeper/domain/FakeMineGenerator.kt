package minesweeper.domain

import minesweeper.domain.point.Mine

/**
 * @param mines: 생성하고자 하는 지뢰의 좌표들
 * 특정 좌표에 지뢰를 생성하도록 매개변수화 가능합니다.
 */
class FakeMineGenerator(
    private val mines: List<Pair<Int, Int>> = listOf(),
) : MineGenerator {
    override fun generate(
        height: Height,
        width: Width,
        count: MineCount,
    ): List<Mine> {
        return mines.map { (row, col) -> Mine(row, col) }
    }
}
