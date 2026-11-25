import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDateTime;
import java.time.Duration;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

interface Calculavel {
    double calcularValor(int horas); // calcula preço do estacionamento por hora
}