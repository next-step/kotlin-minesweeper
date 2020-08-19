package view

import domain.block.Block
import domain.field.MineField
import java.util.LinkedList
import java.util.Queue

fun printGameStart() {
    println("지뢰찾기 게임 시작")
}

fun printMineField(mineField: MineField) {
    val normalBlockQueue: Queue<Block> = LinkedList<Block>(mineField.getBlocks())
    repeat(mineField.getHeight()) {
        repeat(mineField.getWidth()) { printBlock(normalBlockQueue.poll()) }
        println()
    }
}

fun printBlock(block: Block) = when {
    block.isClose -> print("□ ")
    block.isMine() -> print("* ")
    else -> print("${block.getMinesCount()} ")
}
