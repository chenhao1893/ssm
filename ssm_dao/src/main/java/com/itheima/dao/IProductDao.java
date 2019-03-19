package com.itheima.dao;

import com.itheima.domain.Product;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface IProductDao {

    /**
     * 查询所有产品
     * @return
     */
    List<Product> findAll();

    /**
     * 保存产品信息
     * @param product
     */
    void save(Product product);

    /**
     * 根据id查询产品信息
     * @param id
     * @return
     */
    Product findById(String id);

    /**
     * 删除选中
     * @param id
     */
    void delete(String id);


    /**
     * 开启产品状态
     * @param id
     */
    void openProduct(String id);

    void closeProduct(String id);
}
