package day_8

import java.io.File

const val fileName = "src/main/resources/day_8/input"

fun main(args: Array<String>) {
    println(findVisibleTrees())
    println(calculateBestScenicScore())
}

fun findVisibleTrees(): Int {
    var size = 0
    val lines = readFileAsLinesUsingUseLines(fileName)
    val table: Array<Array<Int>> = Array(99) { Array(99) { 0 } }
    val visibleTrees: Array<Array<Boolean>> = Array(99) { Array(99) { false } }
    var i = 0
    lines.forEach { line ->
        var j = 0
        line.toCharArray().forEach { c ->
            if (i == 0 || i == 98 || j == 0 || j == 98) {
                visibleTrees[i][j] = true
                size++
            }
            table[i][j++] = c.digitToInt()
        }
        i++
    }
    size = getTreeVisibility(size, table, visibleTrees)

    return size
}

fun getTreeVisibility(initialSize: Int, table: Array<Array<Int>>, visibleTrees: Array<Array<Boolean>>): Int {
    var size = initialSize
    for (i in 0..98) {
        var max = 0
        for (ii in 0 until 99) {
            if (ii == 0) {
                max = table[i][ii]
            } else if (table[i][ii] > max && !visibleTrees[i][ii]) {
                visibleTrees[i][ii] = true
                size++
                max = table[i][ii]
            } else if (table[i][ii] > max) {
                max = table[i][ii]
            }
        }
        for (ii in 98 downTo 0) {
            if (ii == 98) {
                max = table[i][ii]
            } else if (table[i][ii] > max && !visibleTrees[i][ii]) {
                visibleTrees[i][ii] = true
                size++
                max = table[i][ii]
            }  else if (table[i][ii] > max) {
                max = table[i][ii]
            }
        }
        for (jj in 0 until 99) {
            if (jj == 0) {
                max = table[jj][i]
            } else if (table[jj][i] > max && !visibleTrees[jj][i]) {
                visibleTrees[jj][i] = true
                size++
                max = table[jj][i]
            }  else if (table[jj][i] > max) {
                max = table[jj][i]
            }
        }
        for (jj in 98 downTo 0) {
            if (jj == 98) {
                max = table[jj][i]
            } else if (table[jj][i] > max && !visibleTrees[jj][i]) {
                visibleTrees[jj][i] = true
                size++
                max = table[jj][i]
            }  else if (table[jj][i] > max) {
                max = table[jj][i]
            }
        }
    }
    return size
}

fun calculateBestScenicScore(): Int {
    var score = 0
    val lines = readFileAsLinesUsingUseLines(fileName)
    val table: Array<Array<Int>> = Array(99) { Array(99) { 0 } }
    var i = 0
    lines.forEach { line ->
        var j = 0
        line.toCharArray().forEach { c ->
            table[i][j++] = c.digitToInt()
        }
        i++
    }


    return getBestScenicScore(table)
}

fun getBestScenicScore(table: Array<Array<Int>>): Int {
    var bestScore = 0
    for (i in 0 until 99) {
        for (ii in 0 until 99) {
            var right = 0
            for (j in ii + 1 until 99) {
                right++
                if (table[i][j] >= table[i][ii]) {
                    break
                }
            }
            var left = 0
            for (j in ii - 1 downTo 0) {
                left++
                if (table[i][j] >= table[i][ii]) {
                    break
                }
            }
            var down = 0
            for (j in i + 1 until 99) {
                down++
                if (table[j][ii] >= table[i][ii]) {
                    break
                }
            }
            var up = 0
            for (j in i - 1 downTo 0) {
                up++
                if (table[j][ii] >= table[i][ii]) {
                    break
                }
            }
            val currentScore = right * left * up * down
            if (currentScore > bestScore) {
                bestScore = currentScore
            }
        }
    }
    return bestScore
}

fun readFileAsLinesUsingUseLines(fileName: String): List<String> = File(fileName).useLines { it.toList() }
