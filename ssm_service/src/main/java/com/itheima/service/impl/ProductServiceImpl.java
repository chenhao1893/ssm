package com.itheima.service.impl;

import com.github.pagehelper.PageHelper;
import com.itheima.dao.IProductDao;
import com.itheima.domain.Product;
import com.itheima.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;

    @Override
    public List<Product> findAll(Integer page, Integer pageSize) {
        PageHelper.startPage(page, pageSize);
        List<Product> products = productDao.findAll();
        return products;
    }

    @Override
    public void save(Product product) {
        productDao.save(product);
    }

    @Override
    public void delete(String[] ids) {
        if(ids.length>0){
            for (String id : ids) {
                productDao.delete(id);
            }
        }
    }


    /**
     * 开启产品
     * @param ids
     */
    @Override
    public void openProduct(String[] ids) {
        //遍历数组更改状态
        for (String id : ids) {
            productDao.openProduct(id);
        }
    }

    /**
     * 屏蔽产品
     * @param ids
     */
    @Override
    public void closeProduct(String[] ids) {
        //遍历数组更改状态
        for (String id : ids) {
            productDao.closeProduct(id);
        }
    }
}
