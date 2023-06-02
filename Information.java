package RestAPI.Fetch;

import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Entity(name = "springboot")
@Component
public class Information {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer number;
    String name;
    String nameType;
    Integer quantity;
    Float amount;

    @OneToOne(cascade = CascadeType.ALL)
    @Autowired
    Home home;
    @Lob()
    @Column(columnDefinition = "LONGBLOB")
    byte[] file;

    public byte[] getFile() {
        return file;
    }

    public void setFile(byte[] file) {
        this.file = file;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNameType() {
        return nameType;
    }

    public void setNameType(String nameType) {
        this.nameType = nameType;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Home getHome() {
        return home;
    }

    public void setHome(Home home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "Information{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", nameType='" + nameType + '\'' +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", home=" + home +
                ", file=" + Arrays.toString(file) +
                '}';
    }
}
