package sky.pro.homework.dto;
import lombok.Data;

@Data
public class SocksDto {
    // color of socks
    private String color;
    // percentage of cotton content in socks (%)
    private int cottonPart;
    private int quantity;
}
