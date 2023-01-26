package sky.pro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.util.Objects;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Socks")
public class Socks {
    @Id
    @GeneratedValue
    private long id;
    // color of socks
    private String color;
    // percentage of cotton content in socks (%)
    private int cottonPart;
    private int quantity;

    @Override
    public boolean equals(Object o){
        if (this == o)  return true;
        if ((o == null) || Hibernate.getClass(this)
                != Hibernate.getClass(o)) return false;
        Socks s = (Socks) o;
        return id == s.id && color.equals(s.color) && cottonPart == s.cottonPart
                && quantity == s.quantity;
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, color, cottonPart, quantity);
        result = 31 * result;
        return result;
    }
}
