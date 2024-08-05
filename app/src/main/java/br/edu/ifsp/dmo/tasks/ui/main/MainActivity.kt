package br.edu.ifsp.dmo.tasks.ui.main

import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.view.View.OnClickListener
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.edu.ifsp.dmo.tasks.R
import br.edu.ifsp.dmo.tasks.databinding.ActivityMainBinding
import br.edu.ifsp.dmo.tasks.databinding.DialogTaskBinding
import br.edu.ifsp.dmo.tasks.ui.adapter.TaskAdapter
import br.edu.ifsp.dmo.tasks.ui.listener.TaskClickListener


class MainActivity : AppCompatActivity(), OnClickListener, TaskClickListener {

    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: MainViewModel
    private val adapter = TaskAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        setupRecycleView()
        setupObservers()
        setupListeners()

    }

    private fun setupListeners() {
        binding.buttonAdd.setOnClickListener(this)
    }

    private fun setupRecycleView() {
        binding.recyclerTasks.layoutManager = LinearLayoutManager(this)
        binding.recyclerTasks.adapter = adapter
    }

    private fun setupObservers() {
        viewModel.tasks.observe(this, Observer {
            adapter.submitDasset(it)
            adapter.notifyDataSetChanged()
        })
        viewModel.insertedTask.observe(this, Observer {
            val str: String = if (it) {
                "Tarefa inserida com sucesso."
            } else {
                "Erro ao inserir Tarefa"
            }
            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        })

        viewModel.updatedTask.observe(this) {
            val str = if (it) {
                "Estado da tarefa alterado com sucesso"
            } else {
                "Estado da tarefa não foi alterado"
            }
            Toast.makeText(this, str, Toast.LENGTH_LONG).show()
        }
    }

    override fun onClick(view: View?) {
        if (view != null) {
            if (view.id == R.id.button_add) {
                newTask()
            }
        }
    }

    private fun newTask() {
        val dialogView = layoutInflater.inflate(R.layout.dialog_task, null)

        val bindingDialog: DialogTaskBinding = DialogTaskBinding.bind(dialogView)

        val builder = AlertDialog.Builder(this)
            .setView(dialogView).setTitle("Nova Tarefa")
            .setPositiveButton("Salvar", DialogInterface.OnClickListener { dialog, witch ->
                val str = bindingDialog.editDescription.text.toString()
                viewModel.addTAsk(str)
                dialog.dismiss()
            })
            .setNegativeButton("Cancelar", DialogInterface.OnClickListener { dialog, which ->
                dialog.dismiss()
            })
        val dialog = builder.create()
        dialog.show()

    }

    override fun clickDone(position: Int) {
        viewModel.handleDone(position)
    }


}