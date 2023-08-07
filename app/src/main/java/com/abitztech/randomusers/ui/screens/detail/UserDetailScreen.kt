package com.abitztech.randomusers.ui.screens.detail

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.abitztech.randomusers.data.model.User
import com.abitztech.randomusers.ui.components.AZLoadingDialog
import com.abitztech.randomusers.ui.screens.users.UsersAppbar

@Composable
fun UserDetailScreen(id: String) {
    val viewModel: UserDetailViewModel = hiltViewModel()
    viewModel.setEvent(UserDetailEvent.GetUser(id))
    UserDetailScreen(viewModel)

}

@Composable
fun UserDetailScreen(viewModel: UserDetailViewModel) {
    val uiState by remember { viewModel.uiState }.collectAsState()
//    val effect by remember { viewModel.effect }.collectAsState(initial = UserDetailEffect.None)

    val (pleaseWaitDialog, showPleaseWaitDialog) = remember { mutableStateOf(false) }
    showPleaseWaitDialog(uiState.isLoading)

    Scaffold(topBar = { UsersAppbar() }) {
        Surface(modifier = Modifier.padding(it)) {
            if (uiState.user != null) {
                UserDetailView(uiState.user)
            }
        }
    }

    if (pleaseWaitDialog) {
        AZLoadingDialog {
            showPleaseWaitDialog(false)
        }
    }
}

@Composable
fun UserDetailView(user: User?) {
    Column(
        modifier = Modifier
            .wrapContentSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = rememberAsyncImagePainter(user?.largePicture),
            contentDescription = null,
            modifier = Modifier
                .size(100.dp)
                .clip(RoundedCornerShape(10))
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "${user?.firstName} ${user?.lastName}",
            style = MaterialTheme.typography.titleMedium
        )
        UserDetailItem(user?.email)
        UserDetailItem(user?.phone)
        UserDetailItem(user?.cellPhone)
    }
}

@Composable
fun UserDetailItem(value: String?) {
    value?.let { Text(text = it, style = MaterialTheme.typography.bodySmall) }
}