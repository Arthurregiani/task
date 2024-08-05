package br.edu.ifsp.dmo.tasks.ui.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.edu.ifsp.dmo.tasks.data.model.Task
import br.edu.ifsp.dmo.tasks.data.repository.TasksRepository
import br.edu.ifsp.dmo.tasks.dto.TaskDto

class MainViewModel : ViewModel() {
    private val repository = TasksRepository()

    private val _tasks = MutableLiveData<List<TaskDto>>()
    val tasks: LiveData<List<TaskDto>>
        get() {
            return _tasks
        }

    private val _insertedTask = MutableLiveData<Boolean>()
    val insertedTask: LiveData<Boolean> = _insertedTask

    private val _updatedTask = MutableLiveData<Boolean>()
    val updatedTask: LiveData<Boolean> = _updatedTask

    init {
        loadData()
    }

    fun handleDone(position: Int) {
        val dto = _tasks.value?.get(position)
        if (dto != null) {
            val task = repository.findById(dto.id)
            task.isCompleted = !task.isCompleted
            _updatedTask.value = true
            loadData()
        }
    }

    private fun loadData() {
        val taskList = repository.findAll()
        val taskDtoList = taskList.map { task: Task ->
            TaskDto(task.id, task.description, task.isCompleted)
        }

        _tasks.value = taskDtoList
    }

    fun addTAsk(str: String) {
        val task = Task(str, false)
        repository.insert(task)
        _insertedTask.value = true
        loadData()
    }
}