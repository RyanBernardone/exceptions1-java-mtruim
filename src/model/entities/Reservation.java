package model.entities;
import model.exceptions.DomainException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;

    public static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    public Reservation() {
    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) throws DomainException {
        if (!checkOut.after(checkIn)) {
            throw new DomainException("Datas inválidas! Check-in precisa ser feito antes do check-out");
        }
        this.roomNumber = roomNumber;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    public Integer getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber) {
        this.roomNumber = roomNumber;
    }

    public Date getCheckIn() {
        return checkIn;
    }

    public Date getCheckOut() {
        return checkOut;
    }

    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date checkOut) throws DomainException {
        Date now = new Date();
        if (checkIn.before(now) || checkOut.before(now)){
            throw  new DomainException("Datas inválidas! A novas datas são anteriores a atual");
        }
        if (!checkIn.after(now)) {
            throw new DomainException("Datas inválidas! Check-in precisa ser feito antes do check-out");
        }

        this.checkIn = checkIn;
        this.checkOut = checkOut;
    }

    @Override
    public String toString(){
        StringBuilder sb = new StringBuilder();
        sb.append("Quarto: ").append(roomNumber);
        sb.append(", check-in em: ").append(sdf.format(checkIn));
        sb.append(" com check-out em: ").append(sdf.format(checkOut));
        sb.append(". Duração de ").append(duration()).append(" noites");
        return sb.toString();
    }
}
