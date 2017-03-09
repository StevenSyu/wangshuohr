package tw.com.wangshuo.data;

/**
 * Created by stevenSyu on 10/22/16.
 */

public class Employee {

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Employee{");
        sb.append("uid=").append(uid);
        sb.append(", name='").append(name).append('\'');
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", payRatePerHour='").append(payRatePerHour).append('\'');
        sb.append(", active=").append(active);
        sb.append('}');
        return sb.toString();
    }

    private int uid;
    private String name;
    private String phone;
    private int payRatePerHour;
    private boolean active;

    public int getUid() {
        return uid;
    }

    public void setUid(int uid) {
        this.uid = uid;
    }

    // Required default constructor for Firebase object mapping
    @SuppressWarnings("unused")
    private Employee() {

    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getPayRatePerHour() {
        return payRatePerHour;
    }

    public void setPayRatePerHour(int payRatePerHour) {
        this.payRatePerHour = payRatePerHour;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Employee employee = (Employee) o;

        return uid == employee.uid;

    }

    @Override
    public int hashCode() {
        return uid;
    }
}

