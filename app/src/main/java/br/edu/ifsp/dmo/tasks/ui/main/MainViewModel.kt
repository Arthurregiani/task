package br.edu.ifsp.dmo.tasks.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo.tasks.data.model.Task
import br.edu.ifsp.dmo.tasks.data.repository.TasksRepository
import br.edu.ifsp.dmo.tasks.dto.TaskDto

class MainViewModel: ViewModel() {
    private val repository = TasksRepository()

    private val _tasks = MutableLiveData<List<TaskDto>>()
    val tasks: LiveData<List<TaskDto>> = _tasks

    init {
        loadData()
    }

    private fun loadData() {
        val taskList = repository.findAll()
        val taskDtoList = taskList.map{ task: Task -> TaskDto(task.id, task.description, task.isCompleted)
        }

        _tasks.value = taskDtoList
    }

}