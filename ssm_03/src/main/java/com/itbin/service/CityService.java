package com.itbin.service;

import com.itbin.domain.City;

import java.util.List;

public interface CityService {
    List<City> findAll() throws Exception;
}
