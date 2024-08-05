package com.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.todoapp.ui.theme.Todo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.Instant
import java.util.Date

class TodoViewModel : ViewModel() {
    val todoDao = MainApp.todoDatabase.getTodoDao()
    private var _todoList: LiveData<List<Todo>> = todoDao.getAllTodo()
    val todoList = _todoList

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title: String) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.addTodo(Todo(title = title, createdAt = Date.from(Instant.now())))
        }
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteTodo(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            todoDao.deleteTodo(id)
        }
    }
}