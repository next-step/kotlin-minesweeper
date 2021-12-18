package minesweeper.domain.block

import minesweeper.domain.directions.MineSearchDirections
import minesweeper.domain.directions.OpenDirections
import java.util.LinkedList
import java.util.Queue

data class Blocks(val blocks: Map<Position, Block>) {

    fun isMinePosition(position: Position): Boolean = blocks.isMine(position)

    fun isEmptyBlocksAllOpened(): Boolean = blocks.values.filter { !it.isMine }.all { it.isOpened() }

    fun findBlock(position: Position): Block = blocks.findBlockByPosition(position)

    fun open(firstPosition: Position): Blocks {
        val queue: Queue<Position> = LinkedList(listOf(firstPosition))
        var blocks = blocks.toMutableMap()
        while (queue.isNotEmpty()) {
            val nowPosition = queue.poll()
            blocks[nowPosition] = blocks.findBlockByPosition(nowPosition).open()
            val adjacentBlocks = blocks.adjacentBlocks(nowPosition)
            val block = blocks.findBlockByPosition(nowPosition)
            val adjacentMineCount = block.adjacentMineCount(adjacentBlocks)
            if (!block.isMine && adjacentMineCount.isEmpty()) {
                OpenDirections.values()
                    .map { it.nextPosition(nowPosition) }
                    .filter { blocks.isOpenable(it) }
                    .forEach { queue.offer(it) }
            }
        }
        return Blocks(blocks.toMap())
    }
}

private fun Map<Position, Block>.findBlockByPosition(position: Position) = getValue(position)

private fun Map<Position, Block>.isMine(position: Position): Boolean = findBlockByPosition(position).isMine

private fun Map<Position, Block>.isOpened(position: Position) = findBlockByPosition(position).isOpened()

private fun Map<Position, Block>.adjacentBlocks(nowPosition: Position) =
    MineSearchDirections.values()
        .map { it.nextPosition(nowPosition) }
        .filter { isContains(it) }
        .map { findBlockByPosition(it) }

private fun Map<Position, Block>.isOpenable(position: Position): Boolean {
    if (!isContains(position) || isMine(position)) {
        return false
    }
    return !isOpened(position)
}

private fun Map<Position, Block>.isContains(position: Position): Boolean = position in keys
