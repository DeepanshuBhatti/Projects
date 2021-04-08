package edu.deepanshu.algorithms

import edu.deepanshu.model.ProcessInfo

class ShortestJobFirst {

    companion object {
        private const val ZERO = 0
    }

    private var processIdToProcessMap = hashMapOf<Int, ProcessInfo>()
    private var averageWaitingTime: Double = 0.0
    private var averageTurnAroundTime: Double = 0.0
    private var processExecutionList = arrayListOf<String>()

    fun addProcess(processId: Int, processName: String, burstTime: Int, arrivalTime: Int): Boolean {
        if (processIdToProcessMap.containsKey(processId)) {
            return false
        }
        val process = ProcessInfo(processId, processName, burstTime, arrivalTime, ZERO, ZERO)
        processIdToProcessMap[processId] = process
        return true
    }

    fun getAverageWaitingTime(): Double {
        val processList = processIdToProcessMap.values.toList()
        val totalWaitingTime = processList.map { it.waitingTime }.toList().sum()
        averageWaitingTime = (totalWaitingTime.toDouble() / processList.size)
        return averageWaitingTime
    }

    fun calculate() {
        val processList = processIdToProcessMap.values.toList()
        val totalBurstTime = processList.map { it.burstTime }.toList().sum()
        for (i in 0..totalBurstTime) {
            val tempList = processList.filter { it.arrivalTime <= i && it.burstTime > 0 }.sortedBy { it.burstTime }
            val process = tempList.firstOrNull()
            if (process != null) {
                process.burstTime -= 1
                process.turnAroundTime = i - process.arrivalTime + 1
                processExecutionList.add(process.processName)
                for (tempProcess in tempList) {
                    if (tempProcess != tempList.first()) {
                        tempProcess.waitingTime = tempProcess.waitingTime.plus(1)
                    }
                }
            }
        }
    }

    fun getAverageTurnAroundTime(): Double {
        val processList = processIdToProcessMap.values.toList()
        val totalTurnAroundTime = processList.map { it.turnAroundTime }.toList().sum()
        averageTurnAroundTime = (totalTurnAroundTime.toDouble() / processList.size)
        return averageTurnAroundTime
    }

    fun getGanttChart(): String {
        var i = 0
        var ganttChart = "$i "
        var previous = ""
        for (process in processExecutionList) {
            if (previous != process) {
                ganttChart = "$ganttChart $i $process"
            }
            i++
            previous = process
        }
        ganttChart = "$ganttChart $i"
        return ganttChart
    }

}