package RestAPI.Fetch;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import org.springframework.stereotype.Component;

@Entity
@Component
public class Home {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer leve;
    String keyboard;
    String accessory;
    Integer amnt;

    public Integer getLeve() {
        return leve;
    }

    public void setLeve(Integer leve) {
        this.leve = leve;
    }

    public String getKeyboard() {
        return keyboard;
    }

    public void setKeyboard(String keyboard) {
        this.keyboard = keyboard;
    }

    public String getAccessory() {
        return accessory;
    }

    public void setAccessory(String accessory) {
        this.accessory = accessory;
    }

    public Integer getAmnt() {
        return amnt;
    }

    public void setAmnt(Integer amnt) {
        this.amnt = amnt;
    }

    @Override
    public String toString() {
        return "Home{" +
                "leve=" + leve +
                ", keyboard='" + keyboard + '\'' +
                ", accessory='" + accessory + '\'' +
                ", amnt=" + amnt +
                '}';
    }
}
