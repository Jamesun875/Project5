import java.util.ArrayList;
/**
 * The appointment class can format inputs in to an appintment object or a string object. So, the calender class will
 * handle and write each appointment to its own .txtfile.
 *
 * <p>Purdue University -- CS18000 -- Summer 2022 -- Project4</p>
 *
 * @author James Sun
 * @version July 19, 2022
 */
public class Appointment {
    String title;
    int capacity;
    int booking;
    int startTime;
    int endTime;
    String store;
    String calender;

    public Appointment(String title, String calender, String store, int capacity, int booking, int startTime,
                       int endTime) {
        this.title = title;
        this.store = store;
        this.calender = calender;
        this.capacity = capacity;
        this.booking = booking;
        this.startTime = startTime;
        this.endTime = endTime;
    }
    public String getTitle() {
        return title;
    }

    public String getStore() {
        return store;
    }

    public String getCalender() {
        return calender;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getBooking() {
        return booking;
    }

    public int getStartTime() {
        return startTime;
    }

    public int getEndTime() {
        return endTime;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setStore(String store) {
        this.store = store;
    }


    public void setCalender(String calender) {
        this.calender = calender;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public void setBooking(int booking) {
        this.booking = booking;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public String toString() {
        return String.format("%s,%s,%s,%d,%d,%d,%d", getTitle(), getCalender(), getStore(),
                getCapacity(), getBooking(), getStartTime(), getEndTime());
    }
}
