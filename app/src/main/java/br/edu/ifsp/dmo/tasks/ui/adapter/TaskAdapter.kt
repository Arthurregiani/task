package br.edu.ifsp.dmo.tasks.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import br.edu.ifsp.dmo.tasks.R
import br.edu.ifsp.dmo.tasks.databinding.ItemTaskBinding
import br.edu.ifsp.dmo.tasks.dto.TaskDto
import br.edu.ifsp.dmo.tasks.ui.listener.TaskClickListener

class TaskAdapter(val clickListener: TaskClickListener) :
    RecyclerView.Adapter<TaskAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val binding: ItemTaskBinding = ItemTaskBinding.bind(view)
    }

    private var dataset: List<TaskDto> = emptyList()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val taskDto = dataset[position]
        val colorId = if (taskDto.isCompleted) {
            R.color.green
        } else {
            R.color.gray
        }

        val color = ContextCompat.getColor(
            holder.binding.root.context, colorId
        )


        holder.binding.textDescription.text = taskDto.description
        holder.binding.imageDone.setColorFilter(color)
        holder.binding.imageDone.setOnClickListener {
            clickListener.clickDone(position)
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

    fun submitDasset(data: List<TaskDto>) {
        dataset = data
    }

}