import androidx.compose.animation.*
import androidx.compose.animation.core.*
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material.icons.outlined.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

// --- PALETA DE COLORES PREMIUM ---
val MedicalBlue = Color(0xFF2563EB)
val HealthGreen = Color(0xFF10B981)
val MedicalBg = Color(0xFFF9FAFB)
val TextDark = Color(0xFF111827)
val TextGrey = Color(0xFF4B5563)
val BorderColor = Color(0xFFE5E7EB)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorProfileScreen() {
    val scrollState = rememberScrollState()
    
    Scaffold(
        containerColor = MedicalBg,
        topBar = {
            TopAppBar(
                title = { Text("Perfil Médico", fontWeight = FontWeight.Bold, fontSize = 18.sp) },
                navigationIcon = {
                    IconButton(onClick = { /* Back */ }) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Regresar")
                    }
                },
                actions = {
                    IconButton(onClick = { /* Share */ }) {
                        Icon(Icons.Outlined.Share, contentDescription = "Compartir")
                    }
                    IconButton(onClick = { /* Favorite */ }) {
                        Icon(Icons.Outlined.FavoriteBorder, contentDescription = "Favorito")
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White,
                    titleContentColor = TextDark
                ),
                modifier = Modifier.shadow(4.dp)
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .verticalScroll(scrollState)
        ) {
            DoctorHeaderCard()
            CtaSection()
            
            Column(modifier = Modifier.padding(horizontal = 20.dp)) {
                AboutSection()
                SpecialtiesSection()
                ExperienceSection()
                ServicesSection()
                ScheduleSection()
                LocationSection()
                ReviewsSection()
                FaqSection()
                MedicalFooter()
                Spacer(modifier = Modifier.height(40.dp))
            }
        }
    }
}

@Composable
fun SectionHeader(title: String, actionText: String? = null) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = title,
            style = MaterialTheme.typography.titleLarge,
            fontWeight = FontWeight.Bold,
            color = TextDark
        )
        if (actionText != null) {
            Text(
                text = actionText,
                style = MaterialTheme.typography.labelLarge,
                color = MedicalBlue,
                fontWeight = FontWeight.SemiBold,
                modifier = Modifier.clickable { /* Action */ }
            )
        }
    }
}

@Composable
fun DoctorHeaderCard() {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(20.dp),
        shape = RoundedCornerShape(24.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Brush.verticalGradient(
                        colors = listOf(MedicalBlue.copy(alpha = 0.05f), Color.White)
                    )
                )
                .padding(24.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Box(contentAlignment = Alignment.BottomEnd) {
                    // Placeholder para foto
                    Surface(
                        modifier = Modifier
                            .size(110.dp)
                            .clip(CircleShape)
                            .border(3.dp, Color.White, CircleShape),
                        color = Color.LightGray
                    ) {
                        // Image(...) 
                    }
                    Surface(
                        modifier = Modifier.size(28.dp),
                        shape = CircleShape,
                        color = MedicalBlue,
                        border = BorderBorder(2.dp, Color.White)
                    ) {
                        Icon(
                            Icons.Default.Check,
                            contentDescription = "Verificado",
                            tint = Color.White,
                            modifier = Modifier.padding(4.dp)
                        )
                    }
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Text(
                    text = "Dr. Daniel Martin Escobedo",
                    style = MaterialTheme.typography.headlineSmall,
                    fontWeight = FontWeight.Bold,
                    color = TextDark,
                    textAlign = TextAlign.Center
                )
                
                Text(
                    text = "Gastroenterología y Endoscopía",
                    style = MaterialTheme.typography.bodyLarge,
                    color = MedicalBlue,
                    fontWeight = FontWeight.Medium
                )
                
                Spacer(modifier = Modifier.height(12.dp))
                
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Star, "Rating", tint = Color(0xFFFFB800), modifier = Modifier.size(20.dp))
                    Text(
                        text = " 4.9 ",
                        fontWeight = FontWeight.Bold,
                        style = MaterialTheme.typography.bodyLarge
                    )
                    Text(
                        text = "(124 opiniones)",
                        color = TextGrey,
                        style = MaterialTheme.typography.bodyMedium
                    )
                }
                
                Spacer(modifier = Modifier.height(16.dp))
                
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    HeaderInfoItem(Icons.Outlined.LocationOn, "Guadalajara")
                    HeaderInfoItem(Icons.Outlined.WorkOutline, "12+ años")
                    HeaderInfoItem(Icons.Outlined.CheckCircle, "Disponible")
                }
            }
        }
    }
}

