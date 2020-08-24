package model

class MineCount(val value: Number) {
    constructor(value: String) : this(Number(value.toInt()))
    constructor(value: Int) : this(Number(value))
}
