package model

class BoardSize(private val row: LengthOfSide, private val col: LengthOfSide) {

    fun get(): Int = row.length * col.length
}