@Composable
fun HeaderInfoItem(icon: ImageVector, label: String) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Icon(icon, null, tint = TextGrey, modifier = Modifier.size(20.dp))
        Spacer(modifier = Modifier.height(4.dp))
        Text(label, style = MaterialTheme.typography.labelMedium, color = TextGrey)
    }
}

@Composable
fun CtaSection() {
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp)
            .padding(bottom = 24.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Button(
            onClick = { /* Agendar */ },
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            shape = RoundedCornerShape(16.dp),
            colors = ButtonDefaults.buttonColors(containerColor = MedicalBlue),
            elevation = ButtonDefaults.buttonElevation(defaultElevation = 4.dp)
        ) {
            Icon(Icons.Default.DateRange, null)
            Spacer(modifier = Modifier.width(8.dp))
            Text("Agendar Cita", fontSize = 16.sp, fontWeight = FontWeight.Bold)
        }
        
        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(12.dp)) {
            SecondaryCtaButton(
                icon = Icons.Default.Phone, 
                label = "Llamar", 
                modifier = Modifier.weight(1f),
                color = TextDark
            )
            SecondaryCtaButton(
                icon = Icons.Default.Chat, 
                label = "WhatsApp", 
                modifier = Modifier.weight(1f),
                color = HealthGreen
            )
        }
    }
}

@Composable
fun SecondaryCtaButton(icon: ImageVector, label: String, modifier: Modifier, color: Color) {
    OutlinedButton(
        onClick = { /* Action */ },
        modifier = modifier.height(50.dp),
        shape = RoundedCornerShape(14.dp),
        border = BorderStroke(1.dp, BorderColor),
        colors = ButtonDefaults.outlinedButtonColors(contentColor = color)
    ) {
        Icon(icon, null, modifier = Modifier.size(18.dp))
        Spacer(modifier = Modifier.width(8.dp))
        Text(label, fontWeight = FontWeight.SemiBold)
    }
}

@Composable
fun AboutSection() {
    SectionHeader("Sobre el Doctor")
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Text(
                text = "Especialista en enfermedades del aparato digestivo con enfoque en endoscopía terapéutica de alta complejidad. Mi filosofía se basa en la precisión diagnóstica y el trato humano excepcional.",
                style = MaterialTheme.typography.bodyLarge,
                color = TextGrey,
                lineHeight = 24.sp
            )
            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "Enfoque de atención:",
                fontWeight = FontWeight.Bold,
                color = TextDark
            )
            Text(
                text = "• Prevención de Cáncer de Colon\n• Tecnología de mínima invasión\n• Atención integral personalizada",
                color = TextGrey,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(top = 8.dp)
            )
        }
    }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun SpecialtiesSection() {
    SectionHeader("Especialidades")
    FlowRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        val tags = listOf("Colonoscopía", "Endoscopía", "Reflujo", "Gastritis", "Hígado Graso", "Colon Irritable", "Helicobacter Pylori")
        tags.forEach { tag ->
            SpecialtyChip(tag)
        }
    }
}

