package entregas.deLaPeñaAlejandro.reto004;

public class Tiempo {
    private int horas;
    private int minutos;
    private int segundos;

    public Tiempo(int horas, int minutos, int segundos) {
        if (!esHoraValida(horas) || !esMinutoValido(minutos) || !esSegundoValido(segundos)) {
            throw new IllegalArgumentException("Tiempo no válido");
        }
        this.horas = horas;
        this.minutos = minutos;
        this.segundos = segundos;
    }

    public boolean equals(Tiempo tiempo) {
        return this.horas == tiempo.horas && this.minutos == tiempo.minutos && this.segundos == tiempo.segundos;
    }

    public boolean antesQue(Tiempo tiempo) {
        if (this.horas < tiempo.horas) return true;
        if (this.horas == tiempo.horas && this.minutos < tiempo.minutos) return true;
        if (this.horas == tiempo.horas && this.minutos == tiempo.minutos && this.segundos < tiempo.segundos) return true;
        return false;
    }

    public boolean despuesDe(Tiempo tiempo) {
        return !this.antesQue(tiempo) && !this.equals(tiempo);
    }

    public int compareTo(Tiempo tiempo) {
        if (this.equals(tiempo)) return 0;
        return this.antesQue(tiempo) ? -1 : 1;
    }

    public Tiempo next() {
        int nuevosSegundos = this.segundos + 1;
        int nuevosMinutos = this.minutos;
        int nuevasHoras = this.horas;

        if (nuevosSegundos >= 60) {
            nuevosSegundos = 0;
            nuevosMinutos++;
            if (nuevosMinutos >= 60) {
                nuevosMinutos = 0;
                nuevasHoras++;
                if (nuevasHoras >= 24) {
                    nuevasHoras = 0;
                }
            }
        }

        return new Tiempo(nuevasHoras, nuevosMinutos, nuevosSegundos);
    }

    public String toString() {
        return String.format("%02d:%02d:%02d", horas, minutos, segundos);
    }

    public Tiempo sumar(Tiempo tiempo) {
        int totalSegundos = this.segundos + tiempo.segundos;
        int totalMinutos = this.minutos + tiempo.minutos + totalSegundos / 60;
        int totalHoras = this.horas + tiempo.horas + totalMinutos / 60;

        totalSegundos %= 60;
        totalMinutos %= 60;
        totalHoras %= 24;

        return new Tiempo(totalHoras, totalMinutos, totalSegundos);
    }

    public boolean esHoraValida(int hora) {
        return hora >= 0 && hora < 24;
    }

    public boolean esMinutoValido(int minuto) {
        return minuto >= 0 && minuto < 60;
    }

    public boolean esSegundoValido(int segundo) {
        return segundo >= 0 && segundo < 60;
    }
}
