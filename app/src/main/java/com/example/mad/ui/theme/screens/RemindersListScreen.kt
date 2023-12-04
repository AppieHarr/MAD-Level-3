package com.example.mad.ui.theme.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.mad.R
import com.example.mad.viewmodel.RemindersViewModel

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun RemindersListScreen(navController: NavHostController, viewModel: RemindersViewModel) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = stringResource(id = R.string.app_name)) }
            )
        },
        content = {
            Column(
                Modifier
                    .fillMaxHeight()
                    .padding(16.dp),
                horizontalAlignment = Alignment.Start
            ) {
                Headers()
                RemindersList(viewModel)
            }
        },
        floatingActionButton = {
            RemindersListScreenFab(navController, viewModel)
        }
    )
}
@Composable
private fun Headers() {
    Text(
        text = stringResource(R.string.reminders),
        modifier = Modifier.padding(bottom = 8.dp),
        style = MaterialTheme.typography.h4,
    )
    Text(
        text = stringResource(R.string.see_below),
        modifier = Modifier.padding(
            bottom = 16.dp
        ),
        style = MaterialTheme.typography.body1,
    )
}

@Composable
private fun RemindersList(viewModel: RemindersViewModel) {
    LazyColumn {
        items(items = viewModel.reminders, itemContent = { item ->
            ReminderCard(item)
        })
    }
}

@Composable
private fun ReminderCard(item: String) {
    Card(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .wrapContentHeight()
            .fillMaxWidth(),
        shape = MaterialTheme.shapes.medium,
        elevation = 8.dp
    ) {
        Text(
            text = item,
            Modifier.padding(16.dp)
        )
    }
}


@Composable
fun RemindersListScreenFab(navController: NavHostController, viewModel: RemindersViewModel) {

    FloatingActionButton(
        onClick = {
            navController.navigate(RemindersScreens.NewReminder.name)
        },
        modifier = Modifier
            .padding(16.dp)
            .wrapContentHeight(),
    ) {
        Icon(Icons.Filled.Add, contentDescription = "Add")
    }
}