@Composable
fun SpecialtyChip(label: String) {
    Surface(
        color = MedicalBlue.copy(alpha = 0.08f),
        shape = RoundedCornerShape(100.dp),
        border = BorderStroke(1.dp, MedicalBlue.copy(alpha = 0.2f))
    ) {
        Text(
            text = label,
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 8.dp),
            style = MaterialTheme.typography.labelLarge,
            color = MedicalBlue,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ExperienceSection() {
    SectionHeader("Experiencia y Credenciales")
    Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
        ExperienceItem("Subespecialidad en Endoscopía Gastrointestinal", "Hospital Civil de Guadalajara", "2018 - 2020")
        ExperienceItem("Especialidad en Gastroenterología", "Universidad de Guadalajara", "2014 - 2018")
        ExperienceItem("Certificación Vigente", "Consejo Mexicano de Gastroenterología", "ID: 4529-X")
    }
}

@Composable
fun ExperienceItem(title: String, subtitle: String, date: String) {
    Row(modifier = Modifier.fillMaxWidth(), verticalAlignment = Alignment.Top) {
        Box(
            modifier = Modifier
                .padding(top = 4.dp)
                .size(12.dp)
                .clip(CircleShape)
                .background(MedicalBlue)
        )
        Column(modifier = Modifier.padding(start = 16.dp)) {
            Text(title, fontWeight = FontWeight.Bold, color = TextDark)
            Text(subtitle, style = MaterialTheme.typography.bodyMedium, color = TextGrey)
            Text(date, style = MaterialTheme.typography.labelMedium, color = MedicalBlue)
        }
    }
}

@Composable
fun ServicesSection() {
    SectionHeader("Servicios Médicos", "Ver todos")
    Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
        MedicalServiceCard("Consulta General", "Evaluación diagnóstica completa", "$1,200", "45 min")
        MedicalServiceCard("Endoscopía Diagnóstica", "Procedimiento de alta definición", "Desde $4,500", "1.5 hrs")
    }
}

@Composable
fun MedicalServiceCard(name: String, desc: String, price: String, duration: String) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(48.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(MedicalBlue.copy(alpha = 0.1f)),
                contentAlignment = Alignment.Center
            ) {
                Icon(Icons.Outlined.MedicalServices, null, tint = MedicalBlue)
            }
            Column(modifier = Modifier.weight(1f).padding(horizontal = 16.dp)) {
                Text(name, fontWeight = FontWeight.Bold, color = TextDark)
                Text(desc, style = MaterialTheme.typography.bodySmall, color = TextGrey)
            }
            Column(horizontalAlignment = Alignment.End) {
                Text(price, fontWeight = FontWeight.ExtraBold, color = TextDark)
                Text(duration, style = MaterialTheme.typography.labelSmall, color = TextGrey)
            }
        }
    }
}

@Composable
fun ScheduleSection() {
    SectionHeader("Horarios Disponibles")
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Column(modifier = Modifier.padding(20.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(Icons.Default.AccessTime, null, tint = HealthGreen, modifier = Modifier.size(20.dp))
                Spacer(modifier = Modifier.width(8.dp))
                Text("Próxima cita: Mañana, 09:30 AM", color = HealthGreen, fontWeight = FontWeight.Bold)
            }
            Spacer(modifier = Modifier.height(16.dp))
            HorizontalDivider(color = BorderColor)
            Spacer(modifier = Modifier.height(16.dp))
            ScheduleRow("Lunes - Viernes", "09:00 - 18:00")
            ScheduleRow("Sábados", "10:00 - 14:00")
            ScheduleRow("Domingos", "Cerrado")
        }
    }
}

@Composable
fun ScheduleRow(days: String, hours: String) {
    Row(
        modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(days, color = TextGrey)
        Text(hours, fontWeight = FontWeight.Medium, color = TextDark)
    }
}

@Composable
fun LocationSection() {
    SectionHeader("Ubicación")
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp)
                    .background(Color(0xFFE5E7EB)),
                contentAlignment = Alignment.Center
            ) {
                Text("Mapa Placeholder", color = TextGrey)
            }
            Column(modifier = Modifier.padding(20.dp)) {
                Text("Hospital Puerta de Hierro", fontWeight = FontWeight.Bold, color = TextDark)
                Text("Av. Patria 1355, Zapopan, Jalisco", color = TextGrey)
                Spacer(modifier = Modifier.height(12.dp))
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Outlined.Info, null, tint = MedicalBlue, modifier = Modifier.size(16.dp))
                    Text(" Estacionamiento disponible", style = MaterialTheme.typography.bodySmall, color = TextGrey)
                }
            }
        }
    }
}

