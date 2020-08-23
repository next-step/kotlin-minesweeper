package minesweeper.model

enum class CardinalPoint(val moveUpDown: Int, val moveSide: Int) {
    N(-1, 0),
    NE(-1, 1),
    E(0, 1),
    SE(1, 1),
    S(1, 0),
    SW(1, -1),
    W(0, -1),
    NW(-1, -1);
}
