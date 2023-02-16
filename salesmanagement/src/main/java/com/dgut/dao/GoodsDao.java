package com.dgut.dao;

import com.dgut.entity.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> findAll();
    Goods findById(int id);

    int create(Goods goods);
    int update(Goods goods);
    int delete(int id);
}
