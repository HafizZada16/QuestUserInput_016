package com.example.questuserinput_016.ui.theme

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.i18n.DateTimeFormatter
import java.time.LocalDate
import java.util.Calendar

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormTampilan() {
    val gradientBrush = Brush.verticalGradient(
        colors = listOf(Color(0xFFE0F7FA), Color(0xFFA5D6A7), Color(0xFFC8E6C9))
    )

    Scaffold { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(gradientBrush)
                .padding(padding)
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            FormRegistrasi()
        }
    }
}

@SuppressLint("UnrememberedMutableState")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormRegistrasi() {
    var nama by rememberSaveable { mutableStateOf("") }
    var kotaAsal by rememberSaveable { mutableStateOf("") }
    var tanggalLahir by rememberSaveable { mutableStateOf("") }
    var umur by rememberSaveable { mutableStateOf("") }
    var jenisKelaminOptions = listOf("Laki-laki","Perempuan")
    var jenisKelamin by rememberSaveable { mutableStateOf("") }
    var isSetuju by rememberSaveable { mutableStateOf(false) }
    var showSuccessDialog by rememberSaveable { mutableStateOf(false) }
    val context = LocalContext.current

    val isFormValid by derivedStateOf {
        nama.isNotBlank() &&
                kotaAsal.isNotBlank() &&
                tanggalLahir.isNotBlank() &&
                jenisKelamin.isNotBlank() &&
                isSetuju
    }

    val calendar = Calendar.getInstance()
    val datePickerDialog = DatePickerDialog(
        context,
        { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
            val selectedDate = LocalDate.of(year, month + 1, dayOfMonth)
            tanggalLahir = selectedDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            umur = calculateAge(selectedDate).toString()
        },
        calendar.get(Calendar.YEAR),
        calendar.get(Calendar.MONTH),
        calendar.get(Calendar.DAY_OF_MONTH)
    )

}

fun calculateAge(selectedDate: LocalDate) {}
