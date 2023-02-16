package com.dgut.dao;

import com.dgut.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockDao {
    // 增加库存
    int addStock(Stock stock);

    // 更新库存
    boolean updateStock(Stock stock);

    // 删除库存
    int deleteStock(int id)throws SQLException;

    // 查询库存
    Stock findStockById(int id)throws SQLException ;

    // 查询所有库存
    List<Stock> findAllStock()throws SQLException ;
}
