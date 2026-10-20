public class Reserva {
    private String guest;
    private Quarto roomType;
    private int stayDuration;
    private float stayValue = 100f;

    public Reserva(String guest, Quarto roomType, int stayDuration){
        this.guest = guest;
        this.roomType = roomType;
        this.stayDuration = stayDuration;
    }

    public String getGuest(){
        return guest;
    }

    public int getDuration(){
        return stayDuration;
    }

    public float getTotalValue(){
        return roomType.getDayValue() * stayDuration;
    }

    @Override
    public String toString() {
        return "Reserva de " + guest + "\nSuíte " + roomType.getName() + "\n" + stayDuration + " diárias" + "\nTotal da reserva: " + getTotalValue();
    }
}