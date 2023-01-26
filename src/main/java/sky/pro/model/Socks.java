package sky.pro.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.Hibernate;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "Socks")
public class Socks {

    private long id;
    // color of socks
    private String color;
    // percentage of cotton content in socks (%)
    private int cottonPart;
    private int quantity;
}
