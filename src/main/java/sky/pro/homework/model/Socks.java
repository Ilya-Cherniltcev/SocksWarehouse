package sky.pro.homework.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@Table(name = "socks")
public class Socks {
    @Id
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    // color of socks
    private String color;
    // percentage of cotton content in socks (%)
    @Column(name = "cotton_part")
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
