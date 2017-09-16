package com.sunshineapp.jmsintegrationadapter.converters;

import com.sunshineapp.jmsintegrationadapter.dto.BuildTaskDto;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.core.convert.converter.Converter;

public class BuildTaskDtoConverter2Bytes implements Converter<BuildTaskDto, byte[]> {

    public byte[] convert(BuildTaskDto buildTaskDto) {
        return SerializationUtils.serialize(buildTaskDto);
    }

}