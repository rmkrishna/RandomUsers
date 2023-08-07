package com.abitztech.randomusers.ui.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.abitztech.randomusers.data.model.User
import com.abitztech.randomusers.ui.components.AZLoadingDialog

@Composable
fun UsersScreen(goToDetails: (String) -> Unit) {
    val viewModel: UsersViewModel = hiltViewModel()
    viewModel.setEvent(UsersEvent.GetUsers)
    UsersScreen(goToDetails, viewModel)
}

@Composable
fun UsersScreen(goToDetails: (String) -> Unit, viewModel: UsersViewModel) {
    val uiState by remember { viewModel.uiState }.collectAsState()
//    val effect by remember { viewModel.effect }.collectAsState(initial = UsersEffect.None)

    val (pleaseWaitDialog, showPleaseWaitDialog) = remember { mutableStateOf(false) }
    showPleaseWaitDialog(uiState.isLoading)

    Scaffold(topBar = { UsersAppbar() }) {
        Surface(modifier = Modifier.padding(it)) {
            // For Pagination we can use, androidx.paging:paging-compose, we have count and page in api
            UsersView(goToDetails, uiState.users)
        }
    }

    if (pleaseWaitDialog) {
        AZLoadingDialog {
            showPleaseWaitDialog(false)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UsersAppbar() {
    Surface(shadowElevation = 5.dp) {
        TopAppBar(
            title = {
                Column(
                    modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start
                ) {
                    Text(
                        text = "Users",
                        style = MaterialTheme.typography.titleLarge,
                    )
                }
            },
            navigationIcon = {

            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(),
        )
    }
}

@Composable
fun UsersView(goToDetails: (String) -> Unit, randomUsers: List<User>) {
    LazyColumn {
        randomUsers.forEach {
            item { UserCard(goToDetails, it) }
        }
    }
}

@Composable
private fun UserCard(goToDetails: (String) -> Unit, user: User) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .padding(10.dp)
            .clickable { goToDetails(user.id) }
            .wrapContentHeight(align = Alignment.Top),
        shape = RoundedCornerShape(10.dp),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            UserProfileImage(user.thumbnailPicture)
            UserListDetails(user)
        }
    }
}

@Composable
fun UserListDetails(user: User) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 16.dp)
    ) {
        Text(
            text = "${user.firstName} ${user.lastName}",
            style = MaterialTheme.typography.titleMedium
        )
        Spacer(modifier = Modifier.size(size = 4.dp))
        Text(text = user.email, style = MaterialTheme.typography.bodySmall)
    }
}

@Composable
fun UserProfileImage(thumbnail: String?) {
    Box(
        modifier = Modifier
            .padding(16.dp)
            .size(60.dp)
            .clip(RoundedCornerShape(10.dp))
    ) {
        Image(
            painter = rememberAsyncImagePainter(thumbnail),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.matchParentSize()
        )
    }
}