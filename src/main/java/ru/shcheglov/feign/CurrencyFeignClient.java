package ru.shcheglov.feign;

import feign.RequestLine;
import ru.shcheglov.dto.ValcursDto;

public interface CurrencyFeignClient {

    @RequestLine("GET /XML_daily.asp")
    ValcursDto getXmlDaily();
}
