package view

import domain.Block
import domain.MineField
import java.util.LinkedList
import java.util.Queue

fun printGameStart() {
    println("지뢰찾기 게임 시작")
}

fun printMineField(mineField: MineField) {
    val normalBlockQueue: Queue<Block> = LinkedList<Block>(mineField.blocks)
    repeat(mineField.getHeight()) {
        repeat(mineField.getWidth()) { printBlock(normalBlockQueue.poll()) }
        println()
    }
}

fun printBlock(block: Block) {
    if (block.isMine()) {
        print("* ")
        return
    }
    print("${block.getMinesCount()} ")
}
