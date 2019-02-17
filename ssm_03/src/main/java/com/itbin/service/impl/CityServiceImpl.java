package com.itbin.service.impl;

import com.itbin.domain.City;
import com.itbin.mapper.CityMapper;
import com.itbin.service.CityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
@Service
@Transactional
public class CityServiceImpl implements CityService {
    @Autowired
    private CityMapper mapper;
    @Override
    public List<City> findAll() throws Exception {
        return mapper.findAll();
    }
}
