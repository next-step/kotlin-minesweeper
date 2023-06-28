package minesweeper.domain

class MineSweeperMap(
    val lines: List<Line>
) {
    companion object {
        fun of(height: Int, width: Int, mines: Int): MineSweeperMap {

            require(mines <= height * width) { println("지뢰는 지도 크기 보다 작아야 합니다.") }
            val converter = SequenceConverter(width)
            val pointOfMines: List<Sequence> = Sequence.generate(maxNotContains = width * height, count = mines)

            val lines = (0 until height).map { y ->
                val points = (0 until width).map { x ->
                    val sequence = converter.sequence(Point(x, y))
                    if (pointOfMines.contains(sequence)) MinePoint(x, y)
                    else ClearPoint(x, y)
                }
                Line(points)
            }

            return MineSweeperMap(lines)
        }
    }
}
