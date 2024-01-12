package sky.pro.homework.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.stereotype.Component;
import sky.pro.homework.dto.SocksDto;
import sky.pro.homework.model.Socks;
@Component
@Mapper(componentModel = "spring")
public interface SocksMapper {
    SocksDto toDto(Socks socks);
    @Mapping(target = "id", ignore = true)
    Socks toSocks (SocksDto socksDto);
}
