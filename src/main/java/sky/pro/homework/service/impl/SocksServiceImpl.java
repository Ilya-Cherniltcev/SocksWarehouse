package sky.pro.homework.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import sky.pro.homework.dto.SocksDto;
import sky.pro.homework.exception.SomeThingWrongException;
import sky.pro.homework.exception.WrongRequestParametersException;
import sky.pro.homework.mapper.SocksMapper;
import sky.pro.homework.model.EnumOperations;
import sky.pro.homework.model.Socks;
import sky.pro.homework.repository.SocksRepository;
import sky.pro.homework.service.SocksService;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class SocksServiceImpl implements SocksService {
    private final SocksRepository socksRepository;
    private final SocksMapper mapper;

    /**
     * Find needed socks. And if they exist add them to DB else create new {@link Socks}
     * @param socksDto After mapping work with it (check, add, create)
     */
    @Override
    public SocksDto addSocks(SocksDto socksDto) {
        Socks socks = mapper.toSocks(socksDto);
        Socks updatedSocks;
        log.info("--- Try to find socks with same color and same cottonPart ---");
        if (socksRepository.findByColorIgnoreCaseAndCottonPart(socks.getColor(), socks.getCottonPart()).isPresent()) {
            log.info("Socks exist. So increase quantity of them");
            Socks temp = socksRepository.findByColorIgnoreCaseAndCottonPart(socks.getColor(), socks.getCottonPart()).get();
            log.info("Were quantity --- {} ---, socks color '{}', cottonPart '{}' ",
                    temp.getQuantity(), temp.getColor(), temp.getCottonPart());
            int updateQuantity = socks.getQuantity() + temp.getQuantity();
            temp.setQuantity(updateQuantity);
            updatedSocks = socksRepository.save(temp);
            log.info("+++ Exist socks are increased. Now quantity is {} +++", updatedSocks.getQuantity());
        } else {
            // if socks don't exist then create new it
            socks.setColor(socks.getColor().toLowerCase());
            updatedSocks = socksRepository.save(socks);
            log.info("New socks are created");
        }
        return mapper.toDto(updatedSocks);
    }

    /**
     * Remove finding socks or decrease its quantity if enough {@link Socks}
     * @param socksDto After mapping work with it (check, remove)
     * @throws WrongRequestParametersException if we can't find socks or quantity is wrong send this Exception
     * @throws SomeThingWrongException Some errors if DB hasn't access
     */
    @Override
    public SocksDto removeSocks(SocksDto socksDto) {
        Socks socks = mapper.toSocks(socksDto);
        int decreaseQuantity = socks.getQuantity();
        Socks updatedSocks;
        log.info("--- Try to find socks with same color and same cottonPart ---");
        if (socksRepository.findByColorIgnoreCaseAndCottonPart(socks.getColor(), socks.getCottonPart()).isPresent()) {
            log.info("Socks exist. So decrease quantity of them");
            Socks temp = socksRepository.findByColorIgnoreCaseAndCottonPart(socks.getColor(), socks.getCottonPart()).get();
            log.info("Were quantity --- {} ---, socks color '{}', cottonPart '{}' ",
                    temp.getQuantity(), temp.getColor(), temp.getCottonPart());
            int updateQuantity = temp.getQuantity() - decreaseQuantity;
            if (updateQuantity < 0) {
                log.warn("Wrong input parameters!");
                throw new WrongRequestParametersException();
            }
            if (updateQuantity == 0) {
                // delete these socks
                updatedSocks = temp;
                socksRepository.delete(temp);
                log.info("* Exist socks are removed *");
            } else {
                temp.setColor(socks.getColor().toLowerCase());
                temp.setQuantity(updateQuantity);
                updatedSocks = socksRepository.save(temp);
                log.info("- Exist socks are decreased. Now quantity is {} -", updatedSocks.getQuantity());
            }
        } else {
            log.warn("Wrong input parameters!");
            throw new WrongRequestParametersException();
        }
        return mapper.toDto(updatedSocks);
    }

    /**
     * Get sum of sock's quantity with request {@link Socks}
     * @param color The color of socks in request
     * @param operation Operation in request {@link EnumOperations}
     * @param cottonPart How much cotton consist in socks (in percents)
     * @throws WrongRequestParametersException if we can't find socks or quantity is wrong send this Exception
     */
    @Override
    public Integer getSocks(String color, EnumOperations operation, int cottonPart) {
        List<Socks> socksList = new ArrayList<>();
        log.info("try to add to list socks with same color and cottonPart..");
        try {
            switch (operation) {
                case moreThan:
                    socksList = socksRepository
                            .findAllByColorIgnoreCaseAndCottonPartGreaterThan(color, cottonPart);
                    break;
                case lessThan:
                    socksList = socksRepository
                            .findAllByColorIgnoreCaseAndCottonPartLessThan(color, cottonPart);
                    break;
                case equal:
                    socksList = socksRepository
                            .findAllByColorIgnoreCaseAndCottonPart(color, cottonPart);
                    break;
            }
        } catch (SomeThingWrongException e) {
            log.warn("Something's wrong");
            System.out.println(e.getMessage());
        }
        if (socksList.isEmpty()) {
            log.warn("Wrong input parameters!");
            throw new WrongRequestParametersException();
        }
        log.info("...successful");
        int quantity = 0;
        for (Socks s : socksList) {
            quantity += s.getQuantity();
        }
        log.info("The quantity of socks with needed parameters is {}", quantity);
        return quantity;
    }
}


