package sky.pro.homework.service;

import sky.pro.homework.dto.SocksDto;
import sky.pro.homework.model.EnumOperations;
public interface SocksService {
    SocksDto addSocks(SocksDto socksDto);
    SocksDto removeSocks(SocksDto socksDto);
    Integer getSocks(String color, EnumOperations operation, int cottonPart);
}
