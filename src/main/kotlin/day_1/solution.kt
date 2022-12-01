package day_1

import java.io.File

fun main(args: Array<String>) {
    System.out.println(getFoodList().max())
    System.out.println(getFoodList().sortedArrayDescending().filterIndexed { index, i -> index < 3 }.sum())
}

fun getFoodList(): IntArray {
    val foodList: IntArray = IntArray(1000)
    var currentElf = 0
    val fileName = "src/main/resources/day_1/input"
    File(fileName).forEachLine {line ->
        if (line.isNotEmpty()) {
            foodList[currentElf] = foodList[currentElf] + line.toInt()
        } else {
            currentElf++
        }
    }

    return foodList
}