package ru.shcheglov.feign;

import feign.RequestLine;
import ru.shcheglov.dto.ValkursDto;

public interface CurrencyFeignClient {

    @RequestLine("GET /XML_daily.asp")
    ValkursDto getXmlDaily();
}
