package model

class MineColumn(val blocks: List<Block>) {
    constructor(width: Number) : this(MutableList(width.value) { Block() }.toList())
}
