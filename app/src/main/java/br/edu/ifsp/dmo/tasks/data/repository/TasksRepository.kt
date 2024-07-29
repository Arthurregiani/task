package br.edu.ifsp.dmo.tasks.data.repository

import br.edu.ifsp.dmo.tasks.data.dao.TaskDao
import br.edu.ifsp.dmo.tasks.data.model.Task

class TasksRepository {
    private val dao = TaskDao

    fun findAll(): List<Task>{
        return dao.getAllTasks()
    }

    fun findById(id: Long): Task{
        return dao.getTask(id)
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

    }

    }

    fun insert(task: Task){
        dao.addTask(task)
    }

}