package com.todoapp

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.todoapp.ui.theme.Todo
import java.text.SimpleDateFormat
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun TodoListPage(viewModel: TodoViewModel) {
    val todoList by viewModel.todoList.observeAsState()

    var inputText by remember {
        mutableStateOf("")
    }

    Column(
        modifier = Modifier
            .fillMaxHeight()
            .padding(8.dp)
    ) {

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            OutlinedTextField(value = inputText, onValueChange = {
                inputText = it
            }, modifier = Modifier
                .fillMaxWidth(0.75f)
                .padding(top = 30.dp))
            Button(onClick = {
                viewModel.addTodo(title = inputText)
                inputText = ""
            }, modifier = Modifier.padding(vertical = 30.dp)) {
                Text(text = "Add")
            }
        }

        todoList?.let {
            LazyColumn(
                content = {
                    itemsIndexed(it) { index, item ->
                        TodoItem(item = item) {
                            viewModel.deleteTodo(item.id)
                        }
                    }
                }
            )
        } ?: Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            text = "No items yet",
            fontSize = 16.sp
        )
    }
}

@Composable
fun TodoItem(item: Todo, onDelete: () -> Unit) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(MaterialTheme.colorScheme.primary)
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column(
            modifier = Modifier.weight(1f)
        ) {
            Text(
                text = SimpleDateFormat("HH:mm aa dd/mm", Locale.ENGLISH).format(item.createdAt),
                fontSize = 10.sp,
                color = Color.LightGray
            )
            Text(text = item.title, fontSize = 20.sp, color = Color.White)
        }
        IconButton(onClick = onDelete) {
            Icon(
                painter = painterResource(id = R.drawable.ic_delete),
                contentDescription = "Delete",
                tint = Color.White
            )
        }
    }
}