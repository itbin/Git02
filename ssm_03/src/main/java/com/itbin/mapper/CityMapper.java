package com.itbin.mapper;

import com.itbin.domain.City;

import java.util.List;

public interface CityMapper {
    List<City> findAll() throws Exception;
}
