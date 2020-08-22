package model

class BoardSize(row: LengthOfSide, col: LengthOfSide) {
    val size = row.length * col.length
    val row = row.length
    val col = col.length
}
