package com.itheima.service;

import com.itheima.domain.Product;

import java.util.List;

public interface IProductService {
    public List<Product> findAll(Integer page,Integer pageSize);
    void save(Product product);

    void delete(String[] ids);


    void openProduct(String[] ids);

    void closeProduct(String[] ids);
}
