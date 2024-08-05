package br.edu.ifsp.dmo.tasks.data.repository

import br.edu.ifsp.dmo.tasks.data.dao.TaskDao
import br.edu.ifsp.dmo.tasks.data.model.Task
import br.edu.ifsp.dmo.tasks.ui.adapter.TaskAdapter

class TasksRepository {
    private val dao = TaskDao

    fun findAll(): List<Task> {
        return dao.getAllTasks()
    }

    fun findById(id: Long): Task {
        return dao.getTask(id)
    }

    fun onBindViewHolder(holder: TaskAdapter.ViewHolder, position: Int) {

    }

    fun insert(task: Task) {
        dao.addTask(task)
    }

}