package com.sunshineapp.jmsintegrationadapter.converters;

import com.sunshineapp.jmsintegrationadapter.dto.BuildTaskDto;
import org.apache.commons.lang.SerializationUtils;
import org.springframework.core.convert.converter.Converter;

public class Bytes2BuildTaskDtoConverter implements Converter<byte[], BuildTaskDto> {

    public BuildTaskDto convert(byte[] momMessage) {
        return (BuildTaskDto) SerializationUtils.deserialize(momMessage);
    }

}
