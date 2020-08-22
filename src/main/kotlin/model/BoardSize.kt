package model

class BoardSize(row: LengthOfSide, col: LengthOfSide) {
    val size = row.length * col.length
    val row = row.length - 1
    val col = col.length - 1
}