@Composable
fun ReviewsSection() {
    SectionHeader("Reseñas", "124 opiniones")
    LazyRow(
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        contentPadding = PaddingValues(bottom = 8.dp)
    ) {
        items(3) {
            ReviewCard()
        }
    }
}

@Composable
fun ReviewCard() {
    Card(
        modifier = Modifier.width(280.dp),
        shape = RoundedCornerShape(20.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(modifier = Modifier.size(40.dp).clip(CircleShape).background(Color.LightGray))
                Spacer(modifier = Modifier.width(12.dp))
                Column {
                    Text("Paciente Anónimo", fontWeight = FontWeight.Bold, fontSize = 14.sp)
                    Row {
                        repeat(5) { Icon(Icons.Default.Star, null, tint = Color(0xFFFFB800), modifier = Modifier.size(14.dp)) }
                    }
                }
            }
            Spacer(modifier = Modifier.height(12.dp))
            Text(
                "Excelente atención. El doctor explicó todo con mucha paciencia y claridad. Muy recomendado.",
                style = MaterialTheme.typography.bodySmall,
                color = TextGrey,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(8.dp))
            Text("Hace 2 semanas", style = MaterialTheme.typography.labelSmall, color = TextGrey.copy(alpha = 0.6f))
        }
    }
}

@Composable
fun FaqSection() {
    SectionHeader("Preguntas Frecuentes")
    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        FaqItem("¿Cuándo acudir al gastroenterólogo?", "Si presentas dolor abdominal persistente, cambios en hábitos intestinales o acidez frecuente.")
        FaqItem("¿Cómo prepararse para una endoscopía?", "Generalmente requiere ayuno de 8 horas. Se le enviarán instrucciones detalladas.")
    }
}

@Composable
fun FaqItem(question: String, answer: String) {
    var expanded by remember { mutableStateOf(false) }
    Card(
        modifier = Modifier.fillMaxWidth().clickable { expanded = !expanded },
        shape = RoundedCornerShape(16.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
        border = BorderStroke(1.dp, BorderColor)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(horizontalArrangement = Arrangement.SpaceBetween, verticalAlignment = Alignment.CenterVertically, modifier = Modifier.fillMaxWidth()) {
                Text(question, fontWeight = FontWeight.SemiBold, modifier = Modifier.weight(1f), color = TextDark)
                Icon(if (expanded) Icons.Default.ExpandLess else Icons.Default.ExpandMore, null)
            }
            if (expanded) {
                Spacer(modifier = Modifier.height(8.dp))
                Text(answer, style = MaterialTheme.typography.bodyMedium, color = TextGrey)
            }
        }
    }
}

@Composable
fun MedicalFooter() {
    Spacer(modifier = Modifier.height(32.dp))
    Column(
        modifier = Modifier.fillMaxWidth().padding(bottom = 20.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text("Cédula Profesional: 1234567", style = MaterialTheme.typography.labelMedium, color = TextGrey)
        Text("Aviso de Publicidad COFEPRIS: 213300202A0123", style = MaterialTheme.typography.labelSmall, color = TextGrey.copy(alpha = 0.7f))
        Spacer(modifier = Modifier.height(16.dp))
        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Text("Privacidad", color = MedicalBlue, style = MaterialTheme.typography.labelLarge)
            Text("Términos", color = MedicalBlue, style = MaterialTheme.typography.labelLarge)
        }
    }
}

import androidx.compose.ui.tooling.preview.Preview

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun DoctorProfilePreview() {
    MaterialTheme {
        DoctorProfileScreen()
    }
}
