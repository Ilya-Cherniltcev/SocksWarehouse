package sky.pro.dto;

import lombok.Data;

@Data
public class SocksDto {
    private long id;
    // color of socks
    private String color;
    // percentage of cotton content in socks (%)
    private int cottonPart;
    private int quantity;
}
