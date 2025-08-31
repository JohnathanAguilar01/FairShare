package com.example.fairshare

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun FairShare(
    modifier: Modifier = Modifier
        .background(MaterialTheme.colorScheme.background)
) {
    Column(
        modifier = modifier
            .fillMaxSize()
    ){
        MainLogo()
        Bottom()
    }

}