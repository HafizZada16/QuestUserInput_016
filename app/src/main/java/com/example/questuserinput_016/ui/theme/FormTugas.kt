package com.example.questuserinput_016.ui.theme


import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.widget.DatePicker
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.LocalDate
import java.time.Period
import java.time.format.DateTimeFormatter
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

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White.copy(alpha = 0.9f))
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Form Registrasi",
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.DarkGray
        )
        Spacer(modifier = Modifier.height(24.dp))

        OutlinedTextField(
            value = nama,
            onValueChange = { nama = it },
            label = { Text("Nama Lengkap") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = kotaAsal,
            onValueChange = { kotaAsal = it },
            label = { Text("Kota Asal") },
            modifier = Modifier.fillMaxWidth(),
            singleLine = true
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = tanggalLahir,
            onValueChange = { },
            label = { Text("Tanggal Lahir") },
            readOnly = true,
            trailingIcon = {
                Icon(
                    imageVector = Icons.Default.DateRange,
                    contentDescription = "Pilih Tanggal",
                    modifier = Modifier.clickable { datePickerDialog.show() }
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .clickable { datePickerDialog.show() }
        )
        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = umur,
            onValueChange = { },
            label = { Text("Umur") },
            modifier = Modifier.fillMaxWidth(),
            readOnly = true,
            enabled = false
        )
        Spacer(modifier = Modifier.height(16.dp))

        Text("Jenis Kelamin", modifier = Modifier.fillMaxWidth(), color = Color.Gray)
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            jenisKelaminOptions.forEach { option ->
                RadioButton(
                    selected = (jenisKelamin == option),
                    onClick = { jenisKelamin = option }
                )
                Text(text = option, modifier = Modifier.padding(end = 16.dp))
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { isSetuju = !isSetuju },
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSetuju,
                onCheckedChange = { isSetuju = it }
            )
            Text("Saya setuju dengan syarat dan ketentuan yang berlaku")
        }
        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = {showSuccessDialog = true },
            enabled = isFormValid,
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Text("Submit", fontSize = 16.sp)
        }
    }

    if (showSuccessDialog) {
        AlertDialog(
            onDismissRequest = {showSuccessDialog = false},
            title = {Text("Berhasil")},
            text = {
                Column {
                    Text("Nama: $nama")
                    Text("Kota Asal: $kotaAsal")
                    Text("Tgl Lahir: $tanggalLahir")
                    Text("Umur: $umur")
                    Text("Jenis Kelamin: $jenisKelamin")
                }
            },
            confirmButton = {
                Button(onClick = {showSuccessDialog = false}) {
                    Text("OK")
                }
            }
        )
    }
}

fun calculateAge(birthDate: LocalDate): Int {
    return Period.between(birthDate, LocalDate.now()).years
}

