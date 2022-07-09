package domain

import domain.vo.Height
import domain.vo.MineCount
import domain.vo.Point
import domain.vo.Width

class Game private constructor(val board: Board) {

    fun open(coordinate: Coordinate): GameStatus {
        val cell = board.getCell(coordinate)

        return board.open(cell)
    }

    companion object {
        fun of(width: Width, height: Height, mineCount: MineCount): Game {
            val cellCount = width * height
            require(mineCount.value <= cellCount) { "지뢰를 생성할 공간이 부족합니다" }

            return toCoordinates(width, height)
                .zip(mineCount.toShuffledList(cellCount))
                .map { (coordinate, isMine) -> if (isMine) Mine(coordinate) else Empty(coordinate) }
                .chunked(width.value)
                .map(Row::of)
                .let(Board::of)
                .let(::Game)
        }

        private fun MineCount.toShuffledList(cellCount: Int): List<Boolean> =
            (List(value) { true } + List(cellCount - value) { false }).shuffled()

        private fun toCoordinates(width: Width, height: Height): List<Coordinate> =
            (1..height.value).flatMap { y -> (1..width.value).map { x -> Coordinate(Point(x), Point(y)) } }
    }
}
