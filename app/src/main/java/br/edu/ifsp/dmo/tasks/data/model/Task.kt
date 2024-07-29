package br.edu.ifsp.dmo.tasks.data.model

class Task (var description: String, var isCompleted: Boolean) {

    private companion object{
        var lastId: Long = 1L
    }
    var id: Long = 0L

    init {
        lastId +=1
        id = lastId
    }

}