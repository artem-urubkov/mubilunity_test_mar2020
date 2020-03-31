package com.auru.mobilunity

import java.util.*

fun main() {
    val `in` = Scanner(System.`in`)
    val number = `in`.nextInt()
    System.out.printf("%d %d\n", number / 10, number % 10)
}