package edu.deepanshu.model


data class ProcessInfo(
    val processId: Int,
    val processName: String,
    var burstTime: Int,
    val arrivalTime: Int,
    var waitingTime: Int,
    var turnAroundTime: Int
)