package com.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import com.todoapp.ui.theme.Todo
import java.time.Instant
import java.util.Date

object TodoManager {
    private val todoList = mutableListOf<Todo>()

    fun getAllTodos(): List<Todo> {
        return todoList
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addTodo(title: String) {
        todoList.add(
            Todo(
                System.currentTimeMillis().toInt(), title, Date.from(Instant.now())
            )
        )
    }

    @RequiresApi(Build.VERSION_CODES.N)
    fun deleteTodo(id: Int) {
        todoList.removeIf { todo ->
            todo.id == id
        }
    }
}