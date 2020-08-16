package view

import domain.Block
import domain.MineField
import java.util.LinkedList
import java.util.Queue

fun printGameStart() {
    println("지뢰찾기 게임 시작")
}

fun printMineField(mineField: MineField) {
    val blockQueue: Queue<Block> = LinkedList<Block>(mineField.blocks)
    repeat(mineField.getHeight()) {
        repeat(mineField.getWidth()) { printBlock(blockQueue.poll().isMine) }
        println()
    }
}

fun printBlock(isMine: Boolean) {
    if (isMine) {
        print("* ")
        return
    }
    print("□ ")
}
