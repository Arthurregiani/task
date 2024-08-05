package br.edu.ifsp.dmo.tasks.data.dao

import br.edu.ifsp.dmo.tasks.data.model.Task

//simulando um dao
object TaskDao {
    private val tasks = mutableListOf<Task>()

    fun getAllTasks(): List<Task> = tasks

    fun addTask(task: Task) {
        tasks.add(task)
    }

    fun getTask(id: Long): Task {
        return tasks.stream().filter { item -> item.id == id }.findFirst().orElse(null)
    }

